package com.example.dynamic_datasource_anno.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Author: Wang Dengshou
 * @Description:
 * @Date: Created in 20:31 2018/9/2
 */
@Configuration
@MapperScan(basePackages = "com.example.dynamic_datasource_anno.db.dao")
public class MyBatisConfig {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.example.dynamic_datasource_anno.db.pojo");
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:com/example/dynamic_datasource_anno/db/mapper/*Mapper.xml");
        sessionFactoryBean.setMapperLocations(resources);

        return sessionFactoryBean.getObject();
    }

}
