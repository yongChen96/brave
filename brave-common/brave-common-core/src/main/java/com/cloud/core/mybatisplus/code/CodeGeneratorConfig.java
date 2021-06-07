package com.cloud.core.mybatisplus.code;

import com.cloud.core.mybatisplus.config.DataSourceConfig;
import com.cloud.core.mybatisplus.config.FileCreateConfig;
import lombok.Data;

/**
 * @ClassName: CodeGeneratorConfig
 * @Description: 代码生成配置类
 * @Author: yongchen
 * @Date: 2020/8/31 14:45
 **/
@Data
public class CodeGeneratorConfig {

    private static final String[] SUPER_COLUMNS = new String[]{"id", "create_time", "create_user", "update_time", "update_user"};

    private String[] tableName;
    private String tablePrefix;
    private String outputDir;
    private String author;
    private String packagePath;
    private String modelPath;
    private String parentPath;
    private DataSourceConfig dataSourceConfig;
    private FileCreateConfig fileCreateConfig;
    private String[] columns;

    public CodeGeneratorConfig(){
        this.columns = SUPER_COLUMNS;
    }

    public CodeGeneratorConfig(String[] tableName, String tablePrefix, String outputDir, String author, String packagePath,
                               String modelPath, String parentPath, DataSourceConfig dataSourceConfig, FileCreateConfig fileCreateConfig) {
        this.tableName = tableName;
        this.tablePrefix = tablePrefix;
        this.outputDir = outputDir;
        this.author = author;
        this.packagePath = packagePath;
        this.modelPath = modelPath;
        this.parentPath = parentPath;
        this.dataSourceConfig = dataSourceConfig;
        this.fileCreateConfig = fileCreateConfig;
        this.columns = SUPER_COLUMNS;
    }

    public CodeGeneratorConfig(String[] tableName, String tablePrefix, String outputDir, String author, String packagePath,
                               String modelPath, String parentPath, DataSourceConfig dataSourceConfig, FileCreateConfig fileCreateConfig, String[] columns) {
        this.tableName = tableName;
        this.tablePrefix = tablePrefix;
        this.outputDir = outputDir;
        this.author = author;
        this.packagePath = packagePath;
        this.modelPath = modelPath;
        this.parentPath = parentPath;
        this.dataSourceConfig = dataSourceConfig;
        this.fileCreateConfig = fileCreateConfig;
        this.columns = columns;
    }
}
