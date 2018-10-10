package ${globalConfig.packageConfig.responseBeanPackage}.${table.getClassNameLower()};

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.homedo.odin.solution.model.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @author ${globalConfig.author};
 */
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ${table.getClassName()}Req  extends BaseBean{
<#list table.fieldInfoList as field>
  <#if field.enumBean??>
    @ApiModelProperty(value = "${field.comment}  状态属性")
    private Integer ${field.enumBean.getEnumField()};

  <#elseIf field.objTable??>
    @ApiModelProperty(value = "${field.comment}  关联对象")
    private ${field.objTable.getClassName()}Req ${field.objTable.getClassField()}Req;

  <#else>
    @ApiModelProperty(value = "${field.comment}")
    <#if field.columnType.type=='LocalDateTime'>
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    </#if>
    private ${field.columnType.type} ${field.javaField};

  </#if>
</#list>
}