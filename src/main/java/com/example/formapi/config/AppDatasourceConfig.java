package com.example.formapi.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "applicationEntityManagerFactory",
        transactionManagerRef = "applicationTransactionManager",
        basePackages = {
                "com.example.formapi.repository.application"
        }
)
public class AppDatasourceConfig {

    @Primary
    @Bean(name = "applicationDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource applicationDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "applicationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("applicationDataSource") DataSource dataSource) {
        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");

        return builder
                .dataSource(dataSource)
                .packages("com.example.formapi.domain.application")
                .persistenceUnit("application")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "applicationTransactionManager")
    public PlatformTransactionManager applicationTransactionManager(
            @Qualifier("applicationEntityManagerFactory") EntityManagerFactory applicationEntityManagerFactory) {
        return new JpaTransactionManager(applicationEntityManagerFactory);
    }
}
