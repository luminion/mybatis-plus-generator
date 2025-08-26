package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.InjectionConfig;
import io.github.bootystar.mybatisplus.generator.config.po.CustomFile;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author bootystar
 */
public class Injection extends InjectionConfig {
    public static class Builder {
        protected final Injection config = new Injection();

        protected Builder() {
        }

        protected InjectionConfig build() {
            return this.config;
        }

        /**
         * 输出文件之前消费者
         *
         * @param biConsumer 消费者
         * @return this
         */
        public Builder beforeOutputFile(BiConsumer<TableInfo, Map<String, Object>> biConsumer) {
            this.config.beforeOutputFileBiConsumer = biConsumer;
            return this;
        }

        /**
         * 自定义配置 Map 对象
         *
         * @param customMap Map 对象
         * @return this
         */
        public Builder customMap(Map<String, Object> customMap) {
            this.config.customMap = customMap;
            return this;
        }

        public Builder customFile(CustomFile customFile) {
            this.config.customFiles.add(customFile);
            return this;
        }

        public Builder customFile(List<CustomFile> customFiles) {
            this.config.customFiles.addAll(customFiles);
            return this;
        }
    }
}
