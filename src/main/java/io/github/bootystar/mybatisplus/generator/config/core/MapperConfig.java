/*
 * Copyright (c) 2011-2025, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.bootystar.mybatisplus.generator.config.core;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.handler.IGenerateMapperMethodHandler;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.function.ConverterFileName;
import io.github.bootystar.mybatisplus.generator.model.MapperMethod;
import io.github.bootystar.mybatisplus.generator.util.ClassUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 控制器属性配置
 *
 * @author nieqiurong 2020/10/11.
 * @since 3.5.0
 */
@Slf4j
@Getter
public class MapperConfig implements ITemplate {

    protected MapperConfig() {
    }

    /**
     * 自定义继承的Mapper类全称，带包名
     */
    protected String superClass = ConstVal.SUPER_MAPPER_CLASS;

    /**
     * Mapper标记注解
     *
     * @since 3.5.3
     */
    protected Class<? extends Annotation> mapperAnnotationClass = org.apache.ibatis.annotations.Mapper.class;

    /**
     * 是否开启BaseResultMap（默认 false）
     *
     * @since 3.5.0
     */
    protected boolean baseResultMap;

    /**
     * 是否开启baseColumnList（默认 false）
     *
     * @since 3.5.0
     */
    protected boolean baseColumnList;

    /**
     * 转换输出Mapper文件名称
     *
     * @since 3.5.0
     */
    protected ConverterFileName converterMapperFileName = (entityName -> entityName + ConstVal.MAPPER);

    /**
     * 转换输出Xml文件名称
     *
     * @since 3.5.0
     */
    protected ConverterFileName converterXmlFileName = (entityName -> entityName + ConstVal.MAPPER);

    /**
     * 是否覆盖已有文件（默认 false）
     *
     * @since 3.5.2
     */
    protected boolean fileOverride;

    /**
     * 设置缓存实现类
     *
     * @since 3.5.0
     */
    protected Class<? extends Cache> cache;
    public Class<? extends Cache> getCache() {
        return this.cache == null ? LoggingCache.class : this.cache;
    }

    /**
     * 是否生成XML
     *
     * @since 3.5.6
     */
    protected boolean generateMapperXml = true;

    /**
     * 是否生成Mapper
     *
     * @since 3.5.6
     */
    protected boolean generateMapper = true;

    /**
     * Mapper模板路径
     *
     * @since 3.5.6
     */
    protected String mapperTemplatePath = ConstVal.TEMPLATE_MAPPER;

    /**
     * MapperXml模板路径
     *
     * @since 3.5.6
     */
    protected String mapperXmlTemplatePath = ConstVal.TEMPLATE_XML;

    /**
     * Mapper层方法生成
     *
     * @since 3.5.10
     */
    protected IGenerateMapperMethodHandler generateMapperMethodHandler;

    /**
     * 导包处理方法
     *
     * @since 3.5.11
     */
    protected Function<Set<String>, List<String>> importPackageFunction;

    // =============自定义项==============

    /**
     * 排序字段map
     * 字段名 -> 是否倒序
     */
    protected Map<String, Boolean> sortColumnMap = new LinkedHashMap<>();
    

    
    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = ITemplate.super.renderData(tableInfo);
        boolean enableCache = this.cache != null;
        data.put("enableCache", enableCache);
        data.put("mapperAnnotation", mapperAnnotationClass != null);
        data.put("mapperAnnotationClass", mapperAnnotationClass);
        data.put("baseResultMap", this.baseResultMap);
        data.put("baseColumnList", this.baseColumnList);
        data.put("superMapperClassPackage", this.superClass);
        if (enableCache) {
            Class<? extends Cache> cacheClass = this.getCache();
            data.put("cache", cacheClass);
            data.put("cacheClassName", cacheClass.getName());
        }
        data.put("superMapperClass", ClassUtils.getSimpleName(this.superClass));
        data.put("generateMapperXml", this.generateMapperXml);
        data.put("generateMapper", this.generateMapper);
        List<MapperMethod> methodList = null;
        Set<String> importPackages = new HashSet<>();
        if (generateMapperMethodHandler != null) {
            methodList = generateMapperMethodHandler.getMethodList(tableInfo);
            importPackages.addAll(generateMapperMethodHandler.getImportPackages(tableInfo));
        }
        if (StringUtils.isNotBlank(superClass)) {
            importPackages.add(superClass);
        }
        if (mapperAnnotationClass != null) {
            importPackages.add(mapperAnnotationClass.getName());
        }
        PackageConfig packageConfig = tableInfo.getConfigAdapter().getPackageConfig();
        String entityPackage = packageConfig.getPackageInfo(null, ConstVal.ENTITY) + StringPool.DOT + tableInfo.getEntityName();
        importPackages.add(entityPackage);
        Set<String> javaPackages = importPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toSet());
        Set<String> frameworkPackages = importPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toSet());
        data.put("importPackages", importPackageFunction != null ? importPackageFunction.apply(importPackages) : importPackages.stream().sorted().collect(Collectors.toList()));
        data.put("importMapperFrameworkPackages", importPackageFunction != null ? importPackageFunction.apply(frameworkPackages) : frameworkPackages.stream().sorted().collect(Collectors.toList()));
        data.put("importMapperJavaPackages", importPackageFunction != null ? importPackageFunction.apply(javaPackages) : javaPackages.stream().sorted().collect(Collectors.toList()));
        data.put("mapperMethodList", methodList == null ? Collections.emptyList() : methodList);

        // 排序字段sql
        List<TableField> sortFields = tableInfo.getFields();
        List<String> existColumnNames = sortFields.stream().map(TableField::getColumnName).collect(Collectors.toList());
        if (sortColumnMap != null && !sortColumnMap.isEmpty()) {
            sortColumnMap.entrySet().stream()
                    .filter(e -> existColumnNames.contains(e.getKey()))
                    .map(e -> String.format("a.%s%s", e.getKey(), e.getValue() ? " DESC" : "" ))
                    .reduce((e1, e2) -> e1 + ", " + e2)
                    .ifPresent(e -> data.put("orderBySql", e));
        }
        return data;
    }

}
