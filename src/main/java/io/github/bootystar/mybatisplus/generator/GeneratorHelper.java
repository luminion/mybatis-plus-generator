package io.github.bootystar.mybatisplus.generator;

import io.github.bootystar.mybatisplus.generator.config.DtoConfig;
import io.github.bootystar.mybatisplus.generator.config.GlobalCustomConfig;
import io.github.bootystar.mybatisplus.generator.config.VoConfig;
import io.github.bootystar.mybatisplus.generator.generator.core.EnhanceGenerator;
import io.github.bootystar.mybatisplus.generator.generator.impl.ExtraCodeGenerator;
import io.github.bootystar.mybatisplus.generator.generator.impl.DynamicFieldGenerator;
import io.github.bootystar.mybatisplus.generator.generator.impl.EnhancerGenerator;

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
    public static EnhanceGenerator extraCodeGenerator(String url, String username, String password) {
        return new ExtraCodeGenerator(url, username, password);
    }

    /**
     * 结合mybatis-plus-enhancer功能的生成器
     * 支持链式调用各个配置模块的builder
     *
     * @param url      url
     * @param username 用户名
     * @param password 密码
     * @return {@link EnhanceGenerator }
     */
    public static EnhanceGenerator dynamicFieldGenerator(String url, String username, String password) {
        return new DynamicFieldGenerator(url, username, password);
    }

    /**
     * 增强生成器（原EnhancerGenerator）
     *
     * @param url      url
     * @param username 用户名
     * @param password 密码
     * @return {@link EnhanceGenerator }
     */
    public static EnhanceGenerator enhancerGenerator(String url, String username, String password) {
        return new EnhancerGenerator(url, username, password);
    }
    

}
