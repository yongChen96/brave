package com.cloud.brave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author admin
 * @version 1.0
 * @description: 路由显示信息
 * @date 2021/7/9 11:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDTO implements Serializable {
    private static final long serialVersionUID = 8270260263634152126L;
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    private boolean noCache;

    /**
     * 内链地址（http(s)://开头）
     */
    private String link;

    public MetaDTO(String title, String icon, boolean noCache) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }

    public MetaDTO(String menuName, String icon, String path) {
        this.title = menuName;
        this.icon = icon;
        this.link = path;
    }
}
