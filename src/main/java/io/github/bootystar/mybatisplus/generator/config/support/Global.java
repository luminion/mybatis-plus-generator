package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * 全局配置
 *
 * @see com.baomidou.mybatisplus.generator.config.GlobalConfig
 * @author bootystar
 */
@Getter
public class Global {

    /**
     * 生成文件的输出目录【 windows:C://tmp  linux or mac:/tmp 】
     */
    protected String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "C://tmp" : "/tmp";

    /**
     * 是否打开输出目录
     */
    protected boolean open = true;

    /**
     * 作者
     */
    protected String author = "bootystar";

    /**
     * 开启 Kotlin 模式（默认 false）
     */
    protected boolean kotlin;

    /**
     * 开启 swagger 模式（默认 false 与 springdoc 不可同时使用）
     */
    protected boolean swagger;
    /**
     * 开启 springdoc 模式（默认 false 与 swagger 不可同时使用）
     */
    protected boolean springdoc;

    /**
     * 时间类型对应策略
     */
    protected DateType dateType = DateType.TIME_PACK;

    /**
     * 获取注释日期
     *
     * @since 3.5.0
     */
    protected Supplier<String> commentDate = () -> new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    

}