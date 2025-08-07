package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import com.baomidou.mybatisplus.generator.config.IOutputFile;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;

import java.util.Arrays;
import java.util.List;

/**
 * 策略配置构建器
 * 
 * @see com.baomidou.mybatisplus.generator.config.StrategyConfig.Builder
 * @author bootystar
 */
public class StrategyConfigBuilder implements IConfigBuilder<StrategyConfig> {
    private final StrategyConfig strategyConfig = new StrategyConfig();
    
    @Override
    public StrategyConfig build() {
        return strategyConfig;
    }

    /**
     * 开启大写命名
     *
     * @return this
     * @since 3.5.0
     */
    public StrategyConfigBuilder enableCapitalMode() {
        this.strategyConfig.isCapitalMode = true;
        return this;
    }

    /**
     * 开启跳过视图
     *
     * @return this
     * @since 3.5.0
     */
    public StrategyConfigBuilder enableSkipView() {
        this.strategyConfig.skipView = true;
        return this;
    }

    /**
     * 禁用sql过滤
     *
     * @return this
     * @since 3.5.0
     */
    public StrategyConfigBuilder disableSqlFilter() {
        this.strategyConfig.enableSqlFilter = false;
        return this;
    }

    /**
     * 启用 schema
     *
     * @return this
     * @since 3.5.1
     */
    public StrategyConfigBuilder enableSchema() {
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
    public StrategyConfigBuilder addTablePrefix(String... tablePrefix) {
        return addTablePrefix(Arrays.asList(tablePrefix));
    }

    public StrategyConfigBuilder addTablePrefix(List<String> tablePrefixList) {
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
    public StrategyConfigBuilder addTableSuffix(String... tableSuffix) {
        return addTableSuffix(Arrays.asList(tableSuffix));
    }

    public StrategyConfigBuilder addTableSuffix(List<String> tableSuffixList) {
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
    public StrategyConfigBuilder addFieldPrefix(String... fieldPrefix) {
        return addFieldPrefix(Arrays.asList(fieldPrefix));
    }

    public StrategyConfigBuilder addFieldPrefix(List<String> fieldPrefix) {
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
    public StrategyConfigBuilder addFieldSuffix(String... fieldSuffix) {
        return addFieldSuffix(Arrays.asList(fieldSuffix));
    }

    public StrategyConfigBuilder addFieldSuffix(List<String> fieldSuffixList) {
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
    public StrategyConfigBuilder addInclude(String... include) {
        this.strategyConfig.include.addAll(Arrays.asList(include));
        return this;
    }

    public StrategyConfigBuilder addInclude(List<String> includes) {
        this.strategyConfig.include.addAll(includes);
        return this;
    }

    public StrategyConfigBuilder addInclude(String include) {
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
    public StrategyConfigBuilder addExclude(String... exclude) {
        return addExclude(Arrays.asList(exclude));
    }

    public StrategyConfigBuilder addExclude(List<String> excludeList) {
        this.strategyConfig.exclude.addAll(excludeList);
        return this;
    }

    /**
     * 包含表名
     *
     * @return this
     */
    public StrategyConfigBuilder likeTable(LikeTable likeTable) {
        this.strategyConfig.likeTable = likeTable;
        return this;
    }

    /**
     * 不包含表名
     *
     * @return this
     */
    public StrategyConfigBuilder notLikeTable(LikeTable notLikeTable) {
        this.strategyConfig.notLikeTable = notLikeTable;
        return this;
    }

    /**
     * 输出文件处理
     *
     * @return this
     */
    public StrategyConfigBuilder outputFile(IOutputFile outputFile) {
        this.strategyConfig.outputFile = outputFile;
        return this;
    }
    
}
