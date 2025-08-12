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
package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.*;
import io.github.bootystar.mybatisplus.generator.config.core.*;
import io.github.bootystar.mybatisplus.generator.config.core.support.Controller;
import io.github.bootystar.mybatisplus.generator.config.core.support.Entity;
import io.github.bootystar.mybatisplus.generator.config.core.support.Mapper;
import io.github.bootystar.mybatisplus.generator.config.core.support.Service;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.query.IDatabaseQuery;
import lombok.Getter;
import lombok.Setter;


import java.lang.reflect.Constructor;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 配置汇总 传递给文件生成工具
 *
 * @author YangHu, tangguo, hubin, Juzi, lanjerry
 * @since 2016-08-30
 */
@Getter
public class ConfigBuilder {

    /**
     * 数据库配置信息
     */
    private final DataSourceConfig dataSourceConfig;

    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;
    
    /**
     * 包配置信息
     */
    private final PackageConfig packageConfig;

    /**
     * 策略配置信息
     */
    private StrategyConfig strategyConfig;

    /**
     * 注入配置信息
     */
    private InjectionConfig injectionConfig;
    
    /**
     * entity配置信息
     */
    private Entity entity;
    /**
     * mapper配置信息
     */
    private Mapper mapper;
    /**
     * service配置信息
     */
    private Service service;
    /**
     * controller配置信息
     */
    private Controller controller;


    /**
     * 数据库表信息
     */
    private final List<TableInfo> tableInfoList = new ArrayList<>();

    /**
     * 路径配置信息
     */
    private final Map<OutputFile, String> pathInfo = new HashMap<>();


    /**
     * 过滤正则
     */
    private static final Pattern REGX = Pattern.compile("[~!/@#$%^&*()+\\\\\\[\\]|{};:'\",<.>?]+");


    /**
     * 数据查询实例
     *
     * @since 3.5.3
     */
    private final IDatabaseQuery databaseQuery;

    /**
     * 资源加载器
     *
     * @since 3.5.9
     */
    @Getter
    @Setter
    private TemplateLoadWay templateLoadWay = TemplateLoadWay.FILE;

    /**
     * 在构造器中处理配置
     *
     * @param packageConfig    包配置
     * @param dataSourceConfig 数据源配置
     * @param strategyConfig   表配置
     * @param globalConfig     全局配置
     */
    public ConfigBuilder(
            PackageConfig packageConfig, 
            DataSourceConfig dataSourceConfig, 
            StrategyConfig strategyConfig, 
            GlobalConfig globalConfig, 
            InjectionConfig injectionConfig,
            Entity entity,
            Mapper mapper,
            Service service,
            Controller controller
    ) {
        this.dataSourceConfig = dataSourceConfig;
        this.strategyConfig = Optional.ofNullable(strategyConfig)
                .orElseGet(() -> new StrategyConfig.Builder().build());
        this.globalConfig = Optional.ofNullable(globalConfig)
                .orElseGet(() -> new GlobalConfig.Builder().build());
        this.packageConfig = Optional.ofNullable(packageConfig)
                .orElseGet(() -> new PackageConfig.Builder().build());
        this.injectionConfig = Optional.ofNullable(injectionConfig)
                .orElseGet(() -> new InjectionConfig.Builder().build());
        this.entity = Optional.ofNullable(entity)
                .orElseGet(() -> new Entity.Builder().build());
        this.mapper = Optional.ofNullable(mapper)
                .orElseGet(() -> new Mapper.Builder().build());
        this.service = Optional.ofNullable(service)
                .orElseGet(() -> new Service.Builder().build());
        this.controller = Optional.ofNullable(controller)
                .orElseGet(() -> new Controller.Builder().build());
        PathInfoHandler pathInfoHandler = new PathInfoHandler(this.injectionConfig, this.globalConfig, this.strategyConfig, this.packageConfig);
        this.pathInfo.putAll(pathInfoHandler.getPathInfo());
        Class<? extends IDatabaseQuery> databaseQueryClass = dataSourceConfig.getDatabaseQueryClass();
        try {
            Constructor<? extends IDatabaseQuery> declaredConstructor = databaseQueryClass.getDeclaredConstructor(this.getClass());
            this.databaseQuery = declaredConstructor.newInstance(this);
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("创建IDatabaseQuery实例出现错误:", exception);
        }
    }

    /**
     * 判断表名是否为正则表名(这表名规范比较随意,只能尽量匹配上特殊符号)
     *
     * @param tableName 表名
     * @return 是否正则
     * @since 3.5.0
     */
    public static boolean matcherRegTable(String tableName) {
        return REGX.matcher(tableName).find();
    }

    public ConfigBuilder setStrategyConfig(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
        return this;
    }

    public ConfigBuilder setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }

    public ConfigBuilder setInjectionConfig(InjectionConfig injectionConfig) {
        this.injectionConfig = injectionConfig;
        return this;
    }


    public List<TableInfo> getTableInfoList() {
        if (tableInfoList.isEmpty()) {
            List<TableInfo> tableInfos = this.databaseQuery.queryTables();
            if (!tableInfos.isEmpty()) {
                this.tableInfoList.addAll(tableInfos);
            }
        }
        return tableInfoList;
    }
    
}
