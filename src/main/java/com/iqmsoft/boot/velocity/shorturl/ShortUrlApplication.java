package com.iqmsoft.boot.velocity.shorturl;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.iqmsoft.boot.velocity.shorturl"})
@ComponentScan(basePackages = {"com.iqmsoft.boot.velocity.shorturl"})
public class ShortUrlApplication implements CommandLineRunner {

    @Autowired
    private ShortUrlRepository repository;

    @Value("${application.message}")
    private String message;

    @Value("${spring.data.mongodb.uri}")
    private String databaseURI;

    @Autowired
    private VelocityEngine engine;

  

    public void run(String... args) throws Exception {
      
        for (ShortUrl u : repository.findAll()) {
            log.debug(u.toString());
        }
      
        log.debug("Database URI: " + databaseURI);
        log.debug("Short URLs in DB: " + repository.count());
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ShortUrlApplication.class, args);
    }

}
