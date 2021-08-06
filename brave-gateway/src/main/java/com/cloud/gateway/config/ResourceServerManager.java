package com.cloud.gateway.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.cloud.brave.core.constant.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName: ResourceServerManager
 * @Description: 网关自定义资源鉴权管理起
 * @Author: yongchen
 * @Date: 2021/6/15 11:30
 **/
@Slf4j
@Component
public class ResourceServerManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String path = request.getURI().getPath();
        String token = request.getHeaders().getFirst(AuthConstants.AUTHORIZATION_KEY);

        //1、对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return mono.just(new AuthorizationDecision(true));
        }

        // 2. token为空拒绝访问
        //获取验证码和登录允许token为为空
        /*if (StrUtil.equalsAny(path, "/auth/oauth/token", "/auth/oauth/captch")){
            return Mono.just(new AuthorizationDecision(true));
        }
        if (StrUtil.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }*/

        //3、缓存取资源权限角色关系列表
        Map<Object, Object> resourceRolesMap = redisTemplate.opsForHash().entries(AuthConstants.URL_PERM_ROLES_KEY);
        Iterator<Object> iterator = resourceRolesMap.keySet().iterator();
        List<String> authorities = new ArrayList<>();
        while (iterator.hasNext()) {
            String pattern = (String) iterator.next();
            if (pathMatcher.match(pattern, path)) {
                authorities.addAll(Convert.toList(String.class, resourceRolesMap.get(pattern)));
            }
        }

        // 4、判断用户JWT中携带的角色是否有能通过权限拦截的角色
        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(auth -> {
                    log.info("访问路径：{}", path);
                    log.info("用户角色roleId：{}", auth);
                    log.info("资源需要权限authorities：{}", authorities);
                    String role = auth.substring(AuthConstants.AUTHORITY_PREFIX.length());
                    if (StringUtils.equals(AuthConstants.ROOT, role)) {
                        return true;
                    }
                    return authorities.contains(role);
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        return authorizationDecisionMono;
    }
}
