package io.github.bootystar.mybatisplus.generator.config.support;

import io.github.bootystar.mybatisplus.generator.config.enums.OutputFile;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.config.po.TableField.MetaInfo;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 领域模型配置
 *
 * @author bootystar
 */
public class ModelConfig implements ITemplate {

    /**
     * 查询dto继承实体类
     */
    @Getter
    protected boolean queryDTOExtendsEntity;

    /**
     * vo继承实体类
     */
    @Getter
    protected boolean queryVOExtendsEntity;

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = ITemplate.super.renderData(tableInfo);
        data.put("queryDTOExtendsEntity", this.queryDTOExtendsEntity);
        data.put("queryVOExtendsEntity", this.queryVOExtendsEntity);

        Set<String> insertDTOImportPackages = this.insertDTOImportPackages(tableInfo);
        List<String> insertDTOImportPackages4Framework = insertDTOImportPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("insertDTOImportPackages4Framework", insertDTOImportPackages4Framework);
        List<String> insertDTOImportPackages4Java = insertDTOImportPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("insertDTOImportPackages4Java", insertDTOImportPackages4Java);

        Set<String> updateDTOImportPackages = this.updateDTOImportPackages(tableInfo);
        List<String> updateDTOImportPackages4Framework = updateDTOImportPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("updateDTOImportPackages4Framework", updateDTOImportPackages4Framework);
        List<String> updateDTOImportPackages4Java = updateDTOImportPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("updateDTOImportPackages4Java", updateDTOImportPackages4Java);

        Set<String> queryDTOImportPackages = this.queryDTOImportPackages(tableInfo);
        List<String> queryDTOImportPackages4Framework = queryDTOImportPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("queryDTOImportPackages4Framework", queryDTOImportPackages4Framework);
        List<String> queryDTOImportPackages4Java = queryDTOImportPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("queryDTOImportPackages4Java", queryDTOImportPackages4Java);

        Set<String> queryVOImportPackages = this.queryVOImportPackages(tableInfo);
        List<String> queryVOImportPackages4Framework = queryVOImportPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("queryVOImportPackages4Framework", queryVOImportPackages4Framework);
        List<String> queryVOImportPackages4Java = queryVOImportPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("queryVOImportPackages4Java", queryVOImportPackages4Java);

        return data;
    }

    private void resolveDocImportPackages(GlobalConfig globalConfig, TreeSet<String> importPackages) {
        if (globalConfig.isSpringdoc()) {
            importPackages.add("io.swagger.v3.oas.annotations.media.Schema");
        }
        if (globalConfig.isSwagger()) {
            importPackages.add("io.swagger.annotations.ApiModel");
            importPackages.add("io.swagger.annotations.ApiModelProperty");
        }
        if (globalConfig.isLombok()) {
            if (globalConfig.isChainModel()) {
                importPackages.add("lombok.experimental.Accessors");
            }
            importPackages.add("lombok.Data");
        }
    }

    private Set<String> insertDTOImportPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigurer().getGlobalConfig();
        TreeSet<String> importPackages = new TreeSet<>();
        List<TableField> fields = tableInfo.getFields();

        String size = globalConfig.resolveJakartaClassCanonicalName("validation.constraints.Size");
        String notBlank = globalConfig.resolveJakartaClassCanonicalName("validation.constraints.NotBlank");
        String notNull = globalConfig.resolveJakartaClassCanonicalName("validation.constraints.NotNull");
        for (TableField field : fields) {
            if (field.isKeyFlag()) {
                continue;
            }
            if (field.isVersionField()) {
                continue;
            }
            if (field.isLogicDeleteField()) {
                continue;
            }
            if ("INSERT".equals(field.getFill()) || "INSERT_UPDATE".equals(field.getFill())) {
                continue;
            }
            Optional.ofNullable(field.getColumnType().getPkg()).ifPresent(importPackages::add);
            MetaInfo metaInfo = field.getMetaInfo();
            boolean isString = "String".equals(field.getPropertyType());
            boolean notnullFlag = !metaInfo.isNullable() && metaInfo.getDefaultValue() == null;
            if (notnullFlag) {
                if (isString) {
                    importPackages.add(notBlank);
                } else {
                    importPackages.add(notNull);
                }
            }
            if (isString) {
                importPackages.add(size);
            }
        }
        this.resolveDocImportPackages(globalConfig, importPackages);
        if (globalConfig.isGenerateImport()) {
            String excelIgnoreUnannotated = globalConfig.resolveExcelClassCanonicalName("annotation.ExcelIgnoreUnannotated");
            String excelProperty = globalConfig.resolveExcelClassCanonicalName("annotation.ExcelProperty");
            importPackages.add(excelIgnoreUnannotated);
            importPackages.add(excelProperty);
        }
        return importPackages;
    }


    private Set<String> updateDTOImportPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigurer().getGlobalConfig();
        TreeSet<String> importPackages = new TreeSet<>();
        this.resolveDocImportPackages(globalConfig, importPackages);
        String size = globalConfig.resolveJakartaClassCanonicalName("validation.constraints.Size");
        String notBlank = globalConfig.resolveJakartaClassCanonicalName("validation.constraints.NotBlank");
        String notNull = globalConfig.resolveJakartaClassCanonicalName("validation.constraints.NotNull");
        for (TableField field : tableInfo.getFields()) {
            if (field.isLogicDeleteField()) {
                continue;
            }
            if ("INSERT".equals(field.getFill()) || "INSERT_UPDATE".equals(field.getFill())) {
                continue;
            }
            Optional.ofNullable(field.getColumnType().getPkg()).ifPresent(importPackages::add);
            boolean notnullFlag = field.isKeyFlag() || field.isVersionField();
            boolean isString = "String".equals(field.getPropertyType());
            if (notnullFlag) {
                if (isString) {
                    importPackages.add(notBlank);
                } else {
                    importPackages.add(notNull);
                }
            }
            if (isString) {
                importPackages.add(size);
            }
        }
        return importPackages;
    }

    private Set<String> queryDTOImportPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigurer().getGlobalConfig();
        TreeSet<String> importPackages = new TreeSet<>();
        this.resolveDocImportPackages(globalConfig, importPackages);
        importPackages.add(List.class.getCanonicalName());
        if (queryDTOExtendsEntity) {
            importPackages.add(tableInfo.getConfigurer().getOutputConfig().getOutputClassCanonicalNameMap(tableInfo).get(OutputFile.entity.name()));
            if (globalConfig.isLombok()){
                importPackages.add("lombok.EqualsAndHashCode");
            }
        }
        for (TableField field : tableInfo.getFields()) {
            if (field.isLogicDeleteField()) {
                continue;
            }
            Optional.ofNullable(field.getColumnType().getPkg()).ifPresent(importPackages::add);
        }

        return importPackages;
    }

    private Set<String> queryVOImportPackages(TableInfo tableInfo) {
        TreeSet<String> importPackages = new TreeSet<>();
        GlobalConfig globalConfig = tableInfo.getConfigurer().getGlobalConfig();
        this.resolveDocImportPackages(globalConfig, importPackages);
        if (globalConfig.isGenerateExport()) {
            String excelIgnoreUnannotated = globalConfig.resolveExcelClassCanonicalName("annotation.ExcelIgnoreUnannotated");
            importPackages.add(excelIgnoreUnannotated);
        }
        if (queryVOExtendsEntity) {
            importPackages.add(tableInfo.getConfigurer().getOutputConfig().getOutputClassCanonicalNameMap(tableInfo).get(OutputFile.entity.name()));
            if (globalConfig.isLombok()){
                importPackages.add("lombok.EqualsAndHashCode");
            }
        } else {
            for (TableField field : tableInfo.getFields()) {
                if (field.isLogicDeleteField()) {
                    continue;
                }
                Optional.ofNullable(field.getColumnType().getPkg()).ifPresent(importPackages::add);
            }
            if (globalConfig.isGenerateExport()) {
                String excelProperty = globalConfig.resolveExcelClassCanonicalName("annotation.ExcelProperty");
                importPackages.add(excelProperty);
            }
        }

        return importPackages;
    }

    public Adapter adapter() {
        return new Adapter(this);
    }

    public static class Adapter {
        private final ModelConfig config;

        public Adapter(ModelConfig config) {
            this.config = config;
        }

        /**
         * 查询dto继承实体类
         *
         * @return this
         */
        public Adapter enableQueryDTOExtendsEntity() {
            this.config.queryDTOExtendsEntity = true;
            return this;
        }

        /**
         * 查询vo继承实体类
         *
         * @return this
         */
        public Adapter enableQueryVOExtendsEntity() {
            this.config.queryVOExtendsEntity = true;
            return this;
        }
        
    }

}