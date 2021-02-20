package com.fire.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fire.assessment.dao.PersonRepository;
import com.fire.assessment.dto.Person;
import com.fire.assessment.dto.PersonEntity;
import com.fire.assessment.service.PersonServiceImpl;
import com.fire.assessment.service.exception.PersonException;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepo;

	@InjectMocks
	private PersonServiceImpl personService;

	@Before
	public void setup() {
		personService = new PersonServiceImpl(personRepo);
	}

	@Test
	public void testGetAllPerson() {
		List<PersonEntity> PersonList = new ArrayList<PersonEntity>();
		PersonList.add(new PersonEntity(new Person(22, "Person", "one", "red")));
		PersonList.add(new PersonEntity(new Person(23, "Person", "two", "blue")));
		PersonList.add(new PersonEntity(new Person(24, "Person", "three", "purple")));
		when(personRepo.findAll()).thenReturn(PersonList);

		List<PersonEntity> result = personService.getAllPerson();
		assertEquals(3, result.size());
	}

	@Test
	public void testGetPersonById() {
		PersonEntity person = new PersonEntity(new Person(24, "Person", "three", "purple"));
		Optional<PersonEntity> expectedPerson = Optional.of(person);
		when(personRepo.findById(3l)).thenReturn(expectedPerson);

		Optional<PersonEntity> actual = personService.getPersonById(3l);
		assertEquals(expectedPerson, actual);
	}

	@Test
	public void testSavePerson() {
		Person person = new Person(24, "Person", "three", "purple");
		PersonEntity expected = new PersonEntity(person);
		when(personRepo.save(expected)).thenReturn(expected);

		PersonEntity actual = personService.savePerson(person);
		assertEquals(expected, actual);
	}

	
	@Test(expected = PersonException.class)
	public void testSavePersonInvalidAge() {
		personService.savePerson(new Person(0, "Person", "three", "purple"));
	}

	@Test(expected = PersonException.class)
	public void testSavePersonInvalidFirstNaem() {
		personService.savePerson(new Person(1, " ", "three", "purple"));
	}
	
	@Test
	public void testDeletePerson() {
		personService.deletePersonById(3l);
		verify(personRepo, times(1)).deleteById(3l);
	}

	@Test
	public void testUpdatePerson() {
		Person currentPerson = new Person(30, "person", "four", "red");
		PersonEntity currentObject = new PersonEntity(currentPerson);
		Optional<PersonEntity> optionalCurrentPersonEntity = Optional.of(currentObject);

		Person updatedPerson = new Person(28, "person", "four", "red");
		PersonEntity updatedEntity = new PersonEntity(updatedPerson);
		when(personRepo.save(updatedEntity)).thenReturn(updatedEntity);
		when(personRepo.findById(3l)).thenReturn(optionalCurrentPersonEntity);

		PersonEntity actual = personService.updatePerson(3l, updatedPerson);

		assertEquals(updatedEntity.getAge(), actual.getAge());
		assertEquals(updatedEntity.getFirstName(), actual.getFirstName());
	}

	@Test
	public void testUpdatePersonWithNullValue() {
		Person currentPerson = new Person(30, "person", "four", "red");
		Person updatedPerson = new Person(28, null, "five", "red");

		Person finalPerson = new Person(28,"person","five","red");
		
		PersonEntity currentObject = new PersonEntity(currentPerson);
		Optional<PersonEntity> optionalCurrentPersonEntity = Optional.of(currentObject);
		PersonEntity finalEntity = new PersonEntity(finalPerson);

		when(personRepo.save(finalEntity)).thenReturn(finalEntity);
		when(personRepo.findById(1l)).thenReturn(optionalCurrentPersonEntity);
		PersonEntity actual = personService.updatePerson(1l, updatedPerson);

		assertNotNull(actual.getFirstName());
		assertEquals(finalEntity.getLastName(), actual.getLastName());
	}

	@Test
	public void testUpdatePersonWithEmptyValue() {
		Person currentPerson = new Person(30, "person", "four", "red");
		Person updatedPerson = new Person(28, "", "", "red");

		Person finalPerson = new Person(28,"person","four","red");
		
		PersonEntity currentObject = new PersonEntity(currentPerson);
		Optional<PersonEntity> optionalCurrentPersonEntity = Optional.of(currentObject);
		PersonEntity finalEntity = new PersonEntity(finalPerson);

		when(personRepo.save(finalEntity)).thenReturn(finalEntity);
		when(personRepo.findById(1l)).thenReturn(optionalCurrentPersonEntity);
		
		PersonEntity actual = personService.updatePerson(1l, updatedPerson);

		assertNotNull(actual.getFirstName());
		assertEquals(finalEntity.getLastName(), actual.getLastName());	
		assertEquals(finalEntity.getFirstName(), actual.getFirstName());	
		
	}

}
