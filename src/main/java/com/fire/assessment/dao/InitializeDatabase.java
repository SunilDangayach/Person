package com.fire.assessment.dao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fire.assessment.dto.Person;
import com.fire.assessment.dto.PersonEntity;

@Configuration
class InitializeDatabase {

	@Bean
	CommandLineRunner initDatabase(PersonRepository repository) {
		return args -> {
			repository.save(new PersonEntity(new Person(34, "Sunil", "Dangayach", "red")));
		};

	}
}
