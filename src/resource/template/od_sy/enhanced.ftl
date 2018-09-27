package ${package.Enhanced};

import com.homedo.microservice.odin.wy.bean.po.${entity};
import com.homedo.common.service.BaseService;
import com.homedo.common.dao.bean.po.enhanced.base.BaseEnhanced;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.LocalDate;
import lombok.experimental.Accessors;
import lombok.Data;
import org.springframework.beans.BeanUtils;
<#list enumPack as epack>
import ${epack.name};
</#list>
<#list joinObjPack as obj>
import ${obj.name};
</#list>
/**
 * @author ${author}
 * @date ${date}
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ${entity}Enhanced extends BaseEnhanced{
<#list enhancedFds as field>
    private ${field.eunmsClass} ${field.propertyName};
</#list>

    public ${entity}Enhanced(${entity} bean) {
        BeanUtils.copyProperties(bean, this);
        <#list enhancedEnumMethod as me>
        if (bean.get${me.getMethodUp}() != null) {
        this.${me.propertyName} = ${me.eunmsClass}
            .get${me.eunmsClass}ByCode(bean.get${me.getMethodUp}());
        }
        </#list>

        <#list enhancedObjMethod as obj>
        if (bean.get${obj.joinId}() != null) {
            ${obj.eunmsClass} ${obj.propertyName} = new ${obj.eunmsClass}();
            ${obj.propertyName}.setId(bean.get${obj.joinId}());
            this.${obj.propertyName} = ${obj.propertyName};
        }
        </#list>
    }

}
