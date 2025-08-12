package io.github.bootystar.mybatisplus.generator.support.core;


import io.github.bootystar.mybatisplus.generator.config.core.*;
import io.github.bootystar.mybatisplus.generator.config.core.support.Controller;
import io.github.bootystar.mybatisplus.generator.config.core.support.Entity;
import io.github.bootystar.mybatisplus.generator.config.core.support.Mapper;
import io.github.bootystar.mybatisplus.generator.config.core.support.Service;

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

    EnhanceGenerator<B> entity(Consumer<Entity.Builder> consumer);

    EnhanceGenerator<B> mapper(Consumer<Mapper.Builder> consumer);

    EnhanceGenerator<B> service(Consumer<Service.Builder> consumer);

    EnhanceGenerator<B> controller(Consumer<Controller.Builder> consumer);

    EnhanceGenerator<B> mapperXmlResource(String path);

    EnhanceGenerator<B> initialize();

    EnhanceGenerator<B> enableGlobalFileOverwrite();


}
