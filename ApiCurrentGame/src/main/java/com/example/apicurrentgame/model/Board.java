package com.example.apicurrentgame.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Cell cells;

    public Board(Cell cells) {
        this.cells = cells;
    }

    public Board() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cell getCells() {
        return cells;
    }

    public void setCells(Cell cells) {
        this.cells = cells;
    }
}
