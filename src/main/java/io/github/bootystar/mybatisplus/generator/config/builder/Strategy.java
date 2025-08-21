package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.IOutputFile;
import io.github.bootystar.mybatisplus.generator.config.core.StrategyConfig;
import io.github.bootystar.mybatisplus.generator.config.po.LikeTable;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @author bootystar
 */
public class Strategy extends StrategyConfig {
    public static class Builder {
        protected Strategy config = new Strategy();

        protected Builder() {
        }

        protected StrategyConfig build() {
            return this.config;
        }

        /**
         * 开启大写命名
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableCapitalMode() {
            this.config.isCapitalMode = true;
            return this;
        }

        /**
         * 开启跳过视图
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableSkipView() {
            this.config.skipView = true;
            return this;
        }

        /**
         * 禁用sql过滤
         *
         * @return this
         * @since 3.5.0
         */
        public Builder disableSqlFilter() {
            this.config.enableSqlFilter = false;
            return this;
        }

        /**
         * 启用 schema
         *
         * @return this
         * @since 3.5.1
         */
        public Builder enableSchema() {
            this.config.enableSchema = true;
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
            this.config.tablePrefix.addAll(tablePrefixList);
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
            this.config.tableSuffix.addAll(tableSuffixList);
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
            this.config.fieldPrefix.addAll(fieldPrefix);
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
            this.config.fieldSuffix.addAll(fieldSuffixList);
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
            this.config.include.addAll(Arrays.asList(include));
            return this;
        }

        public Builder addInclude(List<String> includes) {
            this.config.include.addAll(includes);
            return this;
        }

        public Builder addInclude(String include) {
            this.config.include.addAll(Arrays.asList(include.split(",")));
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
            this.config.exclude.addAll(excludeList);
            return this;
        }

        /**
         * 包含表名
         *
         * @return this
         */
        public Builder likeTable(LikeTable likeTable) {
            this.config.likeTable = likeTable;
            return this;
        }

        /**
         * 不包含表名
         *
         * @return this
         */
        public Builder notLikeTable(LikeTable notLikeTable) {
            this.config.notLikeTable = notLikeTable;
            return this;
        }

        /**
         * 输出文件处理
         *
         * @return this
         */
        public Builder outputFile(IOutputFile outputFile) {
            this.config.outputFile = outputFile;
            return this;
        }

        // =============自定义项==============

        /**
         * 额外字段后缀
         *
         * @param suffix   后缀
         * @param operator sql运算符
         * @return this
         */
        public Builder extraFieldSuffix(String suffix, String operator) {
            this.config.extraFieldSuffixMap.put(suffix, operator);
            return this;
        }

        /**
         * 额外字段策略
         *
         * @param extraFieldStrategy 额外字段策略, BiFunction<String, TableField, Boolean>, 3个泛型参数分别为sql运算符,表字段信息,是否生成
         * @return this
         */
        public Builder extraFieldStrategy(BiFunction<String, TableField, Boolean> extraFieldStrategy) {
            this.config.extraFieldStrategy = extraFieldStrategy;
            return this;
        }

        /**
         * 清除额外字段后缀
         *
         * @return this
         */
        public Builder clearExtraFieldSuffix() {
            this.config.extraFieldSuffixMap.clear();
            return this;
        }
    }
}
