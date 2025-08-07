package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

/**
 * 全局配置
 *
 * @see com.baomidou.mybatisplus.generator.config.GlobalConfig
 * @author bootystar
 */
public class GlobalConfig {

    /**
     * 生成文件的输出目录【 windows:C://tmp  linux or mac:/tmp 】
     */
    @Getter
    protected String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "C://tmp" : "/tmp";

    /**
     * 是否打开输出目录
     */
    @Getter
    protected boolean open = true;

    /**
     * 作者
     */
    @Getter
    protected String author = "baomidou";

    /**
     * 开启 Kotlin 模式（默认 false）
     */
    @Getter
    protected boolean kotlin;

    /**
     * 开启 swagger 模式（默认 false 与 springdoc 不可同时使用）
     */
    protected boolean swagger;
    /**
     * 开启 springdoc 模式（默认 false 与 swagger 不可同时使用）
     */
    @Getter
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

    /**
     * 是否生成service 接口（默认 true）
     * 增加此开关的原因：在某些项目实践中，只需要生成service实现类，不需要抽象sevice接口
     * 针对某些项目，生成service接口，开发时反而麻烦，这种情况，可以将该属性设置为false
     * @deprecated 3.5.6 {@link Service.Builder#disableService()}
     */
    @Getter
    @Setter
    @Deprecated
    protected boolean serviceInterface = true;

}