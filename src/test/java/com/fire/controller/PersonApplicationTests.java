package com.fire.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fire.assessment.controller.PersonController;
import com.fire.assessment.dao.PersonRepository;
import com.fire.assessment.service.PersonService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class PersonApplicationTests {

	@InjectMocks
	private PersonController personController;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private PersonRepository personRepo;

	@Mock
	private PersonService personService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

	}

	@Test
	public void getPersonById() throws Exception {

		mockMvc.perform(get("/v1/person/1"))
				.andDo(print())
				.andExpect(jsonPath("$.firstName").value("Sunil"))
				.andExpect(status().isOk());

	}

	@Test
	public void getPersonByIdNotFound() throws Exception {
		mockMvc.perform(get("/v1/person/2"))
				.andDo(print())
				.andExpect(status().isNotFound());

	}

	@Test
	public void getAllPerson() throws Exception {

		mockMvc.perform(get("/v1/person"))
				.andDo(print())
				.andExpect(jsonPath("$[0].firstName").value("Sunil"))
				.andExpect(status().isOk());

	}

	@Test
	public void deletePerson() throws Exception {
		mockMvc.perform(delete("/v1/person/1"))
				.andDo(print())
				.andExpect(status().isOk());

	}

	@Test
	public void deletePersonNotFound() throws Exception {
		mockMvc.perform(delete("/v1/person/2"))
				.andDo(print())
				.andExpect(status().isNotFound());

	}

	@Test
	public void createPerson() throws Exception {
		String examplePerson = "{\"age\":\"35\",\"firstName\":\"get\",\"lastName\":\"test\",\"favouriteColour\":\"blue\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(examplePerson))
				.andExpect(status().isCreated());

	}

	
	@Test
	public void updatePersonTest() throws Exception {
		String examplePerson = "{\"age\":\"35\",\"firstName\":\"get\",\"lastName\":\"test\",\"favouriteColour\":\"blue\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(examplePerson))
			 	.andExpect(
                 status().isOk());

	}
	
	@Test
	public void updatePersonTestWithEmptyFirstName() throws Exception {
		String examplePerson = "{\"age\":\"35\",\"firstName\":\"\",\"lastName\":\"test\",\"favouriteColour\":\"blue\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(examplePerson))
				.andExpect(jsonPath("$.firstName").value("Sunil"))
			 	.andExpect(status().isOk());

	}
	
	@Test
	public void updatePersonTestWithEmptyAge() throws Exception {
		String examplePerson = "{\"age\":\"0\",\"firstName\":\"\",\"lastName\":\"test\",\"favouriteColour\":\"blue\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(examplePerson))
				.andExpect(jsonPath("$.age").value(34))
			 	.andExpect(status().isOk());

	}
	
	@Test
	public void updatePersonTestWithNotFound() throws Exception {
		String examplePerson = "{\"age\":\"35\",\"firstName\":\"\",\"lastName\":\"test\",\"favouriteColour\":\"blue\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/v1/person/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(examplePerson))
			 	.andExpect(
                 status().isNotFound());

	}
	
	/*@Test
	public void createPersonInternalServerError() throws Exception {

		String examplePerson = "{\"age\":\"-1\",\"firstName\":\"get\",\"lastName\":\"test\",\"favouriteColour\":\"blue\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(examplePerson))
	      		.andExpect(status().isInternalServerError())
	            .andExpect(result -> assertEquals("Person age can't be zero or less than Zero", result.getResolvedException().getMessage()));

	}
	
	@Test
	public void createPersonFirstnameInternalServerError() throws Exception {

		String examplePerson = "{\"age\":\"2\",\"firstName\":\"\",\"lastName\":\"test\",\"favouriteColour\":\"blue\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(examplePerson))
				.andExpect(jsonPath("$.status").value(500))
				.andExpect(jsonPath("$.message").value("Person age can't be zero or less than Zero"));
	}*/
	


}
