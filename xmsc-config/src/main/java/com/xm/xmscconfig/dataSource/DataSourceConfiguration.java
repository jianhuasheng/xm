package com.xm.xmscconfig.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.xm.xmscconfig.RoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@Slf4j
public class DataSourceConfiguration {
    private final static String MYSQL_SUFFIX = "?useUnicode=true&characterEncoding=utf-8&useSSL=false";

    private String writeDbUrl="jdbc:mysql://localhost:3306/test";

    private String writeUsername="root";

    private String writePassword="111111";

    private String readDbUrl="jdbc:mysql://localhost:3306/test";

    private String readUsername="root";

    private String readPassword="111111";



    @Bean
    public RoutingDataSource roundRobinDataSourceProxy() {
        RoutingDataSource proxy = new RoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap<>();
        targetDataResources.put(DataSourceType.WRITE, writeDataSource());
        targetDataResources.put(DataSourceType.READ, readDataSource());
        proxy.setDefaultTargetDataSource(readDataSource());//默认源
        proxy.setTargetDataSources(targetDataResources);
        return proxy;
    }

    /**
     * 平台写库
     *
     * @return
     */
    @Primary
    @Bean(name = "writeDataSource", initMethod = "init")
    public DataSource writeDataSource() {
        log.info("写库初始化");
        log.debug("url:{}, user:{}, password:{}", writeDbUrl, writeUsername, writePassword);

        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(writeDbUrl+MYSQL_SUFFIX);
        datasource.setUsername(writeUsername);
        datasource.setPassword(writePassword);
        datasource.setInitialSize(30);
        datasource.setMinIdle(15);
        datasource.setMaxActive(200);
        datasource.setMaxWait(60000);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setValidationQuery("SELECT 'x'");
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return datasource;
    }

    @Bean(name = "readDataSource", initMethod = "init")
    public DataSource readDataSource() {
        log.info("读库初始化");
        log.debug("url:{}, user:{}, password:{}", readDbUrl, readUsername, readPassword);

        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(readDbUrl+MYSQL_SUFFIX);
        datasource.setUsername(readUsername);
        datasource.setPassword(readPassword);
        datasource.setInitialSize(30);
        datasource.setMinIdle(15);
        datasource.setMaxActive(200);
        datasource.setMaxWait(60000);
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        datasource.setValidationQuery("SELECT 'x'");
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return datasource;
    }
}