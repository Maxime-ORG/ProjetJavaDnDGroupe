package com.example.apicurrentgame.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idBoard;
    private int idHero;

    public Game(int id, int idBoard, int idHero) {
        this.id = id;
        this.idBoard = idBoard;
        this.idHero = idHero;
    }

    public Game() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(int idBoard) {
        this.idBoard = idBoard;
    }

    public int getIdHero() {
        return idHero;
    }

    public void setIdHero(int idHero) {
        this.idHero = idHero;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", idBoard=" + idBoard +
                ", idHero=" + idHero +
                '}';
    }
}
