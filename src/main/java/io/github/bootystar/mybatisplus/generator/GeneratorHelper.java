//package io.github.bootystar.mybatisplus.generator;
//
//import io.github.bootystar.mybatisplus.generator.config.builder.DynamicFieldBuilder;
//import io.github.bootystar.mybatisplus.generator.config.builder.DynamicSqlBuilder;
//import io.github.bootystar.mybatisplus.generator.config.builder.ExtraCodeBuilder;
//import io.github.bootystar.mybatisplus.generator.generator.core.EnhanceGenerator;
//import io.github.bootystar.mybatisplus.generator.generator.impl.DynamicFieldGenerator;
//import io.github.bootystar.mybatisplus.generator.generator.impl.DynamicSqlGenerator;
//import io.github.bootystar.mybatisplus.generator.generator.impl.ExtraCodeGenerator;
//
//@SuppressWarnings("unused")
//public abstract class GeneratorHelper {
//
//    /**
//     * 额外代码生成器
//     *
//     * @param url      url
//     * @param username 用户名
//     * @param password 密码
//     * @return {@link EnhanceGenerator }
//     */
//    public static EnhanceGenerator<ExtraCodeBuilder> extraCodeGenerator(String url, String username, String password) {
//        return new ExtraCodeGenerator(url, username, password);
//    }
//
//    /**
//     * 动态字段生成器
//     *
//     * @param url      url
//     * @param username 用户名
//     * @param password 密码
//     * @return {@link EnhanceGenerator }
//     */
//    public static EnhanceGenerator<DynamicFieldBuilder> dynamicFieldGenerator(String url, String username, String password) {
//        return new DynamicFieldGenerator(url, username, password);
//    }
//
//    /**
//     * 动态sql生成器
//     *
//     * @param url      url
//     * @param username 用户名
//     * @param password 密码
//     * @return {@link EnhanceGenerator }
//     */
//    public static EnhanceGenerator<DynamicSqlBuilder> dynamicSqlGenerator(String url, String username, String password) {
//        return new DynamicSqlGenerator(url, username, password);
//    }
//
//
//}
