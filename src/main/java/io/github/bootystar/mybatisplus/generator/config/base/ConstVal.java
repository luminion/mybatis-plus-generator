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
package io.github.bootystar.mybatisplus.generator.config.base;

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
    String PARENT = "Parent";
    String ENTITY = "Entity";
    String MAPPER = "Mapper";
    String MAPPER_XML = "MapperXml";
    String SERVICE = "Service";
    String SERVICE_IMPL = "ServiceImpl";
    String CONTROLLER = "Controller";
    String CREATE_DTO = "CreateDTO";
    String UPDATE_DTO = "UpdateDTO";
    String QUERY_DTO = "QueryDTO";
    String QUERY_VO = "QueryVO";
    String IMPORT_DTO = "ImportDTO";
    String EXPORT_VO = "ExportVO";


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
