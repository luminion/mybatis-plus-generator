package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.enhancer.builder.ExtraFieldSuffixBuilder;
import io.github.bootystar.mybatisplus.enhancer.core.support.DynamicFieldService;
import io.github.bootystar.mybatisplus.enhancer.core.support.DynamicSqlService;
import io.github.bootystar.mybatisplus.generator.info.ClassInfo;
import io.github.bootystar.mybatisplus.generator.strategy.ExtraFieldGenerateStrategy;
import lombok.Getter;

import java.util.function.Consumer;


/**
 * @author bootystar
 */
@Getter
public class DynamicFieldBuilder extends BaseEnhanceBuilder<DynamicFieldBuilder> {

    {
//        mapperDTO = new ClassInfo(UnmodifiableSqlHelper.class);
        dynamicServiceClassInfo = new ClassInfo(DynamicFieldService.class);
    }

    @Override
    public DynamicFieldBuilder disableOverrideMethods() {
        return super.disableOverrideMethods();
    }

    @Override
    public DynamicFieldBuilder withMapSelectDTO() {
        return super.withMapSelectDTO();
    }

    @Override
    public DynamicFieldBuilder withSqlHelperSelectDTO() {
        return super.withSqlHelperSelectDTO();
    }

    @Override
    public DynamicFieldBuilder extraFieldSuffixBuilder(Consumer<ExtraFieldSuffixBuilder> builderConsumer) {
        return super.extraFieldSuffixBuilder(builderConsumer);
    }

    @Override
    public DynamicFieldBuilder extraFieldGenerateStrategy(ExtraFieldGenerateStrategy strategy) {
        return super.extraFieldGenerateStrategy(strategy);
    }
}


