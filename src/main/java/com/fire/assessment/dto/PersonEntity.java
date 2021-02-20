package com.fire.assessment.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class PersonEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Integer age;
	private String firstName;
	private String lastName;
	private String favouriteColour;
	
	
	public PersonEntity (Person person) {
		this.age = person.getAge();
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.favouriteColour = person.getFavouriteColour();
	}

}
