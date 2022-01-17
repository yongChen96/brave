package com.cloud.brave.core.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.cloud.brave.core.constant.AuthConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.misc.BASE64Decoder;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: JWT工具类
 * @date 2021/7/27 11:28
 */
@Slf4j
public class JwtUtils {

    private static final String USER_ID = "id";
    private static final String USER_NAME = "username";
    private static final String USER_PHONE = "userphone";


    @SneakyThrows
    public static JSONObject getJwtPayload() {
        String payload = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader(AuthConstants.JWT_PAYLOAD_KEY);
        if (StringUtils.isNotBlank(payload)) {
            JSONObject jsonObject = JSONUtil.parseObj(URLDecoder.decode(payload, "UTF-8"));
            return jsonObject;
        }
        return null;
    }

    /**
     * 解析JWT获取用户ID
     *
     * @return
     */
    public static Long getUserId() {
        JSONObject object = getJwtPayload();
        if (null == object) {
            return null;
        }
        return object.getLong(USER_ID);
    }

    /**
     * 解析JWT获取获取用户名
     *
     * @return
     */
    public static String getUsername() {
        JSONObject object = getJwtPayload();
        if (null == object) {
            return null;
        }
        return object.getStr(USER_NAME);
    }

    /**
     * 解析JWT获取获取登录账号
     *
     * @return
     */
    public static String getUserPhone() {
        JSONObject object = getJwtPayload();
        if (null == object) {
            return null;
        }
        return object.getStr(USER_PHONE);
    }

    /**
     * 解析JWT获取获取用户信息
     *
     * @return
     */
    public static Object getObject() {
        JSONObject jwtPayload = getJwtPayload();
        if (null == jwtPayload) {
            return null;
        }
        return jwtPayload.get("user");
    }

    /**
     * 获取登录认证的客户端ID
     * <p>
     * 兼容两种方式获取Oauth2客户端信息（client_id、client_secret）
     * 方式一：client_id、client_secret放在请求路径中
     * 方式二：放在请求头（Request Headers）中的Authorization字段，且经过加密，例如 Basic Y2xpZW50OnNlY3JldA== 明文等于 client:secret
     *
     * @return
     */
    @SneakyThrows
    public static String getOAuthClientId() {
        String clientId;

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // 从请求路径中获取
        clientId = request.getParameter(AuthConstants.CLIENT_ID_KEY);
        if (StrUtil.isNotBlank(clientId)) {
            return clientId;
        }

        // 从请求头获取
        String basic = request.getHeader(AuthConstants.AUTHORIZATION_KEY);
        if (StrUtil.isNotBlank(basic) && basic.startsWith(AuthConstants.BASIC_PREFIX)) {
            basic = basic.replace(AuthConstants.BASIC_PREFIX, Strings.EMPTY);
            String basicPlainText = new String(new BASE64Decoder().decodeBuffer(basic), "UTF-8");
            clientId = basicPlainText.split(":")[0]; //client:secret
        }
        return clientId;
    }

    /**
     * JWT获取用户角色列表
     *
     * @return 角色列表
     */
    public static List<String> getRoles() {
        List<String> roles = null;
        JSONObject payload = getJwtPayload();
        if (payload != null && payload.containsKey(AuthConstants.JWT_AUTHORITIES_KEY)) {
            roles = payload.get(AuthConstants.JWT_AUTHORITIES_KEY, List.class);
        }
        return roles;
    }
}
