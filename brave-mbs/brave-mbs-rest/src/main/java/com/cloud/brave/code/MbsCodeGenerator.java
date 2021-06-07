package com.cloud.brave.code;

import com.baomidou.mybatisplus.annotation.DbType;
import com.cloud.core.mybatisplus.CodeGenerator;
import com.cloud.core.mybatisplus.code.CodeGeneratorConfig;
import com.cloud.core.mybatisplus.config.DataSourceConfig;
import com.cloud.core.mybatisplus.config.FileCreateConfig;

/**
 * @ClassName: MbsCodeGenerator
 * @Description: //TODO
 * @Author: yongchen
 * @Date: 2021/6/3 15:20
 **/
public class MbsCodeGenerator {

    public static void main(String[] args) {
        CodeGeneratorConfig generatorConfig = new CodeGeneratorConfig();
        String[] tableName = {"sys_user_role"};
        generatorConfig.setTableName(tableName);
        generatorConfig.setTablePrefix("");
        generatorConfig.setAuthor("yongchen");
        generatorConfig.setParentPath("com.cloud.brave");
        generatorConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");

        generatorConfig.setDataSourceConfig(new DataSourceConfig(DbType.MYSQL,
                "jdbc:mysql://127.0.0.1:13306/brave?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true",
                "com.mysql.cj.jdbc.Driver",
                "root",
                "root"));
        generatorConfig.setFileCreateConfig(new FileCreateConfig());
        CodeGenerator.exec(generatorConfig);
    }
}
