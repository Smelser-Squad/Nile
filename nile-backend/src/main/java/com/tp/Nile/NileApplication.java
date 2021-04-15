package com.tp.Nile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NileApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(NileApplication.class);
		app.setAdditionalProfiles("dev");
		app.run();
	}

}
