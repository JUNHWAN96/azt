package com.example.azt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing // Auditing을 사용하기 위해 추가
public class AztApplication {

    public static void main(String[] args) {
        SpringApplication.run(AztApplication.class, args);
    }

}
