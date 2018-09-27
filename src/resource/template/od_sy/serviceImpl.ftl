package ${package.ServiceImpl};

import com.homedo.microservice.odin.wy.bean.po.${entity};
import com.homedo.microservice.odin.wy.bean.po.enhanced.${entity}Enhanced;
import com.homedo.microservice.odin.wy.persistence.dao.${entity}Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homedo.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.homedo.common.util.Detect;
import com.homedo.microservice.odin.wy.persistence.dao.${entity}Dao;
import java.util.List;
import com.homedo.common.bean.vo.context.UserContext;
import com.google.common.collect.Lists;
import com.homedo.common.dao.bean.po.base.BasePo;
import org.apache.commons.collections.CollectionUtils;
import java.util.stream.Collectors;
import java.util.Collections;
/**
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${table.serviceImplName} extends BaseService<${entity}>{
    @Autowired
    private ${entity}Dao ${entityLower}Dao;

    @Override
    public List <${entity}Enhanced> getEnhanceds(List<?> ids, UserContext userContext) {
        List<${entity}> ${entity}s = ${entityLower}Dao.getListByIds(ids, ${entity}.class);
        if (CollectionUtils.isEmpty(${entity}s)) {
            return Collections.emptyList();
        }
        return this.convent2Enhanceds(${entity}s);
    }

    @Override
    public List <${entity}Enhanced> getEnhanceds4Active(List<?> ids, UserContext userContext) {
        List<${entity}> ${entity}s = ${entityLower}Dao.getListByIdsGreaterThanMark0(ids, ${entity}.class);
        if (CollectionUtils.isEmpty(${entity}s)) {
            return Collections.emptyList();
        }
        return this.convent2Enhanceds(${entity}s);
    }


    @Override
    public List <${entity}Enhanced> convent2Enhanceds(List<? extends BasePo> pos) {
        if (CollectionUtils.isEmpty(pos)) {
            return Collections.emptyList();
        }
        List<${entity}> Areas = (List<${entity}>) pos;
        return Areas.stream().map(bean->new ${entity}Enhanced(bean)).collect(Collectors.toList());
    }


}
