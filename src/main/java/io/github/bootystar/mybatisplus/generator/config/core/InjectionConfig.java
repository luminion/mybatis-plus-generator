/*
 * Copyright (c) 2011-2025, baomidou (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.bootystar.mybatisplus.generator.config.core;

import io.github.bootystar.mybatisplus.generator.ITemplate;
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.builder.CustomFile;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 注入配置
 *
 * @author hubin
 * @since 2016-12-07
 */
@Slf4j
@Getter
public class InjectionConfig implements ITemplate {

    /**
     * 输出文件之前消费者
     */
    protected BiConsumer<TableInfo, Map<String, Object>> beforeOutputFileBiConsumer;

    /**
     * 自定义配置 Map 对象
     * -- GETTER --
     *  获取自定义配置 Map 对象
     */
    protected Map<String, Object> customMap = new HashMap<>();

    /**
     * 自定义模板文件列表
     * -- GETTER --
     *  获取自定义模板文件列表
     *  @since 3.5.3
     */
    protected final List<CustomFile> customFiles = new ArrayList<>();

    /**
     * 输出文件前
     */
    public void beforeOutputFile(TableInfo tableInfo, Map<String, Object> objectMap) {
        if (!customMap.isEmpty()) {
            objectMap.putAll(customMap);
            //增加一个兼容兼容取值,推荐还是直接取值外置key即可,例如abc取值${abc}而不需要${cfg.abc}
            objectMap.put("cfg", customMap);
        }
        if (null != beforeOutputFileBiConsumer) {
            beforeOutputFileBiConsumer.accept(tableInfo, objectMap);
        }
    }

    public static class Builder implements IConfigBuilder<InjectionConfig> {
        protected final InjectionConfig injectionConfig = new InjectionConfig();

        @Override
        public InjectionConfig build() {
            return this.injectionConfig;
        }

        /**
         * 输出文件之前消费者
         *
         * @param biConsumer 消费者
         * @return this
         */
        public Builder beforeOutputFile(BiConsumer<TableInfo, Map<String, Object>> biConsumer) {
            this.injectionConfig.beforeOutputFileBiConsumer = biConsumer;
            return this;
        }

        /**
         * 自定义配置 Map 对象
         *
         * @param customMap Map 对象
         * @return this
         */
        public Builder customMap(Map<String, Object> customMap) {
            this.injectionConfig.customMap = customMap;
            return this;
        }

        /**
         * 自定义配置模板文件
         *
         * @param customFile key为文件名称，value为文件路径
         * @return this
         */
        public Builder customFile(Map<String, String> customFile) {
            return customFile(customFile.entrySet().stream()
                    .map(e -> new CustomFile.Builder().fileName(e.getKey()).templatePath(e.getValue()).build())
                    .collect(Collectors.toList()));
        }

        public Builder customFile(CustomFile customFile) {
            this.injectionConfig.customFiles.add(customFile);
            return this;
        }

        public Builder customFile(List<CustomFile> customFiles) {
            this.injectionConfig.customFiles.addAll(customFiles);
            return this;
        }

        public Builder customFile(Consumer<CustomFile.Builder> consumer) {
            CustomFile.Builder builder = new CustomFile.Builder();
            consumer.accept(builder);
            this.injectionConfig.customFiles.add(builder.build());
            return this;
        }
    }

}
