package io.github.bootystar.mybatisplus.generator.config.po;

import lombok.Data;

/**
 * @author bootystar
 */
@Data
public class ExtraField {
    /**
     * 属性类型
     */
    private String propertyType;
    /**
     * 属性名称
     */
    private String propertyName;
    /**
     * GET和SET方法的前缀
     */
    private String capitalName;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 注释
     */
    private String comment;
    /**
     * 后缀
     */
    private String suffix;
    /**
     * sql运算符
     */
    private String sqlOperator;
    
}
