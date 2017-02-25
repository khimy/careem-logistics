package com.ca.order.dao;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author vivek.agrawal
 */
@Configuration
@EnableJpaRepositories("com.ca.order.dao.repositories")
@EntityScan("com.ca.order.dao.models")
@EnableAutoConfiguration
public class DaoConfig {
}
