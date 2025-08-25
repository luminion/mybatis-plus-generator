package io.github.bootystar.mybatisplus.generator.config.core;

import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.po.TableField;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.config.rules.IColumnType;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import lombok.Getter;

import java.util.List;
import java.util.Map;
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
        if (globalConfig.isLombok()) {
            if (globalConfig.isChainModel()) {
                importPackages.add("lombok.experimental.Accessors");
            }
            importPackages.add("lombok.Data");
            importPackages.add("lombok.EqualsAndHashCode");
        }

        TreeSet<String> importCreateDTOPackages = new TreeSet<>(importPackages);
        TreeSet<String> importUpdateDTOPackages = new TreeSet<>(importPackages);
        

        List<String> importInsertDTOFrameworkPackages = importCreateDTOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importInsertDTOFrameworkPackages", importInsertDTOFrameworkPackages);
        List<String> importInsertDTOJavaPackages = importCreateDTOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importInsertDTOJavaPackages", importInsertDTOJavaPackages);
        
        List<String> importUpdateDTOFrameworkPackages = importUpdateDTOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importUpdateDTOFrameworkPackages", importUpdateDTOFrameworkPackages);
        List<String> importUpdateDTOJavaPackages = importUpdateDTOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
        data.put("importUpdateDTOJavaPackages", importUpdateDTOJavaPackages);
//        
//        List<String> importQueryDTOFrameworkPackages = importQueryDTOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
//        data.put("importQueryDTOFrameworkPackages", importQueryDTOFrameworkPackages);
//        List<String> importQueryDTOJavaPackages = importQueryDTOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
//        data.put("importQueryDTOJavaPackages", importQueryDTOJavaPackages);
//        
//        List<String> importVOFrameworkPackages = importVOPackages.stream().filter(pkg -> !pkg.startsWith("java")).collect(Collectors.toList());
//        data.put("importVOFrameworkPackages", importVOFrameworkPackages);
//        List<String> importVOJavaPackages = importVOPackages.stream().filter(pkg -> pkg.startsWith("java")).collect(Collectors.toList());
//        data.put("importVOJavaPackages", importVOJavaPackages);
//        
//        
        return data;
    }
    
    private Set<String> importCreateDTOPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigAdapter().getGlobalConfig();
        TreeSet<String> importPackages = new TreeSet<>();
        List<TableField> fields = tableInfo.getFields();
        for (TableField field : fields) {
            if (field.isKeyFlag()){
                continue;
            }
            if (field.isVersionField()){
                continue;
            }
            if (field.isLogicDeleteField()){
                continue;
            }
            if ("INSERT".equals(field.getFill()) || "INSERT_UPDATE".equals(field.getFill())){
                continue;
            }
            importPackages.add(field.getColumnType().getPkg());
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
        
        return importPackages;
    }
    
    private Set<String> importQueryDTOPackages(TableInfo tableInfo) {
        GlobalConfig globalConfig = tableInfo.getConfigAdapter().getGlobalConfig();
        TreeSet<String> importPackages = new TreeSet<>();
        importPackages.add("java.util.List");
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
            if (queryDTOExtendsEntity){
                importPackages.add("lombok.EqualsAndHashCode");
            }
        }
        if (queryDTOExtendsEntity){
            importPackages.add(tableInfo.getConfigAdapter().getOutputConfig().getPackageInfo().get(ConstVal.ENTITY) + "." + tableInfo.getEntityName());
        }
        for (TableField field : tableInfo.getFields()) {
            IColumnType columnType = field.getColumnType();
            if (null != columnType && null != columnType.getPkg()) {
                importPackages.add(columnType.getPkg());
            }
        }
        return importPackages;
    }
        
}
