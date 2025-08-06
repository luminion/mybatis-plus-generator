package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class VoConfig {

    private List<CustomFileConfig> customFiles;
    private String package4VO = "vo";
    private String path4VO;

    // 从BaseConfig迁移的VO相关配置
    private boolean fileOverride = false;
    private boolean generateExport = true;

    // 模板相关配置
    private String voTemplate;

    public static class Builder {
        private final VoConfig voConfig = new VoConfig();

        public Builder customFiles(List<CustomFileConfig> customFiles) {
            voConfig.setCustomFiles(customFiles);
            return this;
        }

        public Builder package4VO(String package4VO) {
            voConfig.setPackage4VO(package4VO);
            return this;
        }

        public Builder path4VO(String path4VO) {
            voConfig.setPath4VO(path4VO);
            return this;
        }

        public Builder enableFileOverride() {
            voConfig.setFileOverride(true);
            return this;
        }

        public Builder disableFileOverride() {
            voConfig.setFileOverride(false);
            return this;
        }

        public Builder disableExport() {
            voConfig.setGenerateExport(false);
            return this;
        }

        public Builder voTemplate(String voTemplate) {
            voConfig.setVoTemplate(voTemplate);
            return this;
        }

        public VoConfig build() {
            return this.voConfig;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}