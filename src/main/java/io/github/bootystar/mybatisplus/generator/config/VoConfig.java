package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class VoConfig {

    private List<CustomFileConfig> customFiles;
    private String package4VO = "com.example.vo";

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

        public VoConfig build() {
            return this.voConfig;
        }
    }
}