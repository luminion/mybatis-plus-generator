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
package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.core.*;


/**
 * 配置构建
 *
 * @author nieqiurong 2020/10/11.
 * @since 3.5.0
 */
public class BaseBuilder implements IConfigBuilder<StrategyConfig> {

    private final StrategyConfig strategyConfig;

    public BaseBuilder(StrategyConfig strategyConfig) {
        this.strategyConfig = strategyConfig;
    }

    public Entity.Builder entityBuilder() {
        return strategyConfig.entityBuilder();
    }

    public Controller.Builder controllerBuilder() {
        return strategyConfig.controllerBuilder();
    }

    public Mapper.Builder mapperBuilder() {
        return strategyConfig.mapperBuilder();
    }

    public Service.Builder serviceBuilder() {
        return strategyConfig.serviceBuilder();
    }

    @Override
    public StrategyConfig build() {
        this.strategyConfig.validate();
        return this.strategyConfig;
    }
}
