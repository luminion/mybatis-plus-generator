package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.enhancer.builder.ExtraFieldSuffixBuilder;
import io.github.bootystar.mybatisplus.generator.strategy.ExtraFieldGenerateStrategy;

import java.util.function.Consumer;

/**
 * 默认配置类
 *
 * @author bootystar
 */
public class ExtraCodeBuilder extends BaseEnhanceBuilder<ExtraCodeBuilder> {

    @Override
    public ExtraCodeBuilder extraFieldSuffixBuilder(Consumer<ExtraFieldSuffixBuilder> builderConsumer) {
        return super.extraFieldSuffixBuilder(builderConsumer);
    }

    @Override
    public ExtraCodeBuilder extraFieldGenerateStrategy(ExtraFieldGenerateStrategy strategy) {
        return super.extraFieldGenerateStrategy(strategy);
    }

    @Override
    public ExtraCodeBuilder enableActualType4ServiceMethodParam(){
        return super.enableActualType4ServiceMethodParam();
    }

    @Override
    public ExtraCodeBuilder withMapSelectDTO() {
        return super.withMapSelectDTO();
    }
}
