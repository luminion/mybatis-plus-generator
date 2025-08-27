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

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.po.ClassPayload;
import io.github.bootystar.mybatisplus.generator.config.po.MethodPayload;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import io.github.bootystar.mybatisplus.generator.util.ClassUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 控制器属性配置
 *
 * @author nieqiurong 2020/10/11.
 * @since 3.5.0
 */
@Slf4j
public class ControllerConfig implements ITemplate {

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
     * 请求基础url
     */
    protected String baseUrl;

    /**
     * 跨域注解
     */
    protected boolean crossOrigins;

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
        data.put("controllerMappingHyphen", StringUtils.camelToHyphen(tableInfo.getEntityPath()));
        data.put("controllerMappingHyphenStyle", this.hyphenStyle);
        data.put("restControllerStyle", this.restController);
        data.put("superControllerClassPackage", StringUtils.isBlank(superClass) ? null : superClass);
        data.put("superControllerClass", ClassUtils.getSimpleName(this.superClass));
//        Configurer configAdapter = tableInfo.getConfigAdapter();
//        EntityConfig entityConfig = configAdapter.getEntityConfig();
//        StrategyConfig strategyConfig = configAdapter.getStrategyConfig();
//        ServiceConfig serviceConfig = configAdapter.getServiceConfig();
//        PackageConfig packageConfig = configAdapter.getPackageConfig();
//        GlobalConfig globalConfig = configAdapter.getGlobalConfig();
//
//        Collection<String> importControllerFrameworkPackages = new TreeSet<>();
//        Collection<String> importControllerJavaPackages = new TreeSet<>();
//        data.put("importFrameworkPackages", importControllerFrameworkPackages);
//        data.put("importJavaPackages", importControllerJavaPackages);
//        if (!StringUtils.isBlank(superClass)) {
//            importControllerFrameworkPackages.add(superClass);
//        }
//        if (globalConfig.isSpringdoc()) {
//            importControllerFrameworkPackages.add("io.swagger.v3.oas.annotations.tags.Tag");
//            importControllerFrameworkPackages.add("io.swagger.v3.oas.annotations.*");
//        } else if (globalConfig.isSwagger()) {
//            importControllerFrameworkPackages.add("io.swagger.annotations.Api");
//            importControllerFrameworkPackages.add("io.swagger.annotations.ApiOperation");
//        }
//        if (!restController) {
//            importControllerFrameworkPackages.add("org.springframework.stereotype.Controller");
//        }
//        if (serviceConfig.generateService) {
//            importControllerFrameworkPackages.add(configAdapter.getPackageInfo().get(ConstVal.SERVICE) + "." + tableInfo.getServiceName());
//        } else {
//            importControllerFrameworkPackages.add(packageConfig.getPackageInfo().get(ConstVal.SERVICE_IMPL) + "." + tableInfo.getServiceImplName());
//        }
//        if (returnMethod.isRegistered()) {
//            importControllerFrameworkPackages.add(returnMethod.getClassFullName());
//        }
//
//        String requestBaseUrl = Stream.of(this.baseUrl,
//                        packageConfig.getModuleName(),
//                        this.hyphenStyle ? StringUtils.camelToHyphen(tableInfo.getEntityPath()) : tableInfo.getEntityPath()
//                ).filter(StringUtils::isNotBlank)
//                .collect(Collectors.joining("/"));
//        data.put("requestBaseUrl", requestBaseUrl);
//        String requestBodyStr = "@RequestBody ";
//        String optionalBodyStr = postQuery ? "@RequestBody(required = false) " : "";
//        String validatedStr = globalConfig.isValidated() ? "@Validated " : "";
//        data.put("requestBodyStr", requestBodyStr);
//        data.put("optionalBodyStr", optionalBodyStr);
//        data.put("validatedStr", validatedStr);
//        for (TableField field : tableInfo.getFields()) {
//            if (field.isKeyFlag()) {
//                data.put("primaryKeyPropertyType", field.getPropertyType());
//                break;
//            }
//        }
//        if (globalConfig.isGenerateQuery() || globalConfig.isGenerateExport()) {
//            importControllerFrameworkPackages.add(configAdapter.getPackageInfo().get(ConstVal.QUERY_VO) + "." + tableInfo.getEntityVOName());
//            String entityQueryDTOStr = tableInfo.getEntityQueryDTOName();
//            if (this.queryDTO != null && this.queryDTO.isRegistered()) {
//                if (Map.class.isAssignableFrom(this.queryDTO.getClazz()) && !postQuery) {
//                    entityQueryDTOStr = "@RequestParam " + entityQueryDTOStr;
//                    importControllerJavaPackages.add(queryDTO.getClassFullName());
//                } else {
//                    importControllerFrameworkPackages.add(queryDTO.getClassFullName());
//                }
//            } else {
//                importControllerFrameworkPackages.add(configAdapter.getPackageInfo().get(ConstVal.QUERY_DTO) + "." + tableInfo.getEntityQueryDTOName());
//            }
//            data.put("entityQueryDTOStr", entityQueryDTOStr);
//          
//
//
//            if (this.pathVariable) {
//                data.put("pagePathParams", "/{current}/{size}");
//                data.put("pageMethodParams", "@PathVariable(\"current\") Long current, @PathVariable(\"size\") Long size");
//                data.put("idPathParams", "/{id}");
//                data.put("idMethodParams", "@PathVariable(\"id\") ");
//            } else {
//                data.put("pageMethodParams", "Long current, Long size");
//            }
//            importControllerJavaPackages.add("java.util.List");
//            if (pageMethod.isRegistered()) {
//                importControllerFrameworkPackages.add(pageMethod.getClassFullName());
//                data.put("pageClazz4return", pageMethod.clazz(tableInfo.getEntityVOName()));
//            } else {
//                importControllerFrameworkPackages.add("com.baomidou.mybatisplus.core.metadata.IPage");
//                data.put("pageClazz4return", "IPage<" + tableInfo.getEntityVOName() + ">");
//            }
//        }
//        if (globalConfig.isValidated() && (globalConfig.isGenerateInsert() || globalConfig.isGenerateUpdate())) {
//            importControllerFrameworkPackages.add("org.springframework.validation.annotation.Validated");
//        }
//        if (globalConfig.isGenerateInsert()) {
//            importControllerFrameworkPackages.add(configAdapter.getPackageInfo().get(ConstVal.CREATE_DTO) + "." + tableInfo.getEntityInsertDTOName());
//        }
//        if (globalConfig.isGenerateUpdate()) {
//            importControllerFrameworkPackages.add(configAdapter.getPackageInfo().get(ConstVal.UPDATE_DTO) + "." + tableInfo.getEntityUpdateDTOName());
//        }
//
//        if (globalConfig.generateImport || globalConfig.generateExport) {
//            if (globalConfig.generateImport) {
//                importControllerJavaPackages.add("org.springframework.web.multipart.MultipartFile");
//            }
//            importControllerJavaPackages.add("java.io.IOException");
//            String pkg = globalConfig.resolveJavaApiPackage("servlet.http.HttpServletResponse");
//            if (pkg.startsWith("java")) {
//                importControllerJavaPackages.add(pkg);
//            } else {
//                importControllerFrameworkPackages.add(pkg);
//            }
//        }
//
        return data;
    }

    public Adapter adapter() {
        return new Adapter(this);
    }

    public static class Adapter {
        private final ControllerConfig config;

        public Adapter(ControllerConfig config) {
            this.config = config;
        }

        /**
         * 父类控制器
         *
         * @param clazz 父类控制器
         * @return this
         */
        public Adapter superClass(Class<?> clazz) {
            return superClass(clazz.getName());
        }

        /**
         * 父类控制器
         *
         * @param superClass 父类控制器类名
         * @return this
         */
        public Adapter superClass(String superClass) {
            this.config.superClass = superClass;
            return this;
        }

        /**
         * 关闭@RestController控制器
         *
         * @return this
         * @since 3.5.0
         */
        public Adapter disableRestController() {
            this.config.restController = false;
            return this;
        }

        /**
         * 关闭驼峰转连字符
         *
         * @return this
         * @since 3.5.0
         */
        public Adapter disableHyphenStyle() {
            this.config.hyphenStyle = false;
            return this;
        }

        /**
         * controller请求前缀
         *
         * @param url url
         * @return this
         */
        public Adapter baseUrl(String url) {
            if (url == null || url.isEmpty()) {
                this.config.baseUrl = null;
                return this;
            }
            if (!url.startsWith("/")) {
                url = "/" + url;
            }
            if (url.endsWith("/")) {
                url = url.substring(0, url.length() - 1);
            }
            this.config.baseUrl = url;
            return this;
        }

        /**
         * 跨域注解
         *
         * @return this
         */
        public Adapter enableCrossOrigins() {
            this.config.crossOrigins = true;
            return this;
        }

        /**
         * 复杂参数查询不再使用post, 而是使用get
         *
         * @return this
         */
        public Adapter disablePostQuery() {
            this.config.postQuery = false;
            return this;
        }

        /**
         * 增删查改使用restful风格
         *
         * @return this
         */
        public Adapter enableRestful() {
            this.config.restful = true;
            return this;
        }

        /**
         * 禁用路径变量
         *
         * @return this
         */
        public Adapter disablePathVariable() {
            this.config.pathVariable = false;
            return this;
        }

        /**
         * 禁用消息体接收数据
         *
         * @return this
         */
        public Adapter disableRequestBody() {
            this.config.requestBody = false;
            return this;
        }

        /**
         * 指定controller的返回结果包装类及方法
         *
         * @param methodReference 方法引用
         * @return this
         */
        public <R> Adapter returnMethod(com.baomidou.mybatisplus.core.toolkit.support.SFunction<Object, R> methodReference) {
            this.config.returnMethod = io.github.bootystar.mybatisplus.generator.util.ReflectUtil.lambdaMethodInfo(methodReference, Object.class);
            return this;
        }

        /**
         * 指定controller返回的分页包装类及方法
         *
         * @param methodReference 方法参考
         * @return this
         */
        public <O, R> Adapter pageMethod(com.baomidou.mybatisplus.core.toolkit.support.SFunction<com.baomidou.mybatisplus.core.metadata.IPage<O>, R> methodReference) {
            this.config.pageMethod = io.github.bootystar.mybatisplus.generator.util.ReflectUtil.lambdaMethodInfo(methodReference, com.baomidou.mybatisplus.core.metadata.IPage.class);
            return this;
        }

        /**
         * 指定查询的DTO
         *
         * @return this
         */
        public Adapter queryDTO(Class<?> queryDTO) {
            this.config.queryDTO = new io.github.bootystar.mybatisplus.generator.config.po.ClassPayload(queryDTO);
            return this;
        }
    }
}