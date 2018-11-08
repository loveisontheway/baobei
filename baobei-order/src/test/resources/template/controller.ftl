package ${basePackage}.web;

import ${basePackage}.domain.${domainNameUpperCamel};
import ${basePackage}.service.${domainNameUpperCamel}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.lang.${pkDataType};

/**
 * ${description} - Controller类
 *
 * @author ${author} on ${date}
 */
@Api(description = "${description}")
@Controller
@RequestMapping("${baseRequestMapping}")
public class ${domainNameUpperCamel}Controller {

    @Resource
    private ${domainNameUpperCamel}Service ${domainNameLowerCamel}Service;

    @ApiOperation(value = "新增", notes = "单表新增")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "${domainNameUpperCamel}", value = "${domainNameUpperCamel}对象", required = true, dataType = "Object")
    })
    @PostMapping("/add")
    public String insert(${domainNameUpperCamel} ${domainNameLowerCamel}) {
        int result = ${domainNameLowerCamel}Service.insert(${domainNameLowerCamel});
        return "" + result;
    }

    @ApiOperation(value = "集合", notes = "单表集合")
    @GetMapping("/list")
    public List<${domainNameUpperCamel}> selectAll() {
        List<${domainNameUpperCamel}> list = ${domainNameLowerCamel}Service.selectAll();
        return list;
    }

}