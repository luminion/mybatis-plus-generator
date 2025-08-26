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
    protected String key;
    /**
     * 子包名(可空)
     */
    protected String subPackage;
    /**
     * 格式化文件名称
     */
    protected String formatPattern;
    /**
     * 模板路径
     */
    protected String templatePath;
    /**
     * 输出文件路径
     */
    protected String outputDir;
    /**
     * 输出文件后缀
     */
    protected String outputFileSuffix;
    /**
     * 文件覆盖
     */
    protected boolean fileOverride;
    /**
     * 是否生成
     */
    protected boolean generate = true;

    /**
     * 根据表信息转化输出文件名称
     *
     * @param tableInfo 表信息
     */
    public String convertFormatName(TableInfo tableInfo) {
        return String.format(formatPattern, tableInfo.getEntityName());
    }
    
}
