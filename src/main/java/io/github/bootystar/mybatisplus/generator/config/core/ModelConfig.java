package io.github.bootystar.mybatisplus.generator.config.core;

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
@Getter
public class ModelConfig implements ITemplate {
    protected ModelConfig() {
    }

    /**
     * 查询dto继承实体类
     */
    protected boolean queryDTOExtendsEntity;

    /**
     * vo继承实体类
     */
    protected boolean queryVOExtendsEntity;

    /**
     * 使用新增dto作为导入dto
     */
    protected boolean importOnCreateDTO;

    /**
     * 使用vo作为导出vo
     */
    protected boolean exportOnQueryVO;

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = ITemplate.super.renderData(tableInfo);
        data.put("queryDTOExtendsEntity", this.queryDTOExtendsEntity);
        data.put("queryVOExtendsEntity", this.queryVOExtendsEntity);
        data.put("importOnCreateDTO", this.importOnCreateDTO);
        data.put("exportOnQueryVO", this.exportOnQueryVO);
     
        Set<String> importCreateDTOPackages = this.importCreateDTOPackages(tableInfo);
        Set<String> importUpdateDTOPackages = this.importUpdateDTOPackages(tableInfo);

        List<String> importCreateDTOFrameworkPackages = importCreateDTOPackages.stream()
                .filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importCreateDTOFrameworkPackages", importCreateDTOFrameworkPackages);
        List<String> importCreateDTOJavaPackages = importCreateDTOPackages.stream()
                .filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importCreateDTOJavaPackages", importCreateDTOJavaPackages);

        List<String> importUpdateDTOFrameworkPackages = importUpdateDTOPackages.stream()
                .filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importUpdateDTOFrameworkPackages",
                importUpdateDTOFrameworkPackages);
        List<String> importUpdateDTOJavaPackages = importUpdateDTOPackages.stream()
                .filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importUpdateDTOJavaPackages", importUpdateDTOJavaPackages);

        // List<String> importQueryDTOFrameworkPackages =
        // importQueryDTOPackages.stream().filter(pkg ->
        // !pkg.startsWith("java")).collect(Collectors.toList());
        // data.put("importQueryDTOFrameworkPackages", importQueryDTOFrameworkPackages);
        // List<String> importQueryDTOJavaPackages =
        // importQueryDTOPackages.stream().filter(pkg ->
        // pkg.startsWith("java")).collect(Collectors.toList());
        // data.put("importQueryDTOJavaPackages", importQueryDTOJavaPackages);
        //
        // List<String> importVOFrameworkPackages = importVOPackages.stream().filter(pkg
        // -> !pkg.startsWith("java")).collect(Collectors.toList());
        // data.put("importVOFrameworkPackages", importVOFrameworkPackages);
        // List<String> importVOJavaPackages = importVOPackages.stream().filter(pkg ->
        // pkg.startsWith("java")).collect(Collectors.toList());
        // data.put("importVOJavaPackages", importVOJavaPackages);
        //
        //
        return data;
    }

    private Set<String> importCreateDTOPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigAdapter().getGlobalConfig();
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
                }else{
                    importPackages.add(notNull);
                }
            } 
            if (isString) {
                importPackages.add(size);    
            }
        }
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
        if (importOnCreateDTO) {
            String excelIgnoreUnannotated = globalConfig
                    .resolveExcelApiPackage("excel.annotation.ExcelIgnoreUnannotated");
            String excelProperty = globalConfig.resolveExcelApiPackage("excel.annotation.ExcelProperty");
            importPackages.add(excelIgnoreUnannotated);
            importPackages.add(excelProperty);
        }

        return importPackages;
    }

    private Set<String> importUpdateDTOPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigAdapter().getGlobalConfig();
        TreeSet<String> importPackages = new TreeSet<>();
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
                }else{
                    importPackages.add(notNull);
                }
            }
            if (isString) {
                importPackages.add(size);    
            }
        }
        return importPackages;
    }

}
