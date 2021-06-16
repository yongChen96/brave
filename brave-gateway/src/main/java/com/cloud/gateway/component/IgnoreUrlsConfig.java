package com.cloud.gateway.component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.ldap.LdapProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: IgnoreUrlsConfig
 * @Description: 网关白名单配置
 * @Author: yongchen
 * @Date: 2021/5/6 16:01
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix="secure.ignore")
public class IgnoreUrlsConfig {

    private List<String> urls;

}
