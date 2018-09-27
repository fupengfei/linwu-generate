package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
/**
 * @author ${author}
 * @date ${date}
 */
@Api(value = "接口作用",tags = {"${table.name}"}, description = "描述")
@RequestMapping("/${table.name}")
public interface I${table.controllerName}{

}
