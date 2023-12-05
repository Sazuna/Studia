package fr.inalco.studia;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"fr.inalco.Studia"})
@EnableJpaRepositories(basePackages = "fr.inalco.Studia.repositories")
@EnableTransactionManagement
public class StudiaJpaConfig {
}