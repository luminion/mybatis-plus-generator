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
package io.github.bootystar.mybatisplus.generator.config.po;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.ConfigAdapter;
import io.github.bootystar.mybatisplus.generator.config.core.GlobalConfig;
import io.github.bootystar.mybatisplus.generator.config.rules.IColumnType;
import io.github.bootystar.mybatisplus.generator.jdbc.DatabaseMetaDataWrapper;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 表信息，关联到当前字段信息
 *
 * @author YangHu, lanjerry
 * @since 2016/8/30
 */
public class TableInfo {

    /**
     * 配置适配器
     */
    @Getter
    private final ConfigAdapter configAdapter;

    /**
     * 包导入信息
     */
    @Getter
    private final Set<String> importPackages = new TreeSet<>();

    /**
     * 是否转换
     */
    @Getter
    @Setter
    private boolean convert;

    /**
     * 表名称
     */
    @Getter
    private String name;

    /**
     * 表注释
     */
    @Getter
    private String comment;

    /**
     * 实体名称
     */
    @Getter
    private String entityName;

    /**
     * 实体插入dto名称
     */
    @Getter
    private String entityInsertDTOName;

    /**
     * 实体修改dto名称
     */
    @Getter
    private String entityUpdateDTOName;

    /**
     * 实体查询dto名称
     */
    @Getter
    private String entityQueryDTOName;

    /**
     * 实体vo名称
     */
    @Getter
    private String entityVOName;

    /**
     * mapper名称
     */
    @Getter
    private String mapperName;

    /**
     * xml名称
     */
    @Getter
    private String xmlName;

    /**
     * service名称
     */
    @Getter
    private String serviceName;

    /**
     * serviceImpl名称
     */
    @Getter
    private String serviceImplName;

    /**
     * controller名称
     */
    @Getter
    private String controllerName;

    /**
     * 表字段
     */
    @Getter
    private final List<TableField> fields = new ArrayList<>();

    /**
     * 是否有主键
     */
    @Getter
    @Setter
    private boolean havePrimaryKey;

    /**
     * 公共字段
     */
    @Getter
    private final List<TableField> commonFields = new ArrayList<>();

    /**
     * 字段名称集
     */
    private String fieldNames;


    /**
     * 索引信息
     *
     * @since 3.5.10
     */
    @Setter
    @Getter
    private List<DatabaseMetaDataWrapper.Index> indexList;

    /**
     * 字段信息
     *
     * @since 3.5.10
     */
    @Getter
    private final Map<String, TableField> tableFieldMap = new HashMap<>();

    /**
     * @since 3.5.10
     */
    @Getter
    @Setter
    private String schemaName;

    /**
     * 构造方法
     *
     * @param configBuilder 配置构建
     * @param name          表名
     * @since 3.5.0
     */
    public TableInfo(ConfigAdapter configBuilder, String name) {
        this.configAdapter = configBuilder;
        this.name = name;
    }

    /**
     * @since 3.5.0
     */
    protected TableInfo setConvert() {
        if (this.getConfigAdapter().getStrategyConfig().startsWithTablePrefix(name) || this.getConfigAdapter().getEntityConfig().isTableFieldAnnotationEnable()) {
            this.convert = true;
        } else {
            this.convert = !entityName.equalsIgnoreCase(name);
        }
        return this;
    }

    public String getEntityPath() {
        return entityName.substring(0, 1).toLowerCase() + entityName.substring(1);
    }

    /**
     * @param entityName 实体名称
     * @return this
     */
    public TableInfo setEntityName(String entityName) {
        this.entityName = entityName;
        setConvert();
        return this;
    }

    /**
     * 添加字段
     *
     * @param field 字段
     * @since 3.5.0
     */
    public void addField(TableField field) {
        if (this.getConfigAdapter().getEntityConfig().matchIgnoreColumns(field.getColumnName())) {
            // 忽略字段不在处理
            return;
        }
        tableFieldMap.put(field.getName(), field);
        if (this.getConfigAdapter().getEntityConfig().matchSuperEntityColumns(field.getColumnName())) {
            this.commonFields.add(field);
        } else {
            this.fields.add(field);
        }
    }

    /**
     * @param pkgs 包空间
     * @return this
     * @since 3.5.0
     */
    public TableInfo addImportPackages(String... pkgs) {
        return addImportPackages(Arrays.asList(pkgs));
    }

    public TableInfo addImportPackages(List<String> pkgList) {
        importPackages.addAll(pkgList);
        return this;
    }

    /**
     * 转换filed实体为 xml mapper 中的 base column 字符串信息
     */
    public String getFieldNames() {
        if (StringUtils.isBlank(fieldNames)) {
            this.fieldNames = this.fields.stream().map(TableField::getColumnName).collect(Collectors.joining(", "));
        }
        return this.fieldNames;
    }

    /**
     * 导包处理
     *
     * @since 3.5.0
     */
    public void importPackage() {
        String superEntity = this.getConfigAdapter().getEntityConfig().getSuperClass();
        if (StringUtils.isNotBlank(superEntity)) {
            // 自定义父类
            this.importPackages.add(superEntity);
        } else {
            if (this.getConfigAdapter().getEntityConfig().isActiveRecord()) {
                // 无父类开启 AR 模式
                this.importPackages.add("com.baomidou.mybatisplus.extension.activerecord.Model");
            }
        }
        if (this.getConfigAdapter().getEntityConfig().isSerialVersionUID() || this.getConfigAdapter().getEntityConfig().isActiveRecord()) {
            this.importPackages.add(Serializable.class.getCanonicalName());
            if (this.getConfigAdapter().getEntityConfig().isSerialAnnotation()) {
                this.importPackages.add("java.io.Serial");
            }
        }
        if (this.isConvert()) {
            this.importPackages.add(TableName.class.getCanonicalName());
        }
        IdType idType = this.getConfigAdapter().getEntityConfig().getIdType();
        if (null != idType && this.isHavePrimaryKey()) {
            // 指定需要 IdType 场景
            this.importPackages.add(IdType.class.getCanonicalName());
            this.importPackages.add(TableId.class.getCanonicalName());
        }
        this.fields.forEach(field -> {
            IColumnType columnType = field.getColumnType();
            if (null != columnType && null != columnType.getPkg()) {
                importPackages.add(columnType.getPkg());
            }
            if (field.isKeyFlag()) {
                // 主键
                if (field.isConvert() || field.isKeyIdentityFlag()) {
                    importPackages.add(TableId.class.getCanonicalName());
                }
                // 自增
                if (field.isKeyIdentityFlag()) {
                    importPackages.add(IdType.class.getCanonicalName());
                }
            } else if (field.isConvert()) {
                // 普通字段
                importPackages.add(com.baomidou.mybatisplus.annotation.TableField.class.getCanonicalName());
            }
            if (null != field.getFill()) {
                // 填充字段
                importPackages.add(com.baomidou.mybatisplus.annotation.TableField.class.getCanonicalName());
                //TODO 好像default的不用处理也行,这个做优化项目.
                importPackages.add(FieldFill.class.getCanonicalName());
            }
            if (field.isVersionField()) {
                this.importPackages.add(Version.class.getCanonicalName());
            }
            if (field.isLogicDeleteField()) {
                this.importPackages.add(TableLogic.class.getCanonicalName());
            }
        });
    }

    /**
     * 处理表信息(文件名与导包)
     *
     * @since 3.5.0
     */
    public void processTable() {
        String entityName = this.getConfigAdapter().getEntityConfig().getNameConvert().entityNameConvert(this);
        this.setEntityName(this.getConfigAdapter().getEntityConfig().getConverterFileName().apply(entityName));
        this.entityInsertDTOName = this.getConfigAdapter().getModelConfig().getConverterCreateDTOName().apply(entityName);
        this.entityUpdateDTOName = this.getConfigAdapter().getModelConfig().getConverterUpdateDTOName().apply(entityName);
        this.entityQueryDTOName = this.getConfigAdapter().getModelConfig().getConverterQueryDTOName().apply(entityName);
        this.entityVOName = this.getConfigAdapter().getModelConfig().getConverterQueryVOName().apply(entityName);
        this.mapperName = this.getConfigAdapter().getMapperConfig().getConverterMapperFileName().apply(entityName);
        this.xmlName = this.getConfigAdapter().getMapperConfig().getConverterXmlFileName().apply(entityName);
        this.serviceName = this.getConfigAdapter().getServiceConfig().getConverterServiceFileName().apply(entityName);
        this.serviceImplName = this.getConfigAdapter().getServiceConfig().getConverterServiceImplFileName().apply(entityName);
        this.controllerName = this.getConfigAdapter().getControllerConfig().getConverterFileName().apply(entityName);
        this.importPackage();
    }

    public TableInfo setComment(String comment) {
        GlobalConfig globalConfig = this.getConfigAdapter().getGlobalConfig();
        boolean swagger = globalConfig.isSwagger();
        boolean springdoc = globalConfig.isSpringdoc();
        boolean notBlank = StringUtils.isNotBlank(comment);
        boolean commentUUID = globalConfig.isCommentUUID();
        String uuid = commentUUID ? UUID.randomUUID().toString().substring(0, 4).toUpperCase() : "";
        this.comment = (swagger || springdoc) &&  notBlank ? comment.replace("\"", "\\\"") + uuid : comment;
        return this;
    }

}
