package com.example.ApiBoard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmptyCell implements Cell{

    @JsonIgnore
    private String name;
    private String type;
    @JsonIgnore
    private int value;

    public EmptyCell(){
        type = "empty cell";
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

}
