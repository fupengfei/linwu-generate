package ${globalConfig.packageConfig.responseBeanPackage}.${table.getClassNameLower()};

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.homedo.microservice.odin.wy.api.bean.model.base.BasePageReq
import com.homedo.common.bean.model.base.BaseAMO;
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
public class ${table.getClassName()}PageReq extends BasePageReq{
<#list table.fieldInfoList as field>
  <#if field.enumBean??>
    @ApiModelProperty(value = "${field.comment}  状态属性")
    private Integer ${field.enumBean.getEnumField()};

  <#else>
    <#if field.columnType.type == 'LocalDateTime'>
    <#else>
    @ApiModelProperty(value = "${field.comment}")
    private ${field.columnType.type} ${field.javaField};

    </#if>
  </#if>
</#list>
    @ApiModelProperty(value = "开始时间 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "结束时间 yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
}