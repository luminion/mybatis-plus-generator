package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;

/**
 * service配置构建器
 * 
 * @see com.baomidou.mybatisplus.generator.config.builder.Service.Builder
 * @author bootystar
 */
public class ServiceConfigBuilder implements IConfigBuilder<ServiceConfig> {
    private final ServiceConfig service = new ServiceConfig();
    @Override
    public ServiceConfig build() {
        return service;
    }

    /**
     * Service接口父类
     *
     * @param clazz 类
     * @return this
     */
    public ServiceConfigBuilder superServiceClass(Class<?> clazz) {
        return superServiceClass(clazz.getName());
    }

    /**
     * Service接口父类
     *
     * @param superServiceClass 类名
     * @return this
     */
    public ServiceConfigBuilder superServiceClass(String superServiceClass) {
        this.service.superServiceClass = superServiceClass;
        return this;
    }

    /**
     * Service实现类父类
     *
     * @param clazz 类
     * @return this
     */
    public ServiceConfigBuilder superServiceImplClass(Class<?> clazz) {
        return superServiceImplClass(clazz.getName());
    }

    /**
     * Service实现类父类
     *
     * @param superServiceImplClass 类名
     * @return this
     */
    public ServiceConfigBuilder superServiceImplClass(String superServiceImplClass) {
        this.service.superServiceImplClass = superServiceImplClass;
        return this;
    }

    /**
     * 转换输出service接口文件名称
     *
     * @param converter 　转换处理
     * @return this
     * @since 3.5.0
     */
    public ServiceConfigBuilder convertServiceFileName(ConverterFileName converter) {
        this.service.converterServiceFileName = converter;
        return this;
    }

    /**
     * 转换输出service实现类文件名称
     *
     * @param converter 　转换处理
     * @return this
     * @since 3.5.0
     */
    public ServiceConfigBuilder convertServiceImplFileName(ConverterFileName converter) {
        this.service.converterServiceImplFileName = converter;
        return this;
    }

    /**
     * 格式化service接口文件名称
     *
     * @param format 　格式
     * @return this
     * @since 3.5.0
     */
    public ServiceConfigBuilder formatServiceFileName(String format) {
        return convertServiceFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 格式化service实现类文件名称
     *
     * @param format 　格式
     * @return this
     * @since 3.5.0
     */
    public ServiceConfigBuilder formatServiceImplFileName(String format) {
        return convertServiceImplFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 覆盖已有文件（该方法后续会删除，替代方法为enableFileOverride方法）
     *
     * @see #enableFileOverride()
     */
    @Deprecated
    public ServiceConfigBuilder fileOverride() {
//        LOGGER.warn("fileOverride方法后续会删除，替代方法为enableFileOverride方法");
        this.service.fileOverride = true;
        return this;
    }

    /**
     * 覆盖已有文件
     */
    public ServiceConfigBuilder enableFileOverride() {
        this.service.fileOverride = true;
        return this;
    }

    /**
     * 禁用生成Service
     *
     * @return this
     * @since 3.5.6
     */
    public ServiceConfigBuilder disable() {
        this.service.generateService = false;
        this.service.generateServiceImpl = false;
        return this;
    }

    /**
     * 禁用生成
     *
     * @return this
     * @since 3.5.6
     */
    public ServiceConfigBuilder disableService() {
        this.service.generateService = false;
        return this;
    }

    /**
     * 禁用生成ServiceImpl
     *
     * @return this
     * @since 3.5.6
     */
    public ServiceConfigBuilder disableServiceImpl() {
        this.service.generateServiceImpl = false;
        return this;
    }

    /**
     * Service模板路径
     *
     * @return this
     * @since 3.5.6
     */
    public ServiceConfigBuilder serviceTemplate(String template) {
        this.service.serviceTemplate = template;
        return this;
    }

    /**
     * ServiceImpl模板路径
     *
     * @return this
     * @since 3.5.6
     */
    public ServiceConfigBuilder serviceImplTemplate(String template) {
        this.service.serviceImplTemplate = template;
        return this;
    }
}
