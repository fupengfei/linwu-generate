package ${globalConfig.packageConfig.dao};

import com.homedo.common.dao.BaseDao;
import ${globalConfig.packageConfig.mapper}.I${table.getClassName()}Mapper;
import ${globalConfig.packageConfig.entity};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author ${globalConfig.author};
 */
@Repository
public class ${table.getClassName()}Dao extends BaseDao<${table.getClassName()}>{
    @Autowired
    private I${table.getClassName()}Mapper  ${table.getClassField()}Mapper;
}
