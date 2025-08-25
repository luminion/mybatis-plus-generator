package io.github.bootystar.mybatisplus.generator.config.core;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.bootystar.mybatisplus.generator.config.po.CustomFile;
import io.github.bootystar.mybatisplus.generator.config.po.TableInfo;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author bootystar
 */
public class OutputFileConfig {
    protected FileConfig() {
    }

    /**
     * 全局文件覆盖
     */
    protected boolean fileOverride;

    /**
     * 生成文件的输出目录
     */
    protected String outputDir = System.getProperty("user.dir") + "/src/main/java";

    /**
     * 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
     */
    protected String parent = "io.github.bootystar";

    /**
     * 父包模块名
     */
    protected String moduleName = "";

    /**
     * Entity包名
     */
    protected TemplateFile entity = new TemplateFile("%s","entity","/templates/entity.java");

    /**
     * Mapper包名
     */
    protected TemplateFile mapper = new TemplateFile("%sMapper","mapper","/templates/mapper.java");

    /**
     * MapperConfig XML包名
     */
    protected TemplateFile mapperXml = new TemplateFile("%sMapper","mapper.xml","/templates/mapper.xml");

    /**
     * Service包名
     */
    protected TemplateFile service = new TemplateFile("%sService","service","/templates/service.java");

    /**
     * ServiceConfig Impl包名
     */
    protected TemplateFile serviceImpl = new TemplateFile("%sServiceImpl","service.impl","/templates/serviceImpl.java");

    /**
     * Controller包名
     */
    protected TemplateFile controller = new TemplateFile("%sController","controller","/templates/controller.java");

    /**
     * 新增DTO包名
     */
    protected TemplateFile createDTO = new TemplateFile("%sCreateDTO","model.dto","/templates/createDTO.java");

    /**
     * 修改DTO包名
     */
    protected TemplateFile updateDTO = new TemplateFile("%sUpdateDTO","model.dto","/templates/updateDTO.java");

    /**
     * 查询DTO包名
     */
    protected TemplateFile queryDTO = new TemplateFile("%sQueryDTO","model.dto","/templates/queryDTO.java");

    /**
     * 查询VO包名
     */
    protected TemplateFile queryVO = new TemplateFile("%sQueryVO","model.vo","/templates/queryVO.java");

    /**
     * 导入DTO包名
     */
    protected TemplateFile importDTO = new TemplateFile("%sImportDTO","model.dto","/templates/importDTO.java");

    /**
     * 导出DTO包名
     */
    protected TemplateFile exportVO = new TemplateFile("%sExportVO","model.vo","/templates/exportVO.java");

    /**
     * 父包名
     */
    public String getParentPackage() {
        if (StringUtils.isNotBlank(moduleName)) {
            return parent + StringPool.DOT + moduleName;
        }
        return parent;
    }

    public List<CustomFile> customFiles(TableInfo tableInfo) {
        Stream.of(
                entity
                , mapper
                , mapperXml
                , service
                , serviceImpl
                , controller
                , createDTO
                , updateDTO
                , queryDTO
                , queryVO
                , importDTO
                , exportVO
        ).map(e->{
            CustomFile.Builder builder = new CustomFile.Builder();
            builder.fileName(e.className(tableInfo.getEntityName()))
                    .packageName(getParent())
                    .templatePath(e.templatePath)

        })
    }
}
