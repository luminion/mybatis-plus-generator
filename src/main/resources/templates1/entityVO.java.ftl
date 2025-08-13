package ${basePackage}.${package4VO};

<#if generateExport>
    import ${excelBasePackage}.annotation.ExcelIgnoreUnannotated;
    import ${excelBasePackage}.annotation.ExcelProperty;
</#if>
<#if springdoc??>
    import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger??>
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
    import lombok.Data;
    <#if chainModel>
        import lombok.experimental.Accessors;
    </#if>
</#if>

<#list importPackages4DTO as pkg>
    import ${pkg};
</#list>

/**
* ${(table.comment)!''}-VO
*
* @author ${author}
* @since ${date}
<#if extraClassLinkComment>
    * @see ${package.Entity}.${entity}
    * @see ${package.Mapper}.${table.mapperName}
    * @see ${package.ServiceImpl}.${table.serviceImplName}
</#if>
*/
<#if entityLombokModel>
    @Data
    <#if chainModel>
        @Accessors(chain = true)
    </#if>
</#if>
<#if swaggerModelWithAnnotation>
    <#if springdoc??>
    <#--@Schema(name = "${entity}VO", description = "${(table.comment)!''}-VO")-->
        @Schema(name = "${entity}VO${(swaggerUUID)!''}", description = "${(table.comment)!''}-VO")
    <#elseif swagger??>
    <#--@ApiModel(value = "${entity}VO", description = "${(table.comment)!''}-VO")-->
        @ApiModel(value = "${entity}VO${(swaggerUUID)!''}", description = "${(table.comment)!''}-VO")
    </#if>
</#if>
<#if generateExport>
    @ExcelIgnoreUnannotated
</#if>
public class ${entity}VO {
<#--#if(${entitySerialVersionUID})
##
##    private static final long serialVersionUID = 1L;
###end-->
<#-- ----------  BEGIN 字段循环遍历  ---------- -->
<#list table.fields as field>
    <#if field.logicDeleteField>
    <#else>

        <#if (field.comment)?? && field.comment != "">
            <#if springdoc??>
                @Schema(description = "${field.comment}")
            <#elseif swagger??>
                @ApiModelProperty("${field.comment}")
            <#else>
                /**
                * ${field.comment}
                */
            </#if>
        </#if>
        <#if generateExport><#-- 导出 -->
            @ExcelProperty(value = "${field.comment}")
        </#if>
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------- -->
<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.logicDeleteField>
        <#else>
            <#if field.propertyType == "boolean">
                <#assign getprefix="is">
            <#else>
                <#assign getprefix="get">
            </#if>

            public ${field.propertyType} ${getprefix}${field.capitalName}() {
            return ${field.propertyName};
            }

            <#if chainModel>
                public ${entity}VO set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
            <#else>
                public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
            </#if>
            this.${field.propertyName} = ${field.propertyName};
            <#if chainModel>
                return this;
            </#if>
            }
        </#if>
    </#list>
<#-- --foreach end--- -->
</#if>
<#-- --end of #if(!${entityLombokModel})-- -->
<#if !entityLombokModel>

    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field.logicDeleteField>
        <#else>
            <#if field_index == 0>
                "${field.propertyName} = " + ${field.propertyName} +
            <#else>
                ", ${field.propertyName} = " + ${field.propertyName} +
            </#if>
        </#if>
    </#list>
    "}";
    }
</#if>
}