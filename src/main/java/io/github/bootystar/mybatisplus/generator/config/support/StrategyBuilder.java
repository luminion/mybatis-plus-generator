package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;

import java.util.Arrays;
import java.util.List;

/**
 * 策略配置构建器
 * 
 * @see com.baomidou.mybatisplus.generator.config.StrategyConfig.Builder
 * @author bootystar
 */
public class StrategyBuilder implements IConfigBuilder<Strategy> {
    private final Strategy strategy = new Strategy();
    
    @Override
    public Strategy build() {
        return strategy;
    }

    /**
     * 开启大写命名
     *
     * @return this
     * @since 3.5.0
     */
    public StrategyBuilder enableCapitalMode() {
        this.strategy.isCapitalMode = true;
        return this;
    }

    /**
     * 开启跳过视图
     *
     * @return this
     * @since 3.5.0
     */
    public StrategyBuilder enableSkipView() {
        this.strategy.skipView = true;
        return this;
    }

    /**
     * 禁用sql过滤
     *
     * @return this
     * @since 3.5.0
     */
    public StrategyBuilder disableSqlFilter() {
        this.strategy.enableSqlFilter = false;
        return this;
    }

    /**
     * 启用 schema
     *
     * @return this
     * @since 3.5.1
     */
    public StrategyBuilder enableSchema() {
        this.strategy.enableSchema = true;
        return this;
    }

    /**
     * 增加过滤表前缀
     *
     * @param tablePrefix 过滤表前缀
     * @return this
     * @since 3.5.0
     */
    public StrategyBuilder addTablePrefix(String... tablePrefix) {
        return addTablePrefix(Arrays.asList(tablePrefix));
    }

    public StrategyBuilder addTablePrefix(List<String> tablePrefixList) {
        this.strategy.tablePrefix.addAll(tablePrefixList);
        return this;
    }

    /**
     * 增加过滤表后缀
     *
     * @param tableSuffix 过滤表后缀
     * @return this
     * @since 3.5.1
     */
    public StrategyBuilder addTableSuffix(String... tableSuffix) {
        return addTableSuffix(Arrays.asList(tableSuffix));
    }

    public StrategyBuilder addTableSuffix(List<String> tableSuffixList) {
        this.strategy.tableSuffix.addAll(tableSuffixList);
        return this;
    }

    /**
     * 增加过滤字段前缀
     *
     * @param fieldPrefix 过滤字段前缀
     * @return this
     * @since 3.5.0
     */
    public StrategyBuilder addFieldPrefix(String... fieldPrefix) {
        return addFieldPrefix(Arrays.asList(fieldPrefix));
    }

    public StrategyBuilder addFieldPrefix(List<String> fieldPrefix) {
        this.strategy.fieldPrefix.addAll(fieldPrefix);
        return this;
    }

    /**
     * 增加过滤字段后缀
     *
     * @param fieldSuffix 过滤字段后缀
     * @return this
     * @since 3.5.1
     */
    public StrategyBuilder addFieldSuffix(String... fieldSuffix) {
        return addFieldSuffix(Arrays.asList(fieldSuffix));
    }

    public StrategyBuilder addFieldSuffix(List<String> fieldSuffixList) {
        this.strategy.fieldSuffix.addAll(fieldSuffixList);
        return this;
    }

    /**
     * 增加包含的表名
     *
     * @param include 包含表
     * @return this
     * @since 3.5.0
     */
    public StrategyBuilder addInclude(String... include) {
        this.strategy.include.addAll(Arrays.asList(include));
        return this;
    }

    public StrategyBuilder addInclude(List<String> includes) {
        this.strategy.include.addAll(includes);
        return this;
    }

    public StrategyBuilder addInclude(String include) {
        this.strategy.include.addAll(Arrays.asList(include.split(",")));
        return this;
    }

    /**
     * 增加排除表
     *
     * @param exclude 排除表
     * @return this
     * @since 3.5.0
     */
    public StrategyBuilder addExclude(String... exclude) {
        return addExclude(Arrays.asList(exclude));
    }

    public StrategyBuilder addExclude(List<String> excludeList) {
        this.strategy.exclude.addAll(excludeList);
        return this;
    }

    /**
     * 包含表名
     *
     * @return this
     */
    public StrategyBuilder likeTable(LikeTable likeTable) {
        this.strategy.likeTable = likeTable;
        return this;
    }

    /**
     * 不包含表名
     *
     * @return this
     */
    public StrategyBuilder notLikeTable(LikeTable notLikeTable) {
        this.strategy.notLikeTable = notLikeTable;
        return this;
    }
    
}
