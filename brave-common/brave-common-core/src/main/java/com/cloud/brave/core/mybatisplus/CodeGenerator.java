package com.cloud.brave.core.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.cloud.brave.core.base.controller.BaseController;
import com.cloud.brave.core.mybatisplus.code.CodeGeneratorConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CodeGenerator
 * @Description: 代码生成
 * @Author: yongchen
 * @Date: 2020/8/31 14:10
 **/
public class CodeGenerator {

    /**
     * @Author: yongchen
     * @Description: 代码生成方法
     * @Date: 14:58 2020/8/31
     * @Param: [codeGeneratorConfig]
     * @return: void
     **/
    public static void exec(CodeGeneratorConfig codeGeneratorConfig){
        //代码生成器
        AutoGenerator mpg = new AutoGenerator();
        GlobalConfig globalConfig = globalConfig(codeGeneratorConfig);
        mpg.setGlobalConfig(globalConfig);
        DataSourceConfig dataSourceConfig = dataSourceConfig(codeGeneratorConfig);
        mpg.setDataSource(dataSourceConfig);
        PackageConfig packageConfig = packageConfig(codeGeneratorConfig);
        mpg.setPackageInfo(packageConfig);
        TemplateConfig templateConfig = templateConfig();
        mpg.setTemplate(templateConfig);
        InjectionConfig injectionConfig = injectionConfig(codeGeneratorConfig);
        mpg.setCfg(injectionConfig);
        StrategyConfig strategyConfig = strategyConfig(codeGeneratorConfig);
        mpg.setStrategy(strategyConfig);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * @Author: yongchen
     * @Description: 全局策略配置
     * @Date: 14:22 2020/8/31
     * @Param: [codeGeneratorConfig]
     * @return: com.baomidou.mybatisplus.generator.config.GlobalConfig
     **/
    private static GlobalConfig globalConfig(CodeGeneratorConfig codeGeneratorConfig){
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(codeGeneratorConfig.getOutputDir());
        gc.setAuthor(codeGeneratorConfig.getAuthor());
        gc.setOpen(false);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        // XML生成ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        // 文件覆盖
        gc.setFileOverride(true);
        // 开启 activeRecord 模式
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        return gc;
    }

    /**
     * @Author: yongchen
     * @Description: 数据源配置
     * @Date: 14:25 2020/8/31
     * @Param: [codeGeneratorConfig]
     * @return: com.baomidou.mybatisplus.generator.config.DataSourceConfig
     **/
    private static DataSourceConfig dataSourceConfig(CodeGeneratorConfig codeGeneratorConfig){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(codeGeneratorConfig.getDataSourceConfig().getDbType());
        dsc.setUrl(codeGeneratorConfig.getDataSourceConfig().getUrl());
        dsc.setDriverName(codeGeneratorConfig.getDataSourceConfig().getDriverName());
        dsc.setUsername(codeGeneratorConfig.getDataSourceConfig().getUsername());
        dsc.setPassword(codeGeneratorConfig.getDataSourceConfig().getPassword());
        return dsc;
    }

    /**
     * @Author: yongchen
     * @Description: 包配置
     * @Date: 14:27 2020/8/31
     * @Param: [codeGeneratorConfig]
     * @return: com.baomidou.mybatisplus.generator.config.PackageConfig
     **/
    private static PackageConfig packageConfig(CodeGeneratorConfig codeGeneratorConfig){
        PackageConfig pc = new PackageConfig();
        // 包路径
        pc.setParent(codeGeneratorConfig.getParentPath());
        // 模块名
        // pc.setModuleName("t");
        //文件包名
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setService("service");
        return pc;
    }

    /**
     * @Author: yongchen
     * @Description: 配置模板
     * @Date: 14:32 2020/8/31
     * @Param: []
     * @return: com.baomidou.mybatisplus.generator.config.TemplateConfig
     **/
    private static TemplateConfig templateConfig(){
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setMapper(null);
        templateConfig.setXml(null);
        templateConfig.setEntity(null);
        return templateConfig;
    }

    /**
     * @Author: yongchen
     * @Description: 自定义配置
     * @Date: 14:33 2020/8/31
     * @Param: [codeGeneratorConfig]
     * @return: com.baomidou.mybatisplus.generator.InjectionConfig
     **/
    private static InjectionConfig injectionConfig(CodeGeneratorConfig codeGeneratorConfig){
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        List<FileOutConfig> fileConfig = getFileConfig(codeGeneratorConfig);
        cfg.setFileOutConfigList(fileConfig);
        return cfg;
    }

    /**
     * @Author: yongchen
     * @Description: 模板引擎配置
     * @Date: 14:34 2020/8/31
     * @Param: [codeGeneratorConfig]
     * @return: java.util.List<com.baomidou.mybatisplus.generator.config.FileOutConfig>
     **/
    private static List<FileOutConfig> getFileConfig(CodeGeneratorConfig codeGeneratorConfig){
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 如果模板引擎是 velocity(默认)
        // String templatePath = "/templates/mapper.xml.vm";
        // 如果模板引擎是 freemarker
        String templatePathController = "/templates/controller.java.ftl";
        String templatePathEntity = "/templates/entity.java.ftl";
        String templatePathService = "/templates/service.java.ftl";
        String templatePathServiceImpl = "templates/serviceImpl.java.ftl";
        String templatePathMapper = "/templates/mapper.java.ftl";
        String templatePathXml = "/templates/mapper.xml.ftl";

        // 自定义配置会被优先输出
        if (codeGeneratorConfig.getFileCreateConfig().getGenerateController()){
            focList.add(new FileOutConfig(templatePathController) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return System.getProperty("user.dir") + "/brave-mbs/brave-mbs-rest/src/main/java/com/cloud/brave/controller/"
                            + tableInfo.getControllerName() + StringPool.DOT_JAVA;
                }
            });
        }
        if (codeGeneratorConfig.getFileCreateConfig().getGenerateEntity()){
            focList.add(new FileOutConfig(templatePathEntity) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return System.getProperty("user.dir") + "/brave-mbs/brave-mbs-entity/src/main/java/com/cloud/brave/entity/"
                            + tableInfo.getEntityName() + StringPool.DOT_JAVA;
                }
            });
        }
        if (codeGeneratorConfig.getFileCreateConfig().getGenerateService()){
            focList.add(new FileOutConfig(templatePathService) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return System.getProperty("user.dir") + "/brave-mbs/brave-mbs-biz/src/main/java/com/cloud/brave/service/"
                            + tableInfo.getServiceName() + StringPool.DOT_JAVA;
                }
            });
        }
        if(codeGeneratorConfig.getFileCreateConfig().getGenerateServiceImpl()){
            focList.add(new FileOutConfig(templatePathServiceImpl) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return System.getProperty("user.dir") + "/brave-mbs/brave-mbs-biz/src/main/java/com/cloud/brave/service/impl/"
                            + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
                }
            });
        }
        if (codeGeneratorConfig.getFileCreateConfig().getGenerateMapper()){
            focList.add(new FileOutConfig(templatePathMapper) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return System.getProperty("user.dir") + "/brave-mbs/brave-mbs-biz/src/main/java/com/cloud/brave/mapper/"
                            + tableInfo.getMapperName() + StringPool.DOT_JAVA;
                }
            });
        }
        if (codeGeneratorConfig.getFileCreateConfig().getGenerateXml()){
            focList.add(new FileOutConfig(templatePathXml) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return System.getProperty("user.dir") + "/brave-mbs/brave-mbs-biz/src/main/resources/mapper/base/"
                            + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
        }
        return focList;
    }

    /**
     * @Author: yongchen
     * @Description: 策略配置
     * @Date: 14:41 2020/8/31
     * @Param: [codeGeneratorConfig]
     * @return: com.baomidou.mybatisplus.generator.config.StrategyConfig
     **/
    private static StrategyConfig strategyConfig(CodeGeneratorConfig codeGeneratorConfig){
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setEntityBuilderModel(true);
        strategy.setRestControllerStyle(true);
        // 你自己的父类实体,没有就不用设置!
        strategy.setSuperEntityClass(codeGeneratorConfig.getEntityType().getVal());
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns(codeGeneratorConfig.getColumns());
        // 公共父类 你自己的父类控制器,没有就不用设置!
        strategy.setSuperControllerClass(BaseController.class);
        // 开启TableFiled
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 需要生成的表
        strategy.setInclude(codeGeneratorConfig.getTableName());
        strategy.setControllerMappingHyphenStyle(true);
        // 此处可以修改为您的表前缀
        strategy.setTablePrefix(codeGeneratorConfig.getTablePrefix());
        //RequestMapping 命名方式
        strategy.setControllerMappingHyphenStyle(false);
        return strategy;
    }
}
