package fr.inalco.studia;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"fr.inalco.studia"})
@EnableJpaRepositories(basePackages = "fr.inalco.studia.repositories")
@EnableTransactionManagement
public class StudiaJpaConfig {
}