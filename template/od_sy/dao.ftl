package ${package.Dao};

import com.homedo.common.dao.BaseDao;
import com.homedo.microservice.odin.wy.persistence.mapper.I${entity}Mapper;
import com.homedo.microservice.odin.wy.bean.po.${entity};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ${author}
 * @date ${date}
 */
@Repository
public class ${entity}Dao extends BaseDao<${entity}>{
    @Autowired
    private I${entity}Mapper  ${entityLower}Mapper;
}
