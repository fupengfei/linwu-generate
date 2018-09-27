package ${package.Enums}.${enumPack};

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ${author}
 * @date ${date}
 */
@Getter
@AllArgsConstructor
public enum ${enumsClass}{
<#list databean as bean>
<#if (bean_has_next)>
    ${bean.name}(${bean.code},"${bean.msg}"),
<#else>
    ${bean.name}(${bean.code},"${bean.msg}");
</#if>
</#list>

    private Integer code;
    private String  msg;

    public static ${enumsClass} get${enumsClass}ByCode(Integer code) {
        return Arrays.stream(${enumsClass}.values()).filter(obj -> obj.getCode().equals(code))
                .findFirst().orElse(null);
    }

    public static boolean codeExist(Integer code) {
        return Arrays.stream(${enumsClass}.values()).anyMatch(obj -> obj.getCode().equals(code));
    }
}
