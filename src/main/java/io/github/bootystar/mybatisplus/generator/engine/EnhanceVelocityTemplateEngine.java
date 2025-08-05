package io.github.bootystar.mybatisplus.generator.engine;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import io.github.bootystar.mybatisplus.generator.config.core.CustomConfig;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 自定义Velocity引擎
 *
 * @author bootystar
 */
public class EnhanceVelocityTemplateEngine extends VelocityTemplateEngine {

    private final CustomConfig customConfig;

    public EnhanceVelocityTemplateEngine(CustomConfig customConfig) {
        this.customConfig = customConfig;
    }

//    @Override
//    protected void outputCustomFile(List<CustomFile> customFiles, TableInfo tableInfo, Map<String, Object> objectMap) {
//        if (customFiles == null || customFiles.isEmpty()) {
//            return;
//        }
//        customFiles.forEach(file -> {
//            outputFile(new File(file.getFilePath()), objectMap, file.getTemplatePath(), file.isFileOverride());
//        });
//    }

    @Override
    public Map<String, Object> getObjectMap(ConfigBuilder config, TableInfo tableInfo) {
        Map<String, Object> objectMap = super.getObjectMap(config, tableInfo);
        if (customConfig != null) {
            Map<String, Object> customData = customConfig.renderData(tableInfo);
            if (customData != null) {
                objectMap.putAll(customData);
            }
        }
        objectMap.put("basePackage", config.getPackageConfig().getParent());
        return objectMap;
    }


    @Override
    public AbstractTemplateEngine batchOutput() {
        ConfigBuilder config = this.getConfigBuilder();
        List<TableInfo> tableInfoList = config.getTableInfoList();
        tableInfoList.forEach(tableInfo -> {
            Map<String, Object> objectMap = this.getObjectMap(config, tableInfo);
            Optional.ofNullable(config.getInjectionConfig()).ifPresent(t -> {
                // 添加自定义属性
                t.beforeOutputFile(tableInfo, objectMap);
                // 输出自定义文件
                outputCustomFile(t.getCustomFile(), tableInfo, objectMap);
            });
            // entity
            outputEntity(tableInfo, objectMap);
            // mapper and xml
            outputMapper(tableInfo, objectMap);
            // service
            outputService(tableInfo, objectMap);
            // controller
            outputController(tableInfo, objectMap);
            // 自定义文件
            Optional.ofNullable(customConfig).ifPresent(t -> {
                // 输出自定义文件
//                String parentPath = getPathInfo(OutputFile.parent);
                outputCustomFile(customConfig.customFiles(config, tableInfo), tableInfo, objectMap);
            });
        });
        return this;
    }

    protected void outputEntity(TableInfo tableInfo, Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String entityPath = getPathInfo(OutputFile.entity);
        if (StringUtils.isNotBlank(entityName) && StringUtils.isNotBlank(entityPath)) {
            getTemplateFilePath(template -> template.getEntity(getConfigBuilder().getGlobalConfig().isKotlin())).ifPresent((entity) -> {
                String entityFile = String.format((entityPath + File.separator + "%s" + suffixJavaOrKt()), entityName);
                outputFile(new File(entityFile), objectMap, entity);
            });
        }
    }

    protected void outputMapper(TableInfo tableInfo, Map<String, Object> objectMap) {
        // MpMapper.java
        String entityName = tableInfo.getEntityName();
        String mapperPath = getPathInfo(OutputFile.mapper);
        if (StringUtils.isNotBlank(tableInfo.getMapperName()) && StringUtils.isNotBlank(mapperPath)) {
            getTemplateFilePath(TemplateConfig::getMapper).ifPresent(mapper -> {
                String mapperFile = String.format((mapperPath + File.separator + tableInfo.getMapperName() + suffixJavaOrKt()), entityName);
                outputFile(new File(mapperFile), objectMap, mapper);
            });
        }
        // MpMapper.xml
        String xmlPath = getPathInfo(OutputFile.mapperXml);
        if (StringUtils.isNotBlank(tableInfo.getXmlName()) && StringUtils.isNotBlank(xmlPath)) {
            getTemplateFilePath(TemplateConfig::getXml).ifPresent(xml -> {
                String xmlFile = String.format((xmlPath + File.separator + tableInfo.getXmlName() + ConstVal.XML_SUFFIX), entityName);
                outputFile(new File(xmlFile), objectMap, xml);
            });
        }
    }

    protected void outputService(TableInfo tableInfo, Map<String, Object> objectMap) {
        // IMpService.java
        String entityName = tableInfo.getEntityName();
        String servicePath = getPathInfo(OutputFile.service);
        if (StringUtils.isNotBlank(tableInfo.getServiceName()) && StringUtils.isNotBlank(servicePath)) {
            getTemplateFilePath(TemplateConfig::getService).ifPresent(service -> {
                String serviceFile = String.format((servicePath + File.separator + tableInfo.getServiceName() + suffixJavaOrKt()), entityName);
                outputFile(new File(serviceFile), objectMap, service);
            });
        }
        // MpServiceImpl.java
        String serviceImplPath = getPathInfo(OutputFile.serviceImpl);
        if (StringUtils.isNotBlank(tableInfo.getServiceImplName()) && StringUtils.isNotBlank(serviceImplPath)) {
            getTemplateFilePath(TemplateConfig::getServiceImpl).ifPresent(serviceImpl -> {
                String implFile = String.format((serviceImplPath + File.separator + tableInfo.getServiceImplName() + suffixJavaOrKt()), entityName);
                outputFile(new File(implFile), objectMap, serviceImpl);
            });
        }
    }

    protected void outputController(TableInfo tableInfo, Map<String, Object> objectMap) {
        // MpController.java
        String controllerPath = getPathInfo(OutputFile.controller);
        if (StringUtils.isNotBlank(tableInfo.getControllerName()) && StringUtils.isNotBlank(controllerPath)) {
            getTemplateFilePath(TemplateConfig::getController).ifPresent(controller -> {
                String entityName = tableInfo.getEntityName();
                String controllerFile = String.format((controllerPath + File.separator + tableInfo.getControllerName() + suffixJavaOrKt()), entityName);
                outputFile(new File(controllerFile), objectMap, controller);
            });
        }
    }

    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String otherPath = getPathInfo(OutputFile.other);
        customFile.forEach((key, value) -> {
            String fileName = String.format((otherPath + File.separator + entityName + File.separator + "%s"), key);
            outputFile(new File(fileName), objectMap, value);
        });
    }
}
