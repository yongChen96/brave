package com.cloud.auth.rest;

import com.cloud.auth.entity.Oauth2AccessToken;
import com.cloud.auth.utils.BraveCaptchaUtil;
import com.cloud.core.constant.CacheConstants;
import com.cloud.core.constant.CommonConstants;
import com.cloud.core.exception.BraveException;
import com.cloud.core.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

/**
 * @ClassName: AuthTokenController
 * @Description: AuthTokenController
 * @Author: yongchen
 * @Date: 2021/5/28 15:29
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@Api(value = "AuthTokenController", tags = "登录认证中心")
public class AuthTokenController {

    private final TokenEndpoint tokenEndpoint;
    private final RedisTemplate redisTemplate;
    private final BraveCaptchaUtil braveCaptchaUtil;

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
            @ApiImplicitParam(name = "code", value = "验证码")
    })
    @PostMapping("/token")
    @ApiOperation(value = "OAuth2认证", notes = "login")
    public Result<Oauth2AccessToken> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters, @RequestParam("code") String code) {
        try {
            //验证码校验
            String captchaText = (String) redisTemplate.opsForValue().get(CacheConstants.CAPTCHA_KEY + code.toLowerCase());
            if (StringUtils.isBlank(captchaText)) {
                return Result.failed("验证码错误，请输入正确验证码");
            }
            if (!StringUtils.equals(captchaText, code.toLowerCase())) {
                return Result.failed("验证码错误，请输入正确验证码");
            }
            redisTemplate.delete(CacheConstants.CAPTCHA_KEY);
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

    /**
     * @Author: yongchen
     * @Description: 获取验证码
     * @Date: 11:09 2021/6/16
     * @Param: [captchType, request, response]
     * @return: void
     **/
    @GetMapping("/captch")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "captchType", defaultValue = "1", value = "验证码类型：1:png 2:gif 3:中文 4:算术", required = true))
    public Result getCaptch(@RequestParam(value = "captchType") String captchType) {
        String captha = null;
        switch (captchType) {
            case CommonConstants.CAPTCH_PNG:
                captha = braveCaptchaUtil.captchaForPng();
                break;
            case CommonConstants.CAPTCH_GIF:
                captha = braveCaptchaUtil.captchaForGif();
                break;
            case CommonConstants.CAPTCH_CHINESE:
                captha = braveCaptchaUtil.captchaForChinese();
                break;
            case CommonConstants.CAPTCH_ARITHMETIC:
                captha = braveCaptchaUtil.captchaForArithmetic();
                break;
            default:
                captha = braveCaptchaUtil.captchaForPng();
        }
        return Result.success(captha);
    }
}
