package io.github.bootystar.mybatisplus.generator;

import io.github.bootystar.mybatisplus.generator.config.DtoConfig;
import io.github.bootystar.mybatisplus.generator.config.GlobalCustomConfig;
import io.github.bootystar.mybatisplus.generator.config.VoConfig;
import io.github.bootystar.mybatisplus.generator.generator.core.EnhanceGenerator;
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
    public static EnhanceGenerator extraCodeGenerator(String url, String username, String password) {
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
    public static EnhanceGenerator dynamicFieldGenerator(String url, String username, String password) {
        // 在此处实例化新的配置类，并传递给生成器
        DtoConfig dtoConfig = new DtoConfig();
        VoConfig voConfig = new VoConfig();
        GlobalCustomConfig globalCustomConfig = new GlobalCustomConfig();
        
        // 此处仅为示例，实际的生成器需要接收这些配置
        // return new EnhancerGenerator(url, username, password, dtoConfig, voConfig, globalCustomConfig);
        return null; // 暂时返回null，后续将由实际生成器实现
    }
    

}
