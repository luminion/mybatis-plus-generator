package io.github.bootystar.mybatisplus.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class DtoConfig {

    private List<CustomFileConfig> customFiles;
    private String package4DTO = "com.example.dto";

    public static class Builder {
        private final DtoConfig dtoConfig = new DtoConfig();

        public Builder customFiles(List<CustomFileConfig> customFiles) {
            dtoConfig.setCustomFiles(customFiles);
            return this;
        }

        public Builder package4DTO(String package4DTO) {
            dtoConfig.setPackage4DTO(package4DTO);
            return this;
        }

        public DtoConfig build() {
            return this.dtoConfig;
        }
    }
}