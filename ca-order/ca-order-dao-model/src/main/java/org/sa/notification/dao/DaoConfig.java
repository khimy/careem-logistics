package org.sa.notification.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vivek.agrawal
 */
@Configuration
@EnableJpaRepositories("org.sa.notification.dao.repositories")
@EntityScan("org.sa.notification.dao.models")
public class DaoConfig {
}
