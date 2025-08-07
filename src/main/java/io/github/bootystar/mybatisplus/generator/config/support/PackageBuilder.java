package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.generator.config.IConfigBuilder;

import java.util.Map;

/**
 * 包配置构建器
 * 
 * @see com.baomidou.mybatisplus.generator.config.PackageConfig.Builder
 * @author bootystar
 */
public class PackageBuilder implements IConfigBuilder<Package> {
    private final Package packageConfig = new Package();
    @Override
    public Package build() {
        return packageConfig;
    }


    /**
     * 指定父包名
     *
     * @param parent 父包名
     * @return this
     */
    public PackageBuilder parent(String parent) {
        this.packageConfig.parent = parent;
        return this;
    }

    /**
     * 指定模块名称
     *
     * @param moduleName 模块名
     * @return this
     */
    public PackageBuilder moduleName(String moduleName) {
        this.packageConfig.moduleName = moduleName;
        return this;
    }

    /**
     * 指定实体包名
     *
     * @param entity 实体包名
     * @return this
     */
    public PackageBuilder entity(String entity) {
        this.packageConfig.entity = entity;
        return this;
    }

    /**
     * 指定service接口包名
     *
     * @param service service包名
     * @return this
     */
    public PackageBuilder service(String service) {
        this.packageConfig.service = service;
        return this;
    }

    /**
     * service实现类包名
     *
     * @param serviceImpl service实现类包名
     * @return this
     */
    public PackageBuilder serviceImpl(String serviceImpl) {
        this.packageConfig.serviceImpl = serviceImpl;
        return this;
    }

    /**
     * 指定mapper接口包名
     *
     * @param mapper mapper包名
     * @return this
     */
    public PackageBuilder mapper(String mapper) {
        this.packageConfig.mapper = mapper;
        return this;
    }

    /**
     * 指定xml包名
     *
     * @param xml xml包名
     * @return this
     */
    public PackageBuilder xml(String xml) {
        this.packageConfig.xml = xml;
        return this;
    }

    /**
     * 指定控制器包名
     *
     * @param controller 控制器包名
     * @return this
     */
    public PackageBuilder controller(String controller) {
        this.packageConfig.controller = controller;
        return this;
    }

    /**
     * 路径配置信息
     *
     * @param pathInfo 路径配置信息
     * @return this
     */
    public PackageBuilder pathInfo(Map<String, String> pathInfo) {
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
