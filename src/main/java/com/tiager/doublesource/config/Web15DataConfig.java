package com.tiager.doublesource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.tiager.doublesource.mybatis.mapperTwo",sqlSessionTemplateRef = "web15SqlSessionTemplate")
public class Web15DataConfig {

    private String url="jdbc:mysql://localhost:3306/web_15?useUnicode=true&characterEncoding=utf8&8&allowMultiQueries=true&useSSL=false";
    private String username="root";
    private String password="root123";

    private Integer maxActive = 100;
    private Integer initialSize = 30;
    private Integer maxWait = 60000;
    private Integer minIdle = 20;
    private Integer minEvictableIdleTimeMillis = 300000;



    @Bean("web15DataSource")
    public DataSource instalmentDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxWait(maxWait);
        datasource.setMaxActive(maxActive);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
//            log.error(e.getMessage());

        }
        return datasource;
    }

    @Bean(name = "web15SqlSessionFactory")
    public SqlSessionFactory instalmentSqlSessionFactory(@Qualifier("web15DataSource") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "web15DataSourceTransactionManager")
    public DataSourceTransactionManager instalmentDataSourceTransactionManager(@Qualifier("web15DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "web15SqlSessionTemplate")
    public SqlSessionTemplate instalmentSqlSessionTemplate(@Qualifier("web15SqlSessionFactory") SqlSessionFactory sqlSessionFactory)throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }



}
