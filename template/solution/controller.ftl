package ${globalConfig.packageConfig.controller};

import org.springframework.web.bind.annotation.RestController;
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
import com.homedo.odin.solution.convent.CommonConvent;

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
   private ${table.getClassName()}ServiceImpl ${table.getClassField()}Service;

   @GetMapping
   @ApiOperation(value = "获取${table.comment!}分页列表", response = ${table.getClassName()}PageResp.class)
   public ResultData page(@Validated @ModelAttribute ${table.getClassName()}PageReq req) {

      return result;
   }

   <#list table.fieldInfoList as field>
   <#if field.keyFlag>
   @GetMapping("/{${field.getJavaField()}}")
   @ApiOperation(value = "获取${table.comment!}详情", response = ${table.getClassName()}Resp.class)
   public ResultData detail(@PathVariable("{${field.getJavaField()}}") ${field.columnType.type} ${field.getJavaField()}) {
      return result;
   }

   @DeleteMapping("/{${field.getJavaField()}}")
   @ApiOperation(value = "删除${table.comment!}")
   public ResultData delete(@PathVariable("{${field.getJavaField()}}") ${field.columnType.type} ${field.getJavaField()}) {
      return ResultData.newInstance();
   }

   @PutMapping("/{${field.getJavaField()}}")
   @ApiOperation(value = "修改${table.comment!}")
   public ResultData update(@PathVariable("{${field.getJavaField()}}") ${field.columnType.type} ${field.getJavaField()},@Validated @RequestBody ${table.getClassName()}Req req) {
      ${table.getClassField()}Service.update${table.getClassName()}(${field.getJavaField()},req);
      return ResultData.newInstance();
   }
   </#if>
   </#list>

   @PostMapping
   @ApiOperation(value = "新增${table.comment!}")
   public ResultData add(@Validated @RequestBody ${table.getClassName()}Req req) {
      ${table.getClassField()}Service.add${table.getClassName()}(req);
      return ResultData.newInstance();
   }
}
