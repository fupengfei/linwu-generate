package ${globalConfig.packageConfig.enhanced};

import lombok.Data;
import lombok.experimental.Accessors;
import com.homedo.common.dao.bean.po.base.BasePo;
import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;

/**
 * @author ${globalConfig.author};
 * ${table.comment}
 * 数据增强类
 */
@Data
@Accessors(chain = true)
public class ${table.getClassName()}Enhanced extends BaseEnhanced{

<#list table.fieldInfoList as field>
  <#if field.enumBean??>
    private ${field.enumBean.className}  ${field.enumBean.getEnumField()};
  <#elseIf field.objTable??>
    private ${field.objTable.getClassName()}Enhanced ${field.objTable.getClassField()}Enhanced
  <#else>
    private ${field.columnType.type} ${field.javaField};
  </#if>
</#list>

    public ${table.getClassName()}Enhanced(${table.getClassName()} bean) {
        BeanUtils.copyProperties(bean, this);
    <#list table.fieldInfoList as field>
      <#if field.enumBean??>
        if(bean.get${field.javaFieldFirstUppercase()}()!=null){
          this.${field.enumBean.getEnumField()} = ${field.enumBean.className}.getByCode(bean.get${field.javaFieldFirstUppercase()}());
        }
      <#elseIf field.objTable??>
        if(bean.get${field.javaFieldFirstUppercase()}()!=null){
          this.${field.objTable.getClassField()}Enhanced = new ${field.objTable.getClassName()}Enhanced();
          this.${field.objTable.getClassField()}Enhanced.set${field.objField.javaFieldFirstUppercase()}(bean.get${field.javaFieldFirstUppercase()}())
        }
      </#if>
    </#list>
    }
}