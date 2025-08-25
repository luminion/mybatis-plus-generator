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
@Getter
public class GlobalConfig implements ITemplate {

    protected GlobalConfig() {
    }

    /**
     * 作者
     */
    protected String author = "bootystar";

    /**
     * 时间类型对应策略
     */
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
    protected boolean lombok;

    /**
     * 【实体】是否为链式模型（默认 false）
     *
     * @since 3.3.2
     */
    protected boolean chainModel;

    /**
     * 开启 swagger 模式（默认 false 与 springdoc 不可同时使用）
     */
    protected boolean swagger;
    
    /**
     * 开启 springdoc 模式（默认 false 与 swagger 不可同时使用）
     */
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
        data.put("lombokChainModel", this.chainModel);
        
        return data;
    }
}
