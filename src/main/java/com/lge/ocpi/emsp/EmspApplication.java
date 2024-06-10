package com.lge.ocpi.emsp;


import com.lge.ocpi.emsp.repository.userHelpers.RefreshableCRUDRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories(repositoryBaseClass = RefreshableCRUDRepositoryImpl.class)
@SpringBootApplication
public class EmspApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmspApplication.class, args);
    }
}
