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
@MapperScan(basePackages = "com.ecom.mapper.mysql",sqlSessionFactoryRef = "mySQLSessionFactory")
public class MySQLConfiguration {

//    @Bean(name = "mySQLDataSourceProperties")
//    @ConfigurationProperties("spring.datasource.mysql")
//    public DataSourceProperties dataSourceProperties() {
//        return new DataSourceProperties();
//    }

    @Bean(name = "mySQLDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource dataSource() {
    //public DataSource dataSource(DataSourceProperties properties) {
        //return DataSourceBuilder.create().type(HikariDataSource.class).build();
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name = "mySQLSessionFactory")
    public SqlSessionFactoryBean secondSessionFactory()
            throws Exception {

        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:./mapper/mysql/*.xml"));
        //sqlSessionFactoryBean.setTypeAliasesPackage("com.ecom.pojo.entity");
        ;

        return sqlSessionFactoryBean;
    }
}
