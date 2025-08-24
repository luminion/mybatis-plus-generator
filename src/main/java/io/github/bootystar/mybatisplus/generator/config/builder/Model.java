package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.ModelConfig;

/**
 * @author bootystar
 */
public class Model extends ModelConfig {
    public static class Builder {
        protected final Model config = new Model();

        protected Builder() {
        }

        protected ModelConfig build() {
            return this.config;
        }
        
        /**
         * 格式化新增dto名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatCreateDTOName(String format) {
            this.config.converterCreateDTOName = entityName -> String.format(format, entityName);
            return this;
        }
        
        /**
         * 格式化修改dto名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityUpdateDTOName(String format) {
            this.config.converterUpdateDTOName = entityName -> String.format(format, entityName);
            return this;
        }
        
        /**
         * 格式化查询dto名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityQueryDTOName(String format) {
            this.config.converterQueryDTOName = entityName -> String.format(format, entityName);
            return this;
        }
        
        /**
         * 格式化vo名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityVOName(String format) {
            this.config.converterQueryVOName = entityName -> String.format(format, entityName);
            return this;
        }

        /**
         * 覆盖已有文件
         */
        public Builder enableFileOverride() {
            this.config.fileOverride = true;
            return this;
        }
        
        /**
         * 查询dto继承实体类
         *
         * @return this
         */
        public Builder enableQueryDTOExtendsEntity() {
            this.config.queryDTOExtendsEntity = true;
            return this;
        }

        /**
         * vo继承实体类
         *
         * @return this
         */
        public Builder enableQueryVOExtendsEntity() {
            this.config.queryVOExtendsEntity = true;
            return this;
        }
    }
    
}
