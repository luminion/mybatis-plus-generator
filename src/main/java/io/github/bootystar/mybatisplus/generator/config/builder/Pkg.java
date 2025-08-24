package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.OutputFile;
import io.github.bootystar.mybatisplus.generator.config.core.FileConfig;

import java.util.Collections;
import java.util.Map;

/**
 * @author bootystar
 */
public class Pkg extends FileConfig {
    public static class Builder {
        protected final Pkg config = new Pkg();

        protected Builder() {
        }

        protected FileConfig build() {
            return this.config;
        }

        /**
         * 指定父包名
         * 实际最终包名为 parent.module.entity
         *
         * @param packageName 包名
         * @return this
         */
        public Builder parent(String packageName) {
            this.config.parent = packageName;
            return this;
        }

        /**
         * 指定模块名称
         * 实际最终包名为 parent.module.entity
         *
         * @param packageName 模块名
         * @return this
         */
        public Builder moduleName(String packageName) {
            this.config.moduleName = packageName;
            return this;
        }

        /**
         * 指定实体包名
         *
         * @param packageName 实体包名
         * @return this
         */
        public Builder entity(String packageName) {
            this.config.entity = packageName;
            return this;
        }

        /**
         * 指定新增DTO包名
         *
         * @param packageName 新增DTO包名
         * @return this
         */
        public Builder entityInsertDTO(String packageName) {
            this.config.createDTO = packageName;
            return this;
        }

        /**
         * 指定修改DTO包名
         *
         * @param packageName 修改DTO包名
         * @return this
         */
        public Builder entityUpdateDTO(String packageName) {
            this.config.updateDTO = packageName;
            return this;
        }

        /**
         * 指定查询DTO包名
         *
         * @param packageName 查询DTO包名
         * @return this
         */
        public Builder entityQueryDTO(String packageName) {
            this.config.queryDTO = packageName;
            return this;
        }
    
        /**
         * 指定vo包名
         *
         * @param packageName 包名
         * @return this
         */
        public Builder entityQueryVO(String packageName) {
            this.config.queryVO = packageName;
            return this;
        }

        /**
         * 指定mapper接口包名
         *
         * @param packageName mapper包名
         * @return this
         */
        public Builder mapper(String packageName) {
            this.config.mapper = packageName;
            return this;
        }

        /**
         * 指定xml包名
         *
         * @param packageName xml包名
         * @return this
         */
        public Builder mapperXml(String packageName) {
            this.config.mapperXml = packageName;
            return this;
        }

        /**
         * 指定xml在resources下的路径
         *
         * @param path xml包名
         * @return this
         */
        public Builder mapperXmlResourcePath(String path) {
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
         * @param packageName service包名
         * @return this
         */
        public Builder service(String packageName) {
            this.config.service = packageName;
            return this;
        }

        /**
         * service实现类包名
         *
         * @param packageName service实现类包名
         * @return this
         */
        public Builder serviceImpl(String packageName) {
            this.config.serviceImpl = packageName;
            return this;
        }

        /**
         * 指定控制器包名
         *
         * @param packageName 控制器包名
         * @return this
         */
        public Builder controller(String packageName) {
            this.config.controller = packageName;
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
