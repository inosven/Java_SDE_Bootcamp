package org.example.config;
import org.example.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class HibernateConfig {
    @Bean
    public SessionFactory sessionFactory() {
        return HibernateUtil.getSessionFactory();
    }
}
