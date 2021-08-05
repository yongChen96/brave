package com.cloud.brave.core.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;

/**
 * @ClassName: DataSourceConfig
 * @Description: 数据源配置
 * @Author: yongchen
 * @Date: 2020/8/31 15:20
 **/
@Data
public class DataSourceConfig {
    private DbType dbType;
    private String url;
    private String driverName;
    private String username;
    private String password;

    public DataSourceConfig(){

    }

    public DataSourceConfig(DbType dbType, String url, String driverName, String username, String password) {
        this.dbType = dbType;
        this.url = url;
        this.driverName = driverName;
        this.username = username;
        this.password = password;
    }
}
