package io.github.bootystar.mybatisplus.generator.config.core;

import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;

import java.util.Map;
import java.util.Set;

/**
 * @author bootystar
 */
public class ModelConfig implements ITemplate {

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
