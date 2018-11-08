package ${basePackage}.service.impl;

import ${basePackage}.mapper.${domainNameUpperCamel}Mapper;
import ${basePackage}.domain.${domainNameUpperCamel};
import ${basePackage}.service.${domainNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.${pkDataType};
import java.util.List;

import javax.annotation.Resource;

/**
 * ${description} - ServiceImpl接口实现类
 *
 * @author ${author} on ${date}
 */
@Service
@Transactional
public class ${domainNameUpperCamel}ServiceImpl implements ${domainNameUpperCamel}Service {

    @Resource
    private ${domainNameUpperCamel}Mapper ${domainNameLowerCamel}Mapper;

    @Override
    public int insert(${domainNameUpperCamel} ${domainNameLowerCamel}) {
        return ${domainNameLowerCamel}Mapper.insert(${domainNameLowerCamel});
    }

    @Override
    public List<${domainNameUpperCamel}> selectAll() {
        return ${domainNameLowerCamel}Mapper.selectAll();
    }

}