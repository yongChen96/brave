package com.cloud.gateway.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: ResourceServerConfig
 * @Description: 资源服务器配置
 * @Author: yongchen
 * @Date: 2021/6/15 16:31
 **/
@Configuration
@AllArgsConstructor
public class ResourceServerConfig {

    /*private final AuthorizationManagerConfig authorizationManager;
    private final IgnoreUrlsConfig ignoreUrls;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());
        http.authorizeExchange()
                .pathMatchers(ArrayUtil.toArray(ignoreUrls.getUrls(),String.class)).permitAll()//白名单配置
                .anyExchange().access(authorizationManager)//鉴权管理器配置
                .and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())//处理未授权
                .authenticationEntryPoint(authenticationEntryPoint())//处理未认证
                .and()
                .csrf()
                .disable();
        return http.build();
    }

    *//**
     * 未授权
     *
     * @return
     *//*
    @Bean
    ServerAccessDeniedHandler accessDeniedHandler() {
        return (exchange, denied) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> ResponseUtils.writeErrorInfo(response));
            return mono;
        };
    }

    *//**
     * token无效或者已过期自定义响应
     *//*
    @Bean
    ServerAuthenticationEntryPoint authenticationEntryPoint() {
        return (exchange, e) -> {
            Mono<Void> mono = Mono.defer(() -> Mono.just(exchange.getResponse()))
                    .flatMap(response -> ResponseUtils.writeErrorInfo(response));
            return mono;
        };
    }

    *//**
     * @return
     * @link https://blog.csdn.net/qq_24230139/article/details/105091273
     * ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication
     * 需要把jwt的Claim中的authorities加入
     * 方案：重新定义权限管理器，默认转换器JwtGrantedAuthoritiesConverter
     *//*
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstants.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstants.JWT_AUTHORITIES_KEY);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }*/
}
