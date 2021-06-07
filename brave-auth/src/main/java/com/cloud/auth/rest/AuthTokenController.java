package com.cloud.auth.rest;

import com.cloud.auth.entity.Oauth2AccessToken;
import com.cloud.core.constant.CommonConstants;
import com.cloud.core.exception.BraveException;
import com.cloud.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * @ClassName: AuthTokenController
 * @Description: AuthTokenController
 * @Author: yongchen
 * @Date: 2021/5/28 15:29
 **/
@RestController
@RequestMapping("/oauth")
@Api(tags = "登录认证中心")
public class AuthTokenController {

    @Resource
    private TokenEndpoint tokenEndpoint;

    /**
     * @Author: yongchen
     * @Description: 重写token返回信息
     * @Date: 15:45 2021/5/28
     * @Param: [principal, parameters]
     * @return: com.cloud.core.result.Result<com.cloud.auth.entity.Oauth2AccessToken>
     **/
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", defaultValue = "password", value = "授权模式", required = true),
            @ApiImplicitParam(name = "client_id", defaultValue = "brave", value = "Oauth2客户端ID", required = true),
            @ApiImplicitParam(name = "client_secret", defaultValue = "123456", value = "Oauth2客户端秘钥", required = true),
            @ApiImplicitParam(name = "refresh_token", value = "刷新token"),
            @ApiImplicitParam(name = "username", defaultValue = "admin", value = "登录用户名"),
            @ApiImplicitParam(name = "password", defaultValue = "123456", value = "登录密码"),
    })
    @PostMapping("/token")
    @ApiOperation(value = "OAuth2认证", notes = "login")
    public Result<Oauth2AccessToken> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters){
        try {
            OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
            Oauth2AccessToken accessToken = Oauth2AccessToken.builder()
                    .token(oAuth2AccessToken.getValue())
                    .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                    .expiresIn(oAuth2AccessToken.getExpiresIn())
                    .tokenHead(CommonConstants.token_head).build();
            return Result.success(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BraveException(e.getMessage());
        }
    }
}
