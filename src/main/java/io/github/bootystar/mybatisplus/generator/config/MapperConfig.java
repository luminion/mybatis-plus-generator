package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Map;
import java.util.LinkedHashMap;

@Data
@Accessors(chain = true)
public class MapperConfig {

    // MyBatis-Plus官方配置属性
    private String superClass;
    private boolean enableMapperAnnotation = false;
    private boolean enableBaseResultMap = false;
    private boolean enableBaseColumnList = false;
    private String mapperSuffix = "Mapper";
    private String xmlSuffix = "Mapper";
    private boolean fileOverride = false;
    private String mapperTemplate;
    private String mapperXmlTemplate;

    // 自定义mapper相关配置（从BaseConfig迁移）
    private Map<String, Boolean> orderColumnMap = new LinkedHashMap<>();

    public static class Builder {
        private final MapperConfig mapperConfig = new MapperConfig();

        // MyBatis-Plus官方配置方法
        public Builder superClass(String superClass) {
            mapperConfig.setSuperClass(superClass);
            return this;
        }

        public Builder enableMapperAnnotation() {
            mapperConfig.setEnableMapperAnnotation(true);
            return this;
        }

        public Builder disableMapperAnnotation() {
            mapperConfig.setEnableMapperAnnotation(false);
            return this;
        }

        public Builder enableBaseResultMap() {
            mapperConfig.setEnableBaseResultMap(true);
            return this;
        }

        public Builder disableBaseResultMap() {
            mapperConfig.setEnableBaseResultMap(false);
            return this;
        }

        public Builder enableBaseColumnList() {
            mapperConfig.setEnableBaseColumnList(true);
            return this;
        }

        public Builder disableBaseColumnList() {
            mapperConfig.setEnableBaseColumnList(false);
            return this;
        }

        public Builder formatMapperFileName(String formatMapperFileName) {
            mapperConfig.setMapperSuffix(formatMapperFileName.replace("%s", ""));
            return this;
        }

        public Builder formatXmlFileName(String formatXmlFileName) {
            mapperConfig.setXmlSuffix(formatXmlFileName.replace("%s", ""));
            return this;
        }

        public Builder enableFileOverride() {
            mapperConfig.setFileOverride(true);
            return this;
        }

        public Builder disableFileOverride() {
            mapperConfig.setFileOverride(false);
            return this;
        }

        public Builder mapperTemplate(String mapperTemplate) {
            mapperConfig.setMapperTemplate(mapperTemplate);
            return this;
        }

        public Builder mapperXmlTemplate(String mapperXmlTemplate) {
            mapperConfig.setMapperXmlTemplate(mapperXmlTemplate);
            return this;
        }

        // 自定义配置方法（从BaseBuilder迁移）
        public Builder sortColumn(String column, boolean desc) {
            if (mapperConfig.getOrderColumnMap() == null) {
                mapperConfig.setOrderColumnMap(new LinkedHashMap<>());
            }
            mapperConfig.getOrderColumnMap().put(column, desc);
            return this;
        }

        public Builder sortColumnClear() {
            if (mapperConfig.getOrderColumnMap() != null) {
                mapperConfig.getOrderColumnMap().clear();
            }
            return this;
        }

        public MapperConfig build() {
            return mapperConfig;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}