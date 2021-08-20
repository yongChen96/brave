package com.cloud.brave.auth.config;

import com.cloud.brave.auth.entity.BraveSysUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @ClassName: JwtTokenEnhancer
 * @Description: JWT内容增强器
 * @Author: yongchen
 * @Date: 2021/5/20 17:11
 **/
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        BraveSysUser braveSysUser = (BraveSysUser) authentication.getPrincipal();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", braveSysUser.getId());
        hashMap.put("username", braveSysUser.getUsername());
        hashMap.put("userphone", braveSysUser.getPhone());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(hashMap);
        return accessToken;
    }
}
