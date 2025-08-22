package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.core.GlobalConfig;
import io.github.bootystar.mybatisplus.generator.config.rules.DateType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * @author bootystar
 */
public class Global extends GlobalConfig{
    public static class Builder {
        protected final Global config = new Global();
        
        protected Builder() {
        }
        
        protected GlobalConfig build() {
            return this.config;
        }

        /**
         * 禁止打开输出目录
         */
        public Builder disableOpenDir() {
            this.config.open = false;
            return this;
        }

        /**
         * 输出目录
         */
        public Builder outputDir(String outputDir) {
            this.config.outputDir = outputDir;
            return this;
        }

        /**
         * 作者
         */
        public Builder author(String author) {
            this.config.author = author;
            return this;
        }

        /**
         * 开启 swagger 模式
         */
        public Builder enableSwagger() {
            this.config.swagger = true;
            return this;
        }

        /**
         * 开启 springdoc 模式
         */
        public Builder enableSpringdoc() {
            this.config.springdoc = true;
            return this;
        }

        /**
         * 时间类型对应策略
         */
        public Builder dateType(DateType dateType) {
            this.config.dateType = dateType;
            return this;
        }

        /**
         * 注释日期获取处理
         * example: () -> LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)
         *
         * @param commentDate 获取注释日期
         * @return this
         * @since 3.5.0
         */
        public Builder commentDate(Supplier<String> commentDate) {
            this.config.commentDate = commentDate;
            return this;
        }

        /**
         * 指定注释日期格式化
         *
         * @param pattern 格式
         * @return this
         * @since 3.5.0
         */
        public Builder commentDate(String pattern) {
            return commentDate(() -> new SimpleDateFormat(pattern).format(new Date()));
        }

        /**
         * 启用全局文件覆盖
         */
        public Builder enableFileOverwrite() {
            this.config.fileOverride = true;
            return this;
        }

        /**
         * 不生成查询方法
         *
         * @return this
         */
        public Builder disableQuery() {
            this.config.generateQuery = false;
            return this;
        }
        
        /**
         * 不生成新增方法
         *
         * @return this
         */
        public Builder disableInsert() {
            this.config.generateInsert = false;
            return this;
        }

        /**
         * 不生成更新方法
         *
         * @return this
         */
        public Builder disableUpdate() {
            this.config.generateUpdate = false;
            return this;
        }

        /**
         * 不生成删除方法
         *
         * @return this
         */
        public Builder disableDelete() {
            this.config.generateDelete = false;
            return this;
        }

        /**
         * 不生成导入方法
         *
         * @return this
         */
        public Builder disableImport() {
            this.config.generateImport = false;
            return this;
        }

        /**
         * 不生成导出方法
         *
         * @return this
         */
        public Builder disableExport() {
            this.config.generateExport = false;
            return this;
        }

        /**
         * 禁用参数校验
         *
         * @return this
         */
        public Builder disableValidated() {
            this.config.validated = false;
            return this;
        }

        /**
         * 启用注释链接
         */
        public Builder enableCommentLink() {
            this.config.commentLink = true;
            return this;
        }

        /**
         * 启用注释UUID(防止swagger等文档因为重复模型名称而无法识别)
         *
         * @return this
         */
        public Builder enableCommentUUID() {
            this.config.commentUUID = true;
            return this;
        }

        /**
         * 使用javax包作为javaEE api
         * <p>springboot2.x使用javax, springboot3.x使用jakarta</p>
         * 默认使用jakarta
         *
         * @return this
         */
        public Builder enableJavaxApi() {
            this.config.javaApiPackagePrefix = "javax";
            return this;
        }

        /**
         * 使用EasyExcel
         * <p>默认使用FastExcel</p>
         *
         * @return this
         */
        public Builder enableEasyExcel() {
            this.config.excelApiPackagePrefix = "com.alibaba.excel";
            this.config.excelClass = "EasyExcel";
            return this;
        }

        /**
         * 开启lombok模型
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableLombok() {
            this.config.lombok = true;
            return this;
        }

        /**
         * 开启链式getter和setter
         *
         * @return this
         * @since 3.5.0
         */
        public Builder enableChainModel() {
            this.config.chainModel = true;
            return this;
        }

    }
}
