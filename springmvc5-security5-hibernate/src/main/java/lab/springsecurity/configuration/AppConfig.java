package lab.springsecurity.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScans(value = {@ComponentScan("lab.springsecurity.service"),@ComponentScan("lab.springsecurity.dao")})
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class AppConfig {
	  
	  @Autowired
	  private Environment environment;

	  @Bean
		public LocalSessionFactoryBean getSessionFactory() {
			LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
			factoryBean.setDataSource(dataSource());
			factoryBean.setPackagesToScan(new String[] {"lab.springsecurity.model"});
			factoryBean.setHibernateProperties(hibernateProperties());
			//factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
			//factoryBean.setAnnotatedClasses(User.class);
			return factoryBean;
		}
		
		
		@Bean
		public DataSource dataSource() {
			DriverManagerDataSource dataSrc = new DriverManagerDataSource();
			dataSrc.setDriverClassName(environment.getRequiredProperty("jdbc.driver"));
			dataSrc.setUrl(environment.getRequiredProperty("jdbc.url"));
			dataSrc.setUsername(environment.getRequiredProperty("jdbc.username"));
			dataSrc.setPassword(environment.getRequiredProperty("jdbc.password"));
			return dataSrc;
		}
		
		private Properties hibernateProperties() {
			Properties properties = new Properties();
			properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
			properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
			properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
			properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
			return properties;
		}
		@Bean
		public HibernateTransactionManager getTransactionManager() {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(getSessionFactory().getObject());
			return transactionManager;
		}
}
