package ${basePackage}.${package4DTO};

<#if springdoc??>
    import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger??>
    import io.swagger.annotations.ApiModel;
    import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    <#if chainModel>
        import lombok.experimental.Accessors;
    </#if>
</#if>

<#list importPackages4DTO as pkg>
    import ${pkg};
</#list>
import java.util.List;

/**
* ${(table.comment)!''}-查询DTO
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
    @EqualsAndHashCode(callSuper = true)
    <#if chainModel>
        @Accessors(chain = true)
    </#if>
</#if>
<#if swaggerModelWithAnnotation>
    <#if springdoc??>
    <#--@Schema(name = "${entity}SelectDTO", description = "${(table.comment)!''}-查询DTO")-->
        @Schema(name = "${entity}SelectDTO${(swaggerUUID)!''}", description = "${(table.comment)!''}-查询DTO")
    <#elseif swagger??>
    <#--@ApiModel(value = "${entity}SelectDTO", description = "${(table.comment)!''}-查询DTO")-->
        @ApiModel(value = "${entity}SelectDTO${(swaggerUUID)!''}", description = "${(table.comment)!''}-查询DTO")
    </#if>
</#if>
public class ${entity}SelectDTO extends ${entity} {
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
        private ${field.propertyType} ${field.propertyName};
    <#-- ----------额外字段-----------start-->
        <#if extraFieldSuffixMap??>
            <#list extraFieldSuffixMap.entrySet() as entry>
                <#assign key = entry.key>
                <#assign value = entry.value>
                <#assign extraFieldName= "${field.propertyName}${key}">
                <#if value=="IN"||value=="NOT IN">
                    <#assign extraFieldType= "List<${field.propertyType}>">
                <#else>
                    <#assign extraFieldType= "${field.propertyType}">
                </#if>
                <#assign existField = false>
                <#list table.fields as fieldcheck>
                    <#if fieldcheck.propertyName==extraFieldName>
                        <#assign existField = true>
                        <#break>
                    </#if>
                </#list>
                <#if !existField && extraFieldStrategy.allowGenerate(value,field)>

                    <#if (field.comment)?? && field.comment != "">
                        <#if springdoc??>
                            @Schema(description = "${field.comment}(${value})")
                        <#elseif swagger??>
                            @ApiModelProperty("${field.comment}(${value})")
                        <#else>
                            /**
                            * ${field.comment}(${value})
                            */
                        </#if>
                    </#if>
                    private ${extraFieldType} ${extraFieldName};
                    <#if (value=="LIKE"||value=="NOT LIKE")&&extraFieldType=="String">

                        public ${extraFieldType} get${field.capitalName}${key}() {
                    <#--        if (${extraFieldName} == null || ${extraFieldName}.isEmpty()) return null;-->
                    <#--        if (!${extraFieldName}.startsWith("%")) ${extraFieldName} = "%" + ${extraFieldName};-->
                    <#--        if (!${extraFieldName}.endsWith("%")) ${extraFieldName} = ${extraFieldName} + "%";-->
                    <#--        return ${extraFieldName};-->
                        return ${extraFieldName} == null || ${extraFieldName}.isEmpty() ? null : "%" + ${extraFieldName} + "%";
                        }
                    </#if>
                </#if>
            </#list>
        </#if>
    <#-- ----------额外字段-----------end-->
    </#if>
</#list>
<#-- ----------  END 字段循环遍历  ---------- -->
<#if !entityLombokModel>
<#-- --getter/setter的foreach start--- -->
    <#list table.fields as field>
        <#if field.logicDeleteField>
        <#else>
            <#if field.propertyType == "boolean">
                <#assign getprefix="is">
            <#else>
                <#assign getprefix="get">
            </#if>
        <#-- ----------原始字段getter/setter-----------start-->
            public ${field.propertyType} ${getprefix}${field.capitalName}() {
            return ${field.propertyName};
            }

            <#if chainModel>
                public ${entity}SelectDTO set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
            <#else>
                public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
            </#if>
            this.${field.propertyName} = ${field.propertyName};
            <#if chainModel>
                return this;
            </#if>
            }
        <#-- ----------原始字段getter/setter-----------end-->
            <#if extraFieldSuffixMap??>
            <#-- ----------额外字段getter/setter的foreach-----------start-->
                <#list extraFieldSuffixMap.entrySet() as entry>
                    <#assign key = entry.key>
                    <#assign value = entry.value>
                    <#assign extraFieldName= "${field.propertyName}${key}">
                    <#if value=="IN"||value=="NOT IN">
                        <#assign extraFieldType= "List<${field.propertyType}>">
                    <#else>
                        <#assign extraFieldType= "${field.propertyType}">
                    </#if>
                    <#assign existField = false>
                    <#list table.fields as fieldcheck>
                        <#if fieldcheck.propertyName==extraFieldName>
                            <#assign existField = true>
                            <#break>
                        </#if>
                    </#list>
                    <#if !existField && extraFieldStrategy.allowGenerate(value,field)>
                        <#if (value=="LIKE"||value=="NOT LIKE")&&extraFieldType=="String">
                        <#else>

                            public ${extraFieldType} ${getprefix}${field.capitalName}${key}() {
                            return ${extraFieldName};
                            }
                        </#if>

                        <#if chainModel>
                            public ${entity}SelectDTO set${field.capitalName}${key}(${extraFieldType} ${extraFieldName}) {
                        <#else>
                            public void set${field.capitalName}${key}(${extraFieldType} ${extraFieldName}) {
                        </#if>
                        this.${extraFieldName} = ${extraFieldName};
                        <#if chainModel>
                            return this;
                        </#if>
                        }
                    </#if>
                </#list>
            <#-- ----------额外字段getter/setter的foreach-----------start-->
            </#if>
        </#if>
    </#list>
<#-- --getter/setter的foreach end--- -->
</#if>
<#-- --end of #if(!${entityLombokModel})-- -->
<#if entityColumnConstant>
    <#list table.fields as field>
        <#if selectExcludeFields.contains(field.propertyName)>
        <#elseif field.logicDeleteField>
        <#else>

            public static final String ${field.name?upper_case} = "${field.name}";
        </#if>
    </#list>
</#if>
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
            <#list extraFieldSuffixMap.entrySet() as entry>
                <#assign key = entry.key>
                <#assign value = entry.value>
                <#assign extraFieldName= "${field.propertyName}${key}">
                <#if value=="IN"||value=="NOT IN">
                    <#assign extraFieldType= "List<${field.propertyType}>">
                <#else>
                    <#assign extraFieldType= "${field.propertyType}">
                </#if>
                <#assign existField = false>
                <#list table.fields as fieldcheck>
                    <#if fieldcheck.propertyName==extraFieldName>
                        <#assign existField = true>
                        <#break>
                    </#if>
                </#list>
                <#if !existField && extraFieldStrategy.allowGenerate(value,field)>
                    ", ${extraFieldName} = " + ${extraFieldName} +
                </#if>
            </#list>
        </#if>
    </#list>
    "}";
    }
</#if>
}