package ${package.Mapper};

import com.homedo.microservice.odin.wy.bean.po.${entity};
import com.homedo.common.dao._mapper.support._mapper.MyMapper;
import org.springframework.stereotype.Repository;

/**
 * @author ${author}
 * @date ${date}
 */
@Repository
public interface I${table.mapperName} extends MyMapper<${entity}>{

}
