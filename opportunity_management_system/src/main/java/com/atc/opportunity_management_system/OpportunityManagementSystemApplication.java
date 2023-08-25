package com.atc.opportunity_management_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpportunityManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpportunityManagementSystemApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(){
        return res ->{
            System.out.println("Server is Running");
        };
    }

}
