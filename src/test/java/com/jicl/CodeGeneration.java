package com.jicl;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Auther: SRT00324
 * @Date: 2019/11/28 16:42
 * @Description: 代码生成器
 */
public class CodeGeneration {
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("C:\\Users\\SRT00324\\Desktop\\test");
        gc.setFileOverride(true);
        gc.setActiveRecord(true);//不需要ActiveRecord特性的请改为false
        gc.setEnableCache(false);//XML二级缓存
        gc.setBaseResultMap(true);//XML ResultMap
        gc.setBaseColumnList(false);//XML columList
        gc.setAuthor("xianzilei");//作者


        //自定义文件命名，注意%s 会自动填充表实体属性
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setUrl("jdbc:mysql://localhost:3306/myblog-2?useUnicode=true&characterEncoding=utf-8");
        mpg.setDataSource(dsc);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"t_"});//此处可以修改您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);//表名生成策略
        strategy.setInclude(new String[]{"t_blog", "t_blog_tag", "t_tag", "t_type", "t_user", "t_comment", "t_message", "t_reply"});//需要生成的表
        strategy.setEntityLombokModel(true);//【实体】是否为lombok模型
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);

        mpg.setStrategy(strategy);

        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.jicl");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        //自定义模板
//        mpg.setTemplate(new TemplateConfig().setEntity("templates/entity.java.vm")
//                .setController("templates/controller.java.vm")
//                .setService("templates/service.java.vm")
//                .setServiceImpl("templates/serviceImpl.java.vm")
//                .setMapper("templates/mapper.java.vm")
//                .setXml("templates/mapper.xml.vm"));
        //执行生成
        mpg.execute();
    }
}
