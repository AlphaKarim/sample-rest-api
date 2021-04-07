package com.karim.demo.dao;

import com.karim.demo.model.PersonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, PersonModel personModel);

    default int insertPerson(PersonModel personModel){
        UUID id = UUID.randomUUID();
        return insertPerson(id, personModel);
    }

    List<PersonModel> getAllPersonAvailable();

    Optional<PersonModel> selectPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, PersonModel personModel);
}
