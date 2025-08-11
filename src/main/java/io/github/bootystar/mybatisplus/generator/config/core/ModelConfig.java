package io.github.bootystar.mybatisplus.generator.config.core;

import org.apache.ibatis.type.JdbcType;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author bootystar
 */
@Deprecated
public class ModelConfig {
//
//    /**
//     * DTO所在包
//     */
//    protected String package4DTO = "dto";
//
//    /**
//     * DTO文件的生成路径
//     */
//    protected String path4DTO;
//
//    /**
//     * VO所在包
//     */
//    protected String package4VO = "vo";
//
//    /**
//     * VO文件的生成路径
//     */
//    protected String path4VO;

    /**
     * 新增或修改时排除的字段
     */
    protected Collection<String> editExcludeColumns;


    
    
    // ----- 运行时配置项

    /**
     * DTO及VO的导包
     */
    protected Set<String> importPackages4DTO;

    /**
     * 生成时间
     */
    protected String nowTime;

    /**
     * jdbc时间类型
     */
    protected List<JdbcType> jdbcTimeTypes;

    /**
     * mapper默认排序的sql语句
     */
    protected String orderBySql;

    /**
     * swagger注解的额外后缀uuid
     */
    protected String swaggerUUID;

    /**
     * 是否需要重写serviceImpl中后缀构造器initSuffixBuilder()的方法
     */
    protected boolean overrideInitSuffixBuilder;
}
