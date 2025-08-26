package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.OutputConfig;

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
         * 实体类名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder entityNameFormat(String formatPattern) {
            this.config.entity.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * 实体类包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder entityPackage(String subPackage) {
            this.config.entity.setSubPackage(subPackage);
            return this;
        }

        /**
         * 实体类模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder entityTemplatePath(String templatePath) {
            this.config.entity.setTemplatePath(templatePath);
            return this;
        }

        /**
         * 实体类输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder entityOutputDir(String outputDir) {
            this.config.entity.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用实体类生成
         *
         * @return this
         */
        public Builder entityDisable() {
            this.config.entity.setGenerate(false);
            return this;
        }

        /**
         * mapper名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder mapperNameFormat(String formatPattern) {
            this.config.mapper.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * mapper包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder mapperPackage(String subPackage) {
            this.config.mapper.setSubPackage(subPackage);
            return this;
        }

        /**
         * mapper模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder mapperTemplatePath(String templatePath) {
            this.config.mapper.setTemplatePath(templatePath);
            return this;
        }

        /**
         * mapper输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder mapperOutputDir(String outputDir) {
            this.config.mapper.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用mapper生成
         *
         * @return this
         */
        public Builder mapperDisable() {
            this.config.mapper.setGenerate(false);
            return this;
        }

        /**
         * mapperXml名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder mapperXmlNameFormat(String formatPattern) {
            this.config.mapperXml.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * mapperXml包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder mapperXmlPackage(String subPackage) {
            this.config.mapperXml.setSubPackage(subPackage);
            return this;
        }

        /**
         * mapperXml模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder mapperXmlTemplatePath(String templatePath) {
            this.config.mapperXml.setTemplatePath(templatePath);
            return this;
        }

        /**
         * mapperXml输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder mapperXmlOutputDir(String outputDir) {
            this.config.mapperXml.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用mapperXml生成
         *
         * @return this
         */
        public Builder mapperXmlDisable() {
            this.config.mapperXml.setGenerate(false);
            return this;
        }

        /**
         * service名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder serviceNameFormat(String formatPattern) {
            this.config.service.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * service包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder servicePackage(String subPackage) {
            this.config.service.setSubPackage(subPackage);
            return this;
        }

        /**
         * service模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder serviceTemplatePath(String templatePath) {
            this.config.service.setTemplatePath(templatePath);
            return this;
        }

        /**
         * service输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder serviceOutputDir(String outputDir) {
            this.config.service.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用service生成
         *
         * @return this
         */
        public Builder serviceDisable() {
            this.config.service.setGenerate(false);
            return this;
        }

        /**
         * serviceImpl名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder serviceImplNameFormat(String formatPattern) {
            this.config.serviceImpl.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * serviceImpl包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder serviceImplPackage(String subPackage) {
            this.config.serviceImpl.setSubPackage(subPackage);
            return this;
        }

        /**
         * serviceImpl模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder serviceImplTemplatePath(String templatePath) {
            this.config.serviceImpl.setTemplatePath(templatePath);
            return this;
        }

        /**
         * serviceImpl输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder serviceImplOutputDir(String outputDir) {
            this.config.serviceImpl.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用serviceImpl生成
         *
         * @return this
         */
        public Builder serviceImplDisable() {
            this.config.serviceImpl.setGenerate(false);
            return this;
        }

        /**
         * controller名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder controllerNameFormat(String formatPattern) {
            this.config.controller.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * controller包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder controllerPackage(String subPackage) {
            this.config.controller.setSubPackage(subPackage);
            return this;
        }

        /**
         * controller模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder controllerTemplatePath(String templatePath) {
            this.config.controller.setTemplatePath(templatePath);
            return this;
        }

        /**
         * controller输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder controllerOutputDir(String outputDir) {
            this.config.controller.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用controller生成
         *
         * @return this
         */
        public Builder controllerDisable() {
            this.config.controller.setGenerate(false);
            return this;
        }

        /**
         * createDTO名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder createDTONameFormat(String formatPattern) {
            this.config.createDTO.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * createDTO包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder createDTOPackage(String subPackage) {
            this.config.createDTO.setSubPackage(subPackage);
            return this;
        }

        /**
         * createDTO模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder createDTOTemplatePath(String templatePath) {
            this.config.createDTO.setTemplatePath(templatePath);
            return this;
        }

        /**
         * createDTO输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder createDTOOutputDir(String outputDir) {
            this.config.createDTO.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用createDTO生成
         *
         * @return this
         */
        public Builder createDTODisable() {
            this.config.createDTO.setGenerate(false);
            return this;
        }

        /**
         * updateDTO名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder updateDTONameFormat(String formatPattern) {
            this.config.updateDTO.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * updateDTO包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder updateDTOPackage(String subPackage) {
            this.config.updateDTO.setSubPackage(subPackage);
            return this;
        }

        /**
         * updateDTO模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder updateDTOTemplatePath(String templatePath) {
            this.config.updateDTO.setTemplatePath(templatePath);
            return this;
        }

        /**
         * updateDTO输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder updateDTOOutputDir(String outputDir) {
            this.config.updateDTO.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用updateDTO生成
         *
         * @return this
         */
        public Builder updateDTODisable() {
            this.config.updateDTO.setGenerate(false);
            return this;
        }

        /**
         * queryDTO名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder queryDTONameFormat(String formatPattern) {
            this.config.queryDTO.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * queryDTO包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder queryDTOPackage(String subPackage) {
            this.config.queryDTO.setSubPackage(subPackage);
            return this;
        }

        /**
         * queryDTO模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder queryDTOTemplatePath(String templatePath) {
            this.config.queryDTO.setTemplatePath(templatePath);
            return this;
        }

        /**
         * queryDTO输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder queryDTOOutputDir(String outputDir) {
            this.config.queryDTO.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用queryDTO生成
         *
         * @return this
         */
        public Builder queryDTODisable() {
            this.config.queryDTO.setGenerate(false);
            return this;
        }

        /**
         * queryVO名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder queryVONameFormat(String formatPattern) {
            this.config.queryVO.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * queryVO包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder queryVOPackage(String subPackage) {
            this.config.queryVO.setSubPackage(subPackage);
            return this;
        }

        /**
         * queryVO模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder queryVOTemplatePath(String templatePath) {
            this.config.queryVO.setTemplatePath(templatePath);
            return this;
        }

        /**
         * queryVO输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder queryVOOutputDir(String outputDir) {
            this.config.queryVO.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用queryVO生成
         *
         * @return this
         */
        public Builder queryVODisable() {
            this.config.queryVO.setGenerate(false);
            return this;
        }

        /**
         * importDTO名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder importDTONameFormat(String formatPattern) {
            this.config.importDTO.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * importDTO包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder importDTOPackage(String subPackage) {
            this.config.importDTO.setSubPackage(subPackage);
            return this;
        }

        /**
         * importDTO模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder importDTOTemplatePath(String templatePath) {
            this.config.importDTO.setTemplatePath(templatePath);
            return this;
        }

        /**
         * importDTO输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder importDTOOutputDir(String outputDir) {
            this.config.importDTO.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用importDTO生成
         *
         * @return this
         */
        public Builder importDTODisable() {
            this.config.importDTO.setGenerate(false);
            return this;
        }

        /**
         * exportVO名称格式
         *
         * @param formatPattern 格式
         * @return this
         */
        public Builder exportVONameFormat(String formatPattern) {
            this.config.exportVO.setFormatPattern(formatPattern);
            return this;
        }

        /**
         * exportVO包名
         *
         * @param subPackage 子包名
         * @return this
         */
        public Builder exportVOPackage(String subPackage) {
            this.config.exportVO.setSubPackage(subPackage);
            return this;
        }

        /**
         * exportVO模板路径
         *
         * @param templatePath 模板路径
         * @return this
         */
        public Builder exportVOTemplatePath(String templatePath) {
            this.config.exportVO.setTemplatePath(templatePath);
            return this;
        }

        /**
         * exportVO输出目录
         *
         * @param outputDir 输出目录
         * @return this
         */
        public Builder exportVOOutputDir(String outputDir) {
            this.config.exportVO.setOutputDir(outputDir);
            return this;
        }

        /**
         * 禁用exportVO生成
         *
         * @return this
         */
        public Builder exportVODisable() {
            this.config.exportVO.setGenerate(false);
            return this;
        }

    }
}