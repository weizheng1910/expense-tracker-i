package com.example.expensetrackeri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ExpenseTrackerIApplication{

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerIApplication.class, args);
    }

}
