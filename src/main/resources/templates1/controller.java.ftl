package ${package.Controller};

<#-- ------参数设置----start -->
<#assign requestBaseUrL = "${(baseUrl)!''}${(package.ModuleName)!''}/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>">
<#if requestBody??>
    <#assign requiredBodyStr = "@RequestBody ">
    <#if postQuery??>
        <#assign optionalBodyStr = "@RequestBody(required = false) ">
    </#if>
</#if>
<#if validated??>
    <#assign validatedStr = "@Validated ">
</#if>
<#assign entityInsertDTO = "${entity}InsertDTO">
<#assign entityUpdateDTO = "${entity}UpdateDTO">
<#assign primaryKeyPropertyType="String">
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign primaryKeyPropertyType=field.propertyType>
        <#break>
    </#if>
</#list>
<#assign entityVO = "${entity}VO">
<#if selectDTO.classFullName??>
    <#assign entitySelectDTO = selectDTO.clazz("${entity}")>
    <#assign importStr4SelectDTO="import ${selectDTO.classFullName};">
<#else>
    <#assign entitySelectDTO = "${entity}SelectDTO">
    <#assign importStr4SelectDTO="import ${basePackage}.${package4DTO}.${entity}SelectDTO;">
</#if>
<#if useMapSelectDTO && (!postQuery?? || !requestBody??)>
    <#assign entitySelectDTO = "@RequestParam ${entitySelectDTO}">
</#if>
<#assign requestMethod4Select = "<#if postQuery??>@PostMapping<#else>@GetMapping</#if>">
<#--set($pagePathParams = "@PathVariable(value = ""current"", required = false) Long current, @PathVariable(value = ""size"", required = false) Long size")-->
<#if pathVariable??>
    <#assign pagePathParams = "/{current}/{size}">
    <#assign pageMethodParams = "@PathVariable(\"current\") Long current, @PathVariable(\"size\") Long size">
    <#assign idPathParams = "/{id}">
    <#assign idMethodParams = "@PathVariable(\"id\") ">
<#else>
    <#assign pageMethodParams = "Long current, Long size">
</#if>
<#if pageMethod.classFullName??>
    <#assign pageClazz4return = pageMethod.clazz("${entityVO}")>
<#else>
    <#assign pageClazz4return = "IPage<${entityVO}>">
</#if>
<#if generateService>
    import ${package.Service}.${table.serviceName};
    <#assign baseService = table.serviceName>
<#else>
    import ${package.ServiceImpl}.${table.serviceImplName};
    <#assign baseService = table.serviceImplName>
</#if>
<#-- ------参数设置----end -->
import ${package.Entity}.${entity};
<#if !useMapSelectDTO && (generateSelect||generateExport)>
    ${importStr4SelectDTO}
</#if>
<#if generateInsert||generateImport>
    import ${basePackage}.${package4DTO}.${entityInsertDTO};
</#if>
<#if generateUpdate>
    import ${basePackage}.${package4DTO}.${entityUpdateDTO};
</#if>
<#if generateSelect||generateExport>
    import ${basePackage}.${package4VO}.${entityVO};
</#if>
<#if returnMethod.classFullName??>
    import ${returnMethod.classFullName};
</#if>
<#if generateSelect>
    <#if pageMethod.classFullName??>
        import ${pageMethod.classFullName};
    <#else>
        import io.github.bootystar.mybatisplus.core.metadata.IPage;
    </#if>
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>
<#if springdoc??>
    import io.swagger.v3.oas.annotations.tags.Tag;
    import io.swagger.v3.oas.annotations.*;
<#elseif swagger??>
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
</#if>
<#if autoWired??>
    import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.web.bind.annotation.*;
<#if !restControllerStyle>
    import org.springframework.stereotype.Controller;
</#if>
<#if validated?? && (generateInsert||generateUpdate)>
    import org.springframework.validation.annotation.Validated;
</#if>
<#if generateImport>
    import org.springframework.web.multipart.MultipartFile;
</#if>

<#if !autoWired??>
    import ${javaApiPackage}.annotation.Resource;
</#if>
<#if generateImport||generateExport>
    import ${javaApiPackage}.servlet.http.HttpServletResponse;
</#if>
<#if generateImport||generateExport>
    import java.io.IOException;
</#if>
<#--#if(${generateSelect}||${generateDelete})
##import java.io.Serializable;
###end-->
<#if generateSelect>
    import java.util.List;
    <#if useMapSelectDTO && (generateSelect||generateExport)>
        import java.util.Map;
    </#if>
</#if>

/**
* ${(table.comment)!''}-前端控制器
*
* @author ${author}
* @since ${date}
<#if extraClassLinkComment>
    * @see ${package.Entity}.${entity}
    * @see ${basePackage}.${package4VO}.${entity}VO
    * @see ${package.Mapper}.${table.mapperName}
    * @see ${package.ServiceImpl}.${table.serviceImplName}
</#if>
*/
<#if springdoc??>
<#--@Tag(name = "${table.comment}", description = "${(table.comment)!''}")-->
    @Tag(name = "${table.comment}${(swaggerUUID)!''}", description = "${(table.comment)!''}")
<#elseif swagger??>
<#--@Api(value = "${requestBaseUrL}", tags = "${(table.comment)!''}")-->
    @Api(value = "${requestBaseUrL}", tags = "${(table.comment)!''}${(swaggerUUID)!''}")
</#if>
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
<#if crossOrigins??>
    @CrossOrigin
</#if>
@RequestMapping("${requestBaseUrL}")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>
</#if>

<#if autoWired??>
    @Autowired
<#else>
    @Resource
</#if>
private ${baseService} baseService;
<#-- ------------查询相关--------start -->
<#if generateSelect>

    <#if springdoc??>
        @Operation(summary = "根据id查询详情")
    <#elseif swagger??>
        @ApiOperation(value = "根据id查询详情")
    <#else>
        /**
        * 根据id查询详情
        *
        * @param id 主键id
        * @return 查询结果
    <#--      * @author ${author} -->
        */
    </#if>
    <#if !restControllerStyle>
        @ResponseBody
    </#if>
    <#if restful??>
        @GetMapping("${(idPathParams)!''}")
    <#else>
        @GetMapping("/select${(idPathParams)!''}")
    </#if>
    public ${returnMethod.clazz("${entityVO}")} selectId(${(idMethodParams)!''}${primaryKeyPropertyType} id) {
    return ${returnMethod.method("baseService.voById(id)")};
    }

    <#if springdoc??>
        @Operation(summary = "列表")
    <#elseif swagger??>
        @ApiOperation(value = "列表")
    <#else>
        /**
        * 列表
        *
        * @param s 查询条件
        * @return 查询结果
    <#--      * @author ${author} -->
        */
    </#if>
    <#if !restControllerStyle>
        @ResponseBody
    </#if>
    ${requestMethod4Select}("/list")
    public ${returnMethod.clazz("List<${entityVO}>")} selectList(${(optionalBodyStr)!''}${entitySelectDTO} s) {
    return ${returnMethod.method("baseService.voList(s)")};
    }

    <#if springdoc??>
        @Operation(summary = "分页查询")
    <#elseif swagger??>
        @ApiOperation(value = "分页查询")
    <#else>
        /**
        * 分页查询
        *
        * @param s 查询条件
        * @param current 页码
        * @param size 每页大小
        * @return 查询结果
    <#--      * @author ${author} -->
        */
    </#if>
    <#if !restControllerStyle>
        @ResponseBody
    </#if>
    ${requestMethod4Select}("/page${(pagePathParams)!''}")
    public ${returnMethod.clazz(${pageClazz4return})} selectPage(${(optionalBodyStr)!''}${entitySelectDTO} s, ${(pageMethodParams)!''}) {
    return ${returnMethod.method(${pageMethod.method("baseService.voPage(s, current, size)")})};
    }
</#if>
<#if generateExport>

    <#if springdoc??>
        @Operation(summary = "excel文件导出")
    <#elseif swagger??>
        @ApiOperation(value = "excel文件导出")
    <#else>
        /**
        * excel文件导出
        * @param s 查询条件
        * @param current 页码
        * @param size 每页大小(-1为全部)
    <#--      * @author ${author} -->
        */
    </#if>
    ${requestMethod4Select}("/excel/export${(pagePathParams)!''}")
    public void excelExport(${(optionalBodyStr)!''}${entitySelectDTO} s, ${pageMethodParams}, HttpServletResponse response) throws IOException {
    response.setContentType("application/vnd.ms-excel");
<#--         response.setHeader("Access-Control-Expose-Headers", "Content-Disposition"); -->
    response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xlsx");
    baseService.excelExport(s, response.getOutputStream(), ${entityVO}.class, current, size);
    }
</#if>
<#if generateImport>

    <#if springdoc??>
        @Operation(summary = "excel导入模板")
    <#elseif swagger??>
        @ApiOperation(value = "excel导入模板")
    <#else>
        /**
        * excel导入模板
        *
    <#--      * @author ${author} -->
        */
    </#if>
    @GetMapping("/excel/template")
    public void excelTemplate(HttpServletResponse response) throws IOException {
    response.setContentType("application/vnd.ms-excel");
<#--         response.setHeader("Access-Control-Expose-Headers", "Content-Disposition"); -->
    response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xlsx");
    baseService.excelTemplate(response.getOutputStream(), ${entityInsertDTO}.class);
    }

    <#if springdoc??>
        @Operation(summary = "excel文件导入")
    <#elseif swagger??>
        @ApiOperation(value = "excel文件导入")
    <#else>
        /**
        * excel文件导入
        *
        * @param excel excel文件
        * @return 导入条数
    <#--      * @author ${author} -->
        */
    </#if>
    <#if !restControllerStyle>
        @ResponseBody
    </#if>
    @PostMapping("/excel/import")
    public ${returnMethod.clazz("Integer")} excelImport(@RequestParam("excel") MultipartFile excel) throws IOException {
    if (excel == null || excel.isEmpty()) return ${returnMethod.method(0)};
    return ${returnMethod.method("baseService.excelImport(excel.getInputStream(), ${entityInsertDTO}.class)")};
    }
</#if>
<#if generateInsert>

    <#if springdoc??>
        @Operation(summary = "新增")
    <#elseif swagger??>
        @ApiOperation(value = "新增")
    <#else>
        /**
        * 新增
        *
        * @param s 新增入参对象
        * @return id
    <#--      * @author ${author} -->
        */
    </#if>
    <#if !restControllerStyle>
        @ResponseBody
    </#if>
    <#if restful??>
        @PostMapping("")
    <#else>
        @PostMapping("/insert")
    </#if>
    public ${returnMethod.clazz("Object")} insert(${(requiredBodyStr)!''}${(validatedStr)!''}${entityInsertDTO} s) {
    return ${returnMethod.method("baseService.insertByDTO(s)")};
    }
</#if>
<#if generateUpdate>

    <#if springdoc??>
        @Operation(summary = "修改")
    <#elseif swagger??>
        @ApiOperation(value = "修改")
    <#else>
        /**
        * 修改
        *
        * @param s 修改入参对象
        * @return 是否成功
    <#--      * @author ${author} -->
        */
    </#if>
    <#if !restControllerStyle>
        @ResponseBody
    </#if>
    <#if restful??>
        @PutMapping("")
    <#else>
        @PostMapping("/update")
    </#if>
    public ${returnMethod.clazz("Boolean")} update(${(requiredBodyStr)!''}${(validatedStr)!''}${entityUpdateDTO} s) {
    return ${returnMethod.method("baseService.updateByDTO(s)")};
    }
</#if>
<#if generateDelete>

    <#if springdoc??>
        @Operation(summary = "删除")
    <#elseif swagger??>
        @ApiOperation(value = "删除")
    <#else>
        /**
        * 删除
        *
        * @param id 主键id
        * @return 是否成功
    <#--      * @author ${author} -->
        */
    </#if>
    <#if !restControllerStyle>
        @ResponseBody
    </#if>
    <#if restful??>
        @DeleteMapping("${(idPathParams)!''}")
    <#else>
        @PostMapping("/delete${(idPathParams)!''}")
    </#if>
    public ${returnMethod.clazz("Boolean")} delete(${(idMethodParams)!''}${primaryKeyPropertyType} id) {
    return ${returnMethod.method("baseService.removeById(id)")};
    }
</#if>

}