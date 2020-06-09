package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Description: Primary数据源 配置(扫描) 类
 * @ClassName: PrimaryDatasourceConfig
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2019/09/18 01:49
 * @Copyright: Copyright(c)2019 kk All Rights Reserved
 */
@Order(1)
@Configuration
@MapperScan(basePackages = PrimaryDatasourceConfig.BASE_PACKAGE +  PrimaryDatasourceConfig.DATA_SOURCE_NAME, sqlSessionTemplateRef = PrimaryDatasourceConfig.DATA_SOURCE_NAME + "SqlSessionTemplate")
public class PrimaryDatasourceConfig {
    /**
     * 数据源名称
     */
    public static final String DATA_SOURCE_NAME = "primary";

    /**
     *  dao层 扫描路径
     */
    public static final String BASE_PACKAGE = "com.example.demo.dao.";

    /**
     *  mapping(.xml)扫描 路径
     */
    private static final String MAPPER_PACKAGE = "classpath:mapping/primary/*.xml";

    /**
     *  数据源 在 application 配置文件 中的 路径
     */
    private static final String PROPERTIES_PATH = "spring.datasource.druid." + DATA_SOURCE_NAME;

    /**
     * 数据源初始化
     */
    public PrimaryDatasourceConfig() {
		System.out.println("init primary data source...");
	}

    //@Bean(name = DATA_SOURCE_NAME + "DataSourceProperties")
    //@Qualifier(DATA_SOURCE_NAME + "DataSourceProperties")
    //@Primary
    //@ConfigurationProperties(prefix = PROPERTIES_PATH)
    //public DataSourceProperties dataSourceProperties() {
    //    return new DataSourceProperties() ;
    //}

    /**
     * 创建数据源
     * @return  数据源
     *
     * 注意: jpa 的jar包 与此 多数据源 初始化 有冲突, 可以使用 注释 的方式 初始化
     */
    @Bean(name = DATA_SOURCE_NAME + "DataSource")
    @Qualifier(DATA_SOURCE_NAME + "DataSource")
    @ConfigurationProperties(prefix = PROPERTIES_PATH)
    @Primary
    public DataSource dataSource() {
        //dataSourceProperties().initializeDataSourceBuilder().build();
        return DataSourceBuilder.create().build();
    }
    /**
      * 创建sqlSessionFactory
      *@param dataSource    数据源
      *@throws Exception    异常
      *@return SqlSessionFactory
      */
    @Bean(name = DATA_SOURCE_NAME + "SqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME + "DataSource") DataSource dataSource) throws Exception {
         SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
         bean.setDataSource(dataSource);
         bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_PACKAGE));
         return bean.getObject();
    }
    /**
      * 创建事务
      *@param dataSource    数据源
      *@return DataSourceTransactionManager
      */
     @Bean(name = DATA_SOURCE_NAME + "TransactionManager")
     @Primary
     public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier(DATA_SOURCE_NAME + "DataSource") DataSource dataSource) {
         return new DataSourceTransactionManager(dataSource);
     }
     /**
      * 创建sqlSession模板
      *@param sqlSessionFactory sqlSessionFactory
      *@return SqlSessionTemplate
      */
     @Bean(name = DATA_SOURCE_NAME + "SqlSessionTemplate")
     @Primary
     public SqlSessionTemplate sqlSessionTemplate(@Qualifier(DATA_SOURCE_NAME + "SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
         return new SqlSessionTemplate(sqlSessionFactory);
     }
}
