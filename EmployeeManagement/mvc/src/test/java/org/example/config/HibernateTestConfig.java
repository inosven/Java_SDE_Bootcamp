package org.example.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;
@Configuration
public class HibernateTestConfig {
    @Bean
    @Profile("unit")
    public SessionFactory getSessionFactory() {
        return mock(SessionFactory.class);
    }
}
