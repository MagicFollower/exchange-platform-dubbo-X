package com.abc.platform.datasources;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan(basePackages = "com.abc.platform.business.dal.persistence", sqlSessionFactoryRef = "sqlSessionFactoryBusiness")
public class BusinessDataSource {

    /**
     * 多数据源时，第一个数据源指定Primary
     */
    // @Primary
    @Bean("dataSourceBusiness")
    @ConfigurationProperties("spring.datasource.druid.business")
    public DataSource dataSourceBusiness() {
        return DruidDataSourceBuilder.create().build();
    }

    // @Primary
    @Bean(name = "sqlSessionFactoryBusiness")
    public SqlSessionFactory sqlSessionFactoryBusiness(@Qualifier("dataSourceBusiness") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(resolveMapperLocations("classpath:com/abc/platform/business/dal/persistence/*.xml"));
        return bean.getObject();
    }

    // @Primary
    @Bean(name = "sqlSessionFactoryBusiness")
    public PlatformTransactionManager transactionManagerBusiness(@Qualifier("dataSourceBusiness") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private Resource[] resolveMapperLocations(String mapperLocation) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return resolver.getResources(mapperLocation);
    }
}
