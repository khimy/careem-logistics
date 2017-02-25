package com.ca.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;

/**
 * @author Vivek Agrawal
 */
@SpringBootApplication
public class CAOrderApplication {

    @Autowired
    private ResourceServerProperties resource;

    public static void main(String[] args) {
        SpringApplication.run(CAOrderApplication.class, args);
    }
}
