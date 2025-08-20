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

import io.github.bootystar.mybatisplus.generator.config.rules.DateType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;


/**
 * 全局配置
 *
 * @author hubin
 * @since 2016-12-02
 */
@Slf4j
@Getter
public class GlobalConfig {

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
    @Deprecated
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

    public boolean isSwagger() {
        // springdoc 设置优先于 swagger
        return !springdoc && swagger;
    }

    public String getCommentDate() {
        return commentDate.get();
    }
    
}
