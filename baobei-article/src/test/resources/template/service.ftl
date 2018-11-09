package ${basePackage}.service;

import ${basePackage}.domain.${domainNameUpperCamel};
import java.util.List;
import java.lang.${pkDataType};

/**
 * ${description} - Service接口类
 *
 * @author ${author} on ${date}
 */
public interface ${domainNameUpperCamel}Service {

    int insert(${domainNameUpperCamel} ${domainNameLowerCamel});

    List<${domainNameUpperCamel}> selectAll();

}