package ${globalConfig.packageConfig.mapper};

import ${globalConfig.packageConfig.entity};
import com.homedo.common.dao._mapper.support._mapper.MyMapper;
import org.springframework.stereotype.Repository;

/**
 * @author ${globalConfig.author};
 */
@Repository
public interface I${table.getClassName()}Mapper extends MyMapper<${table.getClassName()}>{

}
