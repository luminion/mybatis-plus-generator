package io.github.bootystar.mybatisplus.generator.config.builder;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import io.github.bootystar.mybatisplus.generator.config.*;

import java.util.List;
import java.util.Map;

/**
 * 配置构建器
 * 用于整合所有配置信息
 *
 * @author bootystar
 */
public class ConfigBuilder {
    
    private final PackageConfig packageConfig;
    private final DataSourceConfig dataSourceConfig;
    private final StrategyConfig strategyConfig;
    private final GlobalConfig globalConfig;
    private final DtoConfig dtoConfig;
    private final VoConfig voConfig;
    private final GlobalCustomConfig globalCustomConfig;
    
    private Map<OutputFile, String> pathInfo;
    private List<com.baomidou.mybatisplus.generator.config.po.TableInfo> tableInfoList;
    
    public ConfigBuilder(
            PackageConfig packageConfig,
            DataSourceConfig dataSourceConfig,
            StrategyConfig strategyConfig,
            GlobalConfig globalConfig,
            DtoConfig dtoConfig,
            VoConfig voConfig,
            GlobalCustomConfig globalCustomConfig) {
        this.packageConfig = packageConfig;
        this.dataSourceConfig = dataSourceConfig;
        this.strategyConfig = strategyConfig;
        this.globalConfig = globalConfig;
        this.dtoConfig = dtoConfig;
        this.voConfig = voConfig;
        this.globalCustomConfig = globalCustomConfig;
    }
    
    public PackageConfig getPackageConfig() {
        return packageConfig;
    }
    
    public DataSourceConfig getDataSourceConfig() {
        return dataSourceConfig;
    }
    
    public StrategyConfig getStrategyConfig() {
        return strategyConfig;
    }
    
    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public DtoConfig getDtoConfig() {
        return dtoConfig;
    }

    public VoConfig getVoConfig() {
        return voConfig;
    }

    public GlobalCustomConfig getGlobalCustomConfig() {
        return globalCustomConfig;
    }
    
    public Map<OutputFile, String> getPathInfo() {
        return pathInfo;
    }

    public void setPathInfo(Map<OutputFile, String> pathInfo) {
        this.pathInfo = pathInfo;
    }

    public List<com.baomidou.mybatisplus.generator.config.po.TableInfo> getTableInfoList() {
        return tableInfoList;
    }

    public void setTableInfoList(List<com.baomidou.mybatisplus.generator.config.po.TableInfo> tableInfoList) {
        this.tableInfoList = tableInfoList;
    }
}