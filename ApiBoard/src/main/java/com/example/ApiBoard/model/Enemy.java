package com.example.ApiBoard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Enemy implements Cell{

    @JsonIgnore
    private int id;

    private String nom;
    private String type;

    private int point_de_vie;

    private int force_attaque;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPoint_de_vie() {
        return point_de_vie;
    }

    public void setPoint_de_vie(int point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    public int getForce_attaque() {
        return force_attaque;
    }

    public void setForce_attaque(int force_attaque) {
        this.force_attaque = force_attaque;
    }
}