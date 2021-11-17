package com.cloud.brave.datasource.doc;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * @author yongchen
 * @description: 数据库设计文档
 * @date 2021/11/17 11:09
 */
public class DatabasesDoc {

    private static final String FILE_OUTPUT_DIR = "";
    private static final String DOC_VERSION = "V1.0.0";
    private static final String DOC_DESCRIPTION = "数据库表设计文档说明书";
    
    /**
     * @description: 生产数据库设计文档说明书
     * @param dataSource 
     * @return: void 
     * @author yongchen
     * @date: 2021/11/17 11:16
     */
    public void generateDatabaseDoc(DataSource dataSource){
        //创建Screw配置
        Configuration config = Configuration.builder()
                //版本
                .version(DOC_VERSION)
                //描述
                .description(DOC_DESCRIPTION)
                //数据源
                .dataSource(dataSource)
                //引擎设置
                .engineConfig(buildEngineConfig())
                //处理配置
                .produceConfig(buildProcessConfig())
                .build();
        //生成数据库设计文档说明书
        new DocumentationExecute(config).execute();
    }
    
    /**
     * @description: 创建 screw 的引擎配置
     * @param  
     * @return: cn.smallbun.screw.core.engine.EngineConfig 
     * @author yongchen
     * @date: 2021/11/17 11:17
     */
    private EngineConfig buildEngineConfig() {
        return EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(FILE_OUTPUT_DIR)
                //是否打开目录
                .openOutputDir(false)
                //文件类型
                .fileType(EngineFileType.WORD)
                //生成模板类型
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称
                .fileName(DOC_DESCRIPTION)
                .build();
    }
    
    /**
     * @description: 创建 screw 的处理配置，一般可忽略
     *                  指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
     * @param
     * @return: cn.smallbun.screw.core.process.ProcessConfig 
     * @author yongchen
     * @date: 2021/11/17 11:17
     */
    private ProcessConfig buildProcessConfig() {
        return ProcessConfig.builder()
                //根据表名指定表生成
                .designatedTableName(Collections.<String>emptyList())
                //根据前缀指定表生成
                .designatedTablePrefix(Collections.<String>emptyList())
                //根据后缀指定表生成
                .designatedTableSuffix(Collections.<String>emptyList())
                //根据表名忽略忽略表
                .ignoreTableName(Collections.<String>emptyList())
                //根据前缀忽略表
                .ignoreTablePrefix(Collections.<String>emptyList())
                //根据后缀忽略表
                .ignoreTableSuffix(Collections.<String>emptyList())
                .build();
    }
}
