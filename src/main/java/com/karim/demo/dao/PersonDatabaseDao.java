package com.karim.demo.dao;

import com.karim.demo.model.PersonEntity;


import org.springframework.data.repository.CrudRepository;

public interface PersonDatabaseDao extends CrudRepository<PersonEntity, Integer> {

}
