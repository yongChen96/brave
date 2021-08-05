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
        if (StrUtil.equalsAny(path, "/auth/oauth/token", "/auth/oauth/captchStream")){
            return Mono.just(new AuthorizationDecision(true));
        }
        if (StrUtil.isBlank(token)) {
            return Mono.just(new AuthorizationDecision(false));
        }

        // 移动端请求无需鉴权，只需认证（即JWT的验签和是否过期判断）
        /*if (pathMatcher.match(AuthConstants.APP_API_PATTERN, path)) {
            // 如果token以"bearer "为前缀，到这一步说明是经过NimbusReactiveJwtDecoder#decode和JwtTimestampValidator#validate等解析和验证通过的，即已认证
            if (StrUtil.isNotBlank(token) && token.startsWith(AuthConstants.AUTHORIZATION_PREFIX)) {
                return Mono.just(new AuthorizationDecision(true));
            } else {
                return Mono.just(new AuthorizationDecision(false));
            }
        }*/

        // Restful接口权限设计
        //String restFulPath = request.getMethodValue() + ":" + path;
        //log.info("请求方法:RESTFUL请求路径：{}", restFulPath);

        // 缓存取【URL权限标识->角色集合】权限规则
        //Map<String, Object> permRolesRules = redisTemplate.opsForHash().entries(AuthConstants.URL_PERM_ROLES_KEY);
        // 根据 “请求路径” 和 权限规则中的“URL权限标识”进行Ant匹配，得出拥有权限的角色集合
        // 【声明定义】有权限的角色集合
        // Set<String> authorities = CollectionUtil.newHashSet();
        // 【声明定义】是否需要被拦截检查的请求，如果缓存中权限规则中没有任何URL权限标识和此次请求的URL匹配，默认不需要被鉴权
        // boolean needToCheck = false;
        /*for (Map.Entry<String, Object> entry : permRolesRules.entrySet()) {
            // 缓存权限规则的键：URL权限标识
            String key = entry.getKey();
            if (pathMatcher.match(key, restFulPath)){
                // 缓存权限规则的值：有请求路径访问权限的角色集合
                List<String> roles = Convert.toList(String.class, entry.getValue());
                hasPermissionRoles.addAll(roles);
                needToCheck = true;
            }
        }*/
        // 没有设置权限规则放行；注：如果默认想拦截所有的请求请移除needToCheck变量逻辑即可，根据需求定制
        /*if (needToCheck == false) {
            return Mono.just(new AuthorizationDecision(true));
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
