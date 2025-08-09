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
package io.github.bootystar.mybatisplus.generator.type;

import io.github.bootystar.mybatisplus.generator.config.core.GlobalConfig;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;
import io.github.bootystar.mybatisplus.generator.config.rules.IColumnType;


/**
 * 类型转换处理器
 *
 * @author nieqiurong 2022/5/12.
 * @since 3.5.3
 */
public interface ITypeConvertHandler {

    /**
     * 转换字段类型
     *
     * @param globalConfig 全局配置
     * @param typeRegistry 类型注册信息
     * @param metaInfo     字段元数据信息
     * @return 子类类型
     */
    IColumnType convert(GlobalConfig globalConfig, TypeRegistry typeRegistry, TableField.MetaInfo metaInfo);

}
