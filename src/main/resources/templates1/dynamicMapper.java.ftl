package ${package.Mapper};

<#if mapperAnnotationClass??>
    import ${mapperAnnotationClass.name};
</#if>
<#if overrideMethods>
<#--import org.apache.ibatis.annotations.Param;-->
    import io.github.bootystar.mybatisplus.core.metadata.IPage;
</#if>
import ${superMapperClassPackage};
import ${package.Entity}.${entity};
import ${basePackage}.${package4VO}.${entity}VO;
import io.github.bootystar.mybatisplus.enhancer.core.DynamicMapper;
<#--#if(${overrideMethods})
##import io.github.bootystar.mybatisplus.enhancer.query.SqlTree;
###end-->

<#if overrideMethods>
    import java.util.List;
</#if>

/**
* ${(table.comment)!''}Mapper
*
* @author ${author}
*/
<#if mapperAnnotationClass??>
    @${mapperAnnotationClass.simpleName}
</#if>
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}>, DynamicMapper
<${entity}VO>
    <#else>
    public interface ${table.mapperName} extends ${superMapperClass}<${entity}>, DynamicMapper
    <${entity}VO> {
        <#if overrideMethods>

        @Override
        List
        <${entity}VO> voSelectByXml(Object s, IPage
            <${entity}VO> page);
                </#if>

                }
</#if>