package org.usra.creditApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CreditCardAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardAppApplication.class, args);
	}

}
