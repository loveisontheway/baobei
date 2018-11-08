package com.yaozhitech.baobei.child.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据源third - MySQL
 * @author jiangjialiang on 2017/8/21
 * @version 1.8.2
 */
@Configuration
@MapperScan(basePackages = "com.yaozhitech.baobei.*.mapper.third", sqlSessionTemplateRef  = "thirdSqlSessionTemplate")
public class DataSourceThirdConfig {

    @Bean(name = "thirdDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.third")
    public DataSource thirdDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "thirdSqlSessionFactory")
    public SqlSessionFactory thirdSqlSessionFactory(@Qualifier("thirdDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/third/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "thirdTransactionManager")
    public DataSourceTransactionManager thirdTransactionManager(@Qualifier("thirdDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "thirdSqlSessionTemplate")
    public SqlSessionTemplate thirdSqlSessionTemplate(@Qualifier("thirdSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
