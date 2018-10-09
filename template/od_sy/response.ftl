package ${globalConfig.packageConfig.responseBeanPackage};

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.homedo.common.bean.model.base.BaseAMO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

/**
 * @author ${globalConfig.author};
 */
@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ${table.getClassName()}Resp extends BasePo{
<#list table.fieldInfoList as field>
  <#if field.enumBean??>
    @ApiModelProperty(value = "${field.comment}  状态属性")
    private ${field.enumBean.className} ${field.enumBean.getEnumField()};
  <#elseIf field.objTable??>
    @ApiModelProperty(value = "${field.comment}  关联对象")
    private ${field.objTable.getClassName()}Resp ${field.objTable.getClassField()}Resp;
  <#else>
    @ApiModelProperty(value = "${field.comment}")
    private ${field.columnType.type} ${field.javaField};
  </#if>
</#list>
}