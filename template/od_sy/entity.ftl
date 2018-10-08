package ${globalConfig.packageConfig.entity};

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import com.homedo.common.dao.bean.po.base.BasePo;
import java.time.LocalDateTime;

/**
 * @author ${globalConfig.author};
 * ${table.comment!}
 */
@Data
@Accessors(chain = true)
@Table(name = "${table.name}")
public class ${table.getClassName()} extends BasePo{

<#list table.fieldInfoList as field>
  /**
    *${field.comment!}
    */
  <#if field.keyFlag>
  @Id
  @GeneratedValue(generator = "JDBC")
  <#else>
  @Column(name = "${field.name}")
  </#if>
  private ${field.columnType.type} ${field.getJavaField()};
</#list>

  /**
  *Java字段映射
  */
<#list table.fieldInfoList as field>
  public static final String ${field.getFieldConstant()} = "${field.name}";
</#list>

  /**
  *表字段映射
  */
<#list table.fieldInfoList as field>
  public static final String ${field.getColumnConstant()} = "${field.name}";
</#list>
}