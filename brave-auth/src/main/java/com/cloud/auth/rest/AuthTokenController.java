package com.cloud.auth.rest;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.auth.entity.BraveLoginInfo;
import com.cloud.auth.entity.BraveUser;
import com.cloud.auth.entity.Oauth2AccessToken;
import com.cloud.auth.utils.BraveCaptchaUtil;
import com.cloud.auth.utils.JwtUtils;
import com.cloud.core.constant.AuthConstants;
import com.cloud.core.constant.CacheConstants;
import com.cloud.core.constant.CommonConstants;
import com.cloud.core.exception.BraveException;
import com.cloud.core.result.Result;
import com.cloud.log.annotation.BraveLoginLog;
import com.wf.captcha.ArithmeticCaptcha;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @PostMapping("/token")
    @BraveLoginLog(value = "登录")
    @ApiOperation(value = "OAuth2认证", notes = "OAuth2认证")
    public Result<Oauth2AccessToken> postAccessToken(Principal principal, @RequestBody BraveLoginInfo braveLoginInfo) {
        try {
            //验证码校验
            String code = braveLoginInfo.getCode();
            if (StringUtils.isBlank(code)) {
                return Result.failed("验证码不能为空");
            }
            String captchaText = (String) redisTemplate.opsForValue().get(CacheConstants.CAPTCHA_KEY + code.toLowerCase());
            if (StringUtils.isBlank(captchaText)) {
                return Result.failed("验证码错误，请输入正确验证码");
            }
            if (!StringUtils.equals(captchaText, code.toLowerCase())) {
                return Result.failed("验证码错误，请输入正确验证码");
            }
            redisTemplate.delete(CacheConstants.CAPTCHA_KEY);

            Map<String, String> parameters = new HashMap<>();
            parameters.put("grant_type", braveLoginInfo.getGrantType());
            parameters.put("client_id", braveLoginInfo.getClientId());
            parameters.put("client_secret", braveLoginInfo.getClientSecret());
            parameters.put("username", braveLoginInfo.getUsername());
            parameters.put("password", braveLoginInfo.getPassword());
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
     * @Description: 退出登录
     * @Date: 16:52 2021/6/18
     * @Param: []
     * @return: com.cloud.core.result.Result
     **/
    @PostMapping("/logout")
    @ApiOperation(value = "退出登录", notes = "退出登录")
    public Result logout() {
        JSONObject payload = JwtUtils.getJwtPayload();
        String jti = payload.getStr(AuthConstants.JWT_JTI);
        Long expireTime = payload.getLong(AuthConstants.JWT_EXP);
        if (expireTime != null) {
            long currentTime = System.currentTimeMillis() / 1000;// 当前时间（单位：秒）
            if (expireTime > currentTime) { // token未过期，添加至缓存作为黑名单限制访问，缓存时间为token过期剩余时间
                redisTemplate.opsForValue().set(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti, null, (expireTime - currentTime), TimeUnit.SECONDS);
            }
        } else { // token 永不过期则永久加入黑名单
            redisTemplate.opsForValue().set(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti, null);
        }
        return Result.success("退出成功");
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
    public Result<String> getCaptch(@RequestParam(value = "captchType") String captchType,
                                    HttpServletResponse response) throws IOException {
        String captch = null;
        switch (captchType) {
            case CommonConstants.CAPTCH_PNG:
                captch = braveCaptchaUtil.captchaForPng(response);
                break;
            case CommonConstants.CAPTCH_GIF:
                captch = braveCaptchaUtil.captchaForGif(response);
                break;
            case CommonConstants.CAPTCH_CHINESE:
                captch = braveCaptchaUtil.captchaForChinese(response);
                break;
            case CommonConstants.CAPTCH_ARITHMETIC:
                captch = braveCaptchaUtil.captchaForArithmetic(response);
                break;
            default:
                captch = braveCaptchaUtil.captchaForPng(response);
        }
        return Result.success(captch);
    }

    /**
     * @param response
     * @return void
     * @Author yongchen
     * @Description 获取验证码-流
     * @Date 9:10 2021/7/15
     **/
    @GetMapping("/captchStream")
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    public void getCaptchStream(HttpServletResponse response) throws IOException {
        //算术类型验证码：宽、高
        ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(100, 40);
        //运算位数
        arithmeticCaptcha.setLen(2);
        //获取运算公式
        String arithmeticString = arithmeticCaptcha.getArithmeticString();
        //获取运算结果
        String text = arithmeticCaptcha.text();
        // 验证码添加到缓存
        redisTemplate.opsForValue().set(CacheConstants.CAPTCHA_KEY + text, text, CacheConstants.CODE_TIME, TimeUnit.SECONDS);
        //输出验证码
        response.setContentType("image/png");
        arithmeticCaptcha.out(response.getOutputStream());
    }
}
