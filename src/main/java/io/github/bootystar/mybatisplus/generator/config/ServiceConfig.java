package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceConfig {

    // MyBatis-Plus官方配置属性
    private String superServiceClass;
    private String superServiceImplClass;
    private boolean serviceInterface = true;
    private String serviceSuffix = "Service";
    private String serviceImplSuffix = "ServiceImpl";
    private boolean fileOverride = false;
    private String serviceTemplate;
    private String serviceImplTemplate;

    // 从BaseConfig迁移的service相关配置
    private boolean generateInsert = true;
    private boolean generateUpdate = true;
    private boolean generateDelete = true;
    private boolean generateSelect = true;
    private boolean overrideMethods = true;

    public static class Builder {
        private final ServiceConfig serviceConfig = new ServiceConfig();

        // MyBatis-Plus官方配置方法
        public Builder superServiceClass(String superServiceClass) {
            serviceConfig.setSuperServiceClass(superServiceClass);
            return this;
        }

        public Builder superServiceImplClass(String superServiceImplClass) {
            serviceConfig.setSuperServiceImplClass(superServiceImplClass);
            return this;
        }

        public Builder enableServiceInterface() {
            serviceConfig.setServiceInterface(true);
            return this;
        }

        public Builder disableServiceInterface() {
            serviceConfig.setServiceInterface(false);
            return this;
        }

        public Builder formatServiceFileName(String formatServiceFileName) {
            serviceConfig.setServiceSuffix(formatServiceFileName.replace("%s", ""));
            return this;
        }

        public Builder formatServiceImplFileName(String formatServiceImplFileName) {
            serviceConfig.setServiceImplSuffix(formatServiceImplFileName.replace("%s", ""));
            return this;
        }

        public Builder enableFileOverride() {
            serviceConfig.setFileOverride(true);
            return this;
        }

        public Builder disableFileOverride() {
            serviceConfig.setFileOverride(false);
            return this;
        }

        public Builder serviceTemplate(String serviceTemplate) {
            serviceConfig.setServiceTemplate(serviceTemplate);
            return this;
        }

        public Builder serviceImplTemplate(String serviceImplTemplate) {
            serviceConfig.setServiceImplTemplate(serviceImplTemplate);
            return this;
        }

        // 从BaseBuilder迁移的方法
        public Builder disableInsert() {
            serviceConfig.setGenerateInsert(false);
            return this;
        }

        public Builder disableUpdate() {
            serviceConfig.setGenerateUpdate(false);
            return this;
        }

        public Builder disableDelete() {
            serviceConfig.setGenerateDelete(false);
            return this;
        }

        public Builder disableSelect() {
            serviceConfig.setGenerateSelect(false);
            return this;
        }

        public Builder disableOverrideMethods() {
            serviceConfig.setOverrideMethods(false);
            return this;
        }

        public ServiceConfig build() {
            return serviceConfig;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}