package com.cloud.brave.core.excel;

import cn.afterturn.easypoi.handler.inter.IExcelDictHandler;

/**
 * @author yongchen
 * @description: excel字典转换
 * @date 2021/9/1 17:48
 */
public class BraveExcelDictHandler implements IExcelDictHandler {

    /**
     * @param dict  字典Key
     * @param obj   对象
     * @param name  属性名称
     * @param value 属性值
     * @description: 转名称
     * @return: java.lang.String
     * @author yongchen
     * @date: 2021/9/1 17:54
     */
    @Override
    public String toName(String dict, Object obj, String name, Object value) {
        return null;
    }

    /**
     * @param dict  字典Key
     * @param obj   对象
     * @param name  属性名称
     * @param value 属性值
     * @description:
     * @return: java.lang.String
     * @author yongchen
     * @date: 2021/9/1 17:56
     */
    @Override
    public String toValue(String dict, Object obj, String name, Object value) {
        return null;
    }
}
