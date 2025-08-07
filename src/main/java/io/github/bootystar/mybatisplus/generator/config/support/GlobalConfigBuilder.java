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
public class GlobalConfigBuilder implements IConfigBuilder<GlobalConfig> {
    private final GlobalConfig globalConfig = new GlobalConfig();
    
    @Override
    public GlobalConfig build() {
        return globalConfig;
    }

    /**
     * 禁止打开输出目录
     */
    public GlobalConfigBuilder disableOpenDir() {
        this.globalConfig.open = false;
        return this;
    }

    /**
     * 输出目录
     */
    public GlobalConfigBuilder outputDir(String outputDir) {
        this.globalConfig.outputDir = outputDir;
        return this;
    }

    /**
     * 作者
     */
    public GlobalConfigBuilder author(String author) {
        this.globalConfig.author = author;
        return this;
    }

    /**
     * 开启 kotlin 模式
     */
    public GlobalConfigBuilder enableKotlin() {
        this.globalConfig.kotlin = true;
        return this;
    }

    /**
     * 开启 swagger 模式
     */
    public GlobalConfigBuilder enableSwagger() {
        this.globalConfig.swagger = true;
        return this;
    }

    /**
     * 开启 springdoc 模式
     */
    public GlobalConfigBuilder enableSpringdoc() {
        this.globalConfig.springdoc = true;
        return this;
    }

    /**
     * 不生成service接口
     */
    public GlobalConfigBuilder disableServiceInterface() {
        this.globalConfig.serviceInterface = false;
        return this;
    }

    /**
     * 时间类型对应策略
     */
    public GlobalConfigBuilder dateType(DateType dateType) {
        this.globalConfig.dateType = dateType;
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
    public GlobalConfigBuilder commentDate(Supplier<String> commentDate) {
        this.globalConfig.commentDate = commentDate;
        return this;
    }

    /**
     * 指定注释日期格式化
     *
     * @param pattern 格式
     * @return this
     * @since 3.5.0
     */
    public GlobalConfigBuilder commentDate(String pattern) {
        return commentDate(() -> new SimpleDateFormat(pattern).format(new Date()));
    }
    
    
}
