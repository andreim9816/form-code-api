package com.example.formapi.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "clientEntityManagerFactory",
        transactionManagerRef = "clientTransactionManager",
        basePackages = {
                "com.example.formapi.repository.client"
        }
)
public class ClientDatasourceConfig {

    @Bean(name = "clientDataSource")
    @ConfigurationProperties(prefix = "client.datasource")
    public DataSource clientDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "clientEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("clientDataSource") DataSource dataSource) {
        Map<String, Object> properties = Map.of(
//                "hibernate.hbm2ddl.auto", "update",
//                "jpa.hibernate.ddl-auto", "update",
//                "hibernate.ddl-auto", "update",
//                "hibernate.temp.use_jdbc_metadata_defaults","false",
//                "hibernate.dialect", "org.hibernate.dialect.OracleDialect",
//                "database-platform", "org.hibernate.dialect.OracleDialect"
        );

        return builder
                .dataSource(dataSource)
                .packages("com.example.formapi.domain.client")
                .persistenceUnit("client")
                .properties(properties)
                .build();
    }

    @Bean(name = "clientTransactionManager")
    public PlatformTransactionManager clientTransactionManager(
            @Qualifier("clientEntityManagerFactory") EntityManagerFactory customerEntityManagerFactory) {
        return new JpaTransactionManager(customerEntityManagerFactory);
    }
}