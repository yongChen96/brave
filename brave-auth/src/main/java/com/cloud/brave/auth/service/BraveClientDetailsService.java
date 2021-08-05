package com.cloud.brave.auth.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @ClassName: BraveClientDetailsService
 * @Description: Client信息
 * @Author: yongchen
 * @Date: 2021/6/8 17:27
 **/
public class BraveClientDetailsService extends JdbcClientDetailsService {

    public BraveClientDetailsService(DataSource dataSource) {
        super(dataSource);
    }

    public ClientDetails loadClientDetails(String clientId){
        return super.loadClientByClientId(clientId);
    }
}
