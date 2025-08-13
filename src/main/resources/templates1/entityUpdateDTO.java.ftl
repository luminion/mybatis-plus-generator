package ${basePackage}.${package4DTO};

<#-- -----参数设置----start -->
<#assign importSize=false>
<#assign importNotNull=false>
<#assign importNotBlank=false>
<#list table.fields as field>
    <#if field.propertyType=="String">
        <#assign importSize=true>
    </#if>
    <#if feild.versionField || field.keyFlag>
        <#assign importNotNull=true>
        <#if field.propertyType=="String">
            <#assign importNotBlank=true>
        </#if>
    </#if>
</#list>
<#-- -----参数设置----end -->
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
<#-- ---字段非空验证---end -->
<#--#if(${springdoc})
##import io.swagger.v3.oas.annotations.media.Schema;
##    #elseif(${swagger})
##import io.swagger.annotations.ApiModel;
##import io.swagger.annotations.ApiModelProperty;
###end-->
<#--#if(${entityLombokModel})
##import lombok.Data;
##    #if(${chainModel})
##import lombok.experimental.Accessors;
##    #end
###end-->

<#if validated>
<#-- ---字段非空验证---start -->
    <#if importSize>
        import ${javaApiPackage}.validation.constraints.Size;
    </#if>
    <#if importNotNull>
        import ${javaApiPackage}.validation.constraints.NotNull;
    </#if>
    <#if importNotBlank>
        import ${javaApiPackage}.validation.constraints.NotBlank;
    </#if>
</#if>
<#list importPackages4DTO as pkg>
    import ${pkg};
</#list>

/**
* ${(table.comment)!''}-修改DTO
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
    <#if chainModel>
        @Accessors(chain = true)
    </#if>
</#if>
<#if swaggerModelWithAnnotation>
    <#if springdoc??>
    <#--@Schema(name = "${entity}UpdateDTO", description = "${(table.comment)!''}-修改DTO")-->
        @Schema(name = "${entity}UpdateDTO${(swaggerUUID)!''}", description = "${(table.comment)!''}-修改DTO")
    <#elseif swagger??>
    <#--@ApiModel(value = "${entity}UpdateDTO", description = "${(table.comment)!''}-修改DTO")-->
        @ApiModel(value = "${entity}UpdateDTO${(swaggerUUID)!''}", description = "${(table.comment)!''}-修改DTO")
    </#if>
</#if>
public class ${entity}UpdateDTO {
<#--#if(${entitySerialVersionUID})
##
##    private static final long serialVersionUID = 1L;
###end-->
<#-- ----------  BEGIN 字段循环遍历  ---------- -->
<#list table.fields as field>
    <#if (editExcludeColumns.contains(field.columnName))>
    <#elseif field.fill?? && (field.fill=="UPDATE" || field.fill=="INSERT_UPDATE")>
    <#elseif field.logicDeleteField>
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
            <#if field.keyFlag><#-- 主键 -->
                @NotNull(message = "${field.comment}为必填项")
                <#if field.propertyType=="String">
                    @NotBlank(message = "${field.comment}为必填项")
                </#if>
            </#if>
            <#if field.versionField><#-- 乐观锁注解 -->
                @NotNull(message = "${field.comment}为必填项")
                <#if field.propertyType=="String">
                    @NotBlank(message = "${field.comment}为必填项")
                </#if>
            </#if>
            <#if field.propertyType == "String"><#-- 字符串长度限制 -->
                @Size(message = "${field.comment}长度不能超过${field.metaInfo.length}", max = ${field.metaInfo.length})
            </#if>
        </#if>
        private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------- -->
<#if !entityLombokModel>
    <#list table.fields as field>
        <#if editExcludeColumns.contains(field.columnName)>
        <#elseif field.fill?? && (field.fill=="UPDATE" || field.fill=="INSERT_UPDATE")>
        <#elseif field.logicDeleteField>
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
                public ${entity}UpdateDTO set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
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
        <#if editExcludeColumns.contains(field.columnName)>
        <#elseif field.fill?? && (field.fill=="UPDATE" || field.fill=="INSERT_UPDATE")>
        <#elseif field.logicDeleteField>
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