package ${package.Entity};

import lombok.Data;
import lombok.experimental.Accessors;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import com.homedo.common.dao.bean.po.base.BasePo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;
/**
 * @author ${author}
 * @date ${date}
 */
@Data
@Accessors(chain = true)
@Table(name = "${table.name}")
public class ${entity} extends BasePo{
<#list table.fields as field>
    /**
     * ${field.comment}
     */
    <#if field.keyFlag>
    @Id
    @GeneratedValue(generator = "JDBC")
    </#if>
    @Column(name = "${field.name}")
    private ${field.propertyType} ${field.propertyName};
</#list>

     /**
     * PO属性查询列表
     */
<#list table.fields as field>
    public static final String FIELD_${field.upField} ="${field.propertyName}";
</#list>

    /**
     * 表列名查询列表
     */
<#list table.fields as field>
    public static final String COLUMN_${field.upField} = "${field.name}";
</#list>
}
