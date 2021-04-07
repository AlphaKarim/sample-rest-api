package com.karim.demo.dao;

import com.karim.demo.model.PersonModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("addRepo")
public class PersonDataAccessService implements PersonDao {

    public static List<PersonModel> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, PersonModel personModel) {
        DB.add(new PersonModel(id, personModel.getName()));
        return 1;
    }

    @Override
    public List<PersonModel> getAllPersonAvailable() {
        return DB;
    }

    @Override
    public Optional<PersonModel> selectPersonById(UUID id) {
        return DB.stream().filter(personModel -> personModel.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<PersonModel> personModel = selectPersonById(id);
        if (personModel.isEmpty()){
            return 0;
        }
        DB.remove(personModel.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, PersonModel update) {
        return selectPersonById(id).map(personModel1 -> {
            int indexOfPersonToDelete = DB.indexOf(personModel1);
            if (indexOfPersonToDelete>=0){
                DB.set(indexOfPersonToDelete,new PersonModel(id ,update.getName()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }


}
