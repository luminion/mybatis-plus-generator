package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.enhancer.builder.ExtraFieldSuffixBuilder;
import io.github.bootystar.mybatisplus.enhancer.helper.SqlHelper;
import io.github.bootystar.mybatisplus.generator.strategy.ExtraFieldGenerateStrategy;
import io.github.bootystar.mybatisplus.generator.info.ClassInfo;

import java.util.Map;
import java.util.function.Consumer;

/**
 * 将非通用配置作为protected寄存
 * 规范子类的重写行为
 * @author bootystar
 */
public abstract class BaseEnhanceBuilder<B extends BaseEnhanceBuilder<B>> extends BaseBuilder<B> {

    /**
     * 不生成重写的方法
     *
     * @return this
     */
    protected B disableOverrideMethods() {
        this.overrideMethods = false;
        return this.getBuilder();
    }

    /**
     * 使用Map作为查询方法入参DTO
     *
     * @return this
     */
    protected B withMapSelectDTO() {
        this.selectDTO = new ClassInfo(Map.class);
        return this.getBuilder();
    }

    /**
     * 使用SqlHelper作为查询方法入参DTO
     *
     * @return this
     */
    protected B withSqlHelperSelectDTO() {
        this.selectDTO = new ClassInfo(SqlHelper.class);
        return this.getBuilder();
    }

    /**
     * 额外字段后缀构造器
     *
     * @param builderConsumer builder消费者
     * @return this
     */
    protected B extraFieldSuffixBuilder(Consumer<ExtraFieldSuffixBuilder> builderConsumer) {
        builderConsumer.accept(this.extraFieldSuffixBuilder);
        return this.getBuilder();
    }

    /**
     * 额外字段策略
     *
     * @param strategy 策略
     * @return this
     */
    protected B extraFieldGenerateStrategy(ExtraFieldGenerateStrategy strategy) {
        this.extraFieldStrategy = strategy;
        return this.getBuilder();
    }

    /**
     * 生成额外代码时,使用实际类型作为Service方法参数
     *
     * @return {@link B }
     */
    protected B enableActualType4ServiceMethodParam(){
        this.actualType4ServiceMethodParam = true;
        return this.getBuilder();
    }

}
