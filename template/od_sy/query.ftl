package ${globalConfig.packageConfig.responseBeanPackage}.${table.getClassNameLower()};

import java.util.List;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author ${globalConfig.author};
 */
@Data
public class ${table.getClassName()}Query{
<#list table.fieldInfoList as field>
  <#if field.enumBean??>
    /*
    *${field.comment}
    **/
    private Integer ${field.enumBean.getEnumField()};
  <#else>
    <#if field.columnType.type == 'LocalDateTime'>
    <#else>
    /*
    *${field.comment}
    **/
    private ${field.columnType.type} ${field.javaField};
    </#if>
  </#if>
</#list>
    /*
    *开始时间
    **/
    private LocalDateTime startDate;
    /*
    *结束时间
    **/
    private LocalDateTime endDate;
}