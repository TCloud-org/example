package com.tcw.example_service.model;

import java.util.UUID;

public class FirstProcessObject {
    private String id;
    private int value;

    public FirstProcessObject() {
    }

    public FirstProcessObject(int value) {
        this.id = UUID.randomUUID().toString();
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
