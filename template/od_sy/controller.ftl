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
import com.homedo.microservice.odin.wy.convert.CommonConvert;
import ${globalConfig.packageConfig.serviceImpl}.${table.getClassName()}Service;
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
   public ResponseData<${table.getClassName()}PageResp> page(@Validated @ModelAttribute ${table.getClassName()}PageReq req) {
      ResponseData result = new ResponseData();
      ${table.getClassName()}Query query = new ${table.getClassName()}Query();
      BeanUtils.copyProperties(req, query);
      //TODO 单表分页示例
      Pagination<${table.getClassName()}Enhanced> pagination = new Pagination<>(req.getPageNum(), req.getPageSize(), true);
      //TODO 分页排序字段示例
      //pagination.addOrder(new Order(ServiceItem.COLUMN_CREATED_TIME, Order.ORDER_DESC), ServiceItem.class);
      pagination = ${table.getClassField()}Service.getEnhancedsPage(pagination, query);

      ${table.getClassName()}PageResp pageResp = new ${table.getClassName()}PageResp();
      pageResp.setCount(pagination.getCount());
      pageResp.setPage(pagination.getIndex());
      pageResp.setSize(pagination.getSize());

      //TODO 分页数据需要填充自行扩展
      ${table.getClassField()}Service.appendEnhancedsAll(pagination.getItems(),null);
      List<${table.getClassName()}Resp> resps = CommonConvert.convent${table.getClassName()}Resp(pagination.getItems());
      pageResp.setItems(resps);

      result.setData(pageResp);
      return result;
   }

   <#list table.fieldInfoList as field>
   <#if field.keyFlag>
   @GetMapping("/{${field.getJavaField()}}")
   @ApiOperation(value = "获取${table.comment!}详情", response = ${table.getClassName()}Resp.class)
   public ResponseData<${table.getClassName()}Resp> detail(@PathVariable("{${field.getJavaField()}}") ${field.columnType.type} ${field.getJavaField()}) {
      ResponseData result = new ResponseData();
      ${table.getClassName()}Enhanced ${table.getClassField()}Enhanced = ${table.getClassField()}Service.getEnhanced(${field.getJavaField()},null);
      if(${table.getClassField()}Enhanced == null){
        return result;
      }
      //TODO 填充关联数据对象，代码差别调整
      ${table.getClassField()}Service.appendEnhancedAll(${table.getClassField()}Enhanced,null);
      ${table.getClassName()}Resp resp = CommonConvert.convert${table.getClassName()}Resp(${table.getClassField()}Enhanced);
      result.setData(resp);
      return result;
   }

   @DeleteMapping("/{${field.getJavaField()}}")
   @ApiOperation(value = "删除${table.comment!}")
   public ResponseData<${table.getClassName()}Resp> delete(@PathVariable("{${field.getJavaField()}}") ${field.columnType.type} ${field.getJavaField()}) {
      //TODO 注意：逻辑删除或物理删除，删除数据是否会影响关联数据展示，需要手动调整
      ${table.getClassField()}Service.delete${table.getClassName()}(${field.getJavaField()});
      return new ResponseData();
   }
   </#if>
   </#list>
}
