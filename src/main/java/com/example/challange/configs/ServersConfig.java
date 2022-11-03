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

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//@Configuration
//@EnableJpaRepositories(
//	entityManagerFactoryRef = ServersConfig.ENTITY_MANAGER_FACTORY_REF,
//	transactionManagerRef = ServersConfig.TRANSACTION_MANAGER_REF,
//	basePackages = { "com.example.challange.repositories" }
//)
public class ServersConfig {
	public static final String ENTITY_MANAGER_FACTORY_REF = "serversEntityManager";
	public static final String TRANSACTION_MANAGER_REF = "serversTransactionManager";
	public static final String SERVER_DATASOURCE_KEY = "serversDataSource";
	public static final String SERVER_DATASOURCE_PROPERTY_KEY = "serversDataSourceProperties";

	@Bean(name = ENTITY_MANAGER_FACTORY_REF)
	public LocalContainerEntityManagerFactoryBean getServersEntityManager(
		EntityManagerFactoryBuilder builder,
		@Qualifier(SERVER_DATASOURCE_KEY) DataSource serversDataSource
	) {
		return builder
			.dataSource(serversDataSource)
			.packages("org.springdemo.multiple.datasources.domain.servers")
			.persistenceUnit("servers")
//			.properties(additionalJpaProperties())
			.build();
	}

	Map<String, Object> additionalJpaProperties() {
		Map<String, Object> map = new HashMap<>();
		map.put("hibernate.hbm2ddl.auto", "create");
		map.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		map.put("hibernate.show_sql", "true");
		return map;
	}

	@Primary
	@Bean(SERVER_DATASOURCE_PROPERTY_KEY)
	public DataSourceProperties serversDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean(SERVER_DATASOURCE_KEY)
	@ConfigurationProperties("spring.datasource")
	public DataSource serversDataSource(@Qualifier(SERVER_DATASOURCE_PROPERTY_KEY) DataSourceProperties serversDataSourceProperties) {
		return serversDataSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean(name = TRANSACTION_MANAGER_REF)
	public JpaTransactionManager transactionManager(@Qualifier("serversEntityManager") EntityManagerFactory serversEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(serversEntityManager);
		return transactionManager;
	}
}
