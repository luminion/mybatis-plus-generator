package io.github.bootystar.mybatisplus.generator.config;

import io.github.bootystar.mybatisplus.generator.info.ClassInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Accessors(chain = true)
public class DtoConfig {

    private List<CustomFileConfig> customFiles;
    private String package4DTO = "dto";
    private String path4DTO;

    // 从BaseConfig迁移的DTO相关配置
    private boolean fileOverride = false;
    private boolean generateInsert = true;
    private boolean generateUpdate = true;
    private boolean generateSelect = true;
    private boolean generateImport = true;
    private boolean generateExport = true;
    private Collection<String> editExcludeColumns;
    private String excelBasePackage = "cn.idev.excel";
    private Set<String> importPackages4DTO;
    private ClassInfo selectDTO;
    private boolean useMapSelectDTO = false;

    // 模板相关配置
    private String insertDtoTemplate;
    private String updateDtoTemplate;
    private String selectDtoTemplate;

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

        public Builder path4DTO(String path4DTO) {
            dtoConfig.setPath4DTO(path4DTO);
            return this;
        }

        public Builder enableFileOverride() {
            dtoConfig.setFileOverride(true);
            return this;
        }

        public Builder disableFileOverride() {
            dtoConfig.setFileOverride(false);
            return this;
        }

        public Builder disableInsert() {
            dtoConfig.setGenerateInsert(false);
            return this;
        }

        public Builder disableUpdate() {
            dtoConfig.setGenerateUpdate(false);
            return this;
        }

        public Builder disableSelect() {
            dtoConfig.setGenerateSelect(false);
            return this;
        }

        public Builder disableImport() {
            dtoConfig.setGenerateImport(false);
            return this;
        }

        public Builder disableExport() {
            dtoConfig.setGenerateExport(false);
            return this;
        }

        public Builder editExcludeColumns(String... columns) {
            dtoConfig.setEditExcludeColumns(List.of(columns));
            return this;
        }

        public Builder useFastExcel() {
            dtoConfig.setExcelBasePackage("cn.idev.excel");
            return this;
        }

        public Builder useEasyExcel() {
            dtoConfig.setExcelBasePackage("com.alibaba.excel");
            return this;
        }

        public Builder importPackages4DTO(Set<String> importPackages) {
            dtoConfig.setImportPackages4DTO(importPackages);
            return this;
        }

        public Builder selectDTO(ClassInfo selectDTO) {
            dtoConfig.setSelectDTO(selectDTO);
            return this;
        }

        public Builder useMapSelectDTO() {
            dtoConfig.setUseMapSelectDTO(true);
            return this;
        }

        public Builder insertDtoTemplate(String insertDtoTemplate) {
            dtoConfig.setInsertDtoTemplate(insertDtoTemplate);
            return this;
        }

        public Builder updateDtoTemplate(String updateDtoTemplate) {
            dtoConfig.setUpdateDtoTemplate(updateDtoTemplate);
            return this;
        }

        public Builder selectDtoTemplate(String selectDtoTemplate) {
            dtoConfig.setSelectDtoTemplate(selectDtoTemplate);
            return this;
        }

        public DtoConfig build() {
            return this.dtoConfig;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}