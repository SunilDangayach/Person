package com.fire.assessment.service;

import java.util.List;
import java.util.Optional;

import com.fire.assessment.dto.Person;
import com.fire.assessment.dto.PersonEntity;

public interface PersonService {

	PersonEntity savePerson(Person person);
	PersonEntity updatePerson(Long id, Person updatedPerson);
	List<PersonEntity> getAllPerson();
	Optional<PersonEntity> getPersonById(Long id);
	void deletePersonById(Long id);
}
