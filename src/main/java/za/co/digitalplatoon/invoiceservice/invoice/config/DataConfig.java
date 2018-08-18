package za.co.digitalplatoon.invoiceservice.invoice.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("/application.properties")
public class DataConfig {
    @Autowired
    Environment environment;

    @Bean
    public DataSource DataSource() {
        DataSourceBuilder b = DataSourceBuilder.create();
        b.url(environment.getProperty("invoices.h2.db.url"));
        b.driverClassName(environment.getProperty("invoices.h2.driver"));
        b.username(environment.getProperty("invoices.db.username"));
        b.password(environment.getProperty("invoices.db.password"));
        return b.build();
    }


    //sessionfactory bean
    @Bean
    public SessionFactory SessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

        builder.addProperties(hibernateProperties());
        builder.scanPackages(environment.getProperty("invoices.entity.package"));

        return builder.buildSessionFactory();
    }

    //Hibernate properties:
    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");

        return properties;
    }

    //transaction manager bean
    @Bean
    public HibernateTransactionManager TransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
}
