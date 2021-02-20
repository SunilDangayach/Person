package com.fire.assessment.dto;

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
public class Person {

	private Integer age;
	private String firstName;
	private String lastName;
	private String favouriteColour;
	
	public Person(Integer age, String firstName, String lastName, String favouriteColour) {
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.favouriteColour = favouriteColour;
	}
}
