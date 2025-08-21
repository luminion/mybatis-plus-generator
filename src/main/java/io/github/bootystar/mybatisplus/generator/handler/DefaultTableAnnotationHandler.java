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
package io.github.bootystar.mybatisplus.generator.handler;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.core.GlobalConfig;
import io.github.bootystar.mybatisplus.generator.config.core.EntityConfig;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.model.ClassAnnotationAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * 表注解处理器
 *
 * @author nieqiurong
 * @since 3.5.10
 */
public class DefaultTableAnnotationHandler implements ITableAnnotationHandler {

    @Override
    public List<ClassAnnotationAttributes> handle(TableInfo tableInfo, EntityConfig entity) {
        List<ClassAnnotationAttributes> annotationAttributesList = new ArrayList<>();
        GlobalConfig globalConfig = tableInfo.getConfigAdapter().getGlobalConfig();
        String comment = tableInfo.getComment();
        if (StringUtils.isBlank(comment)) {
            comment = StringPool.EMPTY;
        }
        boolean kotlin = globalConfig.isKotlin();
//        if (!kotlin && entity.isLombok()) {
//            // 原先kt模板没有处理这些,作为兼容项
//            if (entity.isChain()) {
//                annotationAttributesList.add(new ClassAnnotationAttributes("@Accessors(chain = true)", "lombok.experimental.Accessors"));
//            }
//            if (entity.getSuperClass() != null) {
//                annotationAttributesList.add(new ClassAnnotationAttributes("@EqualsAndHashCode(callSuper = true)", "lombok.EqualsAndHashCode"));
//            }
//            annotationAttributesList.add(new ClassAnnotationAttributes("@Data", "lombok.Data"));
//        }
        if (tableInfo.isConvert()) {
            String schemaName = tableInfo.getSchemaName();
            if (StringUtils.isBlank(schemaName)) {
                schemaName = StringPool.EMPTY;
            } else {
                schemaName = schemaName + StringPool.DOT;
            }
            //@TableName("${schemaName}${table.name}")
            String displayName = String.format("@TableName(\"%s%s\")", schemaName, tableInfo.getName());
            annotationAttributesList.add(new ClassAnnotationAttributes(TableName.class, displayName));
        }
//        if (globalConfig.isSwagger()) {
//            //@ApiModel(value = "${entity}对象", description = "${table.comment!}")
//            String displayName = String.format("@ApiModel(value = \"%s对象\", description = \"%s\")", tableInfo.getEntityName(), comment);
//            annotationAttributesList.add(new ClassAnnotationAttributes(
//                displayName, "io.swagger.annotations.ApiModel", "io.swagger.annotations.ApiModelProperty"));
//        }
//        if (globalConfig.isSpringdoc()) {
//            //@Schema(name = "${entity}", description = "${table.comment!}")
//            String displayName = String.format("@Schema(name = \"%s\", description = \"%s\")", tableInfo.getEntityName(), comment);
//            annotationAttributesList.add(new ClassAnnotationAttributes(displayName, "io.swagger.v3.oas.annotations.media.Schema"));
//        }
        return annotationAttributesList;
    }

}
