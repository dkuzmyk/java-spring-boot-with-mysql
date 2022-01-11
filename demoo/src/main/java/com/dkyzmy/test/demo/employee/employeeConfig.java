package com.dkyzmy.test.demo.employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class employeeConfig {

    @Bean
    CommandLineRunner clr (employeeRepository rep) {
        return args -> {
            employee zuzu = new employee(
                    "Zuzu",
                    "Bakkaretti",
                    LocalDate.of(1996, 1,1),
                    "zuzu@gmail.com"
            );
            employee aku = new employee(
                    "aku",
                    "bakuyaki",
                    LocalDate.of(1992, 1,1),
                    "aku@gmail.com"
            );

            rep.saveAll(List.of(zuzu, aku));

        };
    }
}
