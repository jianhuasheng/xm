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
    @Value("${mysql.local_life_w.jdbc.url}")
    private String writeDbUrl;

    @Value("${mysql.local_life_w.jdbc.username}")
    private String writeUsername;

    @Value("${mysql.local_life_w.jdbc.password}")
    private String writePassword;

    @Value("${mysql.local_life_w.jdbc.url}")
    private String readDbUrl;

    @Value("${mysql.local_life_w.jdbc.username}")
    private String readUsername;

    @Value("${mysql.local_life_w.jdbc.password}")
    private String readPassword;



    @Primary
    @Bean
    public RoutingDataSource roundRobinDataSourceProxy() {
        RoutingDataSource proxy = new RoutingDataSource();
        Map<Object, Object> targetDataResources = new HashMap<>();
        targetDataResources.put(DataSourceType.WRITE, writeDataSource());
        targetDataResources.put(DataSourceType.READ, readDataSource());
        return proxy;
    }

    /**
     * 平台写库
     *
     * @return
     */
    @Bean(name = "writeDataSource", initMethod = "init")
    public DataSource writeDataSource() {
        log.info("写库初始化");
        log.debug("url:{}, user:{}, password:{}", writeDbUrl, writeUsername, writePassword);

        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(writeDbUrl);
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

    /**
     * 读库
     *
     * @return
     */
    @Bean(name = "readDataSource", initMethod = "init")
    public DataSource readDataSource() {
        log.info("读库初始化");
        log.debug("url:{}, user:{}, password:{}", readDbUrl, readUsername, readPassword);

        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(readDbUrl);
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