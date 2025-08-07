package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import org.apache.ibatis.cache.Cache;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * mapper配置构建器
 * 
 * @see com.baomidou.mybatisplus.generator.config.builder.Mapper.Builder
 * @author bootystar
 */
public class MapperBuilder implements IConfigBuilder<Mapper> {
    private final Mapper mapper = new Mapper();
    
    @Override
    public Mapper build() {
        return mapper;
    }

    /**
     * 父类Mapper
     *
     * @param superClass 类名
     * @return this
     */
    public MapperBuilder superClass(String superClass) {
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
    public MapperBuilder superClass(Class<?> superClass) {
        return superClass(superClass.getName());
    }

    /**
     * 开启 @Mapper 注解
     *
     * @return this
     * @see #mapperAnnotation(Class)
     * @since 3.5.1
     * @deprecated 3.5.4
     */
    @Deprecated
    public MapperBuilder enableMapperAnnotation() {
        this.mapper.mapperAnnotation = true;
        //TODO 因为现在mybatis-plus传递mybatis-spring依赖，这里是没问题的，但后面如果考虑脱离mybatis-spring的时候就需要把这里处理掉，建议使用mapperAnnotation方法来标记自己的注解。
        this.mapper.mapperAnnotationClass = org.apache.ibatis.annotations.Mapper.class;
        return this;
    }

    /**
     * 标记 Mapper 注解
     *
     * @param annotationClass 注解Class
     * @return this
     * @since 3.5.3
     */
    public MapperBuilder mapperAnnotation(Class<? extends Annotation> annotationClass) {
        this.mapper.mapperAnnotationClass = annotationClass;
        return this;
    }

    /**
     * 开启baseResultMap
     *
     * @return this
     * @since 3.5.0
     */
    public MapperBuilder enableBaseResultMap() {
        this.mapper.baseResultMap = true;
        return this;
    }

    /**
     * 开启baseColumnList
     *
     * @return this
     * @since 3.5.0
     */
    public MapperBuilder enableBaseColumnList() {
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
    public MapperBuilder cache(Class<? extends Cache> cache) {
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
    public MapperBuilder convertMapperFileName(ConverterFileName converter) {
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
    public MapperBuilder convertXmlFileName(ConverterFileName converter) {
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
    public MapperBuilder formatMapperFileName(String format) {
        return convertMapperFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 格式化Xml文件名称
     *
     * @param format 格式
     * @return this
     * @since 3.5.0
     */
    public MapperBuilder formatXmlFileName(String format) {
        return convertXmlFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 覆盖已有文件（该方法后续会删除，替代方法为enableFileOverride方法）
     *
     * @see #enableFileOverride()
     */
    @Deprecated
    public MapperBuilder fileOverride() {
//        LOGGER.warn("fileOverride方法后续会删除，替代方法为enableFileOverride方法");
        this.mapper.fileOverride = true;
        return this;
    }

    /**
     * 覆盖已有文件
     */
    public MapperBuilder enableFileOverride() {
        this.mapper.fileOverride = true;
        return this;
    }

    /**
     * Service模板路径
     *
     * @return this
     * @since 3.5.6
     */
    public MapperBuilder mapperTemplate(String template) {
        this.mapper.mapperTemplatePath = template;
        return this;
    }

    /**
     * ServiceImpl模板路径
     *
     * @return this
     * @since 3.5.6
     */
    public MapperBuilder mapperXmlTemplate(String template) {
        this.mapper.mapperXmlTemplatePath = template;
        return this;
    }

    /**
     * 禁用Mapper生成
     *
     * @return this
     * @since 3.5.6
     */
    public MapperBuilder disable() {
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
    public MapperBuilder disableMapper() {
        this.mapper.generateMapper = false;
        return this;
    }

    /**
     * 禁用MapperXml生成
     *
     * @return this
     * @since 3.5.6
     */
    public MapperBuilder disableMapperXml() {
        this.mapper.generateMapperXml = false;
        return this;
    }


    /**
     * 导包处理方法
     *
     * @param importPackageFunction 导包处理
     * @return this
     * @since 3.5.11
     */
    public MapperBuilder importPackageFunction(Function<Set<String>, List<String>> importPackageFunction) {
        this.mapper.importPackageFunction = importPackageFunction;
        return this;
    }
    
}
