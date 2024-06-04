package com.abc.exchange.datasources;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
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
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.abc.exchange.base.dal.persistence", sqlSessionFactoryRef = "sqlSessionFactoryBase")
//@MapperScan(basePackages = "com.abc.platform.base.dal.persistence", sqlSessionFactoryRef = "sqlSessionFactoryBase")
public class BaseDataSource {

    /**
     * 多数据源时，第一个数据源指定Primary
     */
//    @Primary
    @Bean("dataSourceBase")
    @ConfigurationProperties("spring.datasource.druid.base")
    public DataSource dataSourceBase() {
        return DruidDataSourceBuilder.create().build();
    }

    //    @Primary
    @Bean(name = "sqlSessionFactoryBase")
    public SqlSessionFactory sqlSessionFactoryBase(@Qualifier("dataSourceBase") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(resolveMapperLocations("classpath:com/abc/exchange/base/dal/persistence/*.xml"));
        return bean.getObject();
    }

    //    @Primary
    @Bean(name = "platformTransactionManagerBase")
    public PlatformTransactionManager transactionManagerBase(@Qualifier("dataSourceBase") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    private Resource[] resolveMapperLocations(String mapperLocation) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return resolver.getResources(mapperLocation);
    }
}
