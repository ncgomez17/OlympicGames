package com.juegosolimpicos;


import com.juegosolimpicos.audit.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.juegosolimpicos.model.entities"})
@EnableJpaRepositories(basePackages = {"com.juegosolimpicos.model.repositories"})
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableMongoRepositories
@EnableMongoAuditing
public class OlympicGamesApplication {
    public static void main(String[] args) {
        SpringApplication.run(OlympicGamesApplication.class, args);
    }
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}