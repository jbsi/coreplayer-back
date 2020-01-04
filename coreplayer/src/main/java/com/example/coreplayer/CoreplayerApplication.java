package com.example.coreplayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
public class CoreplayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreplayerApplication.class, args);
    }

}
