package com.example.apicurrentgame.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String cells;


    public Board() {

    }

    public Board(int id, String cells) {
        this.id = id;
        this.cells = cells;
    }

    public Board(String cells) {
        this.cells = cells;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCells() {
        return cells;
    }

    public void setCells(String cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", cells='" + cells + '\'' +
                '}';
    }
}
