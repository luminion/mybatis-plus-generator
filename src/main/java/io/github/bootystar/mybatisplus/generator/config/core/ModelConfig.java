package io.github.bootystar.mybatisplus.generator.config.core;

import com.baomidou.mybatisplus.annotation.FieldFill;
import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.fill.IFill;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import lombok.Getter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author bootystar
 */
@Getter
public class ModelConfig implements ITemplate {
    protected ModelConfig() {
    }

    /**
     * 文件覆盖
     */
    protected boolean fileOverride;
    
    /**
     * 新增dto名称转换
     */
    protected Function<String, String> converterEntityInsertDTOName = name -> name + ConstVal.ENTITY_INSERT_DTO;
    /**
     * 修改dto名称转换
     */
    protected Function<String, String> converterEntityUpdateDTOName = name -> name + ConstVal.ENTITY_UPDATE_DTO ;
    /**
     * 查询dto名称转换
     */
    protected Function<String, String> converterEntityQueryDTOName = name -> name + ConstVal.ENTITY_QUERY_DTO;
    /**
     * vo名称转换
     */
    protected Function<String, String> converterEntityVOName = name -> name + ConstVal.ENTITY_VO;

    /**
     * 查询dto继承实体类
     */
    protected boolean extendsQueryDTO;

    /**
     * vo继承实体类
     */
    protected boolean extendsVO;

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> data = ITemplate.super.renderData(tableInfo);
        data.put("extendsQueryDTO", this.extendsQueryDTO);
        data.put("extendsVO", this.extendsVO);
        GlobalConfig globalConfig = tableInfo.getConfigAdapter().getGlobalConfig();
        String superClass = tableInfo.getConfigAdapter().getEntityConfig().getSuperClass();
        TreeSet<String> importPackages = tableInfo.getImportPackages().stream()
                .filter(e -> !e.startsWith("com.baomidou.mybatisplus.annotation"))
                .filter(e -> !e.startsWith("java.io.Serializable"))
                .filter(e -> superClass==null || !e.startsWith(superClass))
                .collect(Collectors.toCollection(TreeSet::new));
        if (globalConfig.isSpringdoc()) {
            importPackages.add("io.swagger.v3.oas.annotations.media.Schema");
        }
        if (globalConfig.isSwagger()) {
            importPackages.add("io.swagger.annotations.ApiModel");
            importPackages.add("io.swagger.annotations.ApiModelProperty");
        }
        if (!globalConfig.isKotlin() && globalConfig.isLombok()) {
            if (globalConfig.isChainModel()) {
                importPackages.add("lombok.experimental.Accessors");
            }
            importPackages.add("lombok.Data");
            importPackages.add("lombok.EqualsAndHashCode");
        }

        TreeSet<String> importInsertDTOPackages = new TreeSet<>(importPackages);
        TreeSet<String> importUpdateDTOPackages = new TreeSet<>(importPackages);
        TreeSet<String> importQueryDTOPackages = new TreeSet<>(importPackages);
        TreeSet<String> importVOPackages = new TreeSet<>(importPackages);
        HashSet<String> insertExcludeColumns = new HashSet<>();
        HashSet<String> updateExcludeColumns = new HashSet<>();

        for (TableField field : tableInfo.getFields()) {
            if(field.isLogicDeleteField()){
                continue;
            }
            if (field.isKeyFlag()){
                importUpdateDTOPackages.add(globalConfig.resolveJavaApiPackage("validation.constraints.NotNull"));
                continue;
            }
            if (field.isVersionField()){
                importUpdateDTOPackages.add(globalConfig.resolveJavaApiPackage("validation.constraints.NotNull"));
                continue;
            }
            if ("INSERT".equals(field.getFill()) || "INSERT_UPDATE".equals(field.getFill())){
                insertExcludeColumns.add(field.getColumnName());
                continue;
            }
            if ("UPDATE".equals(field.getFill()) || "INSERT_UPDATE".equals(field.getFill())){
                updateExcludeColumns.add(field.getColumnName());
                continue;
            }
            boolean isString = "String".equals(field.getPropertyType());
            boolean isNullable = field.getMetaInfo().isNullable();
            boolean hasDefaultValue = field.getMetaInfo().getDefaultValue()!=null;
            if (isString){
                importInsertDTOPackages.add(globalConfig.resolveJavaApiPackage("validation.constraints.Size"));
                importUpdateDTOPackages.add(globalConfig.resolveJavaApiPackage("validation.constraints.Size"));
            }
            if (!isNullable && !hasDefaultValue){
                if(isString){
                    importInsertDTOPackages.add(globalConfig.resolveJavaApiPackage("validation.constraints.NotBlank"));
                    importUpdateDTOPackages.add(globalConfig.resolveJavaApiPackage("validation.constraints.NotBlank"));
                }else{
                    importInsertDTOPackages.add(globalConfig.resolveJavaApiPackage("validation.constraints.NotNull"));
                    importUpdateDTOPackages.add(globalConfig.resolveJavaApiPackage("validation.constraints.NotNull"));
                }
            }
        }
        
        boolean generateImport = globalConfig.isGenerateImport();
        boolean generateExport = globalConfig.isGenerateExport();
        String excelProperty = globalConfig.resolveExcelApiPackage("annotation.ExcelProperty");
        String excelIgnoreUnannotated = globalConfig.resolveExcelApiPackage("annotation.ExcelIgnoreUnannotated");
        if (generateImport){
            importInsertDTOPackages.add(excelProperty);
            importInsertDTOPackages.add(excelIgnoreUnannotated);
        }
        if (generateExport){
            importVOPackages.add(excelProperty);
            importVOPackages.add(excelIgnoreUnannotated);
        }
        importQueryDTOPackages.add("java.util.List");
        String entityClassFullName = tableInfo.getConfigAdapter().getPackageInfo().get(ConstVal.ENTITY) + "." + tableInfo.getEntityName();
        if (extendsQueryDTO){
            importQueryDTOPackages.add(entityClassFullName);
        }
        if (extendsVO){
            importVOPackages.add(entityClassFullName);
        }

        List<String> importInsertDTOFrameworkPackages = importInsertDTOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importInsertDTOFrameworkPackages", importInsertDTOFrameworkPackages);
        List<String> importInsertDTOJavaPackages = importInsertDTOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importInsertDTOJavaPackages", importInsertDTOJavaPackages);
        
        List<String> importUpdateDTOFrameworkPackages = importUpdateDTOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importUpdateDTOFrameworkPackages", importUpdateDTOFrameworkPackages);
        List<String> importUpdateDTOJavaPackages = importUpdateDTOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importUpdateDTOJavaPackages", importUpdateDTOJavaPackages);
        
        List<String> importQueryDTOFrameworkPackages = importQueryDTOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importQueryDTOFrameworkPackages", importQueryDTOFrameworkPackages);
        List<String> importQueryDTOJavaPackages = importQueryDTOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importQueryDTOJavaPackages", importQueryDTOJavaPackages);
        
        List<String> importVOFrameworkPackages = importVOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importVOFrameworkPackages", importVOFrameworkPackages);
        List<String> importVOJavaPackages = importVOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importVOJavaPackages", importVOJavaPackages);
        
        data.put("insertExcludeColumns", insertExcludeColumns);
        data.put("updateExcludeColumns", updateExcludeColumns);
        
        
        return data;
    }
}
