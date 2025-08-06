package io.github.bootystar.mybatisplus.generator.config;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Set;
import java.util.HashSet;

@Data
@Accessors(chain = true)
public class EntityConfig {

    // MyBatis-Plus官方配置属性
    private String superClass;
    private boolean enableSerialVersionUID = true;
    private boolean enableChainModel = false;
    private boolean enableLombok = false;
    private boolean enableTableFieldAnnotation = false;
    private boolean enableRemoveIsPrefix = false;
    private boolean activeRecord = false;
    private boolean enableColumnConstant = false;
    private String versionFieldName;
    private String logicDeleteFieldName;
    private NamingStrategy naming = NamingStrategy.no_change;
    private NamingStrategy columnNaming = NamingStrategy.no_change;
    private IdType idType;
    private boolean fileOverride = false;
    private String template;
    private Set<String> addSuperEntityColumns = new HashSet<>();
    private Set<String> addIgnoreColumns = new HashSet<>();
    private Set<String> addTableFills = new HashSet<>();

    public static class Builder {
        private final EntityConfig entityConfig = new EntityConfig();

        // MyBatis-Plus官方配置方法
        public Builder superClass(String superClass) {
            entityConfig.setSuperClass(superClass);
            return this;
        }

        public Builder enableSerialVersionUID() {
            entityConfig.setEnableSerialVersionUID(true);
            return this;
        }

        public Builder disableSerialVersionUID() {
            entityConfig.setEnableSerialVersionUID(false);
            return this;
        }

        public Builder enableChainModel() {
            entityConfig.setEnableChainModel(true);
            return this;
        }

        public Builder disableChainModel() {
            entityConfig.setEnableChainModel(false);
            return this;
        }

        public Builder enableLombok() {
            entityConfig.setEnableLombok(true);
            return this;
        }

        public Builder disableLombok() {
            entityConfig.setEnableLombok(false);
            return this;
        }

        public Builder enableTableFieldAnnotation() {
            entityConfig.setEnableTableFieldAnnotation(true);
            return this;
        }

        public Builder disableTableFieldAnnotation() {
            entityConfig.setEnableTableFieldAnnotation(false);
            return this;
        }

        public Builder enableRemoveIsPrefix() {
            entityConfig.setEnableRemoveIsPrefix(true);
            return this;
        }

        public Builder disableRemoveIsPrefix() {
            entityConfig.setEnableRemoveIsPrefix(false);
            return this;
        }

        public Builder enableActiveRecord() {
            entityConfig.setActiveRecord(true);
            return this;
        }

        public Builder disableActiveRecord() {
            entityConfig.setActiveRecord(false);
            return this;
        }

        public Builder enableColumnConstant() {
            entityConfig.setEnableColumnConstant(true);
            return this;
        }

        public Builder disableColumnConstant() {
            entityConfig.setEnableColumnConstant(false);
            return this;
        }

        public Builder versionColumnName(String versionColumnName) {
            entityConfig.setVersionFieldName(versionColumnName);
            return this;
        }

        public Builder logicDeleteColumnName(String logicDeleteColumnName) {
            entityConfig.setLogicDeleteFieldName(logicDeleteColumnName);
            return this;
        }

        public Builder naming(NamingStrategy naming) {
            entityConfig.setNaming(naming);
            return this;
        }

        public Builder columnNaming(NamingStrategy columnNaming) {
            entityConfig.setColumnNaming(columnNaming);
            return this;
        }

        public Builder idType(IdType idType) {
            entityConfig.setIdType(idType);
            return this;
        }

        public Builder enableFileOverride() {
            entityConfig.setFileOverride(true);
            return this;
        }

        public Builder disableFileOverride() {
            entityConfig.setFileOverride(false);
            return this;
        }

        public Builder template(String template) {
            entityConfig.setTemplate(template);
            return this;
        }

        public Builder addSuperEntityColumns(String... columns) {
            for (String column : columns) {
                entityConfig.getAddSuperEntityColumns().add(column);
            }
            return this;
        }

        public Builder addIgnoreColumns(String... columns) {
            for (String column : columns) {
                entityConfig.getAddIgnoreColumns().add(column);
            }
            return this;
        }

        public EntityConfig build() {
            return entityConfig;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}