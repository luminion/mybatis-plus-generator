package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.ModelConfig;

import java.util.function.Function;

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
         * 覆盖已有文件
         */
        public Builder enableFileOverride() {
            this.config.fileOverride = true;
            return this;
        }
        
        /**
         * 格式化新增dto名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityInsertDTOName(String format) {
            this.config.converterEntityInsertDTOName = entityName -> String.format(format, entityName);
            return this;
        }
        
        /**
         * 格式化修改dto名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityUpdateDTOName(String format) {
            this.config.converterEntityUpdateDTOName = entityName -> String.format(format, entityName);
            return this;
        }
        
        /**
         * 格式化查询dto名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityQueryDTOName(String format) {
            this.config.converterEntityQueryDTOName = entityName -> String.format(format, entityName);
            return this;
        }
        
        /**
         * 格式化vo名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityVOName(String format) {
            this.config.converterEntityVOName = entityName -> String.format(format, entityName);
            return this;
        }
        
        /**
         * 禁用查询dto继承实体类
         *
         * @return this
         */
        public Builder enableQueryDTOExtendsEntity() {
            this.config.extendsQueryDTO = true;
            return this;
        }

        /**
         * 禁用vo继承实体类
         *
         * @return this
         */
        public Builder enableVOExtendsEntity() {
            this.config.extendsVO = true;
            return this;
        }
    }
    
}
