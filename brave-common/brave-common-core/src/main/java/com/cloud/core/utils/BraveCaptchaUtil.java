package com.cloud.core.utils;

import com.cloud.core.constant.CacheConstants;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: BraveCaptchaUtil
 * @Description: 验证码工具类
 * @Author: yongchen
 * @Date: 2021/5/24 11:58
 **/
@UtilityClass
public class BraveCaptchaUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 二维码宽度
     **/
    private static final Integer CAPTCHA_WIDTH = 100;
    /**
     * 二维码高度
     **/
    private static final Integer CAPTCHA_HEIGH = 40;
    /**
     * 二维码位数
     **/
    private static final Integer CAPTCHA_LEN = 4;

    /**
     * @Author: yongchen
     * @Description: 生成png类型的验证码
     * @Date: 13:48 2021/5/24
     * @Param: [response]
     * @return: void
     **/
    public void captchaForPng(HttpServletResponse response) throws IOException {
        SpecCaptcha captcha = new SpecCaptcha(CAPTCHA_WIDTH, CAPTCHA_HEIGH);
        //获取验证码字符
        String text = captcha.text();
        // 验证码添加到缓存
        redisTemplate.opsForValue().set(CacheConstants.CAPTCHA_KEY, text, CacheConstants.CODE_TIME, TimeUnit.SECONDS);
        //输出验证码
        captcha.out(response.getOutputStream());
    }

    /**
     * @Author: yongchen
     * @Description: 生成GIF类型验证码
     * @Date: 14:03 2021/5/24
     * @Param: [response]
     * @return: void
     **/
    public void captchaForGif(HttpServletResponse response) throws IOException {
        //GIF类型验证码，宽、高、验证码位数
        GifCaptcha captcha = new GifCaptcha(CAPTCHA_WIDTH, CAPTCHA_WIDTH, CAPTCHA_LEN);
        //设置验证码类型：字符数字混合
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        //获取验证码字符
        String text = captcha.text();
        // 验证码添加到缓存
        redisTemplate.opsForValue().set(CacheConstants.CAPTCHA_KEY, text, CacheConstants.CODE_TIME, TimeUnit.SECONDS);
        //输出验证码
        captcha.out(response.getOutputStream());
    }

    /**
     * @Author: yongchen
     * @Description: 中文类型验证码
     * @Date: 14:08 2021/5/24
     * @Param: [response]
     * @return: void
     **/
    public void captchaForChinese(HttpServletResponse response) throws IOException {
        //中文类型验证码，宽、高
        ChineseCaptcha chineseCaptcha = new ChineseCaptcha(CAPTCHA_WIDTH, CAPTCHA_HEIGH);
        //获取验证码字符
        String text = chineseCaptcha.text();
        // 验证码添加到缓存
        redisTemplate.opsForValue().set(CacheConstants.CAPTCHA_KEY, text, CacheConstants.CODE_TIME, TimeUnit.SECONDS);
        //输出验证码
        chineseCaptcha.out(response.getOutputStream());
    }

    /**
     * @Author: yongchen
     * @Description: 算术类型验证码
     * @Date: 14:10 2021/5/24
     * @Param: [response]
     * @return: void
     **/
    public void captchaForArithmetic(HttpServletResponse response) throws IOException {
        //算术类型验证码：宽、高
        ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(CAPTCHA_WIDTH, CAPTCHA_HEIGH);
        //运算位数
        arithmeticCaptcha.setLen(CAPTCHA_LEN);
        //获取运算公式
        String arithmeticString = arithmeticCaptcha.getArithmeticString();
        //获取运算结果
        String text = arithmeticCaptcha.text();
        // 验证码添加到缓存
        redisTemplate.opsForValue().set(CacheConstants.CAPTCHA_KEY, text, CacheConstants.CODE_TIME, TimeUnit.SECONDS);
        //输出验证码
        arithmeticCaptcha.out(response.getOutputStream());
    }

    /**
     * 设置相应头
     *
     * @param response HttpServletResponse
     */
    public static void setHeader(HttpServletResponse response) {
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }
}
