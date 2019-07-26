package com.ck.orangeblogcommon.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis plus的代码生成工具
 */
public class CodeGeneratorUtil {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();// 代码生成器主类

        /*
            全局配置
         */
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");// 获取用户程序当前路径（项目根的路径）
        gc.setOutputDir(projectPath);// 生成文件的输出目录(默认值：D 盘根目录)
        gc.setAuthor("ck");// 开发人员(默认值：null)
        gc.setOpen(false);// 是否打开输出目录(默认值：null)
        gc.setFileOverride(false);// 是否覆盖已有文件(默认值：false)
        mpg.setGlobalConfig(gc);

        /*
            数据源配置
         */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.103.17.3:3306/lm_dev?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setSchemaName("public");// 数据库 schema name(例如 PostgreSQL 可指定为 public)
        dsc.setDbType(DbType.MYSQL);// 数据库类型
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");// 驱动名称
        dsc.setUsername("chenkang"); // 用户名
        dsc.setPassword("258963"); // 密码
        /*
           类型转换 默认由 dbType 类型决定选择对应数据库内置实现。
           实现 ITypeConvert 接口自定义数据库 字段类型 转换为自己需要的 java 类型，
           也可以直接重写自带的MySqlTypeConvert类的processTypeConvert方法，是一个道理
         */
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        /*
            包配置
         */
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("foundation");// 父包模块名
        pc.setParent("com.ck");// 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setEntity("orangeblogdao.po");// service包名
        pc.setService("orangeblogservice.service");// service包名
        pc.setServiceImpl("orangeblogservice.service.impl");// ServiceImpl包名
        pc.setMapper("orangeblogdao.mapper");// Mapper包名
        pc.setController("orangeblogservice.controller");// Contoller包名
//        pc.setXml("mapper.xml");// Mapper.xml包名
        mpg.setPackageInfo(pc);

        /*
            配置模板，此模板配置默认生成mybatisplus自带模板的controller.java、service.java、serviceImpl.java、mapper.java、.xml、entity类的文件
            若不想用默认的模板生成的文件，有两种方法，一种是直接set自定义的模板路径，如下面的templateConfig.setController("/templates/MyController.java.ftl")
            第二种是使用自定义配置（下面有讲解），前提是要先把默认值设为null,如templateConfig.setXml(null)。两种方法的共同点是都需要先有自定义的模板文件，
            不同是第一种的文件生成路径是在前面的包配置固定了的，第二种是连文件生成的路径都是可以自定义的！
         */
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setService(null);
        templateConfig.setController(null);
        templateConfig.setMapper(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setEntity(null);
//        templateConfig.setController("/templates/MyController.java.ftl");
        mpg.setTemplate(templateConfig);

        /*
            自定义配置，前面也说了，只要有相应的模板文件，基本上能满足在项目中任何位置生成你想要的文件，比如.java、.xml、.jsp文件等
         */
        InjectionConfig cfg = new InjectionConfig() {
            /**
             * 使用initMap是抽象方法，所以必须被重写，
             * 注入自定义 Map 对象(注意需要setMap放进去)，该对象可以传递到模板引擎通过 cfg.xxx 引用
             */
            @Override
            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("mapperId", "testMapperId");
//                this.setMap(map);
            }
        };
        // 模板的路径，.ftl表示是模板引擎是 freemarker，还有.vm表示模板引擎是 velocity等等
        String templatePath = "/templates/mapper.xml.ftl";
        String templatePath1 = "/templates/mapper.java.ftl";
        String templatePath2 = "/templates/service.java.ftl";
        String templatePath3 = "/templates/serviceImpl.java.ftl";
        String templatePath4 = "/templates/controller.java.ftl";
        String templatePath5 = "/templates/entity.java.ftl";

        // 自定义输出配置，并且自定义配置会被优先输出
        List<FileOutConfig> focList = new ArrayList<>();
        // 配置 FileOutConfig 指定模板文件、输出文件达到自定义文件生成目的，如果同时自定义两个或多个以上，可以直接focList.add多个new FileOutConfig(templatePath) {}配置
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/orange-blog-dao/src/main/resources/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        // mapper.java
        focList.add(new FileOutConfig(templatePath1) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/orange-blog-dao/src/main/java/com/ck/orangeblogdao/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
            }
        });
        // service.java
        focList.add(new FileOutConfig(templatePath2) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/orange-blog-service/src/main/java/com/ck/orangeblogservice/service"
                        + "/" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });
        // serviceImpl.java
        focList.add(new FileOutConfig(templatePath3) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/orange-blog-service/src/main/java/com/ck/orangeblogservice/service/impl"
                        + "/" + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
            }
        });
        // controller.java
        focList.add(new FileOutConfig(templatePath4) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/orange-blog-service/src/main/java/com/ck/orangeblogservice/controller"
                        + "/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });
        // entity.java
        focList.add(new FileOutConfig(templatePath5) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/orange-blog-dao/src/main/java/com/ck/orangeblogdao/po"
                        + "/" + tableInfo.getEntityName()  + StringPool.DOT_JAVA;
            }
        });
        // 自定义判断是否创建文件,该配置用于判断某个类是否需要覆盖创建，如果想覆盖之前已生成的文件，isCreate方法直接返回true，默认是false；
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建，
//                checkDir("调用默认方法创建的目录");
                // 当然也可以自定义哪些文件可以被覆盖哪些不可以被覆盖，更新判断逻辑返回true或者false
                return true;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        /*
            策略配置
         */
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);// 数据库表映射到实体的命名策略:下划线转驼峰
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);// 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        // 自定义继承类全称，带包名 (以下为示例)
        strategy.setSuperEntityClass("com.ck.orangeblogdao.pojo.BasePo");
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setEntityLombokModel(true);// 【实体】是否为lombok模型（默认 false）
        strategy.setRestControllerStyle(true);// 生成 @RestController 控制器
        strategy.setSuperEntityColumns("id");// 写于父类中的公共字段
        strategy.setInclude("blog_discussant");// 需要包含的表名，允许正则表达式（与exclude二选一配置）
//        strategy.setExclude("m_fnd_user1");// 需要排除的表名，允许正则表达式
        strategy.setControllerMappingHyphenStyle(true);// 驼峰转连字符
//        strategy.setEntityTableFieldAnnotationEnable(true);// 是否生成实体时，生成字段注解 默认false;
//        strategy.setTablePrefix(pc.getModuleName() + "_");// 表前缀
        mpg.setStrategy(strategy);

        /*
            设置模板引擎 这里是 freemarker
         */
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
