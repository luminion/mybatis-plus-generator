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

import com.baomidou.mybatisplus.core.toolkit.StringPool;

import java.nio.charset.StandardCharsets;

/**
 * 定义常量
 *
 * @author YangHu, tangguo, hubin
 * @since 2016-08-31
 */
public interface ConstVal {

    String MODULE_NAME = "ModuleName";

    String ENTITY = "Entity";
    String TEMPLATE_ENTITY_JAVA = "/templates/entity.java";
    String TEMPLATE_ENTITY_KT = "/templates/entity.kt";

    String MAPPER = "Mapper";
    String TEMPLATE_MAPPER = "/templates/mapper.java";
    String SUPER_MAPPER_CLASS = "com.baomidou.mybatisplus.core.mapper.BaseMapper";

    String MAPPER_XML = "MapperXml";
    String TEMPLATE_MAPPER_XML = "/templates/mapper.xml";

    String SERVICE = "Service";
    String TEMPLATE_SERVICE = "/templates/service.java";
    String SUPER_SERVICE_CLASS = "com.baomidou.mybatisplus.extension.service.IService";
    
    String SERVICE_IMPL = "ServiceImpl";
    String TEMPLATE_SERVICE_IMPL = "/templates/serviceImpl.java";
    String SUPER_SERVICE_IMPL_CLASS = "com.baomidou.mybatisplus.extension.service.impl.ServiceImpl";
    
    String CONTROLLER = "Controller";
    String TEMPLATE_CONTROLLER = "/templates/controller.java";

    String CREATE_DTO = "CreateDTO";
    String TEMPLATE_CREATE_DTO_JAVA = "/templates/entityCreateDTO.java";

    String UPDATE_DTO = "UpdateDTO";
    String TEMPLATE_UPDATE_DTO_JAVA = "/templates/entityUpdateDTO.java";

    String QUERY_DTO = "QueryDTO";
    String TEMPLATE_QUERY_DTO_JAVA = "/templates/entityQueryDTO.java";

    String QUERY_VO = "QueryVO";
    String TEMPLATE_QUERY_VO_JAVA = "/templates/entityQueryVO.java";

    String EXCEL_IMPORT_DTO = "ImportDTO";
    String TEMPLATE_EXCEL_IMPORT_DTO_JAVA = "/templates/entityImportDTO.java";

    String EXCEL_EXPORT_VO = "ExportVO";
    String TEMPLATE_EXCEL_EXPORT_VO_JAVA = "/templates/entityExportVO.java";



    String PARENT = "Parent";
    String JAVA_TMPDIR = "java.io.tmpdir";
    String UTF8 = StandardCharsets.UTF_8.name();
    String UNDERLINE = "_";

    String JAVA_SUFFIX = StringPool.DOT_JAVA;
    String KT_SUFFIX = ".kt";
    String XML_SUFFIX = ".xml";

    String VM_LOAD_PATH_KEY = "file.resource.loader.class";
    String VM_LOAD_PATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";
    
    /**
     * @see com.baomidou.mybatisplus.core.metadata.TableInfoHelper
     */
    String DEFAULT_ID_NAME = "id";

    /**
     * 主键
     * @since 3.5.12
     */
    String PRIMARY = "PRIMARY";
}
