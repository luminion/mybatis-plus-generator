package ${package.ServiceImpl};

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
<#if generateImport||generateExport>
    import ${excelBasePackage}.EasyExcel;
    import ${excelBasePackage}.write.style.column.LongestMatchColumnWidthStyleStrategy;
</#if>
<#if generateSelect||generateExport>
    import io.github.bootystar.mybatisplus.core.metadata.IPage;
    import io.github.bootystar.mybatisplus.extension.plugins.pagination.Page;
</#if>
<#if generateInsert||generateSelect>
    import io.github.bootystar.mybatisplus.core.metadata.TableInfo;
    import io.github.bootystar.mybatisplus.core.metadata.TableInfoHelper;
</#if>
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
<#-- ------默认导入 start---------- -->
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
<#if generateService>
    import ${package.Service}.${table.serviceName};
</#if>
import ${superServiceImplClassPackage};
<#if generateSelect>
    import org.apache.ibatis.exceptions.TooManyResultsException;
</#if>
import org.springframework.stereotype.Service;
<#-- ------默认导入 end---------- -->
<#if generateInsert||generateUpdate||generateImport>
    import org.springframework.beans.BeanUtils;
</#if>

<#if generateSelect||generateDelete>
    import java.io.Serializable;
</#if>
<#if generateImport>
    import java.io.InputStream;
</#if>
<#if generateImport||generateExport>
    import java.io.OutputStream;
</#if>
<#if generateImport>
    import java.util.stream.Collectors;
</#if>
import java.util.*;
<#-- map反射用 -->
<#--import java.lang.reflect.Field;-->
<#--import java.lang.reflect.Modifier;-->

/**
* ${(table.comment)!''}服务实现类
*
* @author ${author}
* @since ${date}
*/
@Service
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>()<#if generateService>, ${table.serviceName}</#if> {

    }
<#else>
    public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}><#if generateService> implements ${table.serviceName}</#if> {
    <#if generateSelect>

        <#if generateService>
            @Override
        </#if>
        public ${entityVO} voById(Serializable id) {
        HashMap
        <String, Object> map = new HashMap<>();
        map.put(TableInfoHelper.getTableInfo(getEntityClass()).getKeyProperty(), id);
        IPage<${entityVO}> page = new Page<>(1L, 1L);
        List<${entityVO}> vs = getBaseMapper().voQuery(map, page);
        if (vs == null || vs.isEmpty()) return null;
        if (page.getTotal() > 1) throw new TooManyResultsException("Expected one result (or null) to be returned by id, but found:" + page.getTotal());
        return vs.get(0);
        }

        <#if generateService>
            @Override
        </#if>
        public List<${entityVO}> voList(${entitySelectDTO} s) {
        return getBaseMapper().voQuery(s, null);
        }

        <#if generateService>
            @Override
        </#if>
        public IPage<${entityVO}> voPage(${entitySelectDTO} s, Long current, Long size) {
        if (current == null || current < 1) current = 1L;
        if (size == null) size = 10L;
        Page<${entityVO}> page = new Page<>(current, size);
        List<${entityVO}> voList = getBaseMapper().voQuery(s, page);
        page.setRecords(voList);
        return page;
        }
    </#if>
    <#if generateExport>

        <#if generateService>
            @Override
        </#if>
        public void excelExport(${entitySelectDTO} s, OutputStream os, Class<?> clazz, Long current, Long size, String... includeFields) {
        <#if generateSelect>
            List<${entityVO}> voList = voPage(s, current, size).getRecords();
        <#else>
            if (current == null || current < 1) current = 1L;
            if (size == null) size = 10L;
        <#--        Page<${entityVO}> page = new Page<>(current, size);-->
        <#--        List<${entityVO}> voList = getBaseMapper().voQuery(s, page);-->
            List<${entityVO}> voList = getBaseMapper().voQuery(s, new Page<>(current, size));
        </#if>
        EasyExcel.write(os, clazz).includeColumnFieldNames(Arrays.asList(includeFields)).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet().doWrite(voList);
        }
    </#if>
    <#if generateImport>

        <#if generateService>
            @Override
        </#if>
        public void excelTemplate(OutputStream os, Class<?> clazz) {
        EasyExcel.write(os, clazz).sheet().doWrite(Collections.emptyList());
        }

        <#if generateService>
            @Override
        </#if>
        public int excelImport(InputStream is, Class<?> clazz) {
        List<?> dataList = EasyExcel.read(is).head(clazz).sheet().doReadSync();
        List<${entity}> entityList = dataList.stream().map(e -> {
    <#--            ${sourceEntityInsertDTO} dto = (${sourceEntityInsertDTO}) e;-->
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(e, entity);
        return entity;
        }).collect(Collectors.toList());
        super.saveBatch(entityList);
        return entityList.size();
        }
    </#if>
    <#if generateInsert>

        <#if generateService>
            @Override
        </#if>
        public Object insertByDTO(${entityInsertDTO} s) {
        <#if !actualType>
        <#--        ${sourceEntityInsertDTO} dto = (${sourceEntityInsertDTO}) s;-->
        </#if>
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(s, entity);
        super.save(entity);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        return tableInfo.getPropertyValue(entity, tableInfo.getKeyProperty());
        }
    </#if>
    <#if generateUpdate>

        <#if generateService>
            @Override
        </#if>
        public boolean updateByDTO(${entityUpdateDTO} s) {
        <#if !actualType>
        <#--        ${sourceEntityUpdateDTO} dto = (${sourceEntityUpdateDTO}) s;-->
        </#if>
        ${entity} entity = new ${entity}();
        BeanUtils.copyProperties(s, entity);
        return super.updateById(entity);
        }
    </#if>
    <#if generateDelete>

        @Override
        public boolean removeById(Serializable id) {
        return super.removeById(id);
        }
    </#if>

    }
</#if>