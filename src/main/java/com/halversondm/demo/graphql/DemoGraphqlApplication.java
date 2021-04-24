package com.halversondm.demo.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DemoGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoGraphqlApplication.class, args);
	}

}
