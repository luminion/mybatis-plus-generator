package io.github.bootystar.mybatisplus.generator.support.core;


import io.github.bootystar.mybatisplus.generator.config.core.*;
import io.github.bootystar.mybatisplus.generator.config.core.support.ControllerConfig;
import io.github.bootystar.mybatisplus.generator.config.core.support.EntityConfig;
import io.github.bootystar.mybatisplus.generator.config.core.support.MapperConfig;
import io.github.bootystar.mybatisplus.generator.config.core.support.ServiceConfig;

import java.util.function.Consumer;

/**
 * mybatis-plus增强生成器
 * @author bootystar
 */
public interface EnhanceGenerator<B> {

    void execute(String... tableNames);

    EnhanceGenerator<B> dataSourceConfig(Consumer<DataSourceConfig.Builder> consumer);

    EnhanceGenerator<B> globalConfig(Consumer<GlobalConfig.Builder> consumer);

    EnhanceGenerator<B> packageConfig(Consumer<PackageConfig.Builder> consumer);

    EnhanceGenerator<B> strategyConfig(Consumer<StrategyConfig.Builder> consumer);

    EnhanceGenerator<B> injectionConfig(Consumer<InjectionConfig.Builder> consumer);

//    EnhanceGenerator<B> customConfig(Consumer<B> consumer);

    EnhanceGenerator<B> entity(Consumer<EntityConfig.Builder> consumer);

    EnhanceGenerator<B> mapper(Consumer<MapperConfig.Builder> consumer);

    EnhanceGenerator<B> service(Consumer<ServiceConfig.Builder> consumer);

    EnhanceGenerator<B> controller(Consumer<ControllerConfig.Builder> consumer);

    EnhanceGenerator<B> mapperXmlResource(String path);

    EnhanceGenerator<B> initialize();

    EnhanceGenerator<B> enableGlobalFileOverwrite();


}
