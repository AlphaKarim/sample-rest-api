package com.karim.demo.api;

import com.karim.demo.dao.PersonDatabaseDao;
import com.karim.demo.model.PersonEntity;
import com.karim.demo.model.PersonMangoModel;
import com.karim.demo.model.PersonModel;
import com.karim.demo.model.personNormalPojo;
import com.karim.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

   private final PersonService personService;

    @Autowired
    PersonDatabaseDao personDatabaseDao;

   @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@RequestBody PersonModel model){
        personService.addPerson(model);
    }

    @GetMapping
    public List<PersonModel> getAllPersonAvailable(){
       return personService.getAllPersonAvailable();
    }

    @GetMapping(path = "{id}")
    public PersonModel getPersonById(@PathVariable("id") UUID id){
       return  personService.selectPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deletePersonById(@PathVariable("id") UUID id){
       return personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public int updatePersonById(@PathVariable("id") UUID id ,@RequestBody PersonModel personModel){
       return personService.updatePersonById(id, personModel);
    }
    @RequestMapping(value = "/saveindb", method = RequestMethod.POST)
    public int saveUserInDB(@RequestBody personNormalPojo model){
       return personService.saveInDb(model.getRecord(), model.getName());
    }

    @RequestMapping(value = "/saveinmongo", method = RequestMethod.POST)
    public int saveUserInMongoDB(@RequestBody personNormalPojo model){
        return personService.saveInMongoDb(model.getRecord(), model.getName());
    }

    @RequestMapping(value = "/getfrommongo", method = RequestMethod.GET)
    public List<PersonMangoModel> getUserInMongoDB(){
        return personService.getInMongoDb();
    }

}
