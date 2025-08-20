package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.OutputFile;
import io.github.bootystar.mybatisplus.generator.config.core.PackageConfig;

import java.util.Collections;
import java.util.Map;

/**
 * @author bootystar
 */
public class Pkg extends PackageConfig {
    public static class Builder {
        protected final Pkg config = new Pkg();

        protected Builder() {
        }

        protected PackageConfig build() {
            return this.config;
        }

        /**
         * 指定父包名
         * 实际最终包名为 parent.module.entity
         *
         * @param parent 父包名
         * @return this
         */
        public Builder parent(String parent) {
            this.config.parent = parent;
            return this;
        }

        /**
         * 指定模块名称
         * 实际最终包名为 parent.module.entity
         *
         * @param moduleName 模块名
         * @return this
         */
        public Builder moduleName(String moduleName) {
            this.config.moduleName = moduleName;
            return this;
        }

        /**
         * 指定实体包名
         *
         * @param entity 实体包名
         * @return this
         */
        public Builder entity(String entity) {
            this.config.entity = entity;
            return this;
        }

        /**
         * 指定mapper接口包名
         *
         * @param mapper mapper包名
         * @return this
         */
        public Builder mapper(String mapper) {
            this.config.mapper = mapper;
            return this;
        }

        /**
         * 指定xml包名
         *
         * @param xml xml包名
         * @return this
         */
        public Builder xml(String xml) {
            this.config.xml = xml;
            return this;
        }

        /**
         * 指定xml在resources下的路径
         *
         * @param path xml包名
         * @return this
         */
        public Builder xmlUnderResources(String path) {
            String projectPath = System.getProperty("user.dir");
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            this.pathInfo(Collections.singletonMap(OutputFile.mapper, projectPath + "/src/main/resources/" + path));
            return this;
        }

        /**
         * 指定service接口包名
         *
         * @param service service包名
         * @return this
         */
        public Builder service(String service) {
            this.config.service = service;
            return this;
        }

        /**
         * service实现类包名
         *
         * @param serviceImpl service实现类包名
         * @return this
         */
        public Builder serviceImpl(String serviceImpl) {
            this.config.serviceImpl = serviceImpl;
            return this;
        }

        /**
         * 指定控制器包名
         *
         * @param controller 控制器包名
         * @return this
         */
        public Builder controller(String controller) {
            this.config.controller = controller;
            return this;
        }

        /**
         * 指定查询DTO包名
         *
         * @param queryDTO 查询DTO包名
         * @return this
         */
        public Builder queryDTO(String queryDTO) {
            this.config.queryDTO = queryDTO;
            return this;
        }

        /**
         * 指定新增DTO包名
         *
         * @param insertDTO 新增DTO包名
         * @return this
         */
        public Builder insertDTO(String insertDTO) {
            this.config.insertDTO = insertDTO;
            return this;
        }

        /**
         * 指定修改DTO包名
         *
         * @param updateDTO 修改DTO包名
         * @return this
         */
        public Builder updateDTO(String updateDTO) {
            this.config.updateDTO = updateDTO;
            return this;
        }

        /**
         * 路径配置信息
         *
         * @param pathInfo 路径配置信息
         * @return this
         */
        public Builder pathInfo(Map<OutputFile, String> pathInfo) {
            this.config.pathInfo.putAll(pathInfo);
            return this;
        }

        /**
         * 连接父子包名
         *
         * @param subPackage 子包名
         * @return 连接后的包名
         */
        public String joinPackage(String subPackage) {
            return this.config.joinPackage(subPackage);
        }


    }
}
