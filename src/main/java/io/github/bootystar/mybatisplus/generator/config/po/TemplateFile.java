package io.github.bootystar.mybatisplus.generator.config.po;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author bootystar
 */
@Getter
@Setter
@Accessors(chain = true)
public class TemplateFile {
    /**
     * 用于标识文件的key,重复时覆盖
     */
    private String key;
    /**
     * 子包名(可空)
     */
    private String subPackage;
    /**
     * 格式化文件名称
     */
    private String formatPattern;
    /**
     * 模板路径
     */
    private String templatePath;
    /**
     * 输出文件路径
     */
    private String outputDir;
    /**
     * 输出文件后缀
     */
    private String outputFileSuffix;
    /**
     * 文件覆盖
     */
    private boolean fileOverride;
    
}
