package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * 全局配置构建器
 *
 * @see com.baomidou.mybatisplus.generator.config.GlobalConfig.Builder
 * @author bootystar
 */
public class GlobalBuilder implements IConfigBuilder<Global> {
    private final Global global = new Global();
    
    @Override
    public Global build() {
        return global;
    }

    /**
     * 禁止打开输出目录
     */
    public GlobalBuilder disableOpenDir() {
        this.global.open = false;
        return this;
    }

    /**
     * 输出目录
     */
    public GlobalBuilder outputDir(String outputDir) {
        this.global.outputDir = outputDir;
        return this;
    }

    /**
     * 作者
     */
    public GlobalBuilder author(String author) {
        this.global.author = author;
        return this;
    }

    /**
     * 开启 kotlin 模式
     */
    public GlobalBuilder enableKotlin() {
        this.global.kotlin = true;
        return this;
    }

    /**
     * 开启 swagger 模式
     */
    public GlobalBuilder enableSwagger() {
        this.global.swagger = true;
        return this;
    }

    /**
     * 开启 springdoc 模式
     */
    public GlobalBuilder enableSpringdoc() {
        this.global.springdoc = true;
        return this;
    }

    /**
     * 时间类型对应策略
     */
    public GlobalBuilder dateType(DateType dateType) {
        this.global.dateType = dateType;
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
    public GlobalBuilder commentDate(Supplier<String> commentDate) {
        this.global.commentDate = commentDate;
        return this;
    }

    /**
     * 指定注释日期格式化
     *
     * @param pattern 格式
     * @return this
     * @since 3.5.0
     */
    public GlobalBuilder commentDate(String pattern) {
        return commentDate(() -> new SimpleDateFormat(pattern).format(new Date()));
    }
    
    
}
