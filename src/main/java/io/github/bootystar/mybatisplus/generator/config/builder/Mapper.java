package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.MapperConfig;
import io.github.bootystar.mybatisplus.generator.function.ConverterFileName;
import org.apache.ibatis.cache.Cache;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * @author bootystar
 */
public class Mapper extends MapperConfig{
    public static class Builder {
        protected final Mapper config = new Mapper();

        protected Builder() {
        }

        protected MapperConfig build(){
            return config;
        }

        /**
         * 父类Mapper
         *
         * @param superClass 类名
         * @return this
         */
        public Builder superClass(String superClass) {
            this.config.superClass = superClass;
            return this;
        }

        /**
         * 父类Mapper
         *
         * @param superClass 类
         * @return this
         * @since 3.5.0
         */
        public Builder superClass(Class<?> superClass) {
            return superClass(superClass.getName());
        }

        /**
         * 标记 MapperConfig 注解
         *
         * @param annotationClass 注解Class
         * @return this
         * @since 3.5.3
         */
        public Builder mapperAnnotation(Class<? extends Annotation> annotationClass) {
            this.config.mapperAnnotationClass = annotationClass;
            return this;
        }

        /**
         * 开启baseResultMap
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableBaseResultMap() {
            this.config.baseResultMap = true;
            return this;
        }

        /**
         * 开启baseColumnList
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableBaseColumnList() {
            this.config.baseColumnList = true;
            return this;
        }

        /**
         * 设置缓存实现类
         *
         * @param cache 缓存实现
         * @return this
         * @since 3.5.0
         */
        public Builder cache(Class<? extends Cache> cache) {
            this.config.cache = cache;
            return this;
        }

        /**
         * 输出Mapper文件名称转换
         *
         * @param converter 　转换处理
         * @return this
         * @since 3.5.0
         */
        public Builder convertMapperFileName(ConverterFileName converter) {
            this.config.converterMapperFileName = converter;
            return this;
        }

        /**
         * 转换Xml文件名称处理
         *
         * @param converter 　转换处理
         * @return this
         * @since 3.5.0
         */
        public Builder convertXmlFileName(ConverterFileName converter) {
            this.config.converterXmlFileName = converter;
            return this;
        }

        /**
         * 格式化Mapper文件名称
         *
         * @param format 　格式
         * @return this
         * @since 3.5.0
         */
        public Builder formatMapperFileName(String format) {
            return convertMapperFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 格式化Xml文件名称
         *
         * @param format 格式
         * @return this
         * @since 3.5.0
         */
        public Builder formatXmlFileName(String format) {
            return convertXmlFileName((entityName) -> String.format(format, entityName));
        }
        
        /**
         * 覆盖已有文件
         */
        public Builder enableFileOverride() {
            this.config.fileOverride = true;
            return this;
        }

        /**
         * 禁用Mapper生成
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disable() {
            this.config.generateMapper = false;
            this.config.generateMapperXml = false;
            return this;
        }

        /**
         * 禁用Mapper接口生成
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disableMapper() {
            this.config.generateMapper = false;
            return this;
        }

        /**
         * 禁用MapperXml生成
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disableMapperXml() {
            this.config.generateMapperXml = false;
            return this;
        }

        // =============自定义项==============

        /**
         * 清空排序字段
         *
         * @return this
         */
        public Builder clearSortColumnMap() {
            this.config.sortColumnMap.clear();
            return this;
        }

        /**
         * 添加排序字段,越先添加优先级越高
         *
         * @param columnName 字段名
         * @param isDesc     是否倒排
         * @return this
         */
        public Builder sortColumn(String columnName, boolean isDesc) {
            this.config.sortColumnMap.put(columnName, isDesc);
            return this;
        }

    }
}
