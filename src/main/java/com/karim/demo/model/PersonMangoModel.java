package com.karim.demo.model;

import org.springframework.data.annotation.Id;


public class PersonMangoModel {
    @Id
    public String id;

    public long record;
    public String name;

    public PersonMangoModel() {}

    public PersonMangoModel(long record, String lastName) {
        this.record = record;
        this.name = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getRecord() {
        return record;
    }

    public void setRecord(long record) {
        this.record = record;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format(
                "Person[id=%s, record='%s', name='%s']",
                id, record, name);
    }
}
