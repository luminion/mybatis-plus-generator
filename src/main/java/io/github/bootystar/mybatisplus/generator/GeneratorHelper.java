package io.github.bootystar.mybatisplus.generator;

import io.github.bootystar.mybatisplus.generator.config.builder.EnhancerBuilder;
import io.github.bootystar.mybatisplus.generator.config.builder.ExtraCodeBuilder;
import io.github.bootystar.mybatisplus.generator.generator.core.EnhanceGenerator;
import io.github.bootystar.mybatisplus.generator.generator.impl.EnhancerGenerator;
import io.github.bootystar.mybatisplus.generator.generator.impl.ExtraCodeGenerator;

@SuppressWarnings("unused")
public abstract class GeneratorHelper {

    /**
     * 额外代码生成器
     *
     * @param url      url
     * @param username 用户名
     * @param password 密码
     * @return {@link EnhanceGenerator }
     */
    public static EnhanceGenerator<ExtraCodeBuilder> extraCodeGenerator(String url, String username, String password) {
        return new ExtraCodeGenerator(url, username, password);
    }

    /**
     * 结合mybatis-plus-enhancer功能的生成器
     *
     * @param url      url
     * @param username 用户名
     * @param password 密码
     * @return {@link EnhanceGenerator }
     */
    public static EnhanceGenerator<EnhancerBuilder> dynamicFieldGenerator(String url, String username, String password) {
        return new EnhancerGenerator(url, username, password);
    }
    


}
