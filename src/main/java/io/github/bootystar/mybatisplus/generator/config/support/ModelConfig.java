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

        Set<String> importCreateDTOPackages = this.importCreateDTOPackages(tableInfo);
        List<String> importCreateDTOFrameworkPackages = importCreateDTOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importCreateDTOFrameworkPackages", importCreateDTOFrameworkPackages);
        List<String> importCreateDTOJavaPackages = importCreateDTOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importCreateDTOJavaPackages", importCreateDTOJavaPackages);

        Set<String> importUpdateDTOPackages = this.importUpdateDTOPackages(tableInfo);
        List<String> importUpdateDTOFrameworkPackages = importUpdateDTOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importUpdateDTOFrameworkPackages", importUpdateDTOFrameworkPackages);
        List<String> importUpdateDTOJavaPackages = importUpdateDTOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importUpdateDTOJavaPackages", importUpdateDTOJavaPackages);

        Set<String> importQueryDTOPackages = this.importQueryDTOPackages(tableInfo);
        List<String> importQueryDTOFrameworkPackages = importQueryDTOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importQueryDTOFrameworkPackages", importQueryDTOFrameworkPackages);
        List<String> importQueryDTOJavaPackages = importQueryDTOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importQueryDTOJavaPackages", importQueryDTOJavaPackages);

        Set<String> importQueryVOPackages = this.importQueryVOPackages(tableInfo);
        List<String> importQueryVOFrameworkPackages = importQueryVOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importQueryVOFrameworkPackages", importQueryVOFrameworkPackages);
        List<String> importQueryVOJavaPackages = importQueryVOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importQueryVOJavaPackages", importQueryVOJavaPackages);

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

    private Set<String> importCreateDTOPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigurer().getGlobalConfig();
        TreeSet<String> importPackages = new TreeSet<>();
        List<TableField> fields = tableInfo.getFields();

        String size = globalConfig.resolveJavaApiPackage("validation.constraints.Size");
        String notBlank = globalConfig.resolveJavaApiPackage("validation.constraints.NotBlank");
        String notNull = globalConfig.resolveJavaApiPackage("validation.constraints.NotNull");
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
            String excelIgnoreUnannotated = globalConfig.resolveExcelApiPackage("annotation.ExcelIgnoreUnannotated");
            String excelProperty = globalConfig.resolveExcelApiPackage("annotation.ExcelProperty");
            importPackages.add(excelIgnoreUnannotated);
            importPackages.add(excelProperty);
        }
        return importPackages;
    }


    private Set<String> importUpdateDTOPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigurer().getGlobalConfig();
        TreeSet<String> importPackages = new TreeSet<>();
        this.resolveDocImportPackages(globalConfig, importPackages);
        String size = globalConfig.resolveJavaApiPackage("validation.constraints.Size");
        String notBlank = globalConfig.resolveJavaApiPackage("validation.constraints.NotBlank");
        String notNull = globalConfig.resolveJavaApiPackage("validation.constraints.NotNull");
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

    private Set<String> importQueryDTOPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigurer().getGlobalConfig();
        TreeSet<String> importPackages = new TreeSet<>();
        this.resolveDocImportPackages(globalConfig, importPackages);
        importPackages.add(List.class.getCanonicalName());
        if (queryDTOExtendsEntity) {
            importPackages.add(tableInfo.getConfigurer().getOutputConfig().getClassCanonicalName(tableInfo).get(OutputFile.entity.name()));
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

    private Set<String> importQueryVOPackages(TableInfo tableInfo) {
        TreeSet<String> importPackages = new TreeSet<>();
        GlobalConfig globalConfig = tableInfo.getConfigurer().getGlobalConfig();
        this.resolveDocImportPackages(globalConfig, importPackages);
        if (globalConfig.isGenerateExport()) {
            String excelIgnoreUnannotated = globalConfig.resolveExcelApiPackage("annotation.ExcelIgnoreUnannotated");
            importPackages.add(excelIgnoreUnannotated);
        }
        if (queryVOExtendsEntity) {
            importPackages.add(tableInfo.getConfigurer().getOutputConfig().getClassCanonicalName(tableInfo).get(OutputFile.entity.name()));
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
                String excelProperty = globalConfig.resolveExcelApiPackage("annotation.ExcelProperty");
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