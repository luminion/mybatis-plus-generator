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
package io.github.bootystar.mybatisplus.generator.config.support;

import io.github.bootystar.mybatisplus.generator.config.Configurer;
import io.github.bootystar.mybatisplus.generator.config.enums.OutputFile;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import io.github.bootystar.mybatisplus.generator.util.ClassUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Service属性配置
 *
 * @author nieqiurong 2020/10/11.
 * @since 3.5.0
 */
@Slf4j
public class ServiceConfig implements ITemplate {


    /**
     * 自定义继承的Service类全称，带包名
     */
    protected String superServiceClass = "com.baomidou.mybatisplus.extension.service.IService";

    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    protected String superServiceImplClass = "com.baomidou.mybatisplus.extension.service.impl.ServiceImpl";

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("superServiceClassPackage", this.superServiceClass);
        data.put("superServiceClass", ClassUtils.getSimpleName(this.superServiceClass));
        data.put("superServiceImplClassPackage", this.superServiceImplClass);
        data.put("superServiceImplClass", ClassUtils.getSimpleName(this.superServiceImplClass));
        Configurer configurer = tableInfo.getConfigurer();
        GlobalConfig globalConfig = configurer.getGlobalConfig();
        if (globalConfig.isEnhancer()){
            data.put("enhanceMethod", "EnhancedService.super");
        }

        return data;
    }
    
    
    private Set<String> importServicePackages(TableInfo tableInfo) {
        Set<String> importPackages = new TreeSet<>();
        Configurer configurer = tableInfo.getConfigurer();
        GlobalConfig globalConfig = configurer.getGlobalConfig();
        OutputConfig outputConfig = configurer.getOutputConfig();
        importPackages.add(this.superServiceImplClass);
        importPackages.add("org.springframework.stereotype.Service");
        Map<String, String> classCanonicalNameMap = outputConfig.getClassCanonicalName(tableInfo);
        if (outputConfig.getService().isGenerate()){
            importPackages.add(classCanonicalNameMap.get(OutputFile.service.name()));
        }
        if (globalConfig.isEnhancer()){
            importPackages.add("io.github.bootystar.mybatisplus.enhancer.EnhancedService");
        }
        
        


        return importPackages;
    }

    public Adapter adapter() {
        return new Adapter();
    }

    public static class Adapter {
        private final ServiceConfig config;

        public Adapter() {
            this.config = new ServiceConfig();
        }

        /**
         * Service接口父类
         *
         * @param clazz 类
         * @return this
         */
        public Adapter superServiceClass(Class<?> clazz) {
            return superServiceClass(clazz.getName());
        }

        /**
         * Service接口父类
         *
         * @param superServiceClass 类名
         * @return this
         */
        public Adapter superServiceClass(String superServiceClass) {
            this.config.superServiceClass = superServiceClass;
            return this;
        }

        /**
         * Service实现类父类
         *
         * @param clazz 类
         * @return this
         */
        public Adapter superServiceImplClass(Class<?> clazz) {
            return superServiceImplClass(clazz.getName());
        }

        /**
         * Service实现类父类
         *
         * @param superServiceImplClass 类名
         * @return this
         */
        public Adapter superServiceImplClass(String superServiceImplClass) {
            this.config.superServiceImplClass = superServiceImplClass;
            return this;
        }
    }

}