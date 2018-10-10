package ${globalConfig.packageConfig.serviceImpl};

import ${globalConfig.packageConfig.entity};
<#list table.fieldInfoList as field>
<#if field.objTable??>
import ${globalConfig.packageConfig.serviceImpl}.${field.objTable.getClassName()}ServiceImpl;
</#if>
</#list>
import org.springframework.beans.factory.annotation.Autowired;
import com.homedo.core.common.base.service.BaseService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.homedo.common.dao.bean.po.base.BasePo;
import org.apache.commons.collections.CollectionUtils;
import java.util.stream.Collectors;
import java.util.Collections;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author ${globalConfig.author};
 */
@Service
public class ${table.getClassName()}ServiceImpl extends BaseService<${table.getClassName()}Mapper, ${table.getClassName()}> {
<#list table.fieldInfoList as field>
    <#if field.objTable??>
    @Autowired
    private ${field.objTable.getClassName()}ServiceImpl ${field.objTable.getClassField()}Service;
    </#if>
</#list>

<#list table.fieldInfoList as field>
    <#if field.keyFlag>
    @Transactional(rollbackFor = Exception.class)
    public void delete${table.getClassName()}(${field.columnType.type} ${field.getJavaField()}){

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
