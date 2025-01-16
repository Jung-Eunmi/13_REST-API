package com.ohgiraffers.restapiswagger.menu.config;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ohgiraffers.restapiswagger")
@EntityScan(basePackages = "com.ohgiraffers.restapiswagger")
public class TestCrudRestapiSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestCrudRestapiSwaggerApplication.class, args);
    }

}
