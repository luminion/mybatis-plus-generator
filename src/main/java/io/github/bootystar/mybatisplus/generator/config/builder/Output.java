package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.OutputConfig;
import io.github.bootystar.mybatisplus.generator.config.po.TemplateFile;

import java.util.function.Function;

/**
 * @author bootystar
 */
public class Output extends OutputConfig {
    public static class Builder {
        protected final Output config = new Output();

        protected Builder() {
        }

        protected OutputConfig build() {
            return this.config;
        }

        /**
         * 文件输出目录
         *
         * @param outputDir 文件输出目录
         * @return this
         */
        public Builder outputDir(String outputDir) {
            this.config.outputDir = outputDir;
            return this;
        }

        /**
         * 父包名
         *
         * @param parentPackage 父包名
         * @return this
         */
        public Builder parentPackage(String parentPackage) {
            this.config.parentPackage = parentPackage;
            return this;
        }

        /**
         * 模块名
         *
         * @param moduleName 模块名
         * @return this
         */
        public Builder moduleName(String moduleName) {
            this.config.moduleName = moduleName;
            return this;
        }

        /**
         * 启用全局文件覆盖(仅影响本配置提供的模板文件)
         *
         * @return this
         */
        public Builder enableGlobalFileOverride() {
            this.config.globalFileOverride = true;
            return this;
        }

        /**
         * 禁用打开输出目录
         *
         * @return this
         */
        public Builder disableOpenOutputDir() {
            this.config.open = false;
            return this;
        }

        /**
         * 实体类配置
         *
         * @param adapter 适配器
         */
        public Builder entity(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.entity.adapter());
            return this;
        }
        
        /**
         * mapper配置
         *
         * @param adapter 适配器
         */
        public Builder mapper(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.mapper.adapter());
            return this;
        }
        
        /**
         * mapperXml配置
         *
         * @param adapter 适配器
         */
        public Builder mapperXml(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.mapperXml.adapter());
            return this;
        }
        
        /**
         * service配置
         *
         * @param adapter 适配器
         */
        public Builder service(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.service.adapter());
            return this;
        }
        
        /**
         * serviceImpl配置
         *
         * @param adapter 适配器
         */
        public Builder serviceImpl(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.serviceImpl.adapter());
            return this;
        }
        
        /**
         * controller配置
         *
         * @param adapter 适配器
         */
        public Builder controller(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.controller.adapter());
            return this;
        }
        
        /**
         * createDTO配置
         *
         * @param adapter 适配器
         */
        public Builder createDTO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.createDTO.adapter());
            return this;
        }
        
        /**
         * updateDTO配置
         *
         * @param adapter 适配器
         */
        public Builder updateDTO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.updateDTO.adapter());
            return this;
        }
        
        /**
         * queryDTO配置
         *
         * @param adapter 适配器
         */
        public Builder queryDTO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.queryDTO.adapter());
            return this;
        }
        
        /**
         * queryVO配置
         *
         * @param adapter 适配器
         */
        public Builder queryVO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.queryVO.adapter());
            return this;
        }
        
        /**
         * importDTO配置
         *
         * @param adapter 适配器
         */
        public Builder importDTO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.importDTO.adapter());
            return this;
        }
        
        /**
         * exportVO配置
         *
         * @param adapter 适配器
         */
        public Builder exportVO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.exportVO.adapter());
            return this;
        }

    }
}