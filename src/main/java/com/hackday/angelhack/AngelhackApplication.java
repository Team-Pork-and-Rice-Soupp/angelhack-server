package com.hackday.angelhack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AngelhackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AngelhackApplication.class, args);
    }

}
