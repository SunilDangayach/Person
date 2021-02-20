package com.fire.assessment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fire.assessment.dto.PersonEntity;

public interface PersonRepository extends JpaRepository<PersonEntity, Long>{

}
