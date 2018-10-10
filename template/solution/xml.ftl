<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">


<mapper namespace="${globalConfig.packageConfig.mapper}.${table.getClassName()}Mapper">

  <resultMap id="BaseResultMap" type="${globalConfig.packageConfig.entity}.${table.getClassName()}">
      <#list table.fieldInfoList as field>
        <#if field.keyFlag>
        <id column="${field.name}" property="${field.getJavaField()}"/>
        <#else>
        <result column="${field.name}" property="${field.getJavaField()}"/>
        </#if>
      </#list>
  </resultMap>
</mapper>