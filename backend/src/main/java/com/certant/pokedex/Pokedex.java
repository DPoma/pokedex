package com.certant.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Pokedex {
	
	public static void main(String[] args) {	
		SpringApplication.run(Pokedex.class, args);
	}
}
