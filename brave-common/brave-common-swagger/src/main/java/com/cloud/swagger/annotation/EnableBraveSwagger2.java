package com.cloud.swagger.annotation;

import com.cloud.swagger.config.SwaggerAutoConfiguration;
import com.cloud.swagger.support.Swagger2Peoperties;
import com.cloud.swagger.support.SwaggerPeoperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

/**
 * @ClassName: EnableBraveSwagger2
 * @Description: 开启Swagger2
 * @Author: yongchen
 * @Date: 2021/5/18 17:37
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@EnableSwagger2
@EnableConfigurationProperties(SwaggerPeoperties.class)
@Import({SwaggerAutoConfiguration.class})
public @interface EnableBraveSwagger2 {
}
