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
import java.util.stream.Collectors;

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
        if (globalConfig.isEnhancer()) {
            data.put("enhanceMethod", "EnhancedService.super");
        }
        Set<String> importServiceImplPackages = this.importServiceImplPackages(tableInfo);
        Collection<String> importServiceImplJavaPackages = importServiceImplPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        Collection<String> importServiceImplFrameworkPackages = importServiceImplPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importServiceImplJavaPackages", importServiceImplJavaPackages);
        data.put("importServiceImplFrameworkPackages", importServiceImplFrameworkPackages);

        return data;
    }


    private Set<String> importServiceImplPackages(TableInfo tableInfo) {
        Set<String> importPackages = new TreeSet<>();
        Configurer configurer = tableInfo.getConfigurer();
        GlobalConfig globalConfig = configurer.getGlobalConfig();
        OutputConfig outputConfig = configurer.getOutputConfig();
        Map<String, String> classCanonicalNameMap = outputConfig.getOutputClassCanonicalName(tableInfo);
        importPackages.add(classCanonicalNameMap.get(OutputFile.entity.name()));
        importPackages.add(classCanonicalNameMap.get(OutputFile.mapper.name()));
        importPackages.add(this.superServiceImplClass);
        importPackages.add("org.springframework.stereotype.Service");
        if (outputConfig.getService().isGenerate()) {
            importPackages.add(classCanonicalNameMap.get(OutputFile.service.name()));
        }
        // 生成项
        if (globalConfig.isGenerateQuery()) {
            importPackages.add("java.util.List");
            importPackages.add("java.io.Serializable");
            importPackages.add("com.baomidou.mybatisplus.core.metadata.IPage");
            importPackages.add(classCanonicalNameMap.get(OutputFile.queryVO.name()));
        }
        if (globalConfig.isGenerateExport()) {
            importPackages.add("java.io.OutputStream");
        }
        if (globalConfig.isGenerateImport()) {
            importPackages.add("java.io.InputStream");
            importPackages.add("java.io.OutputStream");
        }
        if (globalConfig.isGenerateDelete()) {
            importPackages.add("java.io.Serializable");
        }
        if (globalConfig.isEnhancer()) {
            importPackages.add("io.github.bootystar.mybatisplus.enhancer.EnhancedService");
            importPackages.add(classCanonicalNameMap.get(OutputFile.queryVO.name()));
        } else {
            if (globalConfig.isGenerateQuery()) {
//                importPackages.add("com.baomidou.mybatisplus.core.metadata.TableInfo");
                importPackages.add("com.baomidou.mybatisplus.core.metadata.TableInfoHelper");
                importPackages.add("java.util.HashMap");
                importPackages.add("com.baomidou.mybatisplus.extension.plugins.pagination.Page");
                importPackages.add("org.apache.ibatis.exceptions.TooManyResultsException");
            }
            if (globalConfig.isGenerateExport()) {
                importPackages.add(globalConfig.resolveExcelClassApiCanonicalName());
                importPackages.add(globalConfig.resolveExcelClassCanonicalName("write.style.column.LongestMatchColumnWidthStyleStrategy"));
            }
            if (globalConfig.isGenerateImport()){
                importPackages.add(globalConfig.resolveExcelClassApiCanonicalName());
                importPackages.add("org.springframework.beans.BeanUtils");
            }
            
            if (globalConfig.isGenerateInsert()){
                importPackages.add("com.baomidou.mybatisplus.core.metadata.TableInfo");
                importPackages.add("com.baomidou.mybatisplus.core.metadata.TableInfoHelper");
                importPackages.add("org.springframework.beans.BeanUtils");
            }
            if (globalConfig.isGenerateUpdate()){
                importPackages.add("org.springframework.beans.BeanUtils");
            }
            
            
            
            
            
            if (globalConfig.isGenerateInsert() || globalConfig.isGenerateUpdate() || globalConfig.isGenerateImport()) {
                importPackages.add("org.springframework.beans.BeanUtils");
            }
            if (globalConfig.isGenerateImport() || globalConfig.isGenerateExport()) {
                String excelApi = globalConfig.resolveExcelClassApiCanonicalName();
                importPackages.add(excelApi);
            }
            if (globalConfig.isGenerateInsert() || globalConfig.isGenerateQuery()) {
                importPackages.add("com.baomidou.mybatisplus.core.metadata.TableInfo");
                importPackages.add("com.baomidou.mybatisplus.core.metadata.TableInfoHelper");
            }
            if (globalConfig.isGenerateQuery() || globalConfig.isGenerateExport()) {
                importPackages.add("com.baomidou.mybatisplus.extension.plugins.pagination.Page");
            }

            if (globalConfig.isGenerateImport()) {
                importPackages.add("java.util.stream.Collectors");
            }
            if (globalConfig.isGenerateExport()) {
                String longestMatchColumnWidthStyleStrategy = globalConfig.resolveExcelClassCanonicalName("write.style.column.LongestMatchColumnWidthStyleStrategy");
                importPackages.add(longestMatchColumnWidthStyleStrategy);
            }
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