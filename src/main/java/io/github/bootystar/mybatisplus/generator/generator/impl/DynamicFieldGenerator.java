package io.github.bootystar.mybatisplus.generator.generator.impl;

import io.github.bootystar.mybatisplus.generator.config.builder.EnhancerBuilder;
import io.github.bootystar.mybatisplus.generator.generator.core.AbstractGenerator;

/**
 * 动态字段生成器
 * 支持链式调用各个配置模块的builder
 *
 * @author bootystar
 */
public class DynamicFieldGenerator extends AbstractGenerator<EnhancerBuilder> {

    public DynamicFieldGenerator(String url, String username, String password) {
        super(url, username, password, new EnhancerBuilder());
        
        // 设置默认模板
        this.serviceConfigBuilder
                .serviceTemplate("/bootystar/templates/vm/dynamic/service.java")
                .serviceImplTemplate("/bootystar/templates/vm/dynamic/serviceImpl.java");
        
        this.mapperConfigBuilder
                .mapperTemplate("/bootystar/templates/vm/dynamic/mapper.java")
                .mapperXmlTemplate("/bootystar/templates/vm/dynamic/mapper.xml");
    }
}
