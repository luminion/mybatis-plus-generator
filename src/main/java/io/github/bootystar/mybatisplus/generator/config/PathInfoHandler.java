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
package io.github.bootystar.mybatisplus.generator.config;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.core.*;
import io.github.bootystar.mybatisplus.generator.config.core.ControllerConfig;
import io.github.bootystar.mybatisplus.generator.config.core.EntityConfig;
import io.github.bootystar.mybatisplus.generator.config.core.MapperConfig;
import io.github.bootystar.mybatisplus.generator.config.core.ServiceConfig;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 路径信息处理
 *
 * @author nieqiurong hubin
 * @since 2020-10-06
 * @since 3.5.0
 */
class PathInfoHandler {

    /**
     * 输出文件Map
     */
    @Getter
    private final Map<OutputFile, String> pathInfo = new HashMap<>();

    /**
     * 输出目录
     */
    private final String outputDir;

    /**
     * 包配置信息
     */
    private final PackageConfig packageConfig;

    PathInfoHandler(ConfigAdapter configAdapter) {
        this.outputDir = configAdapter.getGlobalConfig().getOutputDir();
        this.packageConfig = configAdapter.getPackageConfig();
        // 设置默认输出路径
        this.setDefaultPathInfo(configAdapter);
        // 覆盖自定义路径
        Map<OutputFile, String> pathInfo = packageConfig.getPathInfo();
        if (CollectionUtils.isNotEmpty(pathInfo)) {
            this.pathInfo.putAll(pathInfo);
        }
    }

    /**
     * 设置默认输出路径
     *
     * @param configAdapter  配置适配器
     */
    private void setDefaultPathInfo(ConfigAdapter configAdapter) {
        InjectionConfig injectionConfig = configAdapter.getInjectionConfig();
        GlobalConfig globalConfig = configAdapter.getGlobalConfig();
        EntityConfig entity = configAdapter.getEntityConfig();
        TemplateConfig templateConfig = configAdapter.getTemplateConfig();
        if (entity.isGenerate()) {
            putPathInfo(injectionConfig, globalConfig.isKotlin() ? templateConfig.getEntityKt() : templateConfig.getEntity(), OutputFile.entity, ConstVal.ENTITY);
        }
        if (globalConfig.isGenerateInsert() || globalConfig.isGenerateImport()){
            putPathInfo(injectionConfig, templateConfig.getEntityInsertDTO(), OutputFile.createDTO, ConstVal.CREATE_DTO);
        }
        if (globalConfig.isGenerateUpdate()){
            putPathInfo(injectionConfig, templateConfig.getEntityUpdateDTO(), OutputFile.updateDTO, ConstVal.UPDATE_DTO);
        }
        if (globalConfig.isGenerateQuery() || globalConfig.isGenerateExport()) {
            putPathInfo(injectionConfig, templateConfig.getEntityQueryDTO(), OutputFile.queryDTO, ConstVal.QUERY_DTO);
            putPathInfo(injectionConfig, templateConfig.getEntityVO(), OutputFile.queryVO, ConstVal.QUERY_VO);
        }
        MapperConfig mapper = configAdapter.getMapperConfig();
        if (mapper.isGenerateMapper()) {
            putPathInfo(injectionConfig, templateConfig.getMapper(), OutputFile.mapper, ConstVal.MAPPER);
        }
        if (mapper.isGenerateMapperXml()) {
            putPathInfo(injectionConfig, templateConfig.getMapperXml(), OutputFile.mapperXml, ConstVal.MAPPER_XML);
        }
        ServiceConfig service = configAdapter.getServiceConfig();
        if (service.isGenerateService()) {
            putPathInfo(injectionConfig, templateConfig.getService(), OutputFile.service, ConstVal.SERVICE);
        }
        if (service.isGenerateServiceImpl()) {
            putPathInfo(injectionConfig, templateConfig.getServiceImpl(), OutputFile.serviceImpl, ConstVal.SERVICE_IMPL);
        }
        ControllerConfig controller = configAdapter.getControllerConfig();
        if (controller.isGenerate()) {
            putPathInfo(injectionConfig, templateConfig.getController(), OutputFile.controller, ConstVal.CONTROLLER);
        }
        putPathInfo(injectionConfig, OutputFile.parent, ConstVal.PARENT);
    }

    private void putPathInfo(InjectionConfig injectionConfig, String template, OutputFile outputFile, String module) {
        if (StringUtils.isNotBlank(template)) {
            putPathInfo(injectionConfig, outputFile, module);
        }
    }

    private void putPathInfo(InjectionConfig injectionConfig, OutputFile outputFile, String module) {
        pathInfo.putIfAbsent(outputFile, joinPath(outputDir, packageConfig.getPackageInfo(injectionConfig, module)));
    }

    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    private String joinPath(String parentDir, String packageName) {
        if (StringUtils.isBlank(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        return parentDir + packageName;
    }
}
