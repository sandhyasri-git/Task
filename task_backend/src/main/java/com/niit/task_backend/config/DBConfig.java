package com.niit.task_backend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.task_backend.model.UserDetails;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class DBConfig {
	
	private  Properties getHibernateProperties()
	 {
		  Properties properties=new Properties();
		  properties.setProperty("hibernate.show_sql", "true");
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			return properties;
				  
	 }
	//create an instance
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder localSessionFactoryBuilder=new LocalSessionFactoryBuilder(dataSource);
		localSessionFactoryBuilder.scanPackages("com");
		localSessionFactoryBuilder.addProperties(getHibernateProperties());
		
		localSessionFactoryBuilder.addAnnotatedClass(UserDetails.class);
		
		return localSessionFactoryBuilder.buildSessionFactory();
	}

			

			@Bean
			public DataSource getDataSource() {
			    BasicDataSource dataSource = new BasicDataSource();
			    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
				dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
			    dataSource.setUsername("taskdb");
			    dataSource.setPassword("taskdb");
			    return dataSource;
			}
			@Autowired
			@Bean(name="transactionManager")
			public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
			{
				HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager(sessionFactory);
				return hibernateTransactionManager;
				
			}

}
