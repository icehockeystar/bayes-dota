package gg.bayes.challenge.rdbms;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(considerNestedRepositories = true)
public class RdbmsConfig {
}
