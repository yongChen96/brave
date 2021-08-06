package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
<#if swagger2>
import io.swagger.annotations.Api;
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Validated
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if swagger2>
@Api(value = "${table.controllerName}", tags = "${table.comment!?replace("\r\n","")?replace("\r","")?replace("\n","")?trim}")
</#if>
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Resouce
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * 分页查询${tableComment}
    *
    * @param pp 分页查询对象
    * @return 查询结果
    */
    @ApiOperation(value = "分页查询${tableComment}", notes = "分页查询${tableComment}")
    @PostMapping("/page")
    @Validated(SuperEntity.OnlyQuery.class)
    public Result<IPage<${entity}>> page(@RequestBody @Valid PageParam<${entity}DTO> pp) {
        IPage<${entity}> page = pp.getPage();
        ${entity}DTO data = pp.getData();
        // 构建查询条件
        HyLambdaQueryWrapper<${entity}> query = HyLambdaQueryWrapper.lambdaQuery();
        ${table.serviceName?uncap_first}.page(page, query);
        return success(page);
    }

     /**
     * 单体查询${tableComment}
     *
     * @param id 主键id
     * @return 查询结果
     */
     @ApiOperation(value = "查询${tableComment}", notes = "查询${tableComment}")
     @GetMapping("/{id}")
     public Result<${entity}> get(@PathVariable <#list table.commonFields as field><#if field.keyFlag>${field.propertyType}</#if></#list> id) {
         return success(${table.serviceName?uncap_first}.getById(id));
     }

     /**
     * 保存${tableComment}
     *
     * @param ${entity?uncap_first} 保存对象
     * @return 保存结果
     */
     @ApiOperation(value = "保存${tableComment}", notes = "保存${tableComment}不为空的字段")
     @PostMapping("/save")
     public Result<Boolean> save(@RequestBody @Valid ${entity} ${entity?uncap_first}) {
         return success(${table.serviceName?uncap_first}.save(${entity?uncap_first}));
     }

     /**
     * 修改${tableComment}
     *
     * @param ${entity?uncap_first} 修改对象
     * @return 修改结果
     */
     @ApiOperation(value = "修改${tableComment}", notes = "修改${tableComment}不为空的字段")
     @PostMapping("/update")
     public Result<Boolean> update(@RequestBody @Valid ${entity} ${entity?uncap_first}) {
         return success(${table.serviceName?uncap_first}.updateById(${entity?uncap_first}));
     }

     /**
     * 删除${tableComment}
     *
     * @param id 主键id
     * @return 删除结果
     */
     @ApiOperation(value = "删除${tableComment}", notes = "根据id物理删除${tableComment}")
     @DeleteMapping(value = "/{id}")
     public Result<Boolean> delete(@PathVariable <#list table.commonFields as field><#if field.keyFlag>${field.propertyType}</#if></#list> id) {
         return success(${table.serviceName?uncap_first}.removeById(id));
     }
}
</#if>
