package com.karim.demo.service;

import com.karim.demo.dao.PersonDao;
import com.karim.demo.dao.PersonDatabaseDao;
import com.karim.demo.dao.PersonMangoDao;
import com.karim.demo.model.PersonEntity;
import com.karim.demo.model.PersonMangoModel;
import com.karim.demo.model.PersonModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonDao personDao;
    private final PersonDatabaseDao personDatabaseDao;
    private final PersonMangoDao personMangoDao;

    @Autowired
    private RedisTemplate<String,String> stringRedisTemplate;


    @Autowired
    public PersonService(@Qualifier("addRepo") PersonDao personDao, PersonDatabaseDao personDatabaseDao, PersonMangoDao personMangoDao) {
        this.personDao = personDao;
        this.personDatabaseDao = personDatabaseDao;
        this.personMangoDao = personMangoDao;
    }

    public int addPerson(PersonModel personModel){
       return personDao.insertPerson(personModel);
    }

    public List<PersonModel> getAllPersonAvailable() {
        return personDao.getAllPersonAvailable();
    }

    public Optional<PersonModel> selectPersonById(UUID id){
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id){
        return personDao.deletePersonById(id);
    }
    public int updatePersonById(UUID id, PersonModel personModel){
        return personDao.updatePersonById(id, personModel);
    }

    public int saveInDb(long record, String name){
        LOGGER.info("Insert values to redis started");
        stringRedisTemplate.opsForValue().set(String.valueOf(record), name);
        try {
            closeConnection(stringRedisTemplate);
        }catch (RedisConnectionFailureException e){
            closeConnection(stringRedisTemplate);
        }finally {
            closeConnection(stringRedisTemplate);
        }
        LOGGER.info("Insert values to redis completed");
        PersonEntity entity = new PersonEntity();
        entity.setRecord(record);
        entity.setName(name);
        personDatabaseDao.save(entity);
        return 1;
    }

    public int saveInMongoDb(long record, String name){
        LOGGER.info("Insert values to redis started");
        stringRedisTemplate.opsForValue().set(String.valueOf(record), name);
        try {
            closeConnection(stringRedisTemplate);
        }catch (RedisConnectionFailureException e){
            closeConnection(stringRedisTemplate);
        }finally {
            closeConnection(stringRedisTemplate);
        }
        LOGGER.info("Insert values to redis completed");
        PersonMangoModel entity = new PersonMangoModel();
        entity.setRecord(record);
        entity.setName(name);
        personMangoDao.save(entity);
        return 1;
    }
    public List<PersonMangoModel> getInMongoDb(){
        List<PersonMangoModel> list = new ArrayList<>();
        list  = personMangoDao.findAll();
        LOGGER.info("Insert values to redis started");
        stringRedisTemplate.opsForValue().set("personModel", list.toString());
        try {
            closeConnection(stringRedisTemplate);
        }catch (RedisConnectionFailureException e){
            closeConnection(stringRedisTemplate);
        }finally {
            closeConnection(stringRedisTemplate);
        }
        LOGGER.info("Insert values to redis completed");
        return list;
    }

    private void closeConnection(RedisTemplate stringRedisTemplate){
        try {
            JedisConnectionFactory connectionFactory = (JedisConnectionFactory) stringRedisTemplate.getConnectionFactory();
            connectionFactory.getConnection().close();
            connectionFactory.destroy();
        }catch (RedisConnectionFailureException e){
            LOGGER.info("Connection closed already");
        }
    }
}
