package com.example.demo.config.db;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @ClassName: SegDbOneDatasourceConfig
 * @Description:    分库 测试 db_0
 * @Author: kk
 * @version: 1.0.0
 * @Date: 2020/12/28 02:11
 * @Copyright: Copyright(c)2020 kk All Rights Reserved
 */
@Log4j2
@Configuration
@MapperScan(basePackages = SegDbDatasourceConfig.BASE_PACKAGE +  SegDbDatasourceConfig.DATA_SOURCE_NAME,
        sqlSessionTemplateRef = SegDbDatasourceConfig.DATA_SOURCE_NAME + "SqlSessionTemplate")
public class SegDbDatasourceConfig {
    /**
     * 数据源名称
     */
    public static final String DATA_SOURCE_NAME = "seg";

    /**
     *  dao层 扫描路径
     */
    public static final String BASE_PACKAGE = "com.example.demo.dao.";

    /**
     *  mapping(.xml)扫描 路径
     */
    private static final String MAPPER_PACKAGE = "classpath:mapping/" + "seg" + "/*.xml";

    /**
     *  数据源 在 application 配置文件 中的 路径
     */
    private static final String PROPERTIES_PATH = "spring.datasource.druid." + DATA_SOURCE_NAME;

    private static final String DATA_SOURCE = "DataSource";

    public SegDbDatasourceConfig() {
        log.info("init seg data source...");
    }

    /**
     * 创建数据源
     * @return  数据源
     */
    @Bean(name = DATA_SOURCE_NAME + DATA_SOURCE + "0")
    @ConfigurationProperties(prefix = PROPERTIES_PATH + "0")
    public DataSource dataSource0() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = DATA_SOURCE_NAME + DATA_SOURCE + "1")
    @ConfigurationProperties(prefix = PROPERTIES_PATH + "1")
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = DATA_SOURCE_NAME + DATA_SOURCE)
    public DataSource dataSource() {
        //分库设置
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        //添加 数据源(此处 key 必须为 数据库名)
        dataSourceMap.put("seg_db_0", dataSource0());
        dataSourceMap.put("seg_db_1", dataSource1());

        //分表分库规则
        ShardingRuleConfiguration shardingRuleConfiguration = getShardingRuleConfig();
        DataSource dataSource = null;
        try {
            dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfiguration, new Properties());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dataSource;

    }


    /**
      * 创建sqlSessionFactory
      *@param dataSource    数据源
      *@throws Exception
      *@return SqlSessionFactory
      */
    @Bean(name = DATA_SOURCE_NAME + "SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATA_SOURCE_NAME + DATA_SOURCE) DataSource dataSource) throws Exception {
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
     public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier(DATA_SOURCE_NAME + DATA_SOURCE) DataSource dataSource) {
         return new DataSourceTransactionManager(dataSource);
     }
     /**
      * 创建sqlSession模板
      *@param sqlSessionFactory sqlSessionFactory
      *@return SqlSessionTemplate
      */
     @Bean(name = DATA_SOURCE_NAME + "SqlSessionTemplate")
     public SqlSessionTemplate sqlSessionTemplate(@Qualifier(DATA_SOURCE_NAME + "SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
         return new SqlSessionTemplate(sqlSessionFactory);
     }

    /**
     * 获取 sharding 分表 分库 规则
     * @return  ShardingRuleConfiguration
     */
    private ShardingRuleConfiguration getShardingRuleConfig() {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        //配置 分表 , 分库 规则
        //order 表规则
        TableRuleConfiguration orderFormTableRule = getTableRuleConfiguration("order_form", "_${0..3}",
                "seg_db", "_${0..1}", 4, 2, "id");
        //orderItem 表规则
        TableRuleConfiguration orderItemTableRule = getTableRuleConfiguration("order_item", "_${0..3}",
                "seg_db", "_${0..1}", 4, 2, "id");

        //Sharding全局配置
        shardingRuleConfiguration.getTableRuleConfigs().add(orderFormTableRule);
        shardingRuleConfiguration.getTableRuleConfigs().add(orderItemTableRule);
        return shardingRuleConfiguration;
    }

    /**
     * 获取 分表规则 (使用 单个字段 分表)
     *
     * @param tablePrefix       表前缀
     * @param tableSuffixFromat 表后缀规则
     * @param dbPrefix          数据库前缀
     * @param dbSuffixFromat    数据库后缀规则
     * @param shardingTableNum  单个数据库分表数量
     * @param shardingDbNum     分库数量
     * @param shardingColumn    分表字段
     * @return 分表规则
     */
    private TableRuleConfiguration getTableRuleConfiguration(String tablePrefix, String tableSuffixFromat,
                                                             String dbPrefix, String dbSuffixFromat,
                                                             Integer shardingTableNum, Integer shardingDbNum,
                                                             String shardingColumn) {
        TableRuleConfiguration tableRuleConfiguration = new TableRuleConfiguration(tablePrefix,
                dbPrefix + dbSuffixFromat + "." + tablePrefix + tableSuffixFromat);
        //配置分表 规则
        tableRuleConfiguration.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration(shardingColumn,
                tablePrefix + "_$->{" + shardingColumn + " % " + shardingTableNum + " }"));
        //配置分库 规则
        if (Objects.nonNull(shardingDbNum) && shardingDbNum != 1) {
            tableRuleConfiguration.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration(shardingColumn,
                    dbPrefix + "_$->{" + shardingColumn + " % " + shardingDbNum + " }"));
        }
        return tableRuleConfiguration;
    }
}
