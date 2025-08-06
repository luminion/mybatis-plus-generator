package io.github.bootystar.mybatisplus.generator.config;

import io.github.bootystar.mybatisplus.generator.info.ClassInfo;
import io.github.bootystar.mybatisplus.generator.strategy.ExtraFieldGenerateStrategy;
import io.github.bootystar.mybatisplus.generator.strategy.support.ExtraFieldStrategyAuto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Accessors(chain = true)
public class GlobalCustomConfig {

    // 从BaseConfig迁移的全局配置
    private boolean swaggerModelWithAnnotation = false;
    private boolean swaggerAnnotationWithUUID = false;
    private String swaggerUUID;
    private boolean extraClassLinkComment = true;
    private String nowTime;
    private List<JdbcType> jdbcTimeTypes;
    private boolean overrideInitSuffixBuilder = false;
    private Map<String, String> extraFieldSuffixMap = new LinkedHashMap<>();

    // 非通用相关配置
    private boolean overrideMethods = true;
    private ClassInfo mapperDTO;
    private ClassInfo dynamicServiceClassInfo;
    private ExtraFieldGenerateStrategy extraFieldStrategy = new ExtraFieldStrategyAuto();
    private boolean actualType4ServiceMethodParam = false;

    public static class Builder {
        private final GlobalCustomConfig globalCustomConfig = new GlobalCustomConfig();

        // 从BaseBuilder迁移的方法
        public Builder enableSwaggerModelWithAnnotation() {
            globalCustomConfig.setSwaggerModelWithAnnotation(true);
            return this;
        }

        public Builder enableSwaggerAnnotationWithUUID() {
            globalCustomConfig.setSwaggerAnnotationWithUUID(true);
            return this;
        }

        public Builder swaggerUUID(String swaggerUUID) {
            globalCustomConfig.setSwaggerUUID(swaggerUUID);
            return this;
        }

        public Builder disableExtraClassLinkComment() {
            globalCustomConfig.setExtraClassLinkComment(false);
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

        public Builder extraFieldSuffixMap(Map<String, String> extraFieldSuffixMap) {
            globalCustomConfig.setExtraFieldSuffixMap(extraFieldSuffixMap);
            return this;
        }

        public Builder disableOverrideMethods() {
            globalCustomConfig.setOverrideMethods(false);
            return this;
        }

        public Builder mapperDTO(ClassInfo mapperDTO) {
            globalCustomConfig.setMapperDTO(mapperDTO);
            return this;
        }

        public Builder dynamicServiceClassInfo(ClassInfo dynamicServiceClassInfo) {
            globalCustomConfig.setDynamicServiceClassInfo(dynamicServiceClassInfo);
            return this;
        }

        public Builder extraFieldStrategy(ExtraFieldGenerateStrategy extraFieldStrategy) {
            globalCustomConfig.setExtraFieldStrategy(extraFieldStrategy);
            return this;
        }

        public Builder actualType4ServiceMethodParam(boolean actualType4ServiceMethodParam) {
            globalCustomConfig.setActualType4ServiceMethodParam(actualType4ServiceMethodParam);
            return this;
        }

        public GlobalCustomConfig build() {
            return this.globalCustomConfig;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}