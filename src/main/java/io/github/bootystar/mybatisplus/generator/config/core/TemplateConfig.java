/*
 * Copyright (c) 2011-2024, baomidou (jobob@qq.com).
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

import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 模板路径配置项
 *
 * @author tzg hubin
 * @since 2017-06-17
 */
@Slf4j
public class TemplateConfig {

    protected TemplateConfig() {
    }

    /**
     * 设置实体模板路径
     */
    @Getter
    protected String entity = ConstVal.TEMPLATE_ENTITY_JAVA;

    /**
     * 设置实体模板路径(kotlin模板)
     */
    @Getter
    protected String entityKt = ConstVal.TEMPLATE_ENTITY_KT;

    /**
     * 实体插入dto
     */
    @Getter
    protected String entityInsertDTO = ConstVal.TEMPLATE_ENTITY_INSERT_DTO_JAVA;

    /**
     * 实体更新dto
     */
    @Getter
    protected String entityUpdateDTO = ConstVal.TEMPLATE_ENTITY_UPDATE_DTO_JAVA;

    /**
     * 实体查询dto
     */
    @Getter
    protected String entityQueryDTO = ConstVal.TEMPLATE_ENTITY_QUERY_DTO_JAVA;

    /**
     * 实体vo
     */
    @Getter
    protected String entityVO = ConstVal.TEMPLATE_ENTITY_VO_JAVA;

    /**
     * 设置Mapper模板路径
     */
    @Getter
    protected String mapper = ConstVal.TEMPLATE_MAPPER;

    /**
     * 设置MapperXml模板路径
     */
    @Getter
    protected String mapperXml = ConstVal.TEMPLATE_XML;

    /**
     * 设置Service模板路径
     */
    @Getter
    protected String service = ConstVal.TEMPLATE_SERVICE;

    /**
     * 设置ServiceImpl模板路径
     */
    @Getter
    protected String serviceImpl = ConstVal.TEMPLATE_SERVICE_IMPL;

    /**
     * 设置控制器模板路径
     */
    @Getter
    protected String controller = ConstVal.TEMPLATE_CONTROLLER;



}
