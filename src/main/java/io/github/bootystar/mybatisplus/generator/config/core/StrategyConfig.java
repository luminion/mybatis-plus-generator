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
import io.github.bootystar.mybatisplus.generator.ITemplate;
import io.github.bootystar.mybatisplus.generator.config.IOutputFile;
import io.github.bootystar.mybatisplus.generator.config.builder.BaseBuilder;
import io.github.bootystar.mybatisplus.generator.config.core.support.*;
import io.github.bootystar.mybatisplus.generator.config.po.LikeTable;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.config.rules.ExtraFieldStrategy;
import lombok.Getter;
import org.apache.ibatis.type.JdbcType;

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
public class StrategyConfig implements ITemplate{

    protected StrategyConfig() {
    }

    /**
     * 是否大写命名（默认 false）
     */
    protected boolean isCapitalMode;

    /**
     * 是否跳过视图（默认 false）
     */
    protected boolean skipView;

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
     * 启用sql过滤，语法不能支持使用sql过滤表的话，可以考虑关闭此开关.
     *
     * @since 3.3.1
     */
    protected boolean enableSqlFilter = true;

    /**
     * 启用 schema 默认 false
     */
    protected boolean enableSchema;

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

    private final Entity.Builder entityBuilder = new Entity.Builder(this);

    private final Controller.Builder controllerBuilder = new Controller.Builder(this);

    private final Mapper.Builder mapperBuilder = new Mapper.Builder(this);

    private final Service.Builder serviceBuilder = new Service.Builder(this);

    private Entity entity;

    private Controller controller;

    private Mapper mapper;

    private Service service;

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
     * javaEE api包(jakarta或javax)
     * <p>
     * 涉及HttpServletRequest,HttpServletResponse,@Resource
     */
    protected String javaApiPackage = "jakarta";
    
    /**
     * excel注解的包
     */
    protected String excelApiPackage = "cn.idev.excel";
    
    /**
     * excel类
     */
    protected String excelClass = "FastExcel";

    /**
     * 参数校验
     */
    protected boolean validated = true;

    /**
     * 额外类链接注释
     */
    protected boolean extraClassLinkComment = true;

    /**
     * 额外字段后缀
     */
    protected Map<String, String> extraFieldSuffixMap = new LinkedHashMap<>();

    /**
     * 额外字段策略
     */
    protected BiFunction<String, TableField, Boolean> extraFieldStrategy = new ExtraFieldStrategy();

    /**
     * 实体配置构建者
     *
     * @return 实体配置构建者
     * @since 3.5.0
     */
    public Entity.Builder entityBuilder() {
        return entityBuilder;
    }

    /**
     * 实体配置
     *
     * @return 实体配置
     * @since 3.5.0
     */
    public Entity entity() {
        if (entity == null) {
            this.entity = entityBuilder.get();
        }
        return entity;
    }

    /**
     * 控制器配置构建者
     *
     * @return 控制器配置构建者
     * @since 3.5.0
     */
    public Controller.Builder controllerBuilder() {
        return controllerBuilder;
    }

    /**
     * 控制器配置
     *
     * @return 控制器配置
     * @since 3.5.0
     */
    public Controller controller() {
        if (controller == null) {
            this.controller = controllerBuilder.get();
        }
        return controller;
    }

    /**
     * Mapper配置构建者
     *
     * @return Mapper配置构建者
     * @since 3.5.0
     */
    public Mapper.Builder mapperBuilder() {
        return mapperBuilder;
    }

    /**
     * Mapper配置
     *
     * @return Mapper配置
     * @since 3.5.0
     */
    public Mapper mapper() {
        if (mapper == null) {
            this.mapper = mapperBuilder.get();
        }
        return mapper;
    }

    /**
     * Service配置构建者
     *
     * @return Service配置构建者
     * @since 3.5.0
     */
    public Service.Builder serviceBuilder() {
        return serviceBuilder;
    }

    /**
     * Service配置
     *
     * @return Service配置
     * @since 3.5.0
     */
    public Service service() {
        if (service == null) {
            this.service = serviceBuilder.get();
        }
        return service;
    }

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
        // DTO及VO导入的包
        Set<String> importPackages = tableInfo.getImportPackages();
        Set<String> importPackages4DTO = new HashSet<>();
        for (String importPackage : importPackages) {
            if (!importPackage.startsWith("com.baomidou.mybatisplus.annotation")) {
                importPackages4DTO.add(importPackage);
            }
        }
        if (!importPackages4DTO.isEmpty()) {
            map.put("importPackages4DTO", importPackages4DTO);
        }
        List<JdbcType> jdbcTimeTypes = Arrays.asList(
                JdbcType.DATE,
                JdbcType.TIME,
                JdbcType.TIMESTAMP,
                JdbcType.DATETIMEOFFSET,// SQL Server 2008
                JdbcType.TIME_WITH_TIMEZONE,// JDBC 4.2 JDK8
                JdbcType.TIMESTAMP_WITH_TIMEZONE // JDBC 4.2 JDK8
        );
        map.put("jdbcTimeTypes", jdbcTimeTypes);

        return map;
    }

    /**
     * 策略配置构建者
     *
     * @author nieqiurong 2020/10/11.
     * @since 3.5.0
     */
    public static class Builder extends BaseBuilder {

        protected StrategyConfig config;

        public Builder() {
            super(new StrategyConfig());
            config = super.build();
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
         * 不生成新增方法
         *
         * @return this
         */
        public Builder disableInsert() {
            this.config.generateInsert = false;
            return this;
        }

        /**
         * 不生成更新方法
         *
         * @return this
         */
        public Builder disableUpdate() {
            this.config.generateUpdate = false;
            return this;
        }

        /**
         * 不生成删除方法
         *
         * @return this
         */
        public Builder disableDelete() {
            this.config.generateDelete = false;
            return this;
        }

        /**
         * 不生成查询方法
         *
         * @return this
         */
        public Builder disableSelect() {
            this.config.generateSelect = false;
            return this;
        }

        /**
         * 不生成导入方法
         *
         * @return this
         */
        public Builder disableImport() {
            this.config.generateImport = false;
            return this;
        }

        /**
         * 不生成导出方法
         *
         * @return this
         */
        public Builder disableExport() {
            this.config.generateExport = false;
            return this;
        }

        /**
         * 不生成重写父类方法
         * <p>仅特殊配置时有效</p>
         *
         * @return this
         */
        public Builder disableOverrideMethods() {
            this.config.methodOverride = false;
            return this;
        }

        /**
         * 禁用参数校验
         *
         * @return this
         */
        public Builder disableValidated() {
            this.config.validated = false;
            return this;
        }

        /**
         * 使用javax包作为javaEE api
         * <p>springboot2.x使用javax, springboot3.x使用jakarta</p>
         * 默认使用jakarta
         *
         * @return this
         */
        public Builder useJavax() {
            this.config.javaApiPackage = "javax";
            return this;
        }

        /**
         * 使用EasyExcel
         * <p>默认使用FastExcel</p>
         *
         * @return this
         */
        public Builder useEasyExcel() {
            this.config.excelApiPackage = "com.alibaba.excel";
            this.config.excelClass = "EasyExcel";
            return this;
        }

        /**
         * 添加额外类链接注释
         *
         * @return this
         */
        public Builder disableExtraClassLinkComment() {
            this.config.extraClassLinkComment = false;
            return this;
        }

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
