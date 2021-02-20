package com.fire.assessment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fire.assessment.dto.Person;
import com.fire.assessment.dto.PersonEntity;
import com.fire.assessment.service.PersonServiceImpl;
import com.fire.assessment.service.exception.PersonException;

@RestController
@RequestMapping("v1/person")
public class PersonController {

	private final PersonServiceImpl personService;

	@Autowired
	public PersonController(PersonServiceImpl service) {
		this.personService = service;
	}

	@PostMapping
	ResponseEntity<PersonEntity> createPerson(@RequestBody Person person) {
		try {
			PersonEntity createdPerson = personService.savePerson(person);
			return new ResponseEntity<PersonEntity>(createdPerson, HttpStatus.CREATED);
		} catch (PersonException ex) {
			throw ex;
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	ResponseEntity<PersonEntity> updatePerson(@PathVariable Long id, @RequestBody Person updatedPerson) {
		try {
			Optional<PersonEntity> person = personService.getPersonById(id);
			if (person.isPresent()) {
				return new ResponseEntity<PersonEntity>(personService.updatePerson(id, updatedPerson), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	ResponseEntity<List<PersonEntity>> getAllPerson() {
		try {
			List<PersonEntity> persons = personService.getAllPerson();

			if (persons.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<PersonEntity>>(persons, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	ResponseEntity<PersonEntity> getPersonById(@PathVariable Long id) {

		Optional<PersonEntity> person = personService.getPersonById(id);

		if (person.isPresent()) {
			return new ResponseEntity<PersonEntity>(person.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	ResponseEntity<HttpStatus> deletePersonById(@PathVariable Long id) {
		try {
			if (!personService.getPersonById(id).isPresent()) {
				return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
			} else {
				personService.deletePersonById(id);
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
