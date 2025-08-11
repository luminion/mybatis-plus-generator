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
package io.github.bootystar.mybatisplus.generator.config.core.support;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.*;
import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.INameConvert;
import io.github.bootystar.mybatisplus.generator.config.core.GlobalConfig;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.config.rules.NamingStrategy;
import io.github.bootystar.mybatisplus.generator.function.ConverterFileName;
import io.github.bootystar.mybatisplus.generator.model.AnnotationAttributes;
import io.github.bootystar.mybatisplus.generator.model.ClassAnnotationAttributes;
import io.github.bootystar.mybatisplus.generator.util.ClassUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 实体属性配置
 *
 * @author nieqiurong 2020/10/11.
 * @since 3.5.0
 */
@Slf4j
@Getter
public class EntityConfig implements ITemplate {
    
    protected EntityConfig() {
    }

    /**
     * 名称转换
     */
    protected INameConvert nameConvert;

    /**
     * 自定义继承的Entity类全称，带包名
     */
    protected String superClass;

    /**
     * 实体是否生成 serialVersionUID
     */
    protected boolean serialVersionUID = true;

    /**
     * 是否启用 {@link java.io.Serial} (需JAVA 14) 注解
     *
     * @since 3.5.11
     */
    protected boolean serialAnnotation;

    /**
     * 【实体】是否生成字段常量（默认 false）<br>
     * -----------------------------------<br>
     * public static final String ID = "test_id";
     */
    protected boolean columnConstant;

    /**
     * 【实体】是否为链式模型（默认 false）
     *
     * @since 3.3.2
     */
    protected boolean chain;

    /**
     * 【实体】是否为lombok模型（默认 false）<br>
     * <a href="https://projectlombok.org/">document</a>
     */
    protected boolean lombok;

    /**
     * Boolean类型字段是否移除is前缀（默认 false）<br>
     * 比如 : 数据库字段名称 : 'is_xxx',类型为 : tinyint. 在映射实体的时候则会去掉is,在实体类中映射最终结果为 xxx
     */
    protected boolean booleanColumnRemoveIsPrefix;

    /**
     * 是否生成实体时，生成字段注解（默认 false）
     */
    protected boolean tableFieldAnnotationEnable;

    /**
     * 乐观锁字段名称(数据库字段)
     *
     * @since 3.5.0
     */
    protected String versionColumnName;

    /**
     * 乐观锁属性名称(实体字段)
     *
     * @since 3.5.0
     */
    protected String versionPropertyName;

    /**
     * 逻辑删除字段名称(数据库字段)
     *
     * @since 3.5.0
     */
    protected String logicDeleteColumnName;

    /**
     * 逻辑删除属性名称(实体字段)
     *
     * @since 3.5.0
     */
    protected String logicDeletePropertyName;

    /**
     * 数据库表映射到实体的命名策略，默认下划线转驼峰命名
     */
    protected NamingStrategy naming = NamingStrategy.underline_to_camel;

    /**
     * 数据库表字段映射到实体的命名策略
     * <p>未指定按照 naming 执行</p>
     */
    protected NamingStrategy columnNaming = null;
    public NamingStrategy getColumnNaming() {
        // 未指定以 naming 策略为准
        return Optional.ofNullable(columnNaming).orElse(naming);
    }

    /**
     * 自定义基础的Entity类，公共字段
     */
    protected final Set<String> superEntityColumns = new HashSet<>();

    /**
     * 自定义忽略字段
     * <a href="https://github.com/baomidou/generator/issues/46">...</a>
     */
    protected final Set<String> ignoreColumns = new HashSet<>();

    /**
     * 表填充字段
     */
    protected final List<IFill> tableFillList = new ArrayList<>();

    /**
     * 开启 ActiveRecord 模式（默认 false）
     *
     * @since 3.5.0
     */
    protected boolean activeRecord;

    /**
     * 指定生成的主键的ID类型
     *
     * @since 3.5.0
     */
    protected IdType idType;

    /**
     * 转换输出文件名称
     *
     * @since 3.5.0
     */
    protected ConverterFileName converterFileName = (entityName -> entityName);

    /**
     * 是否覆盖已有文件（默认 false）
     *
     * @since 3.5.2
     */
    protected boolean fileOverride;

    /**
     * 是否生成
     *
     * @since 3.5.6
     */
    protected boolean generate = true;

    /**
     * 默认lombok(低版本属性默认只有Getter和Setter)
     * <p>当升级至3.5.10后,默认启用@ToString,如果不需要,可通过{@link Builder#toString(boolean)}关闭</p>
     *
     * @since 3.5.10
     */
    protected boolean defaultLombok = true;

    /**
     * 是否生成ToString
     * <p>低版本下,lombok没有处理ToString逻辑,现在处理生成@ToString</p>
     * <p>支持控制toString方法是否生成</p>
     *
     * @since 3.5.10
     */
    protected boolean toString = true;


    /**
     * 启用字段文档注释 (当注释字段注释不为空才生效)
     * <p>低版本下,如果是启用swagger或者springdoc时,不会生成,现在统一修改为生成文档注释</p>
     *
     * @since 3.5.10
     */
    protected boolean fieldUseJavaDoc = true;

    /**
     * 实体类注解
     *
     * @since 3.5.10
     */
    protected final List<ClassAnnotationAttributes> classAnnotations = new ArrayList<>();

    /**
     * 表注解处理器
     *
     * @since 3.5.10
     */
    protected ITableAnnotationHandler tableAnnotationHandler = new DefaultTableAnnotationHandler();

    /**
     * 字段注解处理器
     *
     * @since 3.5.10
     */
    protected ITableFieldAnnotationHandler tableFieldAnnotationHandler = new DefaultTableFieldAnnotationHandler();

    /**
     * 导包处理方法
     *
     * @since 3.5.11
     */
    protected Function<Set<String>, List<String>> importPackageFunction;

    /**
     * 处理类注解方法 (含类与字段)
     *
     * @since 3.5.11
     */
    protected Function<List<? extends AnnotationAttributes>, List<AnnotationAttributes>> annotationAttributesFunction;

    /**
     * Java模板默认路径
     *
     * @since 3.5.6
     */
    protected String javaTemplate = ConstVal.TEMPLATE_ENTITY_JAVA;
    

    /**
     * <p>
     * 父类 Class 反射属性转换为公共字段
     * </p>
     *
     * @param clazz 实体父类 Class
     */
    public void convertSuperEntityColumns(Class<?> clazz) {
        // 3.5.12版本
//        List<Field> fields = TableInfoHelper.getAllFields(clazz);
//        this.superEntityColumns.addAll(fields.stream().map(field -> {
//            TableId tableId = annotationHandler.getAnnotation(field, TableId.class);
//            if (tableId != null && StringUtils.isNotBlank(tableId.value())) {
//                return tableId.value();
//            }
//            TableField tableField = annotationHandler.getAnnotation(field, TableField.class);
//            if (tableField != null && StringUtils.isNotBlank(tableField.value())) {
//                return tableField.value();
//            }
//            if (null == columnNaming || columnNaming == NamingStrategy.no_change) {
//                return field.getName();
//            }
//            return StringUtils.camelToUnderline(field.getName());
//        }).collect(Collectors.toSet()));
        // 3.5.0 版本
        List<Field> fields = TableInfoHelper.getAllFields(clazz);
        this.superEntityColumns.addAll(fields.stream().map(field -> {
            TableId tableId = field.getAnnotation(TableId.class);
            if (tableId != null && StringUtils.isNotBlank(tableId.value())) {
                return tableId.value();
            }
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField != null && StringUtils.isNotBlank(tableField.value())) {
                return tableField.value();
            }
            if (null == columnNaming || columnNaming == NamingStrategy.no_change) {
                return field.getName();
            }
            return StringUtils.camelToUnderline(field.getName());
        }).collect(Collectors.toSet()));
    }

    /**
     * 匹配父类字段(忽略大小写)
     *
     * @param fieldName 字段名
     * @return 是否匹配
     * @since 3.5.0
     */
    public boolean matchSuperEntityColumns(String fieldName) {
        // 公共字段判断忽略大小写【 部分数据库大小写不敏感 】
        return superEntityColumns.stream().anyMatch(e -> e.equalsIgnoreCase(fieldName));
    }

    /**
     * 匹配忽略字段(忽略大小写)
     *
     * @param fieldName 字段名
     * @return 是否匹配
     * @since 3.5.0
     */
    public boolean matchIgnoreColumns(String fieldName) {
        return ignoreColumns.stream().anyMatch(e -> e.equalsIgnoreCase(fieldName));
    }

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = ITemplate.super.renderData(tableInfo);
        data.put("idType", idType == null ? null : idType.toString());
        data.put("logicDeleteFieldName", this.logicDeleteColumnName);
        data.put("versionFieldName", this.versionColumnName);
        data.put("activeRecord", this.activeRecord);
        data.put("entitySerialVersionUID", this.serialVersionUID);
        data.put("entitySerialAnnotation", this.serialAnnotation);
        data.put("entityColumnConstant", this.columnConstant);
        data.put("entityBuilderModel", this.chain);
        data.put("chainModel", this.chain);
        data.put("entityLombokModel", this.lombok);
        data.put("entityBooleanColumnRemoveIsPrefix", this.booleanColumnRemoveIsPrefix);
        data.put("superEntityClass", ClassUtils.getSimpleName(this.superClass));
        Set<String> importPackages = new HashSet<>(tableInfo.getImportPackages());
        List<ClassAnnotationAttributes> classAnnotationAttributes = new ArrayList<>(this.getClassAnnotations());
        if (tableAnnotationHandler != null) {
            List<ClassAnnotationAttributes> classAnnotationAttributesList = tableAnnotationHandler.handle(tableInfo, this);
            if (classAnnotationAttributesList != null && !classAnnotationAttributesList.isEmpty()) {
                classAnnotationAttributes.addAll(classAnnotationAttributesList);
            }
        }
        classAnnotationAttributes.forEach(attributes -> {
            attributes.handleDisplayName(tableInfo);
            importPackages.addAll(attributes.getImportPackages());
        });
        if (tableFieldAnnotationHandler != null) {
            tableInfo.getFields().forEach(tableField -> {
                List<AnnotationAttributes> annotationAttributes = tableFieldAnnotationHandler.handle(tableInfo, tableField);
                if (annotationAttributes != null && !annotationAttributes.isEmpty()) {
                    tableField.addAnnotationAttributesList(annotationAttributes, annotationAttributesFunction);
                    annotationAttributes.forEach(attributes -> importPackages.addAll(attributes.getImportPackages()));
                }
            });
        }
        data.put("entityFieldUseJavaDoc", fieldUseJavaDoc);
        data.put("entityClassAnnotations", annotationAttributesFunction != null ? annotationAttributesFunction.apply(classAnnotationAttributes) :
            classAnnotationAttributes.stream().sorted(Comparator.comparingInt(s -> s.getDisplayName().length())).collect(Collectors.toList()));
        data.put("importEntityPackages", importPackageFunction != null ? importPackageFunction.apply(importPackages) :
            importPackages.stream().sorted().collect(Collectors.toList()));
        Set<String> javaPackages = importPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toSet());
        data.put("importEntityJavaPackages", importPackageFunction != null ? importPackageFunction.apply(javaPackages) :
            javaPackages.stream().sorted().collect(Collectors.toList()));
        Set<String> frameworkPackages = importPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toSet());
        data.put("importEntityFrameworkPackages", importPackageFunction != null ? importPackageFunction.apply(frameworkPackages) :
            frameworkPackages.stream().sorted().collect(Collectors.toList()));
        data.put("entityToString", this.toString);
        return data;
    }
    
}
