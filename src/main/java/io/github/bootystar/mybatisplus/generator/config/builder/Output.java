package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.OutputConfig;

/**
 * @author bootystar
 */
public class Output extends OutputConfig {
    public static class Builder {
        protected final Output config = new Output();

        protected Builder() {
        }

        protected OutputConfig build() {
            return this.config;
        }
    }
}
