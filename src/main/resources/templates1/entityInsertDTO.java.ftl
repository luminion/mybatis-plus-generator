package ${basePackage}.${package4DTO};

<#if generateImport>
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
    <#if chainModel&&generateImport>
        import lombok.experimental.Accessors;
    </#if>
</#if>

<#if validated>
<#-- ---字段非空验证--- -->
    <#list table.fields as field>
        <#if !field.metaInfo.nullable && !field.metaInfo.defaultValue && !field.keyFlag &&field.propertyType!="String">
            import ${javaApiPackage}.validation.constraints.NotNull;
            <#break>
        </#if>
    </#list>
<#-- ---字符串非空验证--- -->
    <#list table.fields as field>
        <#if !field.metaInfo.nullable && !field.metaInfo.defaultValue && !field.keyFlag &&field.propertyType=="String">
            import ${javaApiPackage}.validation.constraints.NotBlank;
            <#break>
        </#if>
    </#list>
<#-- ---字符串长度验证--- -->
    <#list table.fields as field>
        <#if field.propertyType=="String">
            import ${javaApiPackage}.validation.constraints.Size;
            <#break>
        </#if>
    </#list>
</#if>
<#list importPackages4DTO as pkg>
    import ${pkg};
</#list>

/**
* ${(table.comment)!''}-新增DTO
*
* @author ${author}
* @since ${date}
<#if extraClassLinkComment>
    * @see ${package.Entity}.${entity}
<#-- * @see ${package.Mapper}.${table.mapperName} -->
    * @see ${package.ServiceImpl}.${table.serviceImplName}
</#if>
*/
<#if entityLombokModel>
    @Data
    <#if chainModel&&!generateImport>
        @Accessors(chain = true)
    </#if>
</#if>
<#if swaggerModelWithAnnotation>
    <#if springdoc??>
    <#--@Schema(name = "${entity}InsertDTO ", description = "${(table.comment)!''}-新增DTO")-->
        @Schema(name = "${entity}InsertDTO${(swaggerUUID)!''}", description = "${(table.comment)!''}-新增DTO")
    <#elseif swagger??>
    <#--@ApiModel(value = "${entity}InsertDTO", description = "${(table.comment)!''}-新增DTO")-->
        @ApiModel(value = "${entity}InsertDTO${(swaggerUUID)!''}", description = "${(table.comment)!''}-新增DTO")
    </#if>
</#if>
<#if generateImport>
    @ExcelIgnoreUnannotated
</#if>
public class ${entity}InsertDTO {
<#--#if(${entitySerialVersionUID})
##
##    private static final long serialVersionUID = 1L;
###end-->
<#-- ----------  BEGIN 字段循环遍历  ---------- -->
<#list table.fields as field>
    <#if field.keyFlag>
    <#elseif field.fill?? && (field.fill=="INSERT" || field.fill=="INSERT_UPDATE")>
    <#elseif field.versionField>
    <#elseif field.logicDeleteField>
    <#elseif editExcludeColumns.contains(field.columnName)>
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
        <#if validated><#-- 参数校验 -->
            <#if !field.metaInfo.nullable && !field.metaInfo.defaultValue><#-- 非空 -->
                <#if field.propertyType == "String">
                    @NotBlank(message = "${field.comment}为必填项")
                <#else>
                    @NotNull(message = "${field.comment}为必填项")
                </#if>
            </#if>
            <#if field.propertyType == "String"><#-- 字符串长度 -->
                @Size(message = "${field.comment}长度不能超过${field.metaInfo.length}", max = ${field.metaInfo.length})
            </#if>
        </#if>
        <#if generateImport><#-- excel导入 -->
            @ExcelProperty(value = "${field.comment}")
        </#if>
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------- -->
<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.keyFlag>
        <#elseif field.fill?? && (field.fill=="INSERT" || field.fill=="INSERT_UPDATE")>
        <#elseif field.versionField>
        <#elseif field.logicDeleteField>
        <#elseif editExcludeColumns.contains(field.columnName)>
        <#else>
            <#if field.propertyType == "boolean">
                <#assign getprefix="is">
            <#else>
                <#assign getprefix="get">
            </#if>

            public ${field.propertyType} ${getprefix}${field.capitalName}() {
            return ${field.propertyName};
            }

            <#if chainModel&&!generateImport>
                public ${entity}InsertDTO set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
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

<#if !entityLombokModel>

    @Override
    public String toString() {
    return "${entity}{" +
    <#list table.fields as field>
        <#if field.keyFlag>
        <#elseif field.fill?? && (field.fill=="INSERT" || field.fill=="INSERT_UPDATE")>
        <#elseif field.versionField>
        <#elseif field.logicDeleteField>
        <#elseif editExcludeColumns.contains(field.columnName)>
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