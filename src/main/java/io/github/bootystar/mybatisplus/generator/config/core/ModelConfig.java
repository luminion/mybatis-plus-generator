package io.github.bootystar.mybatisplus.generator.config.core;

import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import lombok.Getter;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

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
    protected Function<String, String> converterEntityInsertDTOName = name -> name;
    /**
     * 修改dto名称转换
     */
    protected Function<String, String> converterEntityUpdateDTOName = name -> name;
    /**
     * 查询dto名称转换
     */
    protected Function<String, String> converterEntityQueryDTOName = name -> name;
    /**
     * vo名称转换
     */
    protected Function<String, String> converterEntityVOName = name -> name;

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
        Map<String, Object> map = ITemplate.super.renderData(tableInfo);
        map.put("extendsQueryDTO", this.extendsQueryDTO);
        map.put("extendsVO", this.extendsVO);
        GlobalConfig globalConfig = tableInfo.getConfigAdapter().getGlobalConfig();
        Set<String> importPackages = tableInfo.getImportPackages();
        if (globalConfig.isSpringdoc()) {
            importPackages.add("io.swagger.v3.oas.annotations.media.Schema");
        }
        if (globalConfig.isSwagger()) {
            importPackages.add("io.swagger.annotations.ApiModel");
            importPackages.add("io.swagger.annotations.ApiModelProperty");
        }
        if (!globalConfig.isKotlin() && globalConfig.isLombok()) {
            if (globalConfig.isChain()) {
                importPackages.add("lombok.experimental.Accessors");
            }
            importPackages.add("lombok.Data");
        }

        return map;
    }
}
