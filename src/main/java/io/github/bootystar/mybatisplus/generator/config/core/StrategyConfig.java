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

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.po.ExtraField;
import io.github.bootystar.mybatisplus.generator.config.po.LikeTable;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.config.rules.ExtraFieldStrategy;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import lombok.Getter;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * 策略配置项
 *
 * @author YangHu, tangguo, hubin
 * @since 2016/8/30
 */
@Getter
public class StrategyConfig implements ITemplate {

    protected StrategyConfig() {
    }

    /**
     * 过滤表前缀
     * example: addTablePrefix("t_")
     * result: t_simple -> Simple
     */
    protected final Set<String> tablePrefix = new HashSet<>();

    /**
     * 过滤表后缀
     * example: addTableSuffix("_0")
     * result: t_simple_0 -> Simple
     */
    protected final Set<String> tableSuffix = new HashSet<>();

    /**
     * 过滤字段前缀
     * example: addFieldPrefix("is_")
     * result: is_deleted -> deleted
     */
    protected final Set<String> fieldPrefix = new HashSet<>();

    /**
     * 过滤字段后缀
     * example: addFieldSuffix("_flag")
     * result: deleted_flag -> deleted
     */
    protected final Set<String> fieldSuffix = new HashSet<>();

    /**
     * 需要包含的表名，允许正则表达式（与exclude二选一配置）<br/>
     * 当{@link #enableSqlFilter}为true时，正则表达式无效.
     */
    protected final Set<String> include = new HashSet<>();

    /**
     * 需要排除的表名，允许正则表达式<br/>
     * 当{@link #enableSqlFilter}为true时，正则表达式无效.
     */
    protected final Set<String> exclude = new HashSet<>();

    /**
     * 包含表名
     *
     * @since 3.3.0
     */
    protected LikeTable likeTable;

    /**
     * 不包含表名
     * <p>
     * 只在{@link io.github.bootystar.mybatisplus.generator.query.SQLQuery}模式下生效.
     * </p>
     *
     * @since 3.3.0
     */
    protected LikeTable notLikeTable;

    /**
     * 额外字段后缀
     */
    protected Map<String, String> extraFieldSuffixMap = new LinkedHashMap<>();

    /**
     * 额外字段策略
     */
    protected BiFunction<String, TableField, Boolean> extraFieldStrategy = new ExtraFieldStrategy();

    /**
     * 启用 schema 默认 false
     */
    protected boolean enableSchema;

    /**
     * 是否大写命名（默认 false）
     */
    protected boolean isCapitalMode;

    /**
     * 是否跳过视图（默认 false）
     */
    protected boolean skipView;

    /**
     * 启用sql过滤，语法不能支持使用sql过滤表的话，可以考虑关闭此开关.
     *
     * @since 3.3.1
     */
    protected boolean enableSqlFilter = true;

    /**
     * 大写命名、字段符合大写字母数字下划线命名
     *
     * @param word 待判断字符串
     */
    public boolean isCapitalModeNaming(String word) {
        return isCapitalMode && StringUtils.isCapitalMode(word);
    }

    /**
     * 表名称匹配过滤表前缀
     *
     * @param tableName 表名称
     * @since 3.3.2
     */
    public boolean startsWithTablePrefix(String tableName) {
        return this.tablePrefix.stream().anyMatch(tableName::startsWith);
    }

    /**
     * 验证配置项
     *
     * @since 3.5.0
     */
    public void validate() {
        boolean isInclude = !this.getInclude().isEmpty();
        boolean isExclude = !this.getExclude().isEmpty();
        if (isInclude && isExclude) {
            throw new IllegalArgumentException("<strategy> 标签中 <include> 与 <exclude> 只能配置一项！");
        }
        if (this.getNotLikeTable() != null && this.getLikeTable() != null) {
            throw new IllegalArgumentException("<strategy> 标签中 <likeTable> 与 <notLikeTable> 只能配置一项！");
        }
    }

    /**
     * 包含表名匹配
     *
     * @param tableName 表名
     * @return 是否匹配
     * @since 3.5.0
     */
    public boolean matchIncludeTable(String tableName) {
        return matchTable(tableName, this.getInclude());
    }

    /**
     * 排除表名匹配
     *
     * @param tableName 表名
     * @return 是否匹配
     * @since 3.5.0
     */
    public boolean matchExcludeTable(String tableName) {
        return matchTable(tableName, this.getExclude());
    }

    /**
     * 表名匹配
     *
     * @param tableName   表名
     * @param matchTables 匹配集合
     * @return 是否匹配
     * @since 3.5.0
     */
    protected boolean matchTable(String tableName, Set<String> matchTables) {
        return matchTables.stream().anyMatch(t -> tableNameMatches(t, tableName));
    }

    /**
     * 表名匹配
     *
     * @param matchTableName 匹配表名
     * @param dbTableName    数据库表名
     * @return 是否匹配
     */
    protected boolean tableNameMatches(String matchTableName, String dbTableName) {
        return matchTableName.equalsIgnoreCase(dbTableName) || StringUtils.matches(matchTableName, dbTableName);
    }

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> map = ITemplate.super.renderData(tableInfo);
        List<ExtraField> extraFields = new ArrayList<>();
        Set<String> existPropertyNames = tableInfo.getFields().stream().map(e -> e.getPropertyName()).collect(Collectors.toSet());
        for (TableField field : tableInfo.getFields()) {
            if (field.isLogicDeleteField()) {
                continue;
            }
            for (Entry<String, String> entry : extraFieldSuffixMap.entrySet()) {
                String suffix = entry.getKey();
                String sqlOperator = entry.getValue().toUpperCase();
                if (extraFieldStrategy.apply(entry.getValue(), field)) {
                    String suffixPropertyName = field.getPropertyName() + suffix;
                    if (existPropertyNames.contains(suffixPropertyName)) {
                        continue;
                    }
                    existPropertyNames.add(suffixPropertyName);
                    ExtraField extraField = new ExtraField();
                    if (sqlOperator.equalsIgnoreCase("IN") || sqlOperator.equalsIgnoreCase("NOT IN")) {
                        extraField.setPropertyType("List<" + field.getPropertyType() + ">");
                    } else {
                        extraField.setPropertyType(field.getPropertyType());
                    }
                    extraField.setPropertyName(suffixPropertyName);
                    extraField.setCapitalName(field.getCapitalName() + suffix);
                    extraField.setColumnName(field.getColumnName());
                    extraField.setComment(this.replaceComment(field.getComment(), sqlOperator));
                    extraField.setSqlOperator(sqlOperator);
                    extraFields.add(extraField);
                }
            }
        }
        map.put("extraFields", extraFields);
        return map;
    }

    /**
     * 替换注释并检查sql运算符是否合规
     *
     * @param comment     评论
     * @param sqlOperator sql运算符
     */
    public String replaceComment(String comment, String sqlOperator) {
        switch (sqlOperator){
            case "LIKE": return comment + "(模糊匹配)";
            case "NOT LIKE": return comment + "(模糊匹配的反结果)";
            case "IN": return comment + "(包含值)";
            case "NOT IN": return comment + "(不包含值)";
            case "IS NULL": return comment + "(为空)";
            case "IS NOT NULL": return comment + "(非空)";
            case ">": return comment + "(大于)";
            case "<": return comment + "(小于)";
            case ">=": return comment + "(大于等于)";
            case "<=": return comment + "(小于等于)";
            case "!=":
            case "<>": return comment + "(不等于)";
            case "&>": return comment + "(包含指定bit位)";
            case "&=": return comment + "(不包含指定bit位)";
            default: throw new IllegalArgumentException(String.format("不支持的后缀字段操作符:%s, 支持的操作符:LIKE,NOT LIKE,IN,NOT IN,IS NULL,IS NOT NULL,>,<,>=,<=,!=,<>,&>,&=",sqlOperator));
        }
    }

}