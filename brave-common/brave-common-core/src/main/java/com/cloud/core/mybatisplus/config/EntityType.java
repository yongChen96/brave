package com.cloud.core.mybatisplus.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author admin
 * @version 1.0
 * @description: 继承实体类型
 * @date 2021/7/19 14:42
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EntityType {

    BASE_ENTITY("com.cloud.core.mybatisplus.entity.BaseEntity", new String[]{"id", "create_time", "create_user", "update_time", "update_user"}),
    BASE_SUPER_ENTITY("com.cloud.core.mybatisplus.entity.BaseSuperEntuty", new String[]{"id", "create_time", "create_user"});

    private String val;
    private String[] columns;
}
