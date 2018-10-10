package ${globalConfig.packageConfig.serviceImpl};

import ${globalConfig.packageConfig.entity};
import ${globalConfig.packageConfig.enhanced};
import ${globalConfig.packageConfig.dao}.${table.getClassName()}Dao;
<#list table.fieldInfoList as field>
<#if field.objTable??>
import ${globalConfig.packageConfig.serviceImpl}.${field.objTable.getClassName()}Service;
</#if>
</#list>
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
import org.springframework.transaction.annotation.Transactional;


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

    public ${table.getClassName()}Enhanced appendEnhancedAll(${table.getClassName()}Enhanced enhanced, UserContext userContext) {
    <#list table.fieldInfoList as field>
    <#if field.objTable??>
        ${field.objTable.getClassName()}Enhanced ${field.objTable.getClassField()}Enhanced = null;
    <#if field.objField.keyFlag>
        ${field.objTable.getClassField()}Enhanced = ${field.objTable.getClassField()}Service.getEnhanced(enhanced.get${field.objTable.getClassName()}Enhanced.get${field.objField.javaFieldFirstUppercase()},null);
    <#else>
        ${field.objTable.getClassName()} ${field.objTable.getClassField()}Param = new ${field.objTable.getClassName()}();
        ${field.objTable.getClassField()}Param.set${field.objField.javaFieldFirstUppercase()}(enhanced.get${field.objTable.getClassName()}Enhanced.get${field.objField.javaFieldFirstUppercase()});
        ${field.objTable.getClassName()} ${field.objTable.getClassField()}DbValue= ${field.objTable.getClassName()}Service.getOneByRecord(${field.objTable.getClassField()}Param,null);
        if(${field.objTable.getClassField()}DbValue!=null){
            ${field.objTable.getClassField()}Enhanced =(${field.objTable.getClassName()}Enhanced)${field.objTable.getClassName()}Service.convent2Enhanced(${field.objTable.getClassField()}DbValue);
        }
    </#if>
        enhanced.set${field.objTable.getClassName()}Enhanced(${field.objTable.getClassField()}Enhanced);

    </#if>
    </#list>
        return enhanced;
    }

    public List<${table.getClassName()}Enhanced> appendEnhancedsAll(List<${table.getClassName()}Enhanced> enhanceds, UserContext userContext) {
        <#list table.fieldInfoList as field>
        <#if field.objTable??>
        append${field.objTable.getClassName()}Enhanced(enhanceds,null);
        </#if>
        </#list>
        return enhancedItem;
    }

<#list table.fieldInfoList as field>
<#if field.objTable??>
    public void append${field.objTable.getClassName()}Enhanced(List<${table.getClassName()}Enhanced> enhanceds,UserContext userContext) {
        if (CollectionUtils.isEmpty(enhanceds)) {
            return;
        }
        List<${field.objField.columnType.type}> ${field.objField.getJavaField()}s = enhanceds.stream().map(bean -> bean.get${field.objTable.getClassName()}Enhanced().get${field.objField.javaFieldFirstUppercase()}())
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(${field.objField.getJavaField()}s)) {
            return;
        }
        List<${field.objTable.getClassName()}Enhanced> ${field.objTable.getClassField()}List = ${field.objTable.getClassField()}Service.getListByKeyValues(${field.objTable.getClassName()}.${field.objField.getFieldConstant()},${field.objField.getJavaField()}s,${field.objTable.getClassName()}.Class,null);
        if (CollectionUtils.isEmpty(${field.objTable.getClassField()}List)) {
            return;
        }
        List<${field.objTable.getClassName()}Enhanced> appendEnhancedList = ${field.objTable.getClassField()}Service.convent2Enhanceds(${field.objTable.getClassField()}List);

        for (${table.getClassName()}Enhanced enhanced : enhanceds) {
            A:for (${field.objTable.getClassName()}Enhanced ${field.objTable.getClassField()}Enhanced : appendEnhancedList) {
                if(${field.objTable.getClassField()}Enhanced.get${field.objField.javaFieldFirstUppercase()}().equals(enhanced.get${field.objTable.getClassName()}Enhanced().get${field.objField.javaFieldFirstUppercase()}())){
                    enhanced.set${field.objTable.getClassName()}Enhanced(${field.objTable.getClassField()}Enhanced);
                    break A;
                }
            }
        }
    }

</#if>
</#list>

<#list table.fieldInfoList as field>
    <#if field.keyFlag>
    @Transactional(rollbackFor = Exception.class)
    public void delete${table.getClassName()}(${field.columnType.type} ${field.getJavaField()}){
        //TODO 对关联数据有影响自行调整
        ${table.getClassName()} ${table.getClassField()} = this.getById(${field.getJavaField()},null);
        if(${table.getClassField()} == null){
            return;
        }

        this.deleteById(${field.getJavaField()},null);

        <#list table.fieldInfoList as fieldInner>
        <#if fieldInner.objTable??>
        Example ${fieldInner.objTable.getClassField()}Ex = new Example(${fieldInner.objTable.getClassName()}.class);
        ${fieldInner.objTable.getClassField()}Ex.createCriteria().andEqualTo(${fieldInner.objTable.getClassName()}.${fieldInner.objField.getFieldConstant()}, ${table.getClassField()}.get${fieldInner.javaFieldFirstUppercase()}());
        ${fieldInner.objTable.getClassName()}Service.deleteByExample(${fieldInner.objTable.getClassField()}Ex);

        </#if>
        </#list>
    }

    @Transactional(rollbackFor = Exception.class)
    public void update${table.getClassName()}(${field.columnType.type} ${field.getJavaField()},${table.getClassName()}Req req){
        //TODO 业务逻辑
    }
    </#if>
</#list>

    @Transactional(rollbackFor = Exception.class)
    public void add${table.getClassName()}(${table.getClassName()}Req req){
        //TODO 业务逻辑

    }
}
