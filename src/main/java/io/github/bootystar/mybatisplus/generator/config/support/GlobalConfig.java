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

import io.github.bootystar.mybatisplus.generator.config.GeneratorConfig;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.config.rules.DateType;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;


/**
 * 全局配置
 *
 * @author hubin
 * @since 2016-12-02
 */
@Slf4j
public class GlobalConfig implements ITemplate {

    /**
     * 作者
     */
    protected String author = "bootystar";

    /**
     * 时间类型对应策略
     */
    @Getter
    protected DateType dateType = DateType.TIME_PACK;

    /**
     * 获取注释日期
     *
     * @since 3.5.0
     */
    protected Supplier<String> commentDate = () -> new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    /**
     * 【实体】是否为lombok模型（默认 false）<br>
     * <a href="https://projectlombok.org/">document</a>
     */
    @Getter
    protected boolean lombok;

    /**
     * 【实体】是否为链式模型（默认 false）
     *
     * @since 3.3.2
     */
    @Getter
    protected boolean chainModel;

    /**
     * 开启 swagger 模式（默认 false 与 springdoc 不可同时使用）
     */
    protected boolean swagger;

    /**
     * 开启 springdoc 模式（默认 false 与 swagger 不可同时使用）
     */
    @Getter
    protected boolean springdoc;

    /**
     * 额外类链接注释
     */
    
    protected boolean commentLink;

    /**
     * 注释UUID, 用于避免swagger等文档无法识别模型
     * <p>
     * 该属性在{@link TableInfo#setComment(String)} 时自动使用
     * </p>
     */
    @Getter
    protected boolean commentUUID;

    /**
     * javaEE api包(jakarta或javax)
     * <p>
     * 涉及HttpServletRequest,HttpServletResponse,@Resource
     */
    protected String javaApiPackagePrefix = "jakarta";

    /**
     * excel注解的包
     */
    protected String excelApiPackagePrefix = "cn.idev.excel";

    /**
     * excel类
     */
    protected String excelClass = "FastExcel";

    /**
     * 生成查询方法
     */
    protected boolean generateQuery = true;

    /**
     * 生成新增方法
     */
    protected boolean generateInsert = true;

    /**
     * 生成更新方法
     */
    protected boolean generateUpdate = true;

    /**
     * 生成删除方法
     */
    protected boolean generateDelete = true;

    /**
     * 生成导入方法
     */
    protected boolean generateImport = true;

    /**
     * 生成导出方法
     */
    protected boolean generateExport = true;

    /**
     * 是否生成重写父类方法
     */
    protected boolean methodOverride = true;

    /**
     * 参数校验
     */
    protected boolean validated = true;

    public boolean isSwagger() {
        // springdoc 设置优先于 swagger
        return !springdoc && swagger;
    }

    public String getCommentDate() {
        return commentDate.get();
    }

    /**
     * 解析java api包
     *
     * @param suffix 后缀
     */
    public String resolveJavaApiPackage(String suffix) {
        return javaApiPackagePrefix + "." + suffix;
    }

    public String resolveExcelApiPackage(String suffix) {
        return excelApiPackagePrefix + "." + suffix;
    }

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = ITemplate.super.renderData(tableInfo);
        data.put("author", this.author);
        data.put("date", this.getCommentDate());
        data.put("swagger", this.isSwagger());
        data.put("springdoc", this.springdoc);
        data.put("generateQuery", this.generateQuery);
        data.put("generateInsert", this.generateInsert);
        data.put("generateUpdate", this.generateUpdate);
        data.put("generateDelete", this.generateDelete);
        data.put("generateImport", this.generateImport);
        data.put("generateExport", this.generateExport);
        data.put("methodOverride", this.methodOverride);
        data.put("validated", this.validated);
        data.put("commentLink", this.commentLink);
        data.put("javaApiPackagePrefix", this.javaApiPackagePrefix);
        data.put("excelApiPackagePrefix", this.excelApiPackagePrefix);
        data.put("excelClass", this.excelClass);
        data.put("lombok", this.lombok);
        data.put("chainModel", this.chainModel);

        return data;
    }

    public Adapter adapter() {
        return new Adapter(this);
    }

    public static class Adapter {
        private final GlobalConfig config;

        public Adapter(GlobalConfig globalConfig) {
            this.config = globalConfig;
        }

        /**
         * 作者
         */
        public Adapter author(String author) {
            this.config.author = author;
            return this;
        }

        /**
         * 时间类型对应策略
         */
        public Adapter dateType(DateType dateType) {
            this.config.dateType = dateType;
            return this;
        }

        /**
         * 指定注释日期格式化
         *
         * @param pattern 格式
         * @return this
         * @since 3.5.0
         */
        public Adapter commentDate(String pattern) {
            this.config.commentDate = () -> new SimpleDateFormat(pattern).format(new Date());
            return this;
        }

        /**
         * 开启lombok模型
         *
         * @return this
         * @since 3.5.0
         */
        public Adapter enableLombok() {
            this.config.lombok = true;
            return this;
        }

        /**
         * 开启链式getter和setter
         *
         * @return this
         * @since 3.5.0
         */
        public Adapter enableChainModel() {
            this.config.chainModel = true;
            return this;
        }

        /**
         * 开启 swagger 模式
         */
        public Adapter enableSwagger() {
            this.config.swagger = true;
            return this;
        }

        /**
         * 开启 springdoc 模式
         */
        public Adapter enableSpringdoc() {
            this.config.springdoc = true;
            return this;
        }

        /**
         * 启用注释链接
         */
        public Adapter enableCommentLink() {
            this.config.commentLink = true;
            return this;
        }

        /**
         * 启用注释UUID(防止swagger等文档因为重复模型名称而无法识别)
         *
         * @return this
         */
        public Adapter enableCommentUUID() {
            this.config.commentUUID = true;
            return this;
        }

        /**
         * 使用javax包作为javaEE api
         * <p>springboot2.x使用javax, springboot3.x使用jakarta</p>
         * 默认使用jakarta
         *
         * @return this
         */
        public Adapter enableJavaxApi() {
            this.config.javaApiPackagePrefix = "javax";
            return this;
        }

        /**
         * 使用EasyExcel
         * <p>默认使用FastExcel</p>
         *
         * @return this
         */
        public Adapter enableEasyExcel() {
            this.config.excelApiPackagePrefix = "com.alibaba.excel";
            this.config.excelClass = "EasyExcel";
            return this;
        }

        /**
         * 不生成查询方法
         *
         * @return this
         */
        public Adapter disableQuery() {
            this.config.generateQuery = false;
            return this;
        }

        /**
         * 不生成新增方法
         *
         * @return this
         */
        public Adapter disableInsert() {
            this.config.generateInsert = false;
            return this;
        }

        /**
         * 不生成更新方法
         *
         * @return this
         */
        public Adapter disableUpdate() {
            this.config.generateUpdate = false;
            return this;
        }

        /**
         * 不生成删除方法
         *
         * @return this
         */
        public Adapter disableDelete() {
            this.config.generateDelete = false;
            return this;
        }

        /**
         * 不生成导入方法
         *
         * @return this
         */
        public Adapter disableImport() {
            this.config.generateImport = false;
            return this;
        }

        /**
         * 不生成导出方法
         *
         * @return this
         */
        public Adapter disableExport() {
            this.config.generateExport = false;
            return this;
        }

        /**
         * 禁用新增和修改的入参校验
         *
         * @return this
         */
        public Adapter disableValidated() {
            this.config.validated = false;
            return this;
        }
    }
}