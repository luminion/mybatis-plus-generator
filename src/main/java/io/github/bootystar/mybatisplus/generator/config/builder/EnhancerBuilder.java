package io.github.bootystar.mybatisplus.generator.config.builder;


import io.github.bootystar.mybatisplus.generator.strategy.ExtraFieldGenerateStrategy;
import lombok.Getter;

import java.util.Map;


/**
 * 动态字段生成器
 *
 * @author bootystar
 */
@Getter
public class EnhancerBuilder extends BaseEnhanceBuilder<EnhancerBuilder> {

    {
//        mapperDTO = new ClassInfo(UnmodifiableSqlHelper.class);
//        dynamicServiceClassInfo = new ClassInfo(DynamicFieldService.class);
    }

    @Override
    public EnhancerBuilder disableOverrideMethods() {
        return super.disableOverrideMethods();
    }

    @Override
    public EnhancerBuilder withMapSelectDTO() {
        return super.withMapSelectDTO();
    }

    @Override
    public EnhancerBuilder withSqlHelperSelectDTO() {
        return super.withSqlHelperSelectDTO();
    }

    @Override
    public EnhancerBuilder extraFieldSuffixMap(Map<String,String> extraFieldSuffixMap) {
        return super.extraFieldSuffixMap(extraFieldSuffixMap);
    }

    @Override
    public EnhancerBuilder extraFieldGenerateStrategy(ExtraFieldGenerateStrategy strategy) {
        return super.extraFieldGenerateStrategy(strategy);
    }
}


