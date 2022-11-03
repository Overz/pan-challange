package com.example.challange.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
//@EnableJpaRepositories(
//	entityManagerFactoryRef = RepositoriesBean.ENTITY_MANAGER_FACTOR_REF,
//	transactionManagerRef = RepositoriesBean.TRANSACTION_MANAGER_REF,
//	basePackages = { "com.example.challange.repositories" }
//)
public class RepositoriesBean {

	public static final String ENTITY_MANAGER_FACTOR_REF = "repositoriesEntityManager";
	public static final String TRANSACTION_MANAGER_REF = "repositoriesTransactionManager";

}
