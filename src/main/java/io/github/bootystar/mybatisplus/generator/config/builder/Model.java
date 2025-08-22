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
         * 新增dto名称转换
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertEntityInsertDTOName(Function<String, String> converter) {
            this.config.converterEntityInsertDTOName = converter;
            return this;
        }
        
        /**
         * 格式化新增dto名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityInsertDTOName(String format) {
            return convertEntityInsertDTOName((entityName) -> String.format(format, entityName));
        }
        
        /**
         * 修改dto名称转换
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertEntityUpdateDTOName(Function<String, String> converter) {
            this.config.converterEntityUpdateDTOName = converter;
            return this;
        }
        
        /**
         * 格式化修改dto名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityUpdateDTOName(String format) {
            return convertEntityUpdateDTOName((entityName) -> String.format(format, entityName));
        }
        
        /**
         * 查询dto名称转换
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertEntityQueryDTOName(Function<String, String> converter) {
            this.config.converterEntityQueryDTOName = converter;
            return this;
        }
        
        /**
         * 格式化查询dto名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityQueryDTOName(String format) {
            return convertEntityQueryDTOName((entityName) -> String.format(format, entityName));
        }
        
        /**
         * vo名称转换
         *
         * @param converter 　转换处理
         * @return this
         */
        public Builder convertEntityVOName(Function<String, String> converter) {
            this.config.converterEntityVOName = converter;
            return this;
        }
        
        /**
         * 格式化vo名称
         *
         * @param format 　格式
         * @return this
         */
        public Builder formatEntityVOName(String format) {
            return convertEntityVOName((entityName) -> String.format(format, entityName));
        }
        
        /**
         * 禁用查询dto继承实体类
         *
         * @return this
         */
        public Builder disableQueryDTOExtendsEntity() {
            this.config.extendsQueryDTO = true;
            return this;
        }

        /**
         * 禁用vo继承实体类
         *
         * @return this
         */
        public Builder disableVOExtendsEntity() {
            this.config.extendsVO = true;
            return this;
        }
    }
    
}
