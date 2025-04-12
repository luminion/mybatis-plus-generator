package io.github.bootystar.mybatisplus.generator.generator.impl;

import io.github.bootystar.mybatisplus.generator.config.builder.ExtraCodeBuilder;
import io.github.bootystar.mybatisplus.generator.generator.core.AbstractGenerator;

/**
 * 额外代码生成器
 * 通过生成额外代码及属性实现逻辑
 *
 * @author bootystar
 */
public class ExtraCodeGenerator extends AbstractGenerator<ExtraCodeBuilder> {

    public ExtraCodeGenerator(String url, String username, String password) {
        super(url, username, password, new ExtraCodeBuilder());
    }

}
