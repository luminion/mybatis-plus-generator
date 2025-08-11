package io.github.bootystar.mybatisplus.generator.config.core;

import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.IOutputFile;
import io.github.bootystar.mybatisplus.generator.config.po.ClassPayload;
import io.github.bootystar.mybatisplus.generator.config.po.LikeTable;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author bootystar
 */
public class StrategyConfigBuilder implements IConfigBuilder<StrategyConfig> {

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

    // =============自定义项==============

    /**
     * 不生成新增方法
     *
     * @return this
     */
    public StrategyConfigBuilder disableInsert() {
        this.strategyConfig.generateInsert = false;
        return this;
    }

    /**
     * 不生成更新方法
     *
     * @return this
     */
    public StrategyConfigBuilder disableUpdate() {
        this.strategyConfig.generateUpdate = false;
        return this;
    }

    /**
     * 不生成删除方法
     *
     * @return this
     */
    public StrategyConfigBuilder disableDelete() {
        this.strategyConfig.generateDelete = false;
        return this;
    }

    /**
     * 不生成查询方法
     *
     * @return this
     */
    public StrategyConfigBuilder disableSelect() {
        this.strategyConfig.generateSelect = false;
        return this;
    }

    /**
     * 不生成导入方法
     *
     * @return this
     */
    public StrategyConfigBuilder disableImport() {
        this.strategyConfig.generateImport = false;
        return this;
    }

    /**
     * 不生成导出方法
     *
     * @return this
     */
    public StrategyConfigBuilder disableExport() {
        this.strategyConfig.generateExport = false;
        return this;
    }
    
    /**
     * 不生成重写父类方法
     * <p>仅特殊配置时有效</p>
     *
     * @return this
     */
    public StrategyConfigBuilder disableOverrideMethods() {
        this.strategyConfig.methodOverride = false;
        return this;
    }
    
    /**
     * 指定查询的DTO
     *
     * @return this
     */
    public StrategyConfigBuilder queryDTO(Class<?> queryDTO) {
        this.strategyConfig.queryDTO = new ClassPayload(queryDTO);
        return this;
    }
    
    /**
     * 新增或修改时排除的字段
     *
     * @return this
     */
    public StrategyConfigBuilder editExcludeColumns(String... editExcludeColumns) {
        this.strategyConfig.editExcludeColumns.addAll(Arrays.asList(editExcludeColumns));
        return this;
    }

    /**
     * 启用swagger/springdoc模型实体的注解
     * <p>
     * 已知swagger注解在同名时有冲突, 禁用后请确保表注释不为空且不同名
     *
     * @return this
     */
    public StrategyConfigBuilder enableSwaggerModelWithAnnotation() {
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
    public StrategyConfigBuilder enableSwaggerAnnotationWithUUID() {
        this.strategyConfig.swaggerAnnotationWithUUID = true;
        return this;
    }

    /**
     * 添加额外类链接注释
     *
     * @return this
     */
    public StrategyConfigBuilder disableExtraClassLinkComment() {
        this.strategyConfig.extraClassLinkComment = false;
        return this;
    }

    /**
     * 使用EasyExcel
     * <p>默认使用FastExcel</p>
     *
     * @return this
     */
    public StrategyConfigBuilder enableEasyExcel() {
        this.strategyConfig.excelBasePackage = "com.alibaba.excel";
        return this;
    }

    /**
     * 额外字段后缀, k->后缀 ,v->sql运算符
     *
     * @param extraFieldSuffixMap 额外字段后缀
     * @return this
     */
    public StrategyConfigBuilder clearExtraFieldSuffix() {
        this.strategyConfig.extraFieldSuffixMap.clear();
        return this;
    }
    
    /**
     * 额外字段后缀
     *
     * @param suffix     后缀
     * @param operator   sql运算符
     * @return this
     */
    public StrategyConfigBuilder extraFieldSuffix(String suffix, String operator) {
        this.strategyConfig.extraFieldSuffixMap.put(suffix, operator);
        return this;
    }

    /**
     * 额外字段策略
     *
     * @param extraFieldStrategy 额外字段策略, BiFunction<String, TableField, Boolean>, 3个泛型参数分别为sql运算符,表字段信息,是否生成
     * @return this
     */
    public StrategyConfigBuilder extraFieldStrategy(BiFunction<String, TableField, Boolean> extraFieldStrategy) {
        this.strategyConfig.extraFieldStrategy = extraFieldStrategy;
        return this;
    }
}
