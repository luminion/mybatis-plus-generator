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
package io.github.bootystar.mybatisplus.generator.config.core.support;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import io.github.bootystar.mybatisplus.generator.ITemplate;
import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.builder.BaseBuilder;
import io.github.bootystar.mybatisplus.generator.config.core.PackageConfig;
import io.github.bootystar.mybatisplus.generator.config.core.StrategyConfig;
import io.github.bootystar.mybatisplus.generator.config.po.ClassPayload;
import io.github.bootystar.mybatisplus.generator.config.po.MethodPayload;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.function.ConverterFileName;
import io.github.bootystar.mybatisplus.generator.util.ClassUtils;
import io.github.bootystar.mybatisplus.generator.util.ReflectUtil;
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
public class Controller implements ITemplate {


    protected Controller() {
    }

    /**
     * 自定义继承的Controller类全称，带包名
     */
    protected String superClass;
    
    /**
     * 生成 <code>@RestController</code> 控制器
     * <pre>
     *      <code>@Controller</code> 
     *      -> 
     *      <code>@RestController</code>
     * </pre>
     */
    protected boolean restController = false;

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
     * javaEE api包(jakarta或javax)
     * <p>
     * 涉及HttpServletRequest,HttpServletResponse,@Resource
     */
    protected String javaApiPackage = "jakarta";

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
    protected ClassPayload queryDTO;

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
     * 参数校验注解
     */
    protected boolean validated = true;

    /**
     * 复杂查询使用post请求
     */
    protected boolean postQuery = true;
    
    /**
     * get请求是否使用@RequestBody注解
     */
    protected boolean queryRequestBody = true;

    /**
     * 模板路径
     * @since 3.5.6
     */
    protected String templatePath = ConstVal.TEMPLATE_CONTROLLER;

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = ITemplate.super.renderData(tableInfo);
        data.put("controllerMappingHyphen", StringUtils.camelToHyphen(tableInfo.getEntityPath()));
        data.put("controllerMappingHyphenStyle", this.hyphenStyle);
        data.put("restControllerStyle", this.restController);
        data.put("superControllerClassPackage", StringUtils.isBlank(superClass) ? null : superClass);
        data.put("superControllerClass", ClassUtils.getSimpleName(this.superClass));

        // 查询DTO导入的包
        List<String> queryDTOImportPackages = new ArrayList<>();

        String entityQueryDTO = "";
        String importStr4QueryDTO = "";
        if (queryDTO!=null){
            if (Map.class.isAssignableFrom(queryDTO.getClazz())) {
                entityQueryDTO = "Map";
            } else {
                entityQueryDTO = queryDTO.getClazz().getSimpleName();
            }
            importStr4QueryDTO = "import " + queryDTO.getClazz().getCanonicalName() + ";";
        }else{
            entityQueryDTO = tableInfo.getEntityName()+"QueryDTO";
            importStr4QueryDTO = "import " + tableInfo.getPackageConfig().getPackageInfo(null, ConstVal.QUERY_DTO) + "." + entityQueryDTO + ";";
        }
        
        
        return data;
    }
    
    public static class Builder extends BaseBuilder {
        protected final Controller controller = new Controller();

        public Builder(StrategyConfig strategyConfig) {
            super(strategyConfig);
        }

        public Controller get() {
            return this.controller;
        }

        /**
         * 父类控制器
         *
         * @param clazz 父类控制器
         * @return this
         */
        public Builder superClass(Class<?> clazz) {
            return superClass(clazz.getName());
        }

        /**
         * 父类控制器
         *
         * @param superClass 父类控制器类名
         * @return this
         */
        public Builder superClass(String superClass) {
            this.controller.superClass = superClass;
            return this;
        }

        /**
         * 关闭驼峰转连字符
         *
         * @return this
         * @since 3.5.0
         */
        public Builder disableHyphenStyle() {
            this.controller.hyphenStyle = true;
            return this;
        }

        /**
         * 关闭@RestController控制器
         *
         * @return this
         * @since 3.5.0
         */
        public Builder disableRestController() {
            this.controller.restController = false;
            return this;
        }

        /**
         * 转换输出文件名称
         *
         * @param converter 　转换处理
         * @return this
         * @since 3.5.0
         */
        public Builder convertFileName(ConverterFileName converter) {
            this.controller.converterFileName = converter;
            return this;
        }

        /**
         * 格式化文件名称
         *
         * @param format 　格式
         * @return this
         * @since 3.5.0
         */
        public Builder formatFileName(String format) {
            return convertFileName((entityName) -> String.format(format, entityName));
        }

        /**
         * 覆盖已有文件
         *
         * @since 3.5.3
         */
        public Builder enableFileOverride() {
            this.controller.fileOverride = true;
            return this;
        }

        /**
         * 禁用生成
         *
         * @return this
         * @since 3.5.6
         */
        public Builder disable() {
            this.controller.generate = false;
            return this;
        }

        /**
         * 指定模板路径
         *
         * @param template 模板路径
         * @return this
         * @since 3.5.6
         */
        public Builder template(String template) {
            this.controller.templatePath = template;
            return this;
        }

        // ============自定义项==============

        /**
         * controller请求前缀
         *
         * @param url url
         * @return this
         */
        public Builder baseUrl(String url) {
            this.controller.baseUrl = url;
            return this;
        }

        /**
         * 跨域注解
         *
         * @return this
         */
        public Builder enableCrossOrigins() {
            this.controller.crossOrigins = true;
            return this;
        }

        /**
         * 使用javax包作为javaEE api
         * <p>springboot2.x使用javax, springboot3.x使用jakarta</p>
         * 默认使用jakarta
         *
         * @return this
         */
        public Builder enableJavax() {
            this.controller.javaApiPackage = "javax";
            return this;
        }


        /**
         * 指定controller的返回结果包装类及方法
         *
         * @param methodReference 方法引用
         * @return this
         */
        public <R> Builder returnMethod(SFunction<Object, R> methodReference) {
            this.controller.returnMethod = ReflectUtil.lambdaMethodInfo(methodReference, Object.class);
            return this;
        }

        /**
         * 指定controller返回的分页包装类及方法
         *
         * @param methodReference 方法参考
         * @return this
         */
        public <O, R> Builder pageMethod(SFunction<IPage<O>, R> methodReference) {
            this.controller.pageMethod = ReflectUtil.lambdaMethodInfo(methodReference, IPage.class);
            return this;
        }

        /**
         * 指定查询的DTO
         *
         * @return this
         */
        public Builder queryDTO(Class<?> queryDTO) {
            this.controller.queryDTO = new ClassPayload(queryDTO);
            return this;
        }

        /**
         * 使用@AutoWired替换@Resource
         *
         * @return this
         */
        public Builder enableAutoWired() {
            this.controller.autoWired = true;
            return this;
        }

        /**
         * 禁止复杂查询使用post请求(使用Get请求替代)
         *
         * @return this
         */
        public Builder disablePost() {
            this.controller.postQuery = false;
            return this;
        }

        /**
         * 禁止get请求使用@RequestBody注解
         *
         * @return this
         */
        public Builder disableGetRequestBody() {
            this.controller.queryRequestBody = false;
            return this;
        }

        /**
         * 增删查改使用restful风格
         *
         * @return this
         */
        public Builder enableRestful() {
            this.controller.restful = true;
            return this;
        }

        /**
         * 禁用路径变量
         *
         * @return this
         */
        public Builder disablePathVariable() {
            this.controller.pathVariable = false;
            return this;
        }

        /**
         * 禁用消息体接收数据
         *
         * @return this
         */
        public Builder disableRequestBody() {
            this.controller.requestBody = false;
            return this;
        }

        /**
         * 禁用参数校验注解
         *
         * @return this
         */
        public Builder disableValidated() {
            this.controller.validated = false;
            return this;
        }
    }
    
}
