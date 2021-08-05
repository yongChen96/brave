package com.cloud.brave.core.constant;

/**
 * @ClassName: SecurityConstants
 * @Description: 认证公共信息
 * @Author: yongchen
 * @Date: 2021/6/9 10:19
 **/
public interface SecurityClientConstants {

    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
	 */
    String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    String BASE_FIND_STATEMENT = "SELECT " + CLIENT_FIELDS + " FROM sys_oauth_client_details";

    /**
     * 默认的查询语句
     */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " ORDER BY client_id";

    /**
     * 按条件client_id 查询
     */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " WHERE client_id = ?";
}