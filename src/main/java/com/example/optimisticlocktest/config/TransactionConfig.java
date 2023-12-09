package com.example.optimisticlocktest.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class TransactionConfig {
    private final EntityManagerFactory db1EntityManagerFactory;

    public TransactionConfig(@Qualifier("db1EntityManagerFactory") EntityManagerFactory db1EntityManagerFactory) {
        this.db1EntityManagerFactory = db1EntityManagerFactory;
    }

    @Primary
    @Bean(name = "db1TransactionManager")
    public PlatformTransactionManager db1TransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(db1EntityManagerFactory);
        return transactionManager;
    }
}
