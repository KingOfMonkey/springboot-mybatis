package com.sailing.springbootmybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author baibing
 * @project: springboot-mybatis
 * @package: com.sailing.springbootmybatis.config
 * @Description: one数据源配置类
 * @date 2018/10/18 17：05
 */
@Configuration
//下面的sqlSessionTemplateRef 值需要和生成的SqlSessionTemplate bean name相同，如果没有指定name,那么就是方法名
@MapperScan(basePackages = {"com.sailing.springbootmybatis.mapper.one"}, sqlSessionTemplateRef = "sqlSessionTemplateOne")
public class DataSourceOneConfig {

    @Value("${mybatis.mapper-locations}")
    private String mapper_location;

    @Value("${mybatis.config-location}")
    private String mybatis_config;

    private Logger logger = LoggerFactory.getLogger(DataSourceOneConfig.class);

    @Primary//多数据源中必须要使用@Primary指定一个主数据源
    @Bean(name = "datasourceOne")
    @ConfigurationProperties(prefix = "spring.datasource.druid.one")
    public DataSource datasourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("datasourceOne") DataSource dataSource) throws Exception {
        logger.info("mapper文件地址：" + mapper_location);
        logger.info("mybatis配置文件地址：" + mybatis_config);
        //在基本的 MyBatis 中,session 工厂可以使用 SqlSessionFactoryBuilder 来创建。
        // 而在 MyBatis-spring 中,则使用SqlSessionFactoryBean 来替代：
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //如果重写了 SqlSessionFactory 需要在初始化的时候手动将 mapper 地址 set到 factory 中，否则会报错：
        //org.apache.ibatis.binding.BindingException: Invalid bound statement (not found)
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapper_location));
        bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(mybatis_config));
        return bean.getObject();
    }

    /**
     * 为选中的数据源 datasourceOne 添加事务管理
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManagerOne(@Qualifier("datasourceOne") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * SqlSessionTemplate 是 SqlSession接口的实现类，是spring-mybatis中的，实现了SqlSession线程安全
     *
     * @param sqlSessionFactory
     * @return
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplateOne(@Qualifier("sqlSessionFactoryOne") SqlSessionFactory sqlSessionFactory) {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}
