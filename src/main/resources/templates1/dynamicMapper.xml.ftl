<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">
    <#if enableCache>

        <!-- 开启二级缓存 -->
        <cache type="${cacheClassName}"/>
    </#if>
    <#if baseResultMap>

        <!-- 通用查询映射结果 -->
        <resultMap id="BaseResultMap" type="${package.Entity}.${entity}">
            <<#list table.fields as field><#--生成主键排在第一位-->
            <#if field.keyFlag>
                <id column="${field.name}" property="${field.propertyName}"/>
            </#if>
            </#list>
            <<#list table.commonFields as field><#--生成公共字段-->
        <result column="${field.name}" property="${field.propertyName}"/>
            </#list>
            <<#list table.fields as field><#--生成普通字段-->
            <#if !field.keyFlag>
                <result column="${field.name}" property="${field.propertyName}"/>
            </#if>
            </#list>
        </resultMap>

        <resultMap id="BaseResultMap4VO" type="${basePackage}.${package4VO}.${entity}VO">
            <<#list table.fields as field><#--生成主键排在第一位-->
            <#if field.keyFlag>
                <id column="${field.name}" property="${field.propertyName}"/>
            </#if>
            </#list>
            <<#list table.commonFields as field><#--生成公共字段-->
        <result column="${field.name}" property="${field.propertyName}"/>
            </#list>
            <<#list table.fields as field><#--生成普通字段-->
            <#if !field.keyFlag && !field.logicDeleteField>
                <result column="${field.name}" property="${field.propertyName}"/>
            </#if>
            </#list>
        </resultMap>
    </#if>
    <#if baseColumnList>

        <!-- 通用查询结果列 -->
        <sql id="Base_Column_List">
            <<#list table.commonFields as field>>
            ${field.columnName},
            </#list>
            ${table.fieldNames}
        </sql>
    </#if>

    <select id="voSelectByXml"<#if resultMapForVO> resultMap="BaseResultMap4VO"<#else> resultType="${basePackage}.${package4VO}.${entity}VO"</#if>>
        SELECT
        a.*
        FROM
        <#--         ${schemaName} ${table.name} a -->
        ${table.name} a
        <where>
            <include refid="io.github.bootystar.mybatisplus.enhancer.core.DynamicMapper.dynamicSelect"/>
            <<#list table.fields as field>>
            <#if field.logicDeleteField>
                <#if field.metaInfo.defaultValue??>
                    AND a.${field.columnName} = ${field.metaInfo.defaultValue}
                <#else>
                    AND a.${field.columnName} IS NULL
                </#if>
                <#break>
            </#if>
            </#list>
        </where>
        <trim prefix="ORDER BY" prefixOverrides=",">
            <include refid="io.github.bootystar.mybatisplus.enhancer.core.DynamicMapper.dynamicSort"/>
            <#if orderBySql??>
                , ${orderBySql}
            </#if>
        </trim>
    </select>

</mapper>