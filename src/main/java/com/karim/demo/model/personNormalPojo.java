package com.karim.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class personNormalPojo {
    private final long record;
    private final String name;

    public personNormalPojo(@JsonProperty("record") long record, @JsonProperty("name") String name) {
        this.record = record;
        this.name = name;
    }

    public long getRecord() {
        return record;
    }

    public String getName() {
        return name;
    }


}
