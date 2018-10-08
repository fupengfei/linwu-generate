package ${globalConfig.packageConfig.serviceImpl};

import ${globalConfig.packageConfig.entity};
import ${globalConfig.packageConfig.enhanced};
import ${globalConfig.packageConfig.dao}.${table.getClassName()}Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homedo.common.service.BaseService;
import java.util.List;
import com.homedo.common.bean.vo.context.UserContext;
import com.google.common.collect.Lists;
import com.homedo.common.dao.bean.po.base.BasePo;
import org.apache.commons.collections.CollectionUtils;
import java.util.stream.Collectors;
import java.util.Collections;


/**
 * @author ${globalConfig.author};
 */
@Service
public class ${table.getClassName()}Service extends BaseService<${table.getClassName()}>{
    @Autowired
    private ${table.getClassName()}Dao ${table.getClassField()}Dao;
<#list table.fieldInfoList as field>
    <#if field.objTable??>
    @Autowired
    private ${field.objTable.getClassName()}Service ${field.objTable.getClassField()}Service;
    </#if>
</#list>

    @Override
    public List <${table.getClassName()}Enhanced> getEnhanceds(List<?> ids, UserContext userContext) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        List<${table.getClassName()}> ${table.getClassField()}List = ${table.getClassField()}Dao.getListByIds(ids, ${table.getClassName()}.class);
        return this.convent2Enhanceds(${table.getClassField()}List);
    }

    @Override
    public List <${table.getClassName()}Enhanced> getEnhanceds4Active(List<?> ids, UserContext userContext) {
        List<${table.getClassName()}> ${table.getClassField()}List = ${table.getClassField()}Dao.getListByIdsGreaterThanMark0(ids, ${table.getClassName()}.class);
        if (CollectionUtils.isEmpty(${table.getClassField()}List)) {
            return Collections.emptyList();
        }
        return this.convent2Enhanceds(${table.getClassField()}List));
    }

    @Override
    public List <${table.getClassName()}Enhanced> convent2Enhanceds(List<? extends BasePo> pos) {
        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }
        List<${table.getClassName()}> beans = (List<${table.getClassName()}>) pos;
        return beans.stream().map(bean->new ${table.getClassName()}Enhanced(bean)).collect(Collectors.toList());
    }

<#list table.fieldInfoList as field>
<#if field.objTable??>
    public List<${table.getClassName()}Enhanced> append${field.objTable.getClassName()}Enhanced(List<${table.getClassName()}Enhanced> enhanceds,
        UserContext userContext) {
        if (CollectionUtils.isEmpty(enhanceds)) {
            return Collections.emptyList();
        }
        List<${field.objField.columnType.type}> ${field.objField.getJavaField()}s = enhanceds.stream().map(bean -> bean.get${field.objTable.getClassName()}Enhanced().get${field.objField.javaFieldFirstUppercase()}())
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(${field.objField.getJavaField()}s)) {
            return Collections.emptyList();
        }
        List<${field.objTable.getClassName()}Enhanced> appendEnhancedList = ${field.objTable.getJavaField()}Service.getEnhancedBy${field.objField.javaFieldFirstUppercase()}s(${field.objField.getJavaField()}s);
        if (CollectionUtils.isEmpty(appendEnhancedList)) {
            return Collections.emptyList();
        }
        return append${field.objTable.getClassName()}Enhanced(enhanceds, appendEnhancedList, userContext);
    }

    public List<${table.getClassName()}Enhanced> enhanceds append${field.objTable.getClassName()}Enhanced(List<${table.getClassName()}Enhanced> enhanceds,
        List<${field.objTable.getClassName()}Enhanced> appendEnhancedList, UserContext userContext) {
        enhanceds.forEach(bean -> this.append${field.objTable.getClassName()}Enhanced(bean, appendEnhancedList, userContext));
        return enhanceds;
    }

    public ${table.getClassName()}Enhanced append${field.objTable.getClassName()}Enhanced(${table.getClassName()}Enhanced enhanced, List<${field.objTable.getClassName()}Enhanced> appendEnhancedList,
            UserContext userContext) {
        appendEnhancedList.forEach(bean -> {
            if (bean.get${field.objField.javaFieldFirstUppercase()}().equals(enhanced.get${field.objTable.getClassName()}Enhanced().get${field.objField.javaFieldFirstUppercase()}())) {
                enhanced.set${field.objTable.getClassName()}Enhanced(bean);
                return enhanced;
            }
        });
        return enhanced;
    }
</#if>
</#list>

}
