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
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.IOutputFile;
import io.github.bootystar.mybatisplus.generator.config.po.LikeTable;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;
import io.github.bootystar.mybatisplus.generator.config.rules.ExtraFieldStrategy;
import lombok.Getter;

import java.io.File;
import java.util.*;
import java.util.function.BiFunction;

/**
 * 策略配置项
 *
 * @author YangHu, tangguo, hubin
 * @since 2016/8/30
 */
@Getter
public class StrategyConfig {

    protected StrategyConfig() {
    }

    /**
     * 是否大写命名（默认 false）
     */
    @Getter
    protected boolean isCapitalMode;

    /**
     * 是否跳过视图（默认 false）
     */
    @Getter
    protected boolean skipView;

    /**
     * 过滤表前缀
     * example: addTablePrefix("t_")
     * result: t_simple -> Simple
     */
    @Getter
    protected final Set<String> tablePrefix = new HashSet<>();

    /**
     * 过滤表后缀
     * example: addTableSuffix("_0")
     * result: t_simple_0 -> Simple
     */
    @Getter
    protected final Set<String> tableSuffix = new HashSet<>();

    /**
     * 过滤字段前缀
     * example: addFieldPrefix("is_")
     * result: is_deleted -> deleted
     */
    @Getter
    protected final Set<String> fieldPrefix = new HashSet<>();

    /**
     * 过滤字段后缀
     * example: addFieldSuffix("_flag")
     * result: deleted_flag -> deleted
     */
    @Getter
    protected final Set<String> fieldSuffix = new HashSet<>();

    /**
     * 需要包含的表名，允许正则表达式（与exclude二选一配置）<br/>
     * 当{@link #enableSqlFilter}为true时，正则表达式无效.
     */
    @Getter
    protected final Set<String> include = new HashSet<>();

    /**
     * 需要排除的表名，允许正则表达式<br/>
     * 当{@link #enableSqlFilter}为true时，正则表达式无效.
     */
    @Getter
    protected final Set<String> exclude = new HashSet<>();

    /**
     * 启用sql过滤，语法不能支持使用sql过滤表的话，可以考虑关闭此开关.
     *
     * @since 3.3.1
     */
    @Getter
    protected boolean enableSqlFilter = true;

    /**
     * 启用 schema 默认 false
     */
    @Getter
    protected boolean enableSchema;

    /**
     * 包含表名
     *
     * @since 3.3.0
     */
    @Getter
    protected LikeTable likeTable;

    /**
     * 不包含表名
     * <p>
     * 只在{@link io.github.bootystar.mybatisplus.generator.query.SQLQuery}模式下生效.
     * </p>
     *
     * @since 3.3.0
     */
    @Getter
    protected LikeTable notLikeTable;

    @Getter
    protected IOutputFile outputFile = (path, ot) -> new File(path);


    // =============自定义项==============

    /**
     * 生成新增方法
     */
    protected boolean generateInsert = true;
    /**
     * 生成更新方法
     */
    protected boolean generateUpdate = true;
    /**
     * 生成删除方法
     */
    protected boolean generateDelete = true;
    /**
     * 生成查询方法
     */
    protected boolean generateSelect = true;
    /**
     * 生成导入方法
     */
    protected boolean generateImport = true;
    /**
     * 生成导出方法
     */
    protected boolean generateExport = true;
    
    /**
     * 是否生成重写父类方法
     */
    protected boolean methodOverride = true;

    /**
     * swagger实体是否添加注解
     */
    protected boolean swaggerModelWithAnnotation;

    /**
     * swagger注解添加uuid标识
     */
    protected boolean swaggerAnnotationWithUUID;

    /**
     * 额外类链接注释
     */
    protected boolean extraClassLinkComment = true;

    /**
     * excel注解的包
     */
    protected String excelBasePackage = "cn.idev.excel";
    
    /**
     * 额外字段后缀
     */
    protected Map<String, String> extraFieldSuffixMap;

    /**
     * 额外字段策略
     */
    protected BiFunction<String, TableField, Boolean> extraFieldStrategy = new ExtraFieldStrategy();


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

    /**
     * 策略配置构建者
     *
     * @author nieqiurong 2020/10/11.
     * @since 3.5.0
     */
    public static class Builder implements IConfigBuilder<StrategyConfig> {

        protected final StrategyConfig strategyConfig = new StrategyConfig();

        @Override
        public StrategyConfig build() {
            this.strategyConfig.validate();
            return strategyConfig;
        }

        /**
         * 开启大写命名
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableCapitalMode() {
            this.strategyConfig.isCapitalMode = true;
            return this;
        }

        /**
         * 开启跳过视图
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableSkipView() {
            this.strategyConfig.skipView = true;
            return this;
        }

        /**
         * 禁用sql过滤
         *
         * @return this
         * @since 3.5.0
         */
        public Builder disableSqlFilter() {
            this.strategyConfig.enableSqlFilter = false;
            return this;
        }

        /**
         * 启用 schema
         *
         * @return this
         * @since 3.5.1
         */
        public Builder enableSchema() {
            this.strategyConfig.enableSchema = true;
            return this;
        }

        /**
         * 增加过滤表前缀
         *
         * @param tablePrefix 过滤表前缀
         * @return this
         * @since 3.5.0
         */
        public Builder addTablePrefix(String... tablePrefix) {
            return addTablePrefix(Arrays.asList(tablePrefix));
        }

        public Builder addTablePrefix(List<String> tablePrefixList) {
            this.strategyConfig.tablePrefix.addAll(tablePrefixList);
            return this;
        }

        /**
         * 增加过滤表后缀
         *
         * @param tableSuffix 过滤表后缀
         * @return this
         * @since 3.5.1
         */
        public Builder addTableSuffix(String... tableSuffix) {
            return addTableSuffix(Arrays.asList(tableSuffix));
        }

        public Builder addTableSuffix(List<String> tableSuffixList) {
            this.strategyConfig.tableSuffix.addAll(tableSuffixList);
            return this;
        }

        /**
         * 增加过滤字段前缀
         *
         * @param fieldPrefix 过滤字段前缀
         * @return this
         * @since 3.5.0
         */
        public Builder addFieldPrefix(String... fieldPrefix) {
            return addFieldPrefix(Arrays.asList(fieldPrefix));
        }

        public Builder addFieldPrefix(List<String> fieldPrefix) {
            this.strategyConfig.fieldPrefix.addAll(fieldPrefix);
            return this;
        }

        /**
         * 增加过滤字段后缀
         *
         * @param fieldSuffix 过滤字段后缀
         * @return this
         * @since 3.5.1
         */
        public Builder addFieldSuffix(String... fieldSuffix) {
            return addFieldSuffix(Arrays.asList(fieldSuffix));
        }

        public Builder addFieldSuffix(List<String> fieldSuffixList) {
            this.strategyConfig.fieldSuffix.addAll(fieldSuffixList);
            return this;
        }

        /**
         * 增加包含的表名
         *
         * @param include 包含表
         * @return this
         * @since 3.5.0
         */
        public Builder addInclude(String... include) {
            this.strategyConfig.include.addAll(Arrays.asList(include));
            return this;
        }

        public Builder addInclude(List<String> includes) {
            this.strategyConfig.include.addAll(includes);
            return this;
        }

        public Builder addInclude(String include) {
            this.strategyConfig.include.addAll(Arrays.asList(include.split(",")));
            return this;
        }

        /**
         * 增加排除表
         *
         * @param exclude 排除表
         * @return this
         * @since 3.5.0
         */
        public Builder addExclude(String... exclude) {
            return addExclude(Arrays.asList(exclude));
        }

        public Builder addExclude(List<String> excludeList) {
            this.strategyConfig.exclude.addAll(excludeList);
            return this;
        }

        /**
         * 包含表名
         *
         * @return this
         */
        public Builder likeTable(LikeTable likeTable) {
            this.strategyConfig.likeTable = likeTable;
            return this;
        }

        /**
         * 不包含表名
         *
         * @return this
         */
        public Builder notLikeTable(LikeTable notLikeTable) {
            this.strategyConfig.notLikeTable = notLikeTable;
            return this;
        }

        /**
         * 输出文件处理
         *
         * @return this
         */
        public Builder outputFile(IOutputFile outputFile) {
            this.strategyConfig.outputFile = outputFile;
            return this;
        }

        // =============自定义项==============

        /**
         * 不生成新增方法
         *
         * @return this
         */
        public Builder disableInsert() {
            this.strategyConfig.generateInsert = false;
            return this;
        }

        /**
         * 不生成更新方法
         *
         * @return this
         */
        public Builder disableUpdate() {
            this.strategyConfig.generateUpdate = false;
            return this;
        }

        /**
         * 不生成删除方法
         *
         * @return this
         */
        public Builder disableDelete() {
            this.strategyConfig.generateDelete = false;
            return this;
        }

        /**
         * 不生成查询方法
         *
         * @return this
         */
        public Builder disableSelect() {
            this.strategyConfig.generateSelect = false;
            return this;
        }

        /**
         * 不生成导入方法
         *
         * @return this
         */
        public Builder disableImport() {
            this.strategyConfig.generateImport = false;
            return this;
        }

        /**
         * 不生成导出方法
         *
         * @return this
         */
        public Builder disableExport() {
            this.strategyConfig.generateExport = false;
            return this;
        }

        /**
         * 启用swagger/springdoc模型实体的注解
         * <p>
         * 已知swagger注解在同名时有冲突, 禁用后请确保表注释不为空且不同名
         *
         * @return this
         */
        public Builder enableSwaggerModelWithAnnotation() {
            this.strategyConfig.swaggerModelWithAnnotation = true;
            return this;
        }

        /**
         * 启用swagger/springdoc文档额外uuid标识
         * <p>
         * 已知swagger注解在同名时有冲突, 禁用后请确保表注释不为空且不同名
         *
         * @return this
         */
        public Builder enableSwaggerAnnotationWithUUID() {
            this.strategyConfig.swaggerAnnotationWithUUID = true;
            return this;
        }

        /**
         * 添加额外类链接注释
         *
         * @return this
         */
        public Builder disableExtraClassLinkComment() {
            this.strategyConfig.extraClassLinkComment = false;
            return this;
        }

        /**
         * 使用EasyExcel
         * <p>默认使用FastExcel</p>
         *
         * @return this
         */
        public Builder enableEasyExcel() {
            this.strategyConfig.excelBasePackage = "com.alibaba.excel";
            return this;
        }

        /**
         * 额外字段后缀
         *
         * @param extraFieldSuffixMap 额外字段后缀, Map<String, String>, 2个泛型参数分别为后缀,sql运算符
         * @return this
         */
        public Builder extraFieldSuffixMap(Map<String, String> extraFieldSuffixMap) {
            this.strategyConfig.extraFieldSuffixMap = extraFieldSuffixMap;
            return this;
        }

        /**
         * 额外字段策略
         *
         * @param extraFieldStrategy 额外字段策略, BiFunction<String, TableField, Boolean>, 3个泛型参数分别为sql运算符,表字段信息,是否生成
         * @return this
         */
        public Builder extraFieldStrategy(BiFunction<String, TableField, Boolean> extraFieldStrategy) {
            this.strategyConfig.extraFieldStrategy = extraFieldStrategy;
            return this;
        }
        



    }
}
