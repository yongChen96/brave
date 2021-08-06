package com.cloud.brave.core.base.config;

import com.cloud.brave.core.SnowflakeId.SnowflakeIDGenerate;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @ClassName: BaseConfig
 * @Description: 基础配置信息
 * @Author: yongchen
 * @Date: 2021/5/25 11:16
 **/
public abstract class BaseConfig {

    /**
     * 默认日期时间格式
     */
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * @Author yongchen
     * @Description id生成器初始化
     * @Date 17:20 2021/7/6
     * @param machineCode
     * @return com.cloud.core.SnowflakeId.SnowflakeIDGenerate
     **/
    @Bean
    public SnowflakeIDGenerate setMachineCode(@Value("${brave.machine-code:1}") Long machineCode){
        return new SnowflakeIDGenerate(machineCode);
    }

    /**
     * 接口返回的 json 类型参数 序列化问题
     * Long -> string
     * date -> string
     *
     * @param builder
     * @return
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai"))
                .build();
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);//排除空字段  : 不能排除，否则前端不能显示null字段，不友好
        //忽略未知字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略不能转移的字符
        objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
        //日期格式
        SimpleDateFormat outputFormat = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
        objectMapper.setDateFormat(outputFormat);
        SimpleModule simpleModule = new SimpleModule();
        /**
         *  将Long,BigInteger序列化的时候,转化为String
         */
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
        //在进出前后台的时候，设置BigDecimal和字符串之间转换
        simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
