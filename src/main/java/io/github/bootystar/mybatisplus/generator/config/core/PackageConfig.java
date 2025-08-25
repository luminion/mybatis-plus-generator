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

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.OutputFile;
import io.github.bootystar.mybatisplus.generator.config.po.CustomFile;
import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包相关的配置项
 *
 * @author YangHu, tangguo, hubin
 * @since 2016-08-30
 */
@Getter
public class PackageConfig {

    protected PackageConfig() {
    }

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    protected String parent = "com.baomidou";

    /**
     * 父包模块名
     */
    protected String moduleName = "";

    /**
     * Entity包名
     */
    protected String entity = "entity";

    /**
     * Mapper包名
     */
    protected String mapper = "mapper";

    /**
     * MapperConfig XML包名
     */
    protected String xml = "mapper.xml";

    /**
     * Service包名
     */
    protected String service = "service";

    /**
     * ServiceConfig Impl包名
     */
    protected String serviceImpl = "service.impl";

    /**
     * Controller包名
     */
    protected String controller = "controller";
    
    /**
     * 新增DTO包名
     */
    protected String createDTO = "model.dto";
    
    /**
     * 修改DTO包名
     */
    protected String updateDTO = "model.dto";

    /**
     * 查询DTO包名
     */
    protected String queryDTO = "model.dto";
    
    /**
     * 查询VO包名
     */
    protected String queryVO = "model.vo";

    /**
     * 导入DTO包名
     */
    protected String excelImportDTO = "model.dto";

    /**
     * 导出DTO包名
     */
    protected String exportExportVO = "model.vo";

    /**
     * 路径配置信息
     */
    protected Map<OutputFile, String> pathInfo = new HashMap<>();

    /**
     * 包配置信息
     *
     * @since 3.5.0
     */
    protected final Map<String, String> packageInfo = new HashMap<>();

    /**
     * 父包名
     */
    public String getParent() {
        if (StringUtils.isNotBlank(moduleName)) {
            return parent + StringPool.DOT + moduleName;
        }
        return parent;
    }

    /**
     * 连接父子包名
     *
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    public String joinPackage(String subPackage) {
        String parent = getParent();
        return StringUtils.isBlank(parent) ? subPackage : (parent + StringPool.DOT + subPackage);
    }

    /**
     * 获取包配置信息
     *
     * @param injectionConfig 配置文件信息
     * @return 包配置信息
     * @since 3.5.10
     */
    public Map<String, String> getPackageInfo(InjectionConfig injectionConfig) {
        if (packageInfo.isEmpty()) {
            packageInfo.put(ConstVal.MODULE_NAME, this.getModuleName());
            packageInfo.put(ConstVal.ENTITY, this.joinPackage(this.getEntity()));
            packageInfo.put(ConstVal.MAPPER, this.joinPackage(this.getMapper()));
            packageInfo.put(ConstVal.MAPPER_XML, this.joinPackage(this.getXml()));
            packageInfo.put(ConstVal.SERVICE, this.joinPackage(this.getService()));
            packageInfo.put(ConstVal.SERVICE_IMPL, this.joinPackage(this.getServiceImpl()));
            packageInfo.put(ConstVal.CONTROLLER, this.joinPackage(this.getController()));
            packageInfo.put(ConstVal.PARENT, this.getParent());
            packageInfo.put(ConstVal.CREATE_DTO, this.joinPackage(this.getCreateDTO()));
            packageInfo.put(ConstVal.UPDATE_DTO, this.joinPackage(this.getUpdateDTO()));
            packageInfo.put(ConstVal.QUERY_DTO, this.joinPackage(this.getQueryDTO()));
            packageInfo.put(ConstVal.QUERY_VO, this.joinPackage(this.getQueryVO()));
            packageInfo.put(ConstVal.EXCEL_IMPORT_DTO, this.joinPackage(this.getExcelImportDTO()));
            packageInfo.put(ConstVal.EXCEL_EXPORT_VO, this.joinPackage(this.getExportExportVO()));
            if (injectionConfig != null) {
                List<CustomFile> customFiles = injectionConfig.getCustomFiles();
                for (CustomFile customFile : customFiles) {
                    if (StringUtils.isNotBlank(customFile.getPackageName())) {
                        String name = customFile.getShortName();
                        if (StringUtils.isNotBlank(this.parent)
                            && customFile.getPackageName().startsWith(this.parent)) {
                            packageInfo.put(name, customFile.getPackageName());
                        } else {
                            packageInfo.put(name, this.joinPackage(customFile.getPackageName()));
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableMap(this.packageInfo);
    }

    /**
     * 获取包配置信息
     *
     * @since 3.5.10
     * @param injectionConfig 注入配置
     * @param module 模块
     * @return 配置信息
     */
    public String getPackageInfo(InjectionConfig injectionConfig, String module) {
        return getPackageInfo(injectionConfig).get(module);
    }
    
}
