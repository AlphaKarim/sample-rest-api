package com.karim.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
    private  long record;
    private  String name;

    public PersonEntity(){

    }

    public long getRecord() {
        return record;
    }

    public void setRecord(long record) {
        this.record = record;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
