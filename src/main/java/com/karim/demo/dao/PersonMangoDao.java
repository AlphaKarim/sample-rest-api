package com.karim.demo.dao;

import com.karim.demo.model.PersonMangoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMangoDao extends MongoRepository<PersonMangoModel,String> {

}
