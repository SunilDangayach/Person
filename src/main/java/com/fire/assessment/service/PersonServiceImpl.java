package com.fire.assessment.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fire.assessment.dao.PersonRepository;
import com.fire.assessment.dto.Person;
import com.fire.assessment.dto.PersonEntity;
import com.fire.assessment.service.exception.PersonException;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository repository;

	@Autowired
	public PersonServiceImpl(PersonRepository repository) {
		this.repository = repository;
	}

	public PersonEntity savePerson(Person person) {
		if(person == null) {
			throw new PersonException("Person Object can't be null");
		}
		if(person.getAge() <= 0) {
			throw new PersonException("Person age can't be zero or less than Zero");
		}
		if(StringUtils.isBlank(person.getFirstName())){
			throw new PersonException("Person first Name can't be empty");
		}
		PersonEntity personEntity = new PersonEntity(person);
		return repository.save(personEntity);
	}

	@Override
	public PersonEntity updatePerson(Long id, Person updatedPerson) {
		Optional<PersonEntity> optionalPersonEntity = repository.findById(id);
		PersonEntity personEntity = optionalPersonEntity.get();
		if(updatedPerson != null) {
			if(updatedPerson.getAge()!=null && updatedPerson.getAge()>0) {
				personEntity.setAge(updatedPerson.getAge());
			}
			if(!StringUtils.isEmpty(updatedPerson.getFavouriteColour())) {
				personEntity.setFavouriteColour(updatedPerson.getFavouriteColour());
			}
			if(!StringUtils.isEmpty(updatedPerson.getFirstName())) {
				personEntity.setFirstName(updatedPerson.getFirstName());
			}
			if(!StringUtils.isEmpty(updatedPerson.getLastName())){
				personEntity.setLastName(updatedPerson.getLastName());
			}
		}
		return repository.save(personEntity);
	}

	@Override
	public List<PersonEntity> getAllPerson() {
		return repository.findAll();
	}

	@Override
	public Optional<PersonEntity> getPersonById(Long id) {
		 return repository.findById(id);
	}

	@Override
	public void deletePersonById(Long id) {
		repository.deleteById(id);
	}
}
