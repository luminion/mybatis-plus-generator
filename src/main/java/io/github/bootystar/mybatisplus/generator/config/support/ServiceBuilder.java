package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;
import com.baomidou.mybatisplus.generator.function.ConverterFileName;

/**
 * service配置构建器
 * 
 * @see com.baomidou.mybatisplus.generator.config.builder.Service.Builder
 * @author bootystar
 */
public class ServiceBuilder implements IConfigBuilder<Service> {
    private final Service service = new Service();
    @Override
    public Service build() {
        return service;
    }

    /**
     * Service接口父类
     *
     * @param clazz 类
     * @return this
     */
    public ServiceBuilder superServiceClass(Class<?> clazz) {
        return superServiceClass(clazz.getName());
    }

    /**
     * Service接口父类
     *
     * @param superServiceClass 类名
     * @return this
     */
    public ServiceBuilder superServiceClass(String superServiceClass) {
        this.service.superServiceClass = superServiceClass;
        return this;
    }

    /**
     * Service实现类父类
     *
     * @param clazz 类
     * @return this
     */
    public ServiceBuilder superServiceImplClass(Class<?> clazz) {
        return superServiceImplClass(clazz.getName());
    }

    /**
     * Service实现类父类
     *
     * @param superServiceImplClass 类名
     * @return this
     */
    public ServiceBuilder superServiceImplClass(String superServiceImplClass) {
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
    public ServiceBuilder convertServiceFileName(ConverterFileName converter) {
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
    public ServiceBuilder convertServiceImplFileName(ConverterFileName converter) {
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
    public ServiceBuilder formatServiceFileName(String format) {
        return convertServiceFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 格式化service实现类文件名称
     *
     * @param format 　格式
     * @return this
     * @since 3.5.0
     */
    public ServiceBuilder formatServiceImplFileName(String format) {
        return convertServiceImplFileName((entityName) -> String.format(format, entityName));
    }

    /**
     * 覆盖已有文件（该方法后续会删除，替代方法为enableFileOverride方法）
     *
     * @see #enableFileOverride()
     */
    @Deprecated
    public ServiceBuilder fileOverride() {
//        LOGGER.warn("fileOverride方法后续会删除，替代方法为enableFileOverride方法");
        this.service.fileOverride = true;
        return this;
    }

    /**
     * 覆盖已有文件
     */
    public ServiceBuilder enableFileOverride() {
        this.service.fileOverride = true;
        return this;
    }

    /**
     * 禁用生成Service
     *
     * @return this
     * @since 3.5.6
     */
    public ServiceBuilder disable() {
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
    public ServiceBuilder disableService() {
        this.service.generateService = false;
        return this;
    }

    /**
     * 禁用生成ServiceImpl
     *
     * @return this
     * @since 3.5.6
     */
    public ServiceBuilder disableServiceImpl() {
        this.service.generateServiceImpl = false;
        return this;
    }

    /**
     * Service模板路径
     *
     * @return this
     * @since 3.5.6
     */
    public ServiceBuilder serviceTemplate(String template) {
        this.service.serviceTemplate = template;
        return this;
    }

    /**
     * ServiceImpl模板路径
     *
     * @return this
     * @since 3.5.6
     */
    public ServiceBuilder serviceImplTemplate(String template) {
        this.service.serviceImplTemplate = template;
        return this;
    }
}
