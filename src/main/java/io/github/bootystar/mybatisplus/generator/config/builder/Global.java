package io.github.bootystar.mybatisplus.generator.config.builder;

import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
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


    }
}
