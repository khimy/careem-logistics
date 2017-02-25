package ca.customer.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vivek.agrawal
 */
@Configuration
@EnableJpaRepositories("ca.customer.dao.repositories")
@EntityScan("ca.customer.dao.models")
public class DaoConfig {
}
