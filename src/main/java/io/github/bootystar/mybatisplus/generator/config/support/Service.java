package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;
import com.baomidou.mybatisplus.generator.util.ClassUtils;
import io.github.bootystar.mybatisplus.generator.config.IReflectiveTemplate;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * service配置
 *
 * @see com.baomidou.mybatisplus.generator.config.builder.Service
 * @author bootystar
 */
public class Service implements IReflectiveTemplate {

    /**
     * 是否生成serviceImpl
     *
     * @since 3.5.6
     */
    @Getter
    protected boolean generateServiceImpl = true;


    /**
     * 是否生成service
     *
     * @since 3.5.6
     */
    @Getter
    protected boolean generateService = true;

    /**
     * @since 3.5.6
     */
    @Getter
    protected String serviceTemplate = ConstVal.TEMPLATE_SERVICE;

    /**
     * @since 3.5.6
     */
    @Getter
    protected String serviceImplTemplate = ConstVal.TEMPLATE_SERVICE_IMPL;

    /**
     * 自定义继承的Service类全称，带包名
     */
    protected String superServiceClass = ConstVal.SUPER_SERVICE_CLASS;

    /**
     * 自定义继承的ServiceImpl类全称，带包名
     */
    protected String superServiceImplClass = ConstVal.SUPER_SERVICE_IMPL_CLASS;

    /**
     * 转换输出Service文件名称
     *
     * @since 3.5.0
     */
    protected ConverterFileName converterServiceFileName = (entityName -> "I" + entityName + ConstVal.SERVICE);

    /**
     * 转换输出ServiceImpl文件名称
     *
     * @since 3.5.0
     */
    protected ConverterFileName converterServiceImplFileName = (entityName -> entityName + ConstVal.SERVICE_IMPL);

    /**
     * 是否覆盖已有文件（默认 false）
     *
     * @since 3.5.2
     */
    @Getter
    protected boolean fileOverride;
    
    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("superServiceClassPackage", this.superServiceClass);
        data.put("superServiceClass", ClassUtils.getSimpleName(this.superServiceClass));
        data.put("superServiceImplClassPackage", this.superServiceImplClass);
        data.put("superServiceImplClass", ClassUtils.getSimpleName(this.superServiceImplClass));
        data.put("generateServiceImpl", this.generateServiceImpl);
        data.put("generateService", this.generateService);
        return data;
    }
    
}