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

    }
}
