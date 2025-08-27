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

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.Configurer;
import io.github.bootystar.mybatisplus.generator.config.support.GlobalConfig;
import io.github.bootystar.mybatisplus.generator.jdbc.DatabaseMetaDataWrapper;
import lombok.Getter;
import lombok.Setter;

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
    private final Configurer configAdapter;

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
    public TableInfo(Configurer configBuilder, String name) {
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
     * 转换filed实体为 xml mapper 中的 base column 字符串信息
     */
    public String getFieldNames() {
        if (StringUtils.isBlank(fieldNames)) {
            this.fieldNames = this.fields.stream().map(TableField::getColumnName).collect(Collectors.joining(", "));
        }
        return this.fieldNames;
    }

    

    /**
     * 处理表信息(文件名与导包)
     *
     * @since 3.5.0
     */
    public void processTable() {
        String entityName = this.getConfigAdapter().getEntityConfig().getNameConvert().entityNameConvert(this);
        this.setEntityName(entityName);
    }

    public TableInfo setComment(String comment) {
        GlobalConfig globalConfig = this.getConfigAdapter().getGlobalConfig();
        boolean swagger = globalConfig.isSwagger();
        boolean springdoc = globalConfig.isSpringdoc();
        boolean notBlank = StringUtils.isNotBlank(comment);
        boolean commentUUID = globalConfig.isCommentUUID();
        String uuid = commentUUID ? "@" + UUID.randomUUID().toString().substring(0, 4).toUpperCase() : "";
        this.comment = (swagger || springdoc) && notBlank ? comment.replace("\"", "\\\"") + uuid : comment;
        return this;
    }

}
