package com.cloud.brave.code;

import com.baomidou.mybatisplus.annotation.DbType;
import com.cloud.brave.mybatisplus.generator.CodeGenerator;
import com.cloud.brave.mybatisplus.generator.code.CodeGeneratorConfig;
import com.cloud.brave.mybatisplus.generator.config.DataSourceConfig;
import com.cloud.brave.mybatisplus.generator.config.EntityType;
import com.cloud.brave.mybatisplus.generator.config.FileCreateConfig;

/**
 * @ClassName: MbsCodeGenerator
 * @Description: 代码生成器
 * @Author: yongchen
 * @Date: 2021/6/3 15:20
 **/
public class MbsCodeGenerator {

    public static void main(String[] args) {
        CodeGeneratorConfig generatorConfig = new CodeGeneratorConfig();
        String[] tableName = {"sys_log"};
        generatorConfig.setTableName(tableName);
        generatorConfig.setTablePrefix("");
        generatorConfig.setAuthor("yongchen");
        generatorConfig.setParentPath("com.cloud.brave");
        generatorConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");

//        FileCreateConfig createConfig = new FileCreateConfig(true);
        FileCreateConfig createConfig = new FileCreateConfig();
        createConfig.setGenerateController(false);
        createConfig.setGenerateEntity(true);
        createConfig.setGenerateService(false);
        createConfig.setGenerateServiceImpl(false);
        createConfig.setGenerateMapper(false);
        createConfig.setGenerateXml(true);
        generatorConfig.setFileCreateConfig(createConfig);

        // 实体父类
        generatorConfig.setEntityType(EntityType.BASE_ENTITY);

        generatorConfig.setDataSourceConfig(new DataSourceConfig(DbType.MYSQL,
                "jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true",
                "com.mysql.cj.jdbc.Driver",
                "root",
                "root"));
        CodeGenerator.exec(generatorConfig);
    }
}
