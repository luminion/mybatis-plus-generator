package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.ServiceConfig;

import java.util.function.Function;

/**
 * @author bootystar
 */
public class Service extends ServiceConfig {
    public static class Builder {
        protected final Service config = new Service();

        protected Builder() {
        }

        protected ServiceConfig build() {
            return this.config;
        }

        /**
         * Service接口父类
         *
         * @param clazz 类
         * @return this
         */
        public Builder superServiceClass(Class<?> clazz) {
            return superServiceClass(clazz.getName());
        }

        /**
         * Service接口父类
         *
         * @param superServiceClass 类名
         * @return this
         */
        public Builder superServiceClass(String superServiceClass) {
            this.config.superServiceClass = superServiceClass;
            return this;
        }

        /**
         * Service实现类父类
         *
         * @param clazz 类
         * @return this
         */
        public Builder superServiceImplClass(Class<?> clazz) {
            return superServiceImplClass(clazz.getName());
        }

        /**
         * Service实现类父类
         *
         * @param superServiceImplClass 类名
         * @return this
         */
        public Builder superServiceImplClass(String superServiceImplClass) {
            this.config.superServiceImplClass = superServiceImplClass;
            return this;
        }

        /**
         * 转换输出service接口文件名称
         *
         * @param converter 　转换处理
         * @return this
         * @since 3.5.0
         */
        public Builder convertServiceFileName(Function<String, String> converter) {
            this.config.converterServiceFileName = converter;
            return this;
        }

        /**
         * 转换输出service实现类文件名称
         *
         * @param converter 　转换处理
         * @return this
         * @since 3.5.0
         */
        public Builder convertServiceImplFileName(Function<String, String> converter) {
            this.config.converterServiceImplFileName = converter;
            return this;
        }

        /**
         * 格式化service接口文件名称
         *
         * @param format 　格式
         * @return this
         * @since 3.5.0
         */
        public Builder formatServiceFileName(String format) {
            return convertServiceFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 格式化service实现类文件名称
         *
         * @param format 　格式
         * @return this
         * @since 3.5.0
         */
        public Builder formatServiceImplFileName(String format) {
            return convertServiceImplFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 覆盖已有文件
         */
        public Builder enableFileOverride() {
            this.config.fileOverride = true;
            return this;
        }

        /**
         * 禁用生成Service
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disable() {
            this.config.generateService = false;
            this.config.generateServiceImpl = false;
            return this;
        }

        /**
         * 禁用生成
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disableService() {
            this.config.generateService = false;
            return this;
        }

        /**
         * 禁用生成ServiceImpl
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disableServiceImpl() {
            this.config.generateServiceImpl = false;
            return this;
        }

    }
}
