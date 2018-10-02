package ${globalConfig.packageConfig.enumPackage}.${bizEnum};

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ${globalConfig.author};
 */
@Getter
@AllArgsConstructor
public enum ${enumBean.className}{
<#list enumBean.properties as pro>
    <#if (pro_has_next)>
    ${pro.name}(${pro.code},"${pro.msg}"),
    <#else>
    ${pro.name}(${pro.code},"${pro.msg}");
    </#if>


</#list>
    private Integer code;
    private String  msg;

    public static ${enumBean.className} get${enumBean.className}ByCode(Integer code) {
        return Arrays.stream(${enumBean.className}.values()).filter(obj -> obj.getCode().equals(code))
                .findFirst().orElse(null);
    }

    public static boolean codeExist(Integer code) {
        return Arrays.stream(${enumBean.className}.values()).anyMatch(obj -> obj.getCode().equals(code));
    }
}
