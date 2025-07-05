package io.github.bootystar.mybatisplus.generator.generator.impl;

import io.github.bootystar.mybatisplus.generator.config.builder.DynamicSqlBuilder;
import io.github.bootystar.mybatisplus.generator.generator.core.AbstractGenerator;

/**
 * 动态sql型生成器
 *
 * @author bootystar
 */
public class DynamicSqlGenerator extends AbstractGenerator<DynamicSqlBuilder> {

    public DynamicSqlGenerator(String url, String username, String password) {
        super(url, username, password, new DynamicSqlBuilder());
        this.strategyConfigBuilder.serviceBuilder()
                .serviceTemplate("/bootystar/templates/vm/dynamic/service.java" )
                .serviceImplTemplate("/bootystar/templates/vm/dynamic/serviceImpl.java" )
        ;
        this.strategyConfigBuilder.mapperBuilder()
                .mapperTemplate("/bootystar/templates/vm/dynamic/mapper.java" )
                .mapperXmlTemplate("/bootystar/templates/vm/dynamic/mapper.xml" )
        ;
        this.strategyConfigBuilder.serviceBuilder()
//                .superServiceClass(DynamicService.class)
//                .superServiceImplClass(DynamicSqlServiceImpl.class)
        ;
        this.strategyConfigBuilder.mapperBuilder()
//                .superClass(DynamicMapper.class)
        ;
    }

}
