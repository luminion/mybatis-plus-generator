package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomFileConfig {

    private String templatePath;
    private String filePath;
    private boolean enabled = true;

    public static class Builder {
        private final CustomFileConfig customFileConfig = new CustomFileConfig();

        public Builder templatePath(String templatePath) {
            customFileConfig.setTemplatePath(templatePath);
            return this;
        }

        public Builder filePath(String filePath) {
            customFileConfig.setFilePath(filePath);
            return this;
        }

        public Builder enabled(boolean enabled) {
            customFileConfig.setEnabled(enabled);
            return this;
        }

        public CustomFileConfig build() {
            return this.customFileConfig;
        }
    }
}