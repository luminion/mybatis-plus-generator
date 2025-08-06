package io.github.bootystar.mybatisplus.generator.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class StrategyConfig {

    private final Entity.Builder entityBuilder;
    private final Controller.Builder controllerBuilder;
    private final Mapper.Builder mapperBuilder;
    private final Service.Builder serviceBuilder;

    private StrategyConfig(Builder builder) {
        this.entityBuilder = builder.entityBuilder;
        this.controllerBuilder = builder.controllerBuilder;
        this.mapperBuilder = builder.mapperBuilder;
        this.serviceBuilder = builder.serviceBuilder;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Entity.Builder entityBuilder = new Entity.Builder();
        private final Controller.Builder controllerBuilder = new Controller.Builder();
        private final Mapper.Builder mapperBuilder = new Mapper.Builder();
        private final Service.Builder serviceBuilder = new Service.Builder();

        public Builder() {
        }

        public Builder entity(Consumer<Entity.Builder> consumer) {
            consumer.accept(this.entityBuilder);
            return this;
        }

        public Builder controller(Consumer<Controller.Builder> consumer) {
            consumer.accept(this.controllerBuilder);
            return this;
        }

        public Builder mapper(Consumer<Mapper.Builder> consumer) {
            consumer.accept(this.mapperBuilder);
            return this;
        }

        public Builder service(Consumer<Service.Builder> consumer) {
            consumer.accept(this.serviceBuilder);
            return this;
        }

        public StrategyConfig build() {
            return new StrategyConfig(this);
        }
    }
}