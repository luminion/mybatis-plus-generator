package ${package.ServiceImpl};

<#-- ------参数设置----start -->
<#assign entityInsertDTO = "${entity}InsertDTO">
<#assign entityUpdateDTO = "${entity}UpdateDTO">
<#assign entityVO = "${entity}VO">
<#if !generateService>
    <#assign superMehtod = "${dynamicServiceClassInfo.classSimpleName}.super">
<#else>
    <#assign superMehtod = "${table.serviceName}.super">
</#if>
<#-- ------参数设置----end -->
<#if overrideMethods&&generateSelect>
    import io.github.bootystar.mybatisplus.core.metadata.IPage;
</#if>
<#if !generateService||overrideMethods>
    import ${basePackage}.${package4VO}.${entityVO};
</#if>
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
<#if generateService>
    import ${package.Service}.${table.serviceName};
</#if>
import ${superServiceImplClassPackage};
<#if !generateService>
    import ${dynamicServiceClassInfo.classFullName};
</#if>
<#if overrideInitSuffixBuilder>
    import io.github.bootystar.mybatisplus.enhancer.builder.ExtraFieldSuffixBuilder;
</#if>
import org.springframework.stereotype.Service;


<#if overrideMethods>
    import java.util.List;
    <#if generateDelete||generateSelect>
        import java.io.Serializable;
    </#if>
    <#if generateImport>
        import java.io.InputStream;
    </#if>
    <#if generateExport>
        import java.io.OutputStream;
    </#if>
</#if>

/**
* ${(table.comment)!''}服务实现类
*
* @author ${author}
*/
@Service
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>()<#if generateService>, ${table.serviceName}<#else>, ${dynamicServiceClassInfo.classSimpleName}<${entityVO}></#if> {

    }
<#else>
    public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}><#if generateService> implements ${table.serviceName}<#else> implements ${dynamicServiceClassInfo.classSimpleName}<${entityVO}></#if> {
    <#if overrideInitSuffixBuilder>

        @Override
        public ExtraFieldSuffixBuilder getSuffixBuilder() {
        return new ExtraFieldSuffixBuilder()
        <#list extraFieldSuffixMap.entrySet() as entry>
            <#assign key = entry.key>
            <#assign value = entry.value>
            <#if value=="!="||value=="<>">
                .ne("${key}")
            <#elseif value==">">
                .gt("${key}")
            <#elseif value==">=">
                .ge("${key}")
            <#elseif value=="<">
                .lt("${key}")
            <#elseif value=="<=">
                .le("${key}")
            <#elseif value=="LIKE">
                .like("${key}")
            <#elseif value=="NOT LIKE">
                .notLike("${key}")
            <#elseif value=="IS NULL">
                .isNull("${key}")
            <#elseif value=="IS NOT NULL">
                .isNotNull("${key}")
            <#elseif value=="IN">
                .in("${key}")
            <#elseif value=="NOT IN">
                .notIn("${key}")
            </#if>
        </#list>
        ;
        }
    </#if>
    <#if overrideMethods>
        <#if generateSelect>

            @Override
            public ${entityVO} voById(Serializable id) {
            return ${superMehtod}.voById(id);
            }

            @Override
            public List<${entityVO}> voList(Object s) {
            return ${superMehtod}.voList(s);
            }

            @Override
            public IPage<${entityVO}> voPage(Object s, Long current, Long size) {
            return ${superMehtod}.voPage(s, current, size);
            }
        </#if>
        <#if generateExport>

            @Override
            public void excelExport(Object s, OutputStream os, Class<?> clazz, Long current, Long size, String... includeFields) {
            ${superMehtod}.excelExport(s, os, clazz, current, size, includeFields);
            }
        </#if>
        <#if generateImport>

            @Override
            public int excelImport(InputStream is, Class<?> clazz) {
        <#--        List<${entityInsertDTO}> dataList = ExcelHelper.read(is).head(clazz).sheet().doReadSync();-->
            return ${superMehtod}.excelImport(is, clazz);
            }
        </#if>
        <#if generateInsert>

            @Override
            public Object insertByDTO(Object s) {
        <#--        ${entityInsertDTO} dto = (${entityInsertDTO}) s;-->
            return ${superMehtod}.insertByDTO(s);
            }
        </#if>
        <#if generateUpdate>

            @Override
            public boolean updateByDTO(Object s) {
        <#--        ${entityUpdateDTO} dto = (${entityUpdateDTO}) s;-->
            return ${superMehtod}.updateByDTO(s);
            }
        </#if>
        <#if generateDelete>

            @Override
            public boolean removeById(Serializable id) {
            return super.removeById(id);
            }
        </#if>

    </#if>

    }
</#if>