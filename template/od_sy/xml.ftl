<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">


<mapper namespace="${globalConfig.packageConfig.mapper}.${table.getClassField()}Mapper">

  <resultMap id="BaseResultMap" type="${globalConfig.packageConfig.mapper}.${table.getClassName()}Mapper">
    <!-- WARNING - @mbggenerated -->
  </resultMap>

  <sql id="Base_Column_List">
  <#list table.fieldInfoList as field>${field.name}<#sep>,</#list>
  </sql>

</mapper>