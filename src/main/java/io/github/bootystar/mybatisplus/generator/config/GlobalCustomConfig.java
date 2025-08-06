package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Accessors(chain = true)
public class GlobalCustomConfig {

    private Map<String, Boolean> orderColumnMap;
    private boolean swaggerAnnotationWithUUID = false;
    private String swaggerUUID;
    private Map<String, String> extraFieldSuffixMap;
    private boolean useMapSelectDTO = false;
    private String nowTime;
    private List<JdbcType> jdbcTimeTypes;
    private Set<String> importPackages4DTO;
    private String orderBySql;

    public static class Builder {
        private final GlobalCustomConfig globalCustomConfig = new GlobalCustomConfig();

        public Builder orderColumnMap(Map<String, Boolean> orderColumnMap) {
            globalCustomConfig.setOrderColumnMap(orderColumnMap);
            return this;
        }

        public Builder swaggerAnnotationWithUUID(boolean swaggerAnnotationWithUUID) {
            globalCustomConfig.setSwaggerAnnotationWithUUID(swaggerAnnotationWithUUID);
            return this;
        }

        public Builder swaggerUUID(String swaggerUUID) {
            globalCustomConfig.setSwaggerUUID(swaggerUUID);
            return this;
        }

        public Builder extraFieldSuffixMap(Map<String, String> extraFieldSuffixMap) {
            globalCustomConfig.setExtraFieldSuffixMap(extraFieldSuffixMap);
            return this;
        }

        public Builder useMapSelectDTO(boolean useMapSelectDTO) {
            globalCustomConfig.setUseMapSelectDTO(useMapSelectDTO);
            return this;
        }

        public Builder nowTime(String nowTime) {
            globalCustomConfig.setNowTime(nowTime);
            return this;
        }

        public Builder jdbcTimeTypes(List<JdbcType> jdbcTimeTypes) {
            globalCustomConfig.setJdbcTimeTypes(jdbcTimeTypes);
            return this;
        }

        public Builder importPackages4DTO(Set<String> importPackages4DTO) {
            globalCustomConfig.setImportPackages4DTO(importPackages4DTO);
            return this;
        }

        public Builder orderBySql(String orderBySql) {
            globalCustomConfig.setOrderBySql(orderBySql);
            return this;
        }

        public GlobalCustomConfig build() {
            return this.globalCustomConfig;
        }
    }
}