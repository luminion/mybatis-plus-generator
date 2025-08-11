package io.github.bootystar.mybatisplus.generator.config.core.support;

import io.github.bootystar.mybatisplus.generator.IGenerateMapperMethodHandler;
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.function.ConverterFileName;
import org.apache.ibatis.cache.Cache;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * @author bootystar
 */
public class MapperConfigBuilder implements IConfigBuilder<MapperConfig> {
    protected final MapperConfig mapper = new MapperConfig();

    @Override
    public MapperConfig build() {
        return mapper;
    }

    /**
     * 父类Mapper
     *
     * @param superClass 类名
     * @return this
     */
    public MapperConfigBuilder superClass(String superClass) {
        this.mapper.superClass = superClass;
        return this;
    }

    /**
     * 父类Mapper
     *
     * @param superClass 类
     * @return this
     * @since 3.5.0
     */
    public MapperConfigBuilder superClass(Class<?> superClass) {
        return superClass(superClass.getName());
    }

    /**
     * 标记 MapperConfig 注解
     *
     * @param annotationClass 注解Class
     * @return this
     * @since 3.5.3
     */
    public MapperConfigBuilder mapperAnnotation(Class<? extends Annotation> annotationClass) {
        this.mapper.mapperAnnotationClass = annotationClass;
        return this;
    }

    /**
     * 开启baseResultMap
     *
     * @return this
     * @since 3.5.0
     */
    public MapperConfigBuilder enableBaseResultMap() {
        this.mapper.baseResultMap = true;
        return this;
    }

    /**
     * 开启baseColumnList
     *
     * @return this
     * @since 3.5.0
     */
    public MapperConfigBuilder enableBaseColumnList() {
        this.mapper.baseColumnList = true;
        return this;
    }

    /**
     * 设置缓存实现类
     *
     * @param cache 缓存实现
     * @return this
     * @since 3.5.0
     */
    public MapperConfigBuilder cache(Class<? extends Cache> cache) {
        this.mapper.cache = cache;
        return this;
    }

    /**
     * 输出Mapper文件名称转换
     *
     * @param converter 　转换处理
     * @return this
     * @since 3.5.0
     */
    public MapperConfigBuilder convertMapperFileName(ConverterFileName converter) {
        this.mapper.converterMapperFileName = converter;
        return this;
    }

    /**
     * 转换Xml文件名称处理
     *
     * @param converter 　转换处理
     * @return this
     * @since 3.5.0
     */
    public MapperConfigBuilder convertXmlFileName(ConverterFileName converter) {
        this.mapper.converterXmlFileName = converter;
        return this;
    }

    /**
     * 格式化Mapper文件名称
     *
     * @param format 　格式
     * @return this
     * @since 3.5.0
     */
    public MapperConfigBuilder formatMapperFileName(String format) {
        return convertMapperFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 格式化Xml文件名称
     *
     * @param format 格式
     * @return this
     * @since 3.5.0
     */
    public MapperConfigBuilder formatXmlFileName(String format) {
        return convertXmlFileName((entityName) -> String.format(format, entityName));
    }


    /**
     * 覆盖已有文件
     */
    public MapperConfigBuilder enableFileOverride() {
        this.mapper.fileOverride = true;
        return this;
    }

    /**
     * mapper模板路径
     *
     * @return this
     * @since 3.5.6
     */
    public MapperConfigBuilder mapperTemplate(String template) {
        this.mapper.mapperTemplatePath = template;
        return this;
    }

    /**
     * mapper.xml模板路径
     *
     * @return this
     * @since 3.5.6
     */
    public MapperConfigBuilder mapperXmlTemplate(String template) {
        this.mapper.mapperXmlTemplatePath = template;
        return this;
    }

    /**
     * 禁用Mapper生成
     *
     * @return this
     * @since 3.5.6
     */
    public MapperConfigBuilder disable() {
        this.mapper.generateMapper = false;
        this.mapper.generateMapperXml = false;
        return this;
    }

    /**
     * 禁用Mapper接口生成
     *
     * @return this
     * @since 3.5.6
     */
    public MapperConfigBuilder disableMapper() {
        this.mapper.generateMapper = false;
        return this;
    }

    /**
     * 禁用MapperXml生成
     *
     * @return this
     * @since 3.5.6
     */
    public MapperConfigBuilder disableMapperXml() {
        this.mapper.generateMapperXml = false;
        return this;
    }

    /**
     * Mapper层方法生成处理器
     *
     * @param generateMapperMethodHandler 处理器
     * @return this
     * @since 3.5.10
     */
    public MapperConfigBuilder generateMapperMethodHandler(IGenerateMapperMethodHandler generateMapperMethodHandler) {
        this.mapper.generateMapperMethodHandler = generateMapperMethodHandler;
        return this;
    }


    /**
     * 导包处理方法
     *
     * @param importPackageFunction 导包处理
     * @return this
     * @since 3.5.11
     */
    public MapperConfigBuilder importPackageFunction(Function<Set<String>, List<String>> importPackageFunction) {
        this.mapper.importPackageFunction = importPackageFunction;
        return this;
    }

    // =============自定义项==============

    /**
     * 清空排序字段
     *
     * @return this
     */
    public MapperConfigBuilder clearSortColumnMap() {
        this.mapper.sortColumnMap.clear();
        return this;
    }

    /**
     * 添加排序字段,越先添加优先级越高
     *
     * @param columnName 字段名
     * @param isDesc     是否倒排
     * @return this
     */
    public MapperConfigBuilder sortColumn(String columnName, boolean isDesc) {
        this.mapper.sortColumnMap.put(columnName, isDesc);
        return this;
    }
    
}
