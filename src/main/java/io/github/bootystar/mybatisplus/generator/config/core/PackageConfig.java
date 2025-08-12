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
import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.OutputFile;
import io.github.bootystar.mybatisplus.generator.config.builder.CustomFile;
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
     * Mapper XML包名
     */
    protected String xml = "mapper.xml";

    /**
     * Service包名
     */
    protected String service = "service";

    /**
     * Service Impl包名
     */
    protected String serviceImpl = "service.impl";

    /**
     * Controller包名
     */
    protected String controller = "controller";

    /**
     * 查询DTO包名
     */
    protected String queryDTO = "dto";
    
    /**
     * 新增DTO包名
     */
    protected String insertDTO = "dto";
    
    /**
     * 修改DTO包名
     */
    protected String updateDTO = "dto";
    
    /**
     * VO包名
     */
    protected String vo = "vo";

    /**
     * 路径配置信息
     */
    protected Map<OutputFile, String> pathInfo;

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
            packageInfo.put(ConstVal.XML, this.joinPackage(this.getXml()));
            packageInfo.put(ConstVal.SERVICE, this.joinPackage(this.getService()));
            packageInfo.put(ConstVal.SERVICE_IMPL, this.joinPackage(this.getServiceImpl()));
            packageInfo.put(ConstVal.CONTROLLER, this.joinPackage(this.getController()));
            packageInfo.put(ConstVal.PARENT, this.getParent());
            packageInfo.put(ConstVal.INSERT_DTO, this.joinPackage(this.getInsertDTO()));
            packageInfo.put(ConstVal.UPDATE_DTO, this.joinPackage(this.getUpdateDTO()));
            packageInfo.put(ConstVal.QUERY_DTO, this.joinPackage(this.getQueryDTO()));
            packageInfo.put(ConstVal.VO, this.joinPackage(this.getVo()));
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

    public static class Builder implements IConfigBuilder<PackageConfig> {
        protected final PackageConfig packageConfig = new PackageConfig();

        public Builder() {
        }

        public Builder(String parent, String moduleName) {
            this.packageConfig.parent = parent;
            this.packageConfig.moduleName = moduleName;
        }

        /**
         * 构建包配置对象
         * <p>当指定{@link #parent(String)} 与 {@link #moduleName(String)}时,其他模块名字会加上这两个作为前缀</p>
         * <p>
         * 例如:
         * <p>当设置 {@link #parent(String)},那么entity的配置为 parent.entity
         * <p>当设置 {@link #parent(String)}与{@link #moduleName(String)},那么entity的配置为 parent.module.entity 
         * </p>
         *
         * @return 包配置对象
         */
        @Override
        public PackageConfig build() {
            return this.packageConfig;
        }

        /**
         * 指定父包名
         *
         * @param parent 父包名
         * @return this
         */
        public Builder parent(String parent) {
            this.packageConfig.parent = parent;
            return this;
        }

        /**
         * 指定模块名称
         *
         * @param moduleName 模块名
         * @return this
         */
        public Builder moduleName(String moduleName) {
            this.packageConfig.moduleName = moduleName;
            return this;
        }

        /**
         * 指定实体包名
         *
         * @param entity 实体包名
         * @return this
         */
        public Builder entity(String entity) {
            this.packageConfig.entity = entity;
            return this;
        }

        /**
         * 指定mapper接口包名
         *
         * @param mapper mapper包名
         * @return this
         */
        public Builder mapper(String mapper) {
            this.packageConfig.mapper = mapper;
            return this;
        }

        /**
         * 指定xml包名
         *
         * @param xml xml包名
         * @return this
         */
        public Builder xml(String xml) {
            this.packageConfig.xml = xml;
            return this;
        }

        /**
         * 指定service接口包名
         *
         * @param service service包名
         * @return this
         */
        public Builder service(String service) {
            this.packageConfig.service = service;
            return this;
        }

        /**
         * service实现类包名
         *
         * @param serviceImpl service实现类包名
         * @return this
         */
        public Builder serviceImpl(String serviceImpl) {
            this.packageConfig.serviceImpl = serviceImpl;
            return this;
        }

        /**
         * 指定控制器包名
         *
         * @param controller 控制器包名
         * @return this
         */
        public Builder controller(String controller) {
            this.packageConfig.controller = controller;
            return this;
        }

        /**
         * 指定查询DTO包名
         *
         * @param queryDTO 查询DTO包名
         * @return this
         */
        public Builder queryDTO(String queryDTO) {
            this.packageConfig.queryDTO = queryDTO;
            return this;
        }

        /**
         * 指定新增DTO包名
         *
         * @param insertDTO 新增DTO包名
         * @return this
         */
        public Builder insertDTO(String insertDTO) {
            this.packageConfig.insertDTO = insertDTO;
            return this;
        }

        /**
         * 指定修改DTO包名
         *
         * @param updateDTO 修改DTO包名
         * @return this
         */
        public Builder updateDTO(String updateDTO) {
            this.packageConfig.updateDTO = updateDTO;
            return this;
        }

        /**
         * 路径配置信息
         *
         * @param pathInfo 路径配置信息
         * @return this
         */
        public Builder pathInfo(Map<OutputFile, String> pathInfo) {
            this.packageConfig.pathInfo = pathInfo;
            return this;
        }

        /**
         * 连接父子包名
         *
         * @param subPackage 子包名
         * @return 连接后的包名
         */
        public String joinPackage(String subPackage) {
            return this.packageConfig.joinPackage(subPackage);
        }


    }
    
}
