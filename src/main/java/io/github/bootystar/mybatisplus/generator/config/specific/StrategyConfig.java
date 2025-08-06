package io.github.bootystar.mybatisplus.generator.config.specific;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 策略配置
 *
 * @author bootystar
 */
@Data
@Accessors(chain = true)
public class StrategyConfig {
    
    private EntityConfig entityConfig = new EntityConfig();
    
    private MapperConfig mapperConfig = new MapperConfig();
    
    private ServiceConfig serviceConfig = new ServiceConfig();
    
    private ControllerConfig controllerConfig = new ControllerConfig();

    public StrategyConfig() {
    }

    /**
     * 实体策略配置构建器
     */
    public static class EntityBuilder {
        private final StrategyConfig strategyConfig;
        private final EntityConfig entityConfig;

        public EntityBuilder(StrategyConfig strategyConfig) {
            this.strategyConfig = strategyConfig;
            this.entityConfig = strategyConfig.getEntityConfig();
        }

        public EntityBuilder enableFileOverride() {
            entityConfig.setFileOverride(true);
            return this;
        }

        public EntityBuilder disableFileOverride() {
            entityConfig.setFileOverride(false);
            return this;
        }

        public EntityBuilder enableSerialVersionUID() {
            entityConfig.setEnableSerialVersionUID(true);
            return this;
        }

        public EntityBuilder disableSerialVersionUID() {
            entityConfig.setEnableSerialVersionUID(false);
            return this;
        }

        public EntityBuilder enableChainModel() {
            entityConfig.setEnableChainModel(true);
            return this;
        }

        public EntityBuilder disableChainModel() {
            entityConfig.setEnableChainModel(false);
            return this;
        }

        public EntityBuilder enableLombok() {
            entityConfig.setEnableLombok(true);
            return this;
        }

        public EntityBuilder disableLombok() {
            entityConfig.setEnableLombok(false);
            return this;
        }

        public EntityBuilder enableTableFieldAnnotation() {
            entityConfig.setEnableTableFieldAnnotation(true);
            return this;
        }

        public EntityBuilder disableTableFieldAnnotation() {
            entityConfig.setEnableTableFieldAnnotation(false);
            return this;
        }

        public EntityBuilder enableRemoveIsPrefix() {
            entityConfig.setEnableRemoveIsPrefix(true);
            return this;
        }

        public EntityBuilder disableRemoveIsPrefix() {
            entityConfig.setEnableRemoveIsPrefix(false);
            return this;
        }

        public EntityBuilder enableActiveRecord() {
            entityConfig.setActiveRecord(true);
            return this;
        }

        public EntityBuilder disableActiveRecord() {
            entityConfig.setActiveRecord(false);
            return this;
        }

        public EntityBuilder enableColumnConstant() {
            entityConfig.setEnableColumnConstant(true);
            return this;
        }

        public EntityBuilder disableColumnConstant() {
            entityConfig.setEnableColumnConstant(false);
            return this;
        }

        public EntityBuilder superClass(String superClass) {
            entityConfig.setSuperclass(superClass);
            return this;
        }

        public EntityBuilder versionColumnName(String versionColumnName) {
            entityConfig.setVersionFieldName(versionColumnName);
            return this;
        }

        public EntityBuilder logicDeleteColumnName(String logicDeleteColumnName) {
            entityConfig.setLogicDeleteFieldName(logicDeleteColumnName);
            return this;
        }

        public EntityBuilder naming(String naming) {
            entityConfig.setNaming(naming);
            return this;
        }

        public EntityBuilder columnNaming(String columnNaming) {
            entityConfig.setColumnNaming(columnNaming);
            return this;
        }

        public EntityBuilder idType(com.baomidou.mybatisplus.annotation.IdType idType) {
            // This would need to be handled appropriately
            return this;
        }

        public StrategyConfig end() {
            return strategyConfig;
        }
    }

    /**
     * Mapper策略配置构建器
     */
    public static class MapperBuilder {
        private final StrategyConfig strategyConfig;
        private final MapperConfig mapperConfig;

        public MapperBuilder(StrategyConfig strategyConfig) {
            this.strategyConfig = strategyConfig;
            this.mapperConfig = strategyConfig.getMapperConfig();
        }

        public MapperBuilder enableFileOverride() {
            mapperConfig.setFileOverride(true);
            return this;
        }

        public MapperBuilder disableFileOverride() {
            mapperConfig.setFileOverride(false);
            return this;
        }

        public MapperBuilder enableMapperAnnotation() {
            mapperConfig.setEnableMapperAnnotation(true);
            return this;
        }

        public MapperBuilder disableMapperAnnotation() {
            mapperConfig.setEnableMapperAnnotation(false);
            return this;
        }

        public MapperBuilder enableBaseResultMap() {
            mapperConfig.setEnableBaseResultMap(true);
            return this;
        }

        public MapperBuilder disableBaseResultMap() {
            mapperConfig.setEnableBaseResultMap(false);
            return this;
        }

        public MapperBuilder enableBaseColumnList() {
            mapperConfig.setEnableBaseColumnList(true);
            return this;
        }

        public MapperBuilder disableBaseColumnList() {
            mapperConfig.setEnableBaseColumnList(false);
            return this;
        }

        public MapperBuilder superClass(String superClass) {
            mapperConfig.setSuperClass(superClass);
            return this;
        }

        public MapperBuilder mapperAnnotation(Class<? extends java.lang.annotation.Annotation> annotation) {
            // This would need to be handled appropriately
            return this;
        }

        public MapperBuilder mapperTemplate(String mapperTemplate) {
            // This would need to be handled appropriately
            return this;
        }

        public MapperBuilder mapperXmlTemplate(String mapperXmlTemplate) {
            // This would need to be handled appropriately
            return this;
        }

        public StrategyConfig end() {
            return strategyConfig;
        }
    }

    /**
     * Service策略配置构建器
     */
    public static class ServiceBuilder {
        private final StrategyConfig strategyConfig;
        private final ServiceConfig serviceConfig;

        public ServiceBuilder(StrategyConfig strategyConfig) {
            this.strategyConfig = strategyConfig;
            this.serviceConfig = strategyConfig.getServiceConfig();
        }

        public ServiceBuilder enableFileOverride() {
            serviceConfig.setFileOverride(true);
            return this;
        }

        public ServiceBuilder disableFileOverride() {
            serviceConfig.setFileOverride(false);
            return this;
        }

        public ServiceBuilder enableServiceInterface() {
            serviceConfig.setServiceInterface(true);
            return this;
        }

        public ServiceBuilder disableServiceInterface() {
            serviceConfig.setServiceInterface(false);
            return this;
        }

        public ServiceBuilder serviceTemplate(String serviceTemplate) {
            // This would need to be handled appropriately
            return this;
        }

        public ServiceBuilder serviceImplTemplate(String serviceImplTemplate) {
            // This would need to be handled appropriately
            return this;
        }

        public ServiceBuilder superServiceClass(Class<?> superServiceClass) {
            // This would need to be handled appropriately
            return this;
        }

        public ServiceBuilder superServiceImplClass(String superServiceImplClass) {
            serviceConfig.setSuperServiceImplClass(superServiceImplClass);
            return this;
        }

        public ServiceBuilder formatServiceFileName(String formatServiceFileName) {
            serviceConfig.setServiceSuffix(formatServiceFileName.replace("%s", ""));
            return this;
        }

        public ServiceBuilder formatServiceImplFileName(String formatServiceImplFileName) {
            serviceConfig.setServiceImplSuffix(formatServiceImplFileName.replace("%s", ""));
            return this;
        }

        public StrategyConfig end() {
            return strategyConfig;
        }
    }

    /**
     * Controller策略配置构建器
     */
    public static class ControllerBuilder {
        private final StrategyConfig strategyConfig;
        private final ControllerConfig controllerConfig;

        public ControllerBuilder(StrategyConfig strategyConfig) {
            this.strategyConfig = strategyConfig;
            this.controllerConfig = strategyConfig.getControllerConfig();
        }

        public ControllerBuilder enableFileOverride() {
            controllerConfig.setFileOverride(true);
            return this;
        }

        public ControllerBuilder disableFileOverride() {
            controllerConfig.setFileOverride(false);
            return this;
        }

        public ControllerBuilder enableRestStyle() {
            controllerConfig.setRestControllerStyle(true);
            return this;
        }

        public ControllerBuilder disableRestStyle() {
            controllerConfig.setRestControllerStyle(false);
            return this;
        }

        public ControllerBuilder enableHyphenStyle() {
            controllerConfig.setHyphenStyle(true);
            return this;
        }

        public ControllerBuilder disableHyphenStyle() {
            controllerConfig.setHyphenStyle(false);
            return this;
        }

        public ControllerBuilder superClass(String superClass) {
            controllerConfig.setSuperClass(superClass);
            return this;
        }

        public ControllerBuilder template(String template) {
            // This would need to be handled appropriately
            return this;
        }

        public ControllerBuilder formatFileName(String formatFileName) {
            controllerConfig.setControllerSuffix(formatFileName.replace("%s", ""));
            return this;
        }

        public StrategyConfig end() {
            return strategyConfig;
        }
    }

    /**
     * 获取实体策略配置构建器
     */
    public EntityBuilder entityBuilder() {
        return new EntityBuilder(this);
    }

    /**
     * 获取Mapper策略配置构建器
     */
    public MapperBuilder mapperBuilder() {
        return new MapperBuilder(this);
    }

    /**
     * 获取Service策略配置构建器
     */
    public ServiceBuilder serviceBuilder() {
        return new ServiceBuilder(this);
    }

    /**
     * 获取Controller策略配置构建器
     */
    public ControllerBuilder controllerBuilder() {
        return new ControllerBuilder(this);
    }
}