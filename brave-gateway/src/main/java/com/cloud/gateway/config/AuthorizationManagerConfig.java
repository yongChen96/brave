package com.cloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AuthorizationManagerConfig
 * @Description: 网关自定义鉴权管理器
 * @Author: yongchen
 * @Date: 2021/6/15 11:30
 **/
@Slf4j
@Component
public class AuthorizationManagerConfig {
    //implements ReactiveAuthorizationManager<AuthorizationContext> {

    /*@Resource
    private RedisTemplate redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        // Restful接口权限设计 @link https://www.cnblogs.com/haoxianrui/p/14396990.html
        String restPath = request.getMethodValue() + "_" + request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();
        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }
        // 从redis取【权限->角色(集合)】规则
        Map<String, Object> permRolesRule = redisTemplate.opsForHash().entries(CacheConstants.URL_PERM_ROLES_KEY);
        Set<String> hasPermRoles = CollectionUtil.newHashSet(); // 有权限的角色集合
        boolean needCheck = false; // 是否被设置需要鉴权
        for (Map.Entry<String, Object> permRoles : permRolesRule.entrySet()) {
            String perm = permRoles.getKey(); // URL权限标识
            if (pathMatcher.match(perm, restPath)) {
                List<String> roles = Convert.toList(String.class, permRoles.getValue());
                hasPermRoles.addAll(Convert.toList(String.class, roles));
                needCheck = true;
            }
        }
        // 如果没有设置权限拦截则放行
        if (needCheck == false) {
            return Mono.just(new AuthorizationDecision(true));
        }
        // 判断用户JWT中携带的角色是否有能通过权限拦截的角色
        Mono<AuthorizationDecision> authorizationDecisionMono = mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authority -> {
                    log.info("访问路径：{}", restPath);
                    log.info("用户角色roleId：{}", authority);
                    log.info("资源需要权限authorities：{}", hasPermRoles);
                    String role = authority.substring(AuthConstants.AUTHORIZATION_PREFIX.length()); // ROOT
                    if (AuthConstants.ROOT.equals(role)) { // 如果是超级管理员则放行
                        return true;
                    }
                    return hasPermRoles.contains(role); // 用户角色中只要有一个满足则通过权限校验
                })
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        return authorizationDecisionMono;
    }*/
}
