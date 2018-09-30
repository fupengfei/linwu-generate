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
 * ${table.comment}
 */
@Data
@Accessors(chain = true)
@Table(name = "${table.name}")
public class ${table.file} extends BasePo{

<#list table.fieldInfoList as field>
  <#if field.keyFlag>
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "${field.name}")
  <#else>
    @Column(name = "${field.name}")
  </#if>
    /**
    *${field.comment}
    */
    private ${field.columnType.type} ${field.javaField};
</#list>

    /**
    *Java字段映射
    */
<#list table.fieldInfoList as field>
    public static final String ${field.fieldConstant} = "${field.name}";
</#list>

    /**
    *表字段映射
    */
<#list table.fieldInfoList as field>
    public static final String ${field.columnConstant} = "${field.name}";
</#list>
}