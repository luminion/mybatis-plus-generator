package ${package.Service};

<#-- ------参数设置----start -->
<#--#foreach($field in ${table.fields})
##    #if(${field.keyFlag})
##        #set($primaryKeyPropertyType=${field.propertyType})
##        #break
##    #end
###end-->
<#assign actualType = actualType4ServiceMethodParam>
<#assign entityVO = "${entity}VO">
<#assign sourceEntityInsertDTO = "${entity}InsertDTO">
<#assign sourceEntityUpdateDTO = "${entity}UpdateDTO">
<#if selectDTO.classFullName??>
    <#assign sourceEntitySelectDTO = selectDTO.clazz("${entity}")>
    <#assign importStr4SelectDTO="import ${selectDTO.classFullName};">
<#else>
    <#assign sourceEntitySelectDTO = "${entity}SelectDTO">
    <#assign importStr4SelectDTO="import ${basePackage}.${package4DTO}.${entity}SelectDTO;">
</#if>
<#if actualType>
    <#assign entityInsertDTO = "${sourceEntityInsertDTO}">
    <#assign entityUpdateDTO = "${sourceEntityUpdateDTO}">
    <#assign entitySelectDTO = "${sourceEntitySelectDTO}">
<#else>
    <#assign entityInsertDTO = "Object">
    <#assign entityUpdateDTO = "Object">
    <#assign entitySelectDTO = "Object">
</#if>
<#-- ------参数设置----end -->
<#if generateSelect||generateExport>
    import io.github.bootystar.mybatisplus.core.metadata.IPage;
</#if>
import ${superServiceClassPackage};
import ${package.Entity}.${entity};
<#if generateSelect||generateExport>
    import ${basePackage}.${package4VO}.${entityVO};
</#if>
<#if actualType>
    <#if !useMapSelectDTO && (generateSelect||generateExport)>
        ${importStr4SelectDTO}
    </#if>
    <#if generateInsert||generateImport>
        import ${basePackage}.${package4DTO}.${entityInsertDTO};
    </#if>
    <#if generateUpdate>
        import ${basePackage}.${package4DTO}.${entityUpdateDTO};
    </#if>
</#if>
<#if generateImport>
    import java.io.InputStream;
</#if>
<#if generateImport||generateExport>
    import java.io.OutputStream;
</#if>
<#if generateSelect||generateExport>
    import java.io.Serializable;
    import java.util.List;
    <#if useMapSelectDTO && actualType && (generateSelect||generateExport)>
        ${importStr4SelectDTO}
    </#if>
</#if>

/**
* ${(table.comment)!''}服务类
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
    <#if generateSelect>

        ${entityVO} voById(Serializable id);

        List<${entityVO}> voList(${entitySelectDTO} s);

        IPage<${entityVO}> voPage(${entitySelectDTO} s, Long current, Long size);
    </#if>
    <#if generateExport>

        void excelExport(${entitySelectDTO} s, OutputStream os, Class<?> clazz, Long current, Long size, String... includeFields);
    </#if>
    <#if generateImport>

        void excelTemplate(OutputStream os, Class<?> clazz);

        int excelImport(InputStream is, Class<?> clazz);
    </#if>
    <#if generateInsert>

        Object insertByDTO(${entityInsertDTO} s);
    </#if>
    <#if generateUpdate>

        boolean updateByDTO(${entityUpdateDTO} s);
    </#if>


    }
</#if>