package io.github.bootystar.mybatisplus.generator.generator.core;

import com.baomidou.mybatisplus.generator.config.*;
import io.github.bootystar.mybatisplus.generator.config.builder.BaseBuilder;

import java.util.function.Consumer;

/**
 * mybatis-plus增强生成器
 * @author bootystar
 */
public interface EnhanceGenerator<B extends BaseBuilder<B>> {

    void execute(String... tableNames);

    EnhanceGenerator<B> dataSourceConfig(Consumer<DataSourceConfig.Builder> consumer);

    EnhanceGenerator<B> globalConfig(Consumer<GlobalConfig.Builder> consumer);

    EnhanceGenerator<B> packageConfig(Consumer<PackageConfig.Builder> consumer);

    EnhanceGenerator<B> strategyConfig(Consumer<StrategyConfig.Builder> consumer);

    EnhanceGenerator<B> injectionConfig(Consumer<InjectionConfig.Builder> consumer);

    EnhanceGenerator<B> customConfig(Consumer<B> consumer);

//    EnhanceGenerator<B> entity(Consumer<Entity.Builder> consumer);
//
//    EnhanceGenerator<B> mapper(Consumer<Mapper.Builder> consumer);
//
//    EnhanceGenerator<B> service(Consumer<Service.Builder> consumer);
//
//    EnhanceGenerator<B> controller(Consumer<Controller.Builder> consumer);

    EnhanceGenerator<B> mapperXmlResource(String path);

    EnhanceGenerator<B> initialize();

    EnhanceGenerator<B> enableGlobalFileOverwrite();


}
