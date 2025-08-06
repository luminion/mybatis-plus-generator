package io.github.bootystar.mybatisplus.generator.config.specific;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * 包配置
 *
 * @author bootystar
 */
@Data
@Accessors(chain = true)
public class PackageConfig {

    private String parent = "com.baomidou";
    private String moduleName = "";
    private String entity = "entity";
    private String service = "service";
    private String serviceImpl = "service.impl";
    private String mapper = "mapper";
    private String xml = "mapper.xml";
    private String controller = "controller";
    private Map<String, String> pathInfo = new HashMap<>();

    public PackageConfig() {
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getParent() {
        return parent;
    }

    public String getEntity() {
        return entity;
    }

    public String getService() {
        return service;
    }

    public String getServiceImpl() {
        return serviceImpl;
    }

    public String getMapper() {
        return mapper;
    }

    public String getXml() {
        return xml;
    }

    public String getController() {
        return controller;
    }

    public Map<String, String> getPathInfo() {
        return pathInfo;
    }

    public String getPackage(String module) {
        if (module == null) {
            return "";
        }
        switch (module) {
            case "Entity":
                return entity;
            case "Service":
                return service;
            case "ServiceImpl":
                return serviceImpl;
            case "Mapper":
                return mapper;
            case "Xml":
                return xml;
            case "Controller":
                return controller;
            default:
                return "";
        }
    }
}