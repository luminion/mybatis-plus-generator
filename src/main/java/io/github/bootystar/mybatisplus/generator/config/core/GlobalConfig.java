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
import java.util.UUID;
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
     * 生成文件的输出目录【 windows:D://  linux or mac:/tmp 】
     */
    protected String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "D://" : "/tmp";

    /**
     * 是否打开输出目录
     */
    protected boolean open = true;

    /**
     * 作者
     */
    protected String author = "bootystar";

    /**
     * 开启 Kotlin 模式（默认 false）
     * @deprecated 不支持kotlin
     */
    protected boolean kotlin = false;

    /**
     * 开启 swagger 模式（默认 false 与 springdoc 不可同时使用）
     */
    protected boolean swagger;
    
    /**
     * 开启 springdoc 模式（默认 false 与 swagger 不可同时使用）
     */
    protected boolean springdoc;

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
     * 全局文件覆盖
     */
    protected boolean fileOverride;

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

    /**
     * 额外类链接注释
     */
    protected boolean extraComment = true;

    /**
     * 注释UUID, 用于避免swagger等文档无法识别模型
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

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = ITemplate.super.renderData(tableInfo);
        data.put("author", this.author);
        data.put("date", this.getCommentDate());
        data.put("kotlin", this.kotlin);
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
        data.put("extraComment", this.extraComment);
        if(this.commentUUID){
            data.put("commentUUID", UUID.randomUUID().toString().substring(0, 4).toUpperCase());
        }
        data.put("javaApiPackagePrefix", this.javaApiPackagePrefix);
        data.put("excelApiPackagePrefix", this.excelApiPackagePrefix);
        data.put("excelClass", this.excelClass);
        
        return data;
    }
}
