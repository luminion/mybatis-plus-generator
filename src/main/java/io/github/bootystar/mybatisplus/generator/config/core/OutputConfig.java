package io.github.bootystar.mybatisplus.generator.config.core;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.po.CustomFile;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.config.po.TemplateFile;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author bootystar
 */
@Setter
@Accessors(chain = true)
public class OutputConfig implements ITemplate {

    /**
     * 生成文件的输出目录【 windows:C://tmp  linux or mac:/tmp 】
     */
    @Getter
    protected String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "C://tmp" : "/tmp";

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    protected String parent = "io.github.bootystar";

    /**
     * 父包模块名
     */
    protected String moduleName = "";

    /**
     * 全局文件覆盖
     */
    @Getter
    protected boolean globalFileOverride;

    /**
     * 是否打开输出目录
     */
    @Getter
    protected boolean open = true;

    @Getter
    TemplateFile entity = new TemplateFile()
            .setKey("entity")
            .setFormatPattern("%s")
            .setSubPackage("entity")
            .setTemplatePath("/templates/entity.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile mapper = new TemplateFile()
            .setKey("mapper")
            .setFormatPattern("%sMapper")
            .setSubPackage("mapper")
            .setTemplatePath("/templates/mapper.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile mapperXml = new TemplateFile()
            .setKey("mapperXml")
            .setFormatPattern("%sMapper")
            .setSubPackage("mapper.xml")
            .setTemplatePath("/templates/mapper.xml")
            .setOutputFileSuffix(".xml");
    @Getter
    TemplateFile service = new TemplateFile()
            .setKey("service")
            .setFormatPattern("%sService")
            .setSubPackage("service")
            .setTemplatePath("/templates/service.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile serviceImpl = new TemplateFile()
            .setKey("serviceImpl")
            .setFormatPattern("%sServiceImpl")
            .setSubPackage("service.impl")
            .setTemplatePath("/templates/serviceImpl.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile controller = new TemplateFile()
            .setKey("controller")
            .setFormatPattern("%sController")
            .setSubPackage("controller")
            .setTemplatePath("/templates/controller.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile createDTO = new TemplateFile()
            .setKey("createDTO")
            .setFormatPattern("%sCreateDTO")
            .setSubPackage("dto")
            .setTemplatePath("/templates/createDTO.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile updateDTO = new TemplateFile()
            .setKey("updateDTO")
            .setFormatPattern("%sUpdateDTO")
            .setSubPackage("dto")
            .setTemplatePath("/templates/updateDTO.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile queryDTO = new TemplateFile()
            .setKey("queryDTO")
            .setFormatPattern("%s")
            .setSubPackage("dto")
            .setTemplatePath("/templates/queryDTO.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile queryVO = new TemplateFile()
            .setKey("queryVO")
            .setFormatPattern("%s")
            .setSubPackage("vo")
            .setTemplatePath("/templates/queryVO.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile importDTO = new TemplateFile()
            .setKey("importDTO")
            .setFormatPattern("%sImportDTO")
            .setSubPackage("dto")
            .setTemplatePath("/templates/importDTO.java")
            .setOutputFileSuffix(".java");
    @Getter
    TemplateFile exportVO = new TemplateFile()
            .setKey("exportVO")
            .setFormatPattern("%sExportVO")
            .setSubPackage("vo")
            .setTemplatePath("/templates/exportVO.java")
            .setOutputFileSuffix(".java");

    protected Stream<TemplateFile> templateFileStream() {
        return Stream.of(entity, mapper, mapperXml, service, serviceImpl, controller, createDTO, updateDTO, queryDTO, queryVO, importDTO, exportVO);
    }

    /**
     * 父包名
     */
    public String getParentPackage() {
        if (StringUtils.isNotBlank(moduleName)) {
            return parent + StringPool.DOT + moduleName;
        }
        return parent;
    }

    /**
     * 连接父子包名
     *
     * @param subPackage 子包名
     * @return 连接后的包名
     */
    public String joinPackage(String subPackage) {
        String parent = getParentPackage();
        return StringUtils.isBlank(parent) ? subPackage : (parent + StringPool.DOT + subPackage);
    }

    /**
     * 连接路径字符串
     *
     * @param parentDir   路径常量字符串
     * @param packageName 包名
     * @return 连接后的路径
     */
    public String joinPath(String parentDir, String packageName) {
        if (StringUtils.isBlank(parentDir)) {
            parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
        }
        if (!StringUtils.endsWith(parentDir, File.separator)) {
            parentDir += File.separator;
        }
        packageName = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        return parentDir + packageName;
    }

    /**
     * 获取包信息
     */
    public Map<String, String> getPackageInfo() {
        return templateFileStream().collect(Collectors.toMap(
                TemplateFile::getKey,
                e -> joinPackage(e.getSubPackage())
        ));
    }

    /**
     * 获取输出文件
     */
    public List<CustomFile> getCustomFiles() {
        return templateFileStream()
                .filter(TemplateFile::isGenerate)
                .map(e -> {
                    CustomFile customFile = new CustomFile();
                    String fileOutputDir = e.getOutputDir();
                    if (fileOutputDir == null) {
                        String joinPackage = joinPackage(e.getSubPackage());
                        fileOutputDir = joinPath(outputDir, joinPackage);
                    }
                    customFile
                            .setFormatNameFunction(e::convertFormatName)
                            .setTemplatePath(e.getTemplatePath())
                            .setOutputFileSuffix(e.getOutputFileSuffix())
                            .setOutputDir(fileOutputDir)
                            .setFileOverride(e.isFileOverride() || this.globalFileOverride)
                    ;
                    return customFile;
                }).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> map = ITemplate.super.renderData(tableInfo);
        map.put("package", this.getPackageInfo());
        map.put("entity", tableInfo.getEntityName());
        map.put("createDTO", createDTO.convertFormatName(tableInfo));
        map.put("updateDTO", updateDTO.convertFormatName(tableInfo));
        map.put("queryDTO", queryDTO.convertFormatName(tableInfo));
        map.put("queryVO", queryVO.convertFormatName(tableInfo));
        map.put("importDTO", importDTO.convertFormatName(tableInfo));
        map.put("exportVO", exportVO.convertFormatName(tableInfo));
        map.put("mapper", mapper.convertFormatName(tableInfo));
        map.put("mapperXml", mapperXml.convertFormatName(tableInfo));
        map.put("service", service.convertFormatName(tableInfo));
        map.put("serviceImpl", serviceImpl.convertFormatName(tableInfo));
        map.put("controller", controller.convertFormatName(tableInfo));
        return map;
    }
}