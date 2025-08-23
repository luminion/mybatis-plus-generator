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
package io.github.bootystar.mybatisplus.generator.config;

import io.github.bootystar.mybatisplus.generator.config.core.*;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.query.IDatabaseQuery;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 配置汇总 传递给文件生成工具
 *
 * @author YangHu, tangguo, hubin, Juzi, lanjerry
 * @since 2016-08-30
 */
@Getter
public class ConfigAdapter {
    /**
     * 数据库配置信息
     */
    private DataSourceConfig dataSourceConfig;
    /**
     * 全局配置信息
     */
    private GlobalConfig globalConfig;
    /**
     * 包配置信息
     */
    private PackageConfig packageConfig;
    /**
     * 策略配置信息
     */
    private StrategyConfig strategyConfig;
    /**
     * 注入配置信息
     */
    private InjectionConfig injectionConfig;
    /**
     * 实体配置
     */
    private EntityConfig entityConfig;
    /**
     * 映射器配置
     */
    private MapperConfig mapperConfig;
    /**
     * 服务配置
     */
    private ServiceConfig serviceConfig;
    /**
     * 控制器配置
     */
    private ControllerConfig controllerConfig;
    /**
     * 模型配置
     */
    private ModelConfig modelConfig;
    /**
     * 模板配置
     */
    private TemplateConfig templateConfig;

    /**
     * 过滤正则
     */
    private static final Pattern REGX = Pattern.compile("[~!/@#$%^&*()+\\\\\\[\\]|{};:'\",<.>?]+");

    /**
     * 数据库表信息
     */
    private final List<TableInfo> tableInfo = new ArrayList<>();

    /**
     * 路径配置信息
     */
    private final Map<OutputFile, String> pathInfo = new HashMap<>();
    
    /**
     * 包配置信息
     */
    private final Map<String, String> packageInfo = new HashMap<>();
    
    /**
     * 数据查询实例
     * @since 3.5.3
     */
    private final IDatabaseQuery databaseQuery;

    /**
     * 资源加载器
     * @since 3.5.9
     */
    @Setter
    private TemplateLoadWay templateLoadWay = TemplateLoadWay.FILE;
    
    public ConfigAdapter(DataSourceConfig dataSourceConfig,
                         GlobalConfig globalConfig,
                         PackageConfig packageConfig,
                         StrategyConfig strategyConfig,
                         InjectionConfig injectionConfig,
                         EntityConfig entityConfig,
                         MapperConfig mapperConfig,
                         ServiceConfig serviceConfig,
                         ControllerConfig controllerConfig,
                         ModelConfig modelConfig, 
                         TemplateConfig templateConfig
    ) {
        this.dataSourceConfig = dataSourceConfig;
        this.globalConfig = globalConfig;
        this.packageConfig = packageConfig;
        this.strategyConfig = strategyConfig;
        this.injectionConfig = injectionConfig;
        this.entityConfig = entityConfig;
        this.mapperConfig = mapperConfig;
        this.serviceConfig = serviceConfig;
        this.controllerConfig = controllerConfig;
        this.modelConfig = modelConfig;
        this.templateConfig = templateConfig;
        
        // 设置默认名称转换
        INameConvert nameConvert = entityConfig.getNameConvert();
        if (nameConvert == null) {
            entityConfig.setNameConvert(new INameConvert.DefaultNameConvert(this));
        }
        // 设置路径信息
        this.pathInfo.putAll(new PathInfoHandler(this).getPathInfo());
        // 设置包信息
        this.packageInfo.putAll(packageConfig.getPackageInfo(injectionConfig));
        Class<? extends IDatabaseQuery> databaseQueryClass = dataSourceConfig.getDatabaseQueryClass();
        try {
            Constructor<? extends IDatabaseQuery> declaredConstructor = databaseQueryClass.getDeclaredConstructor(this.getClass());
            this.databaseQuery = declaredConstructor.newInstance(this);
            // 设置表信息
            List<TableInfo> tableInfos = this.databaseQuery.queryTables();
            if (!tableInfos.isEmpty()) {
                this.tableInfo.addAll(tableInfos);
            }
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
}
