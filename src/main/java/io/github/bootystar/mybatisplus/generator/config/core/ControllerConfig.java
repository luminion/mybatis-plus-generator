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

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.ConfigAdapter;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.po.ClassPayload;
import io.github.bootystar.mybatisplus.generator.config.po.MethodPayload;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.function.ConverterFileName;
import io.github.bootystar.mybatisplus.generator.util.ClassUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 控制器属性配置
 *
 * @author nieqiurong 2020/10/11.
 * @since 3.5.0
 */
@Slf4j
@Getter
public class ControllerConfig implements ITemplate {


    protected ControllerConfig() {
    }

    /**
     * 自定义继承的Controller类全称，带包名
     */
    protected String superClass;

    /**
     * 生成 <code>@RestController</code> 控制器
     * <pre>
     *      <code>@ControllerConfig</code>
     *      ->
     *      <code>@RestController</code>
     * </pre>
     */
    protected boolean restController = true;

    /**
     * 驼峰转连字符
     * <pre>
     *      <code>@RequestMapping("/managerUserActionHistory")</code> -> <code>@RequestMapping("/manager-user-action-history")</code>
     * </pre>
     */
    protected boolean hyphenStyle = true;

    /**
     * 转换输出控制器文件名称
     *
     * @since 3.5.0
     */
    protected ConverterFileName converterFileName = (entityName -> entityName + ConstVal.CONTROLLER);

    /**
     * 是否覆盖已有文件（默认 false）
     *
     * @since 3.5.2
     */
    protected boolean fileOverride;

    /**
     * 是否生成
     *
     * @since 3.5.6
     */
    protected boolean generate = true;

    /**
     * 模板路径
     *
     * @since 3.5.6
     */
    protected String templatePath = ConstVal.TEMPLATE_CONTROLLER;

    // =============自定义项==============

    /**
     * 请求基础url
     */
    protected String baseUrl;

    /**
     * 跨域注解
     */
    protected boolean crossOrigins;

    /**
     * 使用@AutoWired替换@Resource
     */
    protected boolean autoWired;

    /**
     * restful样式
     */
    protected boolean restful;

    /**
     * 请求路径参数
     */
    protected boolean pathVariable = true;

    /**
     * controller是否使用@RequestBody注解
     */
    protected boolean requestBody = true;

    /**
     * 复杂查询使用post请求
     */
    protected boolean postQuery = true;

    /**
     * get请求是否使用@RequestBody注解
     */
    protected boolean queryRequestBody = true;

    /**
     * 返回结果方法
     */
    protected MethodPayload returnMethod = new MethodPayload();

    /**
     * 分页结果方法
     */
    protected MethodPayload pageMethod = new MethodPayload();

    /**
     * 指定查询的DTO
     */
    protected ClassPayload queryDTO = new ClassPayload();

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = ITemplate.super.renderData(tableInfo);
//        data.put("controllerMappingHyphen", StringUtils.camelToHyphen(tableInfo.getEntityPath()));
//        data.put("controllerMappingHyphenStyle", this.hyphenStyle);
        data.put("restControllerStyle", this.restController);
//        data.put("superControllerClassPackage", StringUtils.isBlank(superClass) ? null : superClass);
        data.put("superControllerClass", ClassUtils.getSimpleName(this.superClass));
        ConfigAdapter configAdapter = tableInfo.getConfigAdapter();
        EntityConfig entityConfig = configAdapter.getEntityConfig();
        StrategyConfig strategyConfig = configAdapter.getStrategyConfig();
        ServiceConfig serviceConfig = configAdapter.getServiceConfig();
        PackageConfig packageConfig = configAdapter.getPackageConfig();
        GlobalConfig globalConfig = configAdapter.getGlobalConfig();

        Collection<String> importFrameworkPackages = new TreeSet<>();
        Collection<String> importJavaPackages = new TreeSet<>();
        data.put("importFrameworkPackages",importFrameworkPackages);
        data.put("importJavaPackages",importJavaPackages);
        if (!StringUtils.isBlank(superClass)) {
            importFrameworkPackages.add(superClass);
        }
        if (globalConfig.isSpringdoc()){
            importFrameworkPackages.add("io.swagger.v3.oas.annotations.tags.Tag");
            importFrameworkPackages.add("io.swagger.v3.oas.annotations.*");
        }else if (globalConfig.isSwagger()){
            importFrameworkPackages.add("io.swagger.annotations.Api");
            importFrameworkPackages.add("io.swagger.annotations.ApiOperation");
        }
        if (autoWired){
            importFrameworkPackages.add("org.springframework.beans.factory.annotation.Autowired");
        }else{
            String pkg = strategyConfig.getJavaApiPackage("annotation.Resource");
            if (pkg.startsWith("java")){
                importJavaPackages.add(pkg);
            }else {
                importFrameworkPackages.add(pkg);
            }
        }
        if (!restController){
            importFrameworkPackages.add("org.springframework.stereotype.Controller");
        }
        if (serviceConfig.generateService){
            importFrameworkPackages.add(packageConfig.getService());
        }else{
            importFrameworkPackages.add(packageConfig.getServiceImpl());
        }
        if (returnMethod.isRegistered()){
            importFrameworkPackages.add(returnMethod.getClassFullName());
        }
        
        String requestBaseUrl = Stream.of(this.baseUrl,
                        packageConfig.getModuleName(),
                        this.hyphenStyle ? StringUtils.camelToHyphen(tableInfo.getEntityPath()):tableInfo.getEntityPath()
                ).filter(StringUtils::isNotBlank)
                .collect(Collectors.joining("/"));
        data.put("requestBaseUrl", requestBaseUrl);
        String requestBodyStr = "@RequestBody ";
        String optionalBodyStr = postQuery ? "@RequestBody(required = false) ":"";
        String validatedStr = strategyConfig.isValidated() ? "@Validated ":"";
        data.put("requestBodyStr", requestBodyStr);
        data.put("optionalBodyStr", optionalBodyStr);
        data.put("validatedStr", validatedStr);
        for (TableField field : tableInfo.getFields()) {
            if (field.isKeyFlag()){
                data.put("primaryKeyPropertyType", field.getPropertyType());
                break;
            }
        }
        
        if (this.queryDTO == null) {
            this.queryDTO = entityConfig.getQueryDTO();
        }
        if (strategyConfig.isGenerateSelect() || strategyConfig.isGenerateExport()){
            importFrameworkPackages.add(entityConfig.getViewObject().getClassFullName());
            String entityQueryDTOStr = this.queryDTO.getClazz().getSimpleName();
            if (Map.class.isAssignableFrom(this.queryDTO.getClazz()) && !postQuery) {
                entityQueryDTOStr = "@RequestParam " + entityQueryDTOStr;
                importJavaPackages.add(queryDTO.getClassFullName());
            } else {
                importFrameworkPackages.add(queryDTO.getClassFullName());
            }
            data.put("entityQueryDTOStr", entityQueryDTOStr);
            if (this.pathVariable){
                data.put("pagePathParams","/{current}/{size}");
                data.put("pageMethodParams","@PathVariable(\"current\") Long current, @PathVariable(\"size\") Long size");
                data.put("idPathParams","/{id}");
                data.put("idMethodParams","@PathVariable(\"id\") ");
            }else{
                data.put("pageMethodParams","Long current, Long size");
            }
            importJavaPackages.add("java.util.List");
            if (pageMethod.isRegistered()){
                importFrameworkPackages.add(pageMethod.getClassFullName());
                data.put("pageClazz4return",pageMethod.classReturnGenericTypeStr(entityConfig.getViewObject().getClassSimpleName()));
            } else {
                importFrameworkPackages.add("com.baomidou.mybatisplus.core.metadata.IPage");
                data.put("pageClazz4return", "IPage<" + entityConfig.getViewObject().getClassSimpleName() + ">");
            }
        }
        if (strategyConfig.isValidated() && (strategyConfig.isGenerateInsert() || strategyConfig.isGenerateUpdate())){
            importFrameworkPackages.add("org.springframework.validation.annotation.Validated");
        }
        if(strategyConfig.isGenerateInsert()){
            ClassPayload insertDTO = entityConfig.getInsertDTO();
            if (insertDTO.isRegistered()) {
                importFrameworkPackages.add(insertDTO.getClassFullName());
            }
        }
        if (strategyConfig.isGenerateUpdate()){
            ClassPayload updateDTO = entityConfig.getUpdateDTO();
            if (updateDTO.isRegistered()){
                importFrameworkPackages.add(updateDTO.getClassFullName());
            }
        }
      
        if (strategyConfig.generateImport || strategyConfig.generateExport){
            if (strategyConfig.generateImport){
                importJavaPackages.add("org.springframework.web.multipart.MultipartFile");    
            }
            importJavaPackages.add("java.io.IOException");
            String pkg = strategyConfig.getJavaApiPackage("servlet.http.HttpServletResponse");
            if (pkg.startsWith("java")){
                importJavaPackages.add(pkg);
            }else {
                importFrameworkPackages.add(pkg);
            }
            
        }

        return data;
    }

}

