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

import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.function.ConverterFileName;
import io.github.bootystar.mybatisplus.generator.util.ClassUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Service属性配置
 *
 * @author nieqiurong 2020/10/11.
 * @since 3.5.0
 */
@Slf4j
@Getter
public class ServiceConfig implements ITemplate {


    protected ServiceConfig() {
    }

    /**
     * 是否生成service
     *
     * @since 3.5.6
     */
    protected boolean generateService = true;

    /**
     * 是否生成serviceImpl
     *
     * @since 3.5.6
     */
    protected boolean generateServiceImpl = true;

    /**
     * 自定义继承的Service类全称，带包名
     */
    protected String superServiceClass = ConstVal.SUPER_SERVICE_CLASS;

    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    protected String superServiceImplClass = ConstVal.SUPER_SERVICE_IMPL_CLASS;

    /**
     * 转换输出Service文件名称
     *
     * @since 3.5.0
     */
    protected ConverterFileName converterServiceFileName = (entityName -> "I" + entityName + ConstVal.SERVICE);

    /**
     * 转换输出ServiceImpl文件名称
     *
     * @since 3.5.0
     */
    protected ConverterFileName converterServiceImplFileName = (entityName -> entityName + ConstVal.SERVICE_IMPL);

    /**
     * 是否覆盖已有文件（默认 false）
     *
     * @since 3.5.2
     */
    protected boolean fileOverride;

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("superServiceClassPackage", this.superServiceClass);
        data.put("superServiceClass", ClassUtils.getSimpleName(this.superServiceClass));
        data.put("superServiceImplClassPackage", this.superServiceImplClass);
        data.put("superServiceImplClass", ClassUtils.getSimpleName(this.superServiceImplClass));
        data.put("generateServiceImpl", this.generateServiceImpl);
        data.put("generateService", this.generateService);
        return data;
    }

}
