package com.cloud.brave.auth.rest;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @ClassName: RsaKeyController
 * @Description: 生成公钥控制器
 * @Author: yongchen
 * @Date: 2021/5/26 17:52
 **/
@RestController
@RequestMapping("/rsa")
@Api(tags = "生成RSA公钥")
public class RsaKeyController {

    @Resource
    private KeyPair keyPair;
    
    /**
     * @Author: yongchen
     * @Description: 生成RSA公钥
     * @Date: 17:58 2021/5/26
     * @Param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @GetMapping("/getPublicKey")
    @ApiOperation(value = "获取RSA公钥")
    public Map<String, Object> getPublicKey(){
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey rsaKey = new RSAKey.Builder(rsaPublicKey).build();
        return new JWKSet(rsaKey).toJSONObject();
    }
}
