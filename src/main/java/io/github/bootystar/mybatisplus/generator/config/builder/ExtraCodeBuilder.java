package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.strategy.ExtraFieldGenerateStrategy;

import java.util.Map;

/**
 * 默认配置类
 *
 * @author bootystar
 */
public class ExtraCodeBuilder extends BaseEnhanceBuilder<ExtraCodeBuilder> {

    @Override
    public ExtraCodeBuilder extraFieldSuffixMap(Map<String, String> extraFieldSuffixMap) {
        return super.extraFieldSuffixMap(extraFieldSuffixMap);
    }

    @Override
    public ExtraCodeBuilder extraFieldGenerateStrategy(ExtraFieldGenerateStrategy strategy) {
        return super.extraFieldGenerateStrategy(strategy);
    }

    @Override
    public ExtraCodeBuilder enableActualType4ServiceMethodParam(){
        return super.enableActualType4ServiceMethodParam();
    }

    /**
     * 使用Map作为查询方法入参DTO
     * @deprecated 不建议, 使用该方式时, like后缀查询需要值手动添加前后%, 否则结果会和等于查询一致
     * @return {@link ExtraCodeBuilder }
     */
    @Override
    @Deprecated
    public ExtraCodeBuilder withMapSelectDTO() {
        return super.withMapSelectDTO();
    }
}
