package com.example.ApiBoard.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Offensive implements Cell {
    private int id;
    @JsonIgnore
    private String name;
    private String type;
    @JsonIgnore
    private int value;

    public Offensive() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
