package io.github.bootystar.mybatisplus.generator.engine;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import io.github.bootystar.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import io.github.bootystar.mybatisplus.generator.config.DtoConfig;
import io.github.bootystar.mybatisplus.generator.config.VoConfig;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 自定义Velocity引擎
 *
 * @author bootystar
 */
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnhanceVelocityTemplateEngine {

    private final ConfigBuilder configBuilder;
    private final VelocityEngine velocityEngine;

    public EnhanceVelocityTemplateEngine(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
        Properties p = new Properties();
        p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        p.setProperty("file.resource.loader.path", ".");
        p.setProperty("UTF-8", "UTF-8");
        this.velocityEngine = new VelocityEngine(p);
    }

    public void batchOutput() {
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
        Map<com.baomidou.mybatisplus.generator.config.OutputFile, String> pathInfo = configBuilder.getPathInfo();
        String parentPath = pathInfo.get(com.baomidou.mybatisplus.generator.config.OutputFile.valueOf("parent"));

        DtoConfig dtoConfig = configBuilder.getDtoConfig();
        String pathUnderParent4DTO = dtoConfig.getPackage4DTO().replaceAll("\\.", "\\" + File.separator);
        String dtoPath = dtoConfig.getPath4DTO();
        if (StringUtils.isBlank(dtoPath)) {
            dtoPath = parentPath + File.separator + pathUnderParent4DTO + File.separator;
        }

        if (dtoConfig.isGenerateInsert() || dtoConfig.isGenerateImport()) {
            String fileName = dtoPath + entityName + "InsertDTO.java";
            outputFile(new File(fileName), objectMap, "/bootystar/templates/vm/base/entityInsertDTO.java.vm");
        }

        if (dtoConfig.isGenerateUpdate()) {
            String fileName = dtoPath + entityName + "UpdateDTO.java";
            outputFile(new File(fileName), objectMap, "/bootystar/templates/vm/base/entityUpdateDTO.java.vm");
        }

        if (dtoConfig.getSelectDTO() == null) {
            String fileName = dtoPath + entityName + "SelectDTO.java";
            outputFile(new File(fileName), objectMap, "/bootystar/templates/vm/base/entitySelectDTO.java.vm");
        }

        VoConfig voConfig = configBuilder.getVoConfig();
        String pathUnderParent4VO = voConfig.getPackage4VO().replaceAll("\\.", "\\" + File.separator);
        String voPath = voConfig.getPath4VO();
        if (StringUtils.isBlank(voPath)) {
            voPath = parentPath + File.separator + pathUnderParent4VO + File.separator;
        }
        String fileName = voPath + entityName + "VO.java";
        outputFile(new File(fileName), objectMap, "/bootystar/templates/vm/base/entityVO.java.vm");
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