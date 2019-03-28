package com.tiager.doublesource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.tiager.doublesource.mybatis.mapper",sqlSessionTemplateRef = "web16SqlSessionTemplate")
public class Web16DataConfig {

    private String url="jdbc:mysql://localhost:3306/web_16?useUnicode=true&characterEncoding=utf8&8&allowMultiQueries=true&useSSL=false";
    private String username="root";
    private String password="root123";

    private Integer maxActive = 100;
    private Integer initialSize = 30;
    private Integer maxWait = 60000;
    private Integer minIdle = 20;
    private Integer minEvictableIdleTimeMillis = 300000;



    @Bean("web16DataSource")
    @Primary
    public DataSource faceValueCircleDataSource() {
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


    @Bean(name = "web16SqlSessionFactory")
    public SqlSessionFactory faceValueCircleSqlSessionFactory(@Qualifier("web16DataSource") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "web16DataSourceTransactionManager")
    public DataSourceTransactionManager faceValueCircleDataSourceTransactionManager(@Qualifier("web16DataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "web16SqlSessionTemplate")
    public SqlSessionTemplate faceValueCircleSqlSessionTemplate(@Qualifier("web16SqlSessionFactory") SqlSessionFactory sqlSessionFactory)throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
