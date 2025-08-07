package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.baomidou.mybatisplus.generator.util.ClassUtils;
import io.github.bootystar.mybatisplus.generator.config.IReflectiveTemplate;
import lombok.Getter;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Function;

/**
 * mapper配置
 *
 * @see com.baomidou.mybatisplus.generator.config.builder.Mapper
 * @author bootystar
 */
public class Mapper implements IReflectiveTemplate {

    /**
     * 自定义继承的Mapper类全称，带包名
     */
    protected String superClass = ConstVal.SUPER_MAPPER_CLASS;

    /**
     * 是否添加 @Mapper 注解（默认 false）
     *
     * @see #mapperAnnotationClass
     * @since 3.5.1
     * @deprecated 3.5.4
     */
    @Deprecated
    protected boolean mapperAnnotation;

    /**
     * Mapper标记注解
     *
     * @since 3.5.3
     */
    protected Class<? extends Annotation> mapperAnnotationClass;

    /**
     * 是否开启BaseResultMap（默认 false）
     *
     * @since 3.5.0
     */
    @Getter
    protected boolean baseResultMap;

    /**
     * 是否开启baseColumnList（默认 false）
     *
     * @since 3.5.0
     */
    @Getter
    protected boolean baseColumnList;

    /**
     * 转换输出Mapper文件名称
     *
     * @since 3.5.0
     */
    @Getter
    protected ConverterFileName converterMapperFileName = (entityName -> entityName + ConstVal.MAPPER);

    /**
     * 转换输出Xml文件名称
     *
     * @since 3.5.0
     */
    @Getter
    protected ConverterFileName converterXmlFileName = (entityName -> entityName + ConstVal.MAPPER);

    /**
     * 是否覆盖已有文件（默认 false）
     *
     * @since 3.5.2
     */
    @Getter
    protected boolean fileOverride;

    /**
     * 设置缓存实现类
     *
     * @since 3.5.0
     */
    protected Class<? extends Cache> cache;
    

    /**
     * 是否生成XML
     *
     * @since 3.5.6
     */
    @Getter
    protected boolean generateMapperXml = true;

    /**
     * 是否生成Mapper
     *
     * @since 3.5.6
     */
    @Getter
    protected boolean generateMapper = true;

    /**
     * Mapper模板路径
     *
     * @since 3.5.6
     */
    @Getter
    protected String mapperTemplatePath = ConstVal.TEMPLATE_MAPPER;

    /**
     * MapperXml模板路径
     *
     * @since 3.5.6
     */
    @Getter
    protected String mapperXmlTemplatePath = ConstVal.TEMPLATE_XML;

    /**
     * 导包处理方法
     *
     * @since 3.5.11
     */
    protected Function<Set<String>, List<String>> importPackageFunction;



    public Class<? extends Cache> getCache() {
        return this.cache == null ? LoggingCache.class : this.cache;
    }




    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        boolean enableCache = this.cache != null;
        data.put("enableCache", enableCache);
        data.put("baseResultMap", this.baseResultMap);
        data.put("baseColumnList", this.baseColumnList);
        data.put("superMapperClassPackage", this.superClass);
        if(enableCache){
            Class<? extends Cache> cacheClass = this.getCache();
            data.put("cache", cacheClass);
            data.put("cacheClassName", cacheClass.getName());
        }
        data.put("superMapperClass", ClassUtils.getSimpleName(this.superClass));
        return data;
    }
}