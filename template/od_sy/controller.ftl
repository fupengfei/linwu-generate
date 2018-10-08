package ${globalConfig.packageConfig.controller};

import org.springframework.web.bind.annotation.RestController;
import com.homedo.common.bean.data.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author ${globalConfig.author};
 */
@Api(tags = {"${table.name!}"}, description = "${table.comment!}")
@RestController
@RequestMapping("/${table.name}")
@Validated
public class ${table.getClassName()}Controller{
   @Autowired
   private ${table.getClassName()}Service ${table.getClassField()}Service;

   @GetMapping
   @ApiOperation(value = "获取${table.comment!}分页列表", response = ${table.getClassName()}PageResp.class)
   public ResponseData<${table.getClassName()}PageResp> page(@ModelAttribute ${table.getClassName()}PageReq req) {
      ResponseData result = new ResponseData();
      ${table.getClassName()}Query query = new ${table.getClassName()}Query();
      BeanUtils.copyProperties(req, query);
      Pagination<${table.getClassName()}Enhanced> pagination = new Pagination<>(req.getPage(), req.getSize(), true);
      //TODO 单表分页排序字段示例
      //pagination.addOrder(new Order(ServiceItem.COLUMN_CREATED_TIME, Order.ORDER_DESC), ServiceItem.class);
      pagination = ${table.getClassField()}Service.getEnhancedsPage(pagination, query);
      ${table.getClassName()}PageResp pageResp = new ${table.getClassName()}PageResp();
      //do something
      pageResp.setCount(pagination.getCount());
      pageResp.setPage(pagination.getIndex());
      pageResp.setSize(pagination.getSize());
      //TODO 分页数据需要填充自行扩展
      pageResp.setItems(pagination.getItems());
      result.setData(pageResp);
      return result;
   }

   <#list table.fieldInfoList as field>
   <#if field.keyFlag>
   @GetMapping("/{${field.name}}")
   @ApiOperation(value = "获取${table.comment!}详情", response = ${table.getClassName()}Resp.class)
   public ResponseData<${table.getClassName()}Resp> detail(@PathVariable("{${field.name}}") ${field.columnType.type} ${field.getJavaField()}) {
      ResponseData result = new ResponseData();
      ${table.getClassName()} ${table.getClassField()} = ${table.getClassField()}Service.getById(${field.getJavaField()},null);
      if(${table.getClassField()}==null){
        return result;
      }
      ${table.getClassName()}Enhanced groupEnhanced = (${table.getClassName()}Enhanced) ${table.getClassField()}Service.convent2Enhanced(${table.getClassField()});
      //TODO  填充 详情需要关联数据信息
      <#list table.fieldInfoList as field>
      <#if field.objTable??>

      </#if>
      </#list>
      return result;
   }
   </#if>
   </#list>
}
