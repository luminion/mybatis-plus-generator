package io.github.bootystar.mybatisplus.generator.config.core;

import io.github.bootystar.mybatisplus.generator.config.IConfigBuilder;
import io.github.bootystar.mybatisplus.generator.config.OutputFile;

import java.util.Map;

/**
 * @author bootystar
 */
public class PackageConfigBuilder implements IConfigBuilder<PackageConfig> {
    protected final PackageConfig packageConfig = new PackageConfig();

    public PackageConfigBuilder() {
    }

    public PackageConfigBuilder(String parent, String moduleName) {
        this.packageConfig.parent = parent;
        this.packageConfig.moduleName = moduleName;
    }
    
    /**
     * 构建包配置对象
     * <p>当指定{@link #parent(String)} 与 {@link #moduleName(String)}时,其他模块名字会加上这两个作为前缀</p>
     * <p>
     * 例如:
     * <p>当设置 {@link #parent(String)},那么entity的配置为 parent.entity
     * <p>当设置 {@link #parent(String)}与{@link #moduleName(String)},那么entity的配置为 parent.module.entity 
     * </p>
     *
     * @return 包配置对象
     */
    @Override
    public PackageConfig build() {
        return this.packageConfig;
    }

    /**
     * 指定父包名
     *
     * @param parent 父包名
     * @return this
     */
    public PackageConfigBuilder parent(String parent) {
        this.packageConfig.parent = parent;
        return this;
    }

    /**
     * 指定模块名称
     *
     * @param moduleName 模块名
     * @return this
     */
    public PackageConfigBuilder moduleName(String moduleName) {
        this.packageConfig.moduleName = moduleName;
        return this;
    }

    /**
     * 指定实体包名
     *
     * @param entity 实体包名
     * @return this
     */
    public PackageConfigBuilder entity(String entity) {
        this.packageConfig.entity = entity;
        return this;
    }

    /**
     * 指定service接口包名
     *
     * @param service service包名
     * @return this
     */
    public PackageConfigBuilder service(String service) {
        this.packageConfig.service = service;
        return this;
    }

    /**
     * service实现类包名
     *
     * @param serviceImpl service实现类包名
     * @return this
     */
    public PackageConfigBuilder serviceImpl(String serviceImpl) {
        this.packageConfig.serviceImpl = serviceImpl;
        return this;
    }

    /**
     * 指定mapper接口包名
     *
     * @param mapper mapper包名
     * @return this
     */
    public PackageConfigBuilder mapper(String mapper) {
        this.packageConfig.mapper = mapper;
        return this;
    }

    /**
     * 指定xml包名
     *
     * @param xml xml包名
     * @return this
     */
    public PackageConfigBuilder xml(String xml) {
        this.packageConfig.xml = xml;
        return this;
    }

    /**
     * 指定控制器包名
     *
     * @param controller 控制器包名
     * @return this
     */
    public PackageConfigBuilder controller(String controller) {
        this.packageConfig.controller = controller;
        return this;
    }

    /**
     * 路径配置信息
     *
     * @param pathInfo 路径配置信息
     * @return this
     */
    public PackageConfigBuilder pathInfo(Map<OutputFile, String> pathInfo) {
        this.packageConfig.pathInfo = pathInfo;
        return this;
    }

    /**
     * 连接父子包名
     *
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    public String joinPackage(String subPackage) {
        return this.packageConfig.joinPackage(subPackage);
    }


}
