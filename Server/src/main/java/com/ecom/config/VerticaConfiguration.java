package com.ecom.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@Slf4j
@MapperScan(basePackages = "com.ecom.mapper.vertica",sqlSessionFactoryRef = "verticaSessionFactory")
public class VerticaConfiguration {

//    @Bean(name = "verticaDataSourceProperties")
//    @ConfigurationProperties(prefix = "spring.datasource.vertica")
//    public DataSourceProperties dataSourceProperties() {
//
//        return new DataSourceProperties();
//    }


    @Bean(name = "verticaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.vertica")
    public DataSource dataSource() {
        //log.info("vertica datasource detail is " + properties.determineUrl() +properties.getUrl()+properties.getDriverClassName());
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "verticaSessionFactory")
    public SqlSessionFactoryBean verticaSessionFactory()
            throws Exception {

        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        //sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:./mapper/vertica/*.xml"));
        //sqlSessionFactoryBean.setTypeAliasesPackage("com.ecom.pojo.entity");

        return sqlSessionFactoryBean;
    }
}
