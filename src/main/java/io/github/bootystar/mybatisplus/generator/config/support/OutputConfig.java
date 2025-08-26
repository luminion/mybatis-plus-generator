package io.github.bootystar.mybatisplus.generator.config.support;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.GeneratorConfig;
import io.github.bootystar.mybatisplus.generator.config.interfaces.ConstVal;
import io.github.bootystar.mybatisplus.generator.config.po.CustomFile;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;
import io.github.bootystar.mybatisplus.generator.config.po.TemplateFile;
import io.github.bootystar.mybatisplus.generator.fill.ITemplate;
import lombok.Getter;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 输出文件配置
 *
 * @author bootystar
 */
public class OutputConfig implements ITemplate {

    /**
     * 生成文件的输出目录【 windows:C://tmp  linux or mac:/tmp 】
     */
    @Getter
    protected String outputDir = System.getProperty("os.name").toLowerCase().contains("windows") ? "C://tmp" : "/tmp";

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    protected String parentPackage = "io.github.bootystar";

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
    protected TemplateFile entity = new TemplateFile().setKey(ConstVal.ENTITY).setFormatPattern("%s").setSubPackage("entity").setTemplatePath("/templates/entity.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile mapper = new TemplateFile().setKey(ConstVal.MAPPER).setFormatPattern("%sMapper").setSubPackage("mapper").setTemplatePath("/templates/mapper.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile mapperXml = new TemplateFile().setKey(ConstVal.MAPPER_XML).setFormatPattern("%sMapper").setSubPackage("mapper.xml").setTemplatePath("/templates/mapper.xml").setOutputFileSuffix(".xml");
    @Getter
    protected TemplateFile service = new TemplateFile().setKey(ConstVal.SERVICE).setFormatPattern("%sService").setSubPackage("service").setTemplatePath("/templates/service.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile serviceImpl = new TemplateFile().setKey(ConstVal.SERVICE_IMPL).setFormatPattern("%sServiceImpl").setSubPackage("service.impl").setTemplatePath("/templates/serviceImpl.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile controller = new TemplateFile().setKey(ConstVal.CONTROLLER).setFormatPattern("%sController").setSubPackage("controller").setTemplatePath("/templates/controller.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile createDTO = new TemplateFile().setKey(ConstVal.CREATE_DTO).setFormatPattern("%sCreateDTO").setSubPackage("dto").setTemplatePath("/templates/createDTO.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile updateDTO = new TemplateFile().setKey(ConstVal.UPDATE_DTO).setFormatPattern("%sUpdateDTO").setSubPackage("dto").setTemplatePath("/templates/updateDTO.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile queryDTO = new TemplateFile().setKey(ConstVal.QUERY_DTO).setFormatPattern("%sQueryDTO").setSubPackage("dto").setTemplatePath("/templates/queryDTO.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile queryVO = new TemplateFile().setKey(ConstVal.QUERY_VO).setFormatPattern("%sQueryVO").setSubPackage("vo").setTemplatePath("/templates/queryVO.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile importDTO = new TemplateFile().setKey(ConstVal.IMPORT_DTO).setFormatPattern("%sImportDTO").setSubPackage("dto").setTemplatePath("/templates/importDTO.java").setOutputFileSuffix(".java");
    @Getter
    protected TemplateFile exportVO = new TemplateFile().setKey(ConstVal.EXPORT_VO).setFormatPattern("%sExportVO").setSubPackage("vo").setTemplatePath("/templates/exportVO.java").setOutputFileSuffix(".java");

    protected Stream<TemplateFile> templateFileStream() {
        return Stream.of(entity, mapper, mapperXml, service, serviceImpl, controller, createDTO, updateDTO, queryDTO, queryVO, importDTO, exportVO);
    }

    /**
     * 父包名
     */
    public String getParentPackage() {
        if (StringUtils.isNotBlank(moduleName)) {
            return parentPackage + StringPool.DOT + moduleName;
        }
        return parentPackage;
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
        return templateFileStream().collect(Collectors.toMap(TemplateFile::getKey, e -> joinPackage(e.getSubPackage())));
    }

    /**
     * 获取输出文件
     */
    public List<CustomFile> getCustomFiles() {
        return templateFileStream().filter(TemplateFile::isGenerate).map(e -> {
            CustomFile customFile = new CustomFile();
            String fileOutputDir = e.getOutputDir();
            if (fileOutputDir == null) {
                String joinPackage = joinPackage(e.getSubPackage());
                fileOutputDir = joinPath(outputDir, joinPackage);
            }
            customFile.setFormatNameFunction(e::convertFormatName).setTemplatePath(e.getTemplatePath()).setOutputFileSuffix(e.getOutputFileSuffix()).setOutputDir(fileOutputDir).setFileOverride(e.isFileOverride() || this.globalFileOverride);
            return customFile;
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> renderData(TableInfo tableInfo) {
        Map<String, Object> map = ITemplate.super.renderData(tableInfo);
        map.put("package", this.getPackageInfo());
        map.put("entity", entity.convertFormatName(tableInfo));
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

    public Adapter adapter() {
        return new Adapter(this);
    }

    public static class Adapter {
        private final OutputConfig config;

        public Adapter(OutputConfig config) {
            this.config = config;
        }

        /**
         * 文件输出目录
         *
         * @param outputDir 文件输出目录
         * @return this
         */
        public Adapter outputDir(String outputDir) {
            this.config.outputDir = outputDir;
            return this;
        }

        /**
         * 父包名
         *
         * @param parentPackage 父包名
         * @return this
         */
        public Adapter parentPackage(String parentPackage) {
            this.config.parentPackage = parentPackage;
            return this;
        }

        /**
         * 模块名
         *
         * @param moduleName 模块名
         * @return this
         */
        public Adapter moduleName(String moduleName) {
            this.config.moduleName = moduleName;
            return this;
        }

        /**
         * 启用全局文件覆盖(仅影响本配置提供的模板文件)
         *
         * @return this
         */
        public Adapter enableGlobalFileOverride() {
            this.config.globalFileOverride = true;
            return this;
        }

        /**
         * 禁用打开输出目录
         *
         * @return this
         */
        public Adapter disableOpenOutputDir() {
            this.config.open = false;
            return this;
        }

        /**
         * 实体类配置
         *
         * @param adapter 适配器
         */
        public Adapter entity(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.entity.adapter());
            return this;
        }

        /**
         * mapper配置
         *
         * @param adapter 适配器
         */
        public Adapter mapper(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.mapper.adapter());
            return this;
        }

        /**
         * mapperXml配置
         *
         * @param adapter 适配器
         */
        public Adapter mapperXml(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.mapperXml.adapter());
            return this;
        }

        /**
         * service配置
         *
         * @param adapter 适配器
         */
        public Adapter service(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.service.adapter());
            return this;
        }

        /**
         * serviceImpl配置
         *
         * @param adapter 适配器
         */
        public Adapter serviceImpl(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.serviceImpl.adapter());
            return this;
        }

        /**
         * controller配置
         *
         * @param adapter 适配器
         */
        public Adapter controller(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.controller.adapter());
            return this;
        }

        /**
         * createDTO配置
         *
         * @param adapter 适配器
         */
        public Adapter createDTO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.createDTO.adapter());
            return this;
        }

        /**
         * updateDTO配置
         *
         * @param adapter 适配器
         */
        public Adapter updateDTO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.updateDTO.adapter());
            return this;
        }

        /**
         * queryDTO配置
         *
         * @param adapter 适配器
         */
        public Adapter queryDTO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.queryDTO.adapter());
            return this;
        }

        /**
         * queryVO配置
         *
         * @param adapter 适配器
         */
        public Adapter queryVO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.queryVO.adapter());
            return this;
        }

        /**
         * importDTO配置
         *
         * @param adapter 适配器
         */
        public Adapter importDTO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.importDTO.adapter());
            return this;
        }

        /**
         * exportVO配置
         *
         * @param adapter 适配器
         */
        public Adapter exportVO(Function<TemplateFile.Adapter, TemplateFile.Adapter> adapter) {
            adapter.apply(this.config.exportVO.adapter());
            return this;
        }
    }
}