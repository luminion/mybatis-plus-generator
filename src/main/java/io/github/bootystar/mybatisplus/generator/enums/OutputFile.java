package io.github.bootystar.mybatisplus.generator.enums;

/**
 * 输出文件枚举
 * 包含所有生成文件的类型和对应的模板路径
 *
 * @author bootystar
 */
public enum OutputFile {
    
    entity("/templates/entity.java"),
    mapper("/templates/mapper.java"),
    mapperXml("/templates/mapper.xml"),
    service("/templates/service.java"),
    serviceImpl("/templates/serviceImpl.java"),
    controller("/templates/controller.java"),
    
    dto_insert("/bootystar/templates/vm/base/entityInsertDTO.java.vm"),
    dto_update("/bootystar/templates/vm/base/entityUpdateDTO.java.vm"),
    dto_select("/bootystar/templates/vm/base/entitySelectDTO.java.vm"),
    
    // 自定义VO文件类型
    vo("/bootystar/templates/vm/base/entityVO.java.vm"),
    
    // 其他文件类型
    parent(""),
    other("");
    
    private final String templatePath;
    
    OutputFile(String templatePath) {
        this.templatePath = templatePath;
    }
    
    public String getTemplatePath() {
        return templatePath;
    }
    
    /**
     * 获取文件后缀名
     */
    public String getFileSuffix() {
        switch (this) {
            case entity:
                return ".java";
            case mapper:
                return "Mapper.java";
            case mapperXml:
                return "Mapper.xml";
            case service:
                return "Service.java";
            case serviceImpl:
                return "ServiceImpl.java";
            case controller:
                return "Controller.java";
            case dto_insert:
                return "InsertDTO.java";
            case dto_update:
                return "UpdateDTO.java";
            case dto_select:
                return "SelectDTO.java";
            case vo:
                return "VO.java";
            default:
                return "";
        }
    }
    
    /**
     * 判断是否为DTO类型文件
     */
    public boolean isDto() {
        return this == dto_insert || this == dto_update || this == dto_select;
    }
    
    /**
     * 判断是否为VO类型文件
     */
    public boolean isVo() {
        return this == vo;
    }
    
    /**
     * 判断是否为自定义文件类型
     */
    public boolean isCustomFile() {
        return isDto() || isVo();
    }
}
