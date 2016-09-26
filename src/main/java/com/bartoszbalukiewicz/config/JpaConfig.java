package com.bartoszbalukiewicz.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by postgres on 2016-09-20.
 */
@Configuration
@ComponentScan
public class JpaConfig {
    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.dataSourceClassName}")
    private String dataSourceClassName;

    @Value("${spring.datasource.poolName}")
    private String poolName;

    @Value("${spring.datasource.connectionTimeout}")
    private int connectionTimeout;

    @Value("${spring.datasource.maxLifetime}")
    private int maxLifetime;

    @Value("${spring.datasource.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${spring.datasource.minimumIdle}")
    private int minimumIdle;

    @Value("${spring.datasource.idleTimeout}")
    private int idleTimeout;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        Properties jpaProperties = new Properties();
        jpaProperties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        jpaProperties.put(Environment.HBM2DDL_AUTO, "update");;
        jpaProperties.put(Environment.ENABLE_LAZY_LOAD_NO_TRANS, "true");
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setJpaProperties(jpaProperties);
        emf.setDataSource(dataSource());

        // TODO: SCAN?
        emf.setPackagesToScan("com.bartoszbalukiewicz.model");

        return emf;

    }


    @Bean
    @Primary
    public DataSource dataSource() {
        Properties dataSourceProps = new Properties();
        dataSourceProps.put("url", dataSourceUrl);
        dataSourceProps.put("user", user);
        dataSourceProps.put("password", password);

        Properties configProps = new Properties();
        configProps.put("dataSourceClassName", dataSourceClassName);
        configProps.put("poolName", poolName);
        configProps.put("maximumPoolSize", maximumPoolSize);
        configProps.put("minimumIdle", minimumIdle);
        configProps.put("connectionTimeout", connectionTimeout);
        configProps.put("idleTimeout", idleTimeout);
        configProps.put("dataSourceProperties", dataSourceProps);

        HikariConfig hikariConfig = new HikariConfig(configProps);
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
        return hikariDataSource;
    }

    @Bean
    @Primary
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabase(Database.MYSQL);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
