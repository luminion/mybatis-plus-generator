package io.github.bootystar.mybatisplus.generator.generator.impl;


import io.github.bootystar.mybatisplus.enhancer.core.DynamicMapper;
import io.github.bootystar.mybatisplus.enhancer.core.DynamicService;
import io.github.bootystar.mybatisplus.generator.config.builder.DynamicFieldBuilder;
import io.github.bootystar.mybatisplus.generator.generator.core.AbstractGenerator;

/**
 * 额外属性型生成器
 * 生成可供查询的额外属性
 *
 * @author booty
 */
public class DynamicFieldGenerator extends AbstractGenerator<DynamicFieldBuilder> {

    public DynamicFieldGenerator(String url, String username, String password) {
        super(url, username, password, new DynamicFieldBuilder());
        this.strategyConfigBuilder.serviceBuilder()
                .serviceTemplate("/templates/dynamic/service.java" )
                .serviceImplTemplate("/templates/dynamic/serviceImpl.java")
        ;
        this.strategyConfigBuilder.mapperBuilder()
                .mapperTemplate("/templates/dynamic/mapper.java" )
                .mapperXmlTemplate("/templates/dynamic/mapper.xml" )
        ;
        this.strategyConfigBuilder.serviceBuilder()
//                .superServiceClass(DynamicService.class)
//                .superServiceImplClass(DynamicFieldServiceImpl.class)
        ;
        this.strategyConfigBuilder.mapperBuilder()
//                .superClass(DynamicMapper.class)
        ;
    }

}
