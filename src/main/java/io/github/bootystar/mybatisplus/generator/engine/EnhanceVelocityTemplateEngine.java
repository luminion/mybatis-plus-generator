package io.github.bootystar.mybatisplus.generator.engine;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import io.github.bootystar.mybatisplus.generator.config.support.Package;
import io.github.bootystar.mybatisplus.generator.config.support.PackageBuilder;
import io.github.bootystar.mybatisplus.generator.config.support.StrategyBuilder;
import io.github.bootystar.mybatisplus.generator.enums.OutputFile;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 自定义Velocity引擎
 *
 * @author bootystar
 */
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Properties;

public class EnhanceVelocityTemplateEngine extends VelocityTemplateEngine {

    private final VelocityEngine velocityEngine;
   
    private DataSourceConfig.Builder dataSourceBuilder;
    private PackageBuilder packageBuilder;
    private StrategyBuilder  strategyBuilder;

    public EnhanceVelocityTemplateEngine() {
        Properties p = new Properties();
        p.setProperty(ConstVal.VM_LOAD_PATH_KEY, ConstVal.VM_LOAD_PATH_VALUE);
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, StringPool.EMPTY);
        p.setProperty(Velocity.ENCODING_DEFAULT, ConstVal.UTF8);
        p.setProperty(Velocity.INPUT_ENCODING, ConstVal.UTF8);
        p.setProperty("file.resource.loader.unicode", StringPool.TRUE);
        velocityEngine = new VelocityEngine(p);
    }

    public void batchOutput() {
        
        ConfigBuilder config = new ConfigBuilder(
                this.packageConfigBuilder.build(),
                this.dataSourceConfigBuilder.build(),
                this.strategyConfigBuilder.build(),
                null,
                this.globalConfigBuilder.build(),
                this.injectionConfigBuilder.build()
        );
        List<TableInfo> tableInfoList = configBuilder.getTableInfoList();
        for (TableInfo tableInfo : tableInfoList) {
            Map<String, Object> objectMap = getObjectMap(tableInfo);
            
            // ... 此处将添加各种文件的输出逻辑 ...

            // 自定义文件
            outputCustomFile(tableInfo, objectMap);
        }
    }

    private Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("table", tableInfo);
        objectMap.put("package", configBuilder.getPackageConfig());
        objectMap.put("strategy", configBuilder.getStrategyConfig());
        objectMap.put("global", configBuilder.getGlobalConfig());
        objectMap.put("dto", configBuilder.getDtoConfig());
        objectMap.put("vo", configBuilder.getVoConfig());
        objectMap.put("globalCustom", configBuilder.getGlobalCustomConfig());
        return objectMap;
    }

    private void outputCustomFile(TableInfo tableInfo, Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        Map<OutputFile, String> pathInfo = configBuilder.getPathInfo();
        String parentPath = pathInfo.get(OutputFile.parent);

        DtoConfig dtoConfig = configBuilder.getDtoConfig();
        String pathUnderParent4DTO = dtoConfig.getPackage4DTO().replaceAll("\\.", "\\" + File.separator);
        String dtoPath = dtoConfig.getPath4DTO();
        if (StringUtils.isBlank(dtoPath)) {
            dtoPath = parentPath + File.separator + pathUnderParent4DTO + File.separator;
        }

        // 使用新的OutputFile枚举和模板路径
        if (dtoConfig.isGenerateInsert() || dtoConfig.isGenerateImport()) {
            String fileName = dtoPath + entityName + OutputFile.dto_insert.getFileSuffix();
            String templatePath = dtoConfig.getInsertDtoTemplate() != null ?
                dtoConfig.getInsertDtoTemplate() : OutputFile.dto_insert.getTemplatePath();
            outputFile(new File(fileName), objectMap, templatePath);
        }

        if (dtoConfig.isGenerateUpdate()) {
            String fileName = dtoPath + entityName + OutputFile.dto_update.getFileSuffix();
            String templatePath = dtoConfig.getUpdateDtoTemplate() != null ?
                dtoConfig.getUpdateDtoTemplate() : OutputFile.dto_update.getTemplatePath();
            outputFile(new File(fileName), objectMap, templatePath);
        }

        if (dtoConfig.getSelectDTO() == null && dtoConfig.isGenerateSelect()) {
            String fileName = dtoPath + entityName + OutputFile.dto_select.getFileSuffix();
            String templatePath = dtoConfig.getSelectDtoTemplate() != null ?
                dtoConfig.getSelectDtoTemplate() : OutputFile.dto_select.getTemplatePath();
            outputFile(new File(fileName), objectMap, templatePath);
        }

        VoConfig voConfig = configBuilder.getVoConfig();
        String pathUnderParent4VO = voConfig.getPackage4VO().replaceAll("\\.", "\\" + File.separator);
        String voPath = voConfig.getPath4VO();
        if (StringUtils.isBlank(voPath)) {
            voPath = parentPath + File.separator + pathUnderParent4VO + File.separator;
        }

        if (voConfig.isGenerateExport()) {
            String fileName = voPath + entityName + OutputFile.vo.getFileSuffix();
            String templatePath = voConfig.getVoTemplate() != null ?
                voConfig.getVoTemplate() : OutputFile.vo.getTemplatePath();
            outputFile(new File(fileName), objectMap, templatePath);
        }
    }

    private void outputFile(File file, Map<String, Object> objectMap, String templatePath) {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Template template = velocityEngine.getTemplate(templatePath, "UTF-8");
            try (FileOutputStream fos = new FileOutputStream(file);
                 OutputStreamWriter ow = new OutputStreamWriter(fos, "UTF-8");
                 BufferedWriter writer = new BufferedWriter(ow)) {
                template.merge(new VelocityContext(objectMap), writer);
            }
        } catch (Exception e) {
            throw new RuntimeException("渲染模板失败，表名：" + objectMap.get("table"), e);
        }
    }
}