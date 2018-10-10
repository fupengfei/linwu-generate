package ${globalConfig.packageConfig.entity};

import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * @author ${globalConfig.author};
 * ${table.comment!}
 */
@Data
@Accessors(chain = true)
@Table(name = "${table.name}")
public class ${table.getClassName()} Model<${table.getClassName()}>{

<#list table.fieldInfoList as field>
  /**
    *${field.comment!}
    */
  <#if field.keyFlag>
  @TableId(value = "${field.name}", type = IdType.AUTO)
  <#else>
  @TableField("${field.name}")
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