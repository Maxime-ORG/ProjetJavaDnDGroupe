package com.example.apicurrentgame.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enemy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String type;
    private Integer point_de_vie;
    private Integer force_attaque;

    public Enemy() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getPoint_de_vie() {
        return point_de_vie;
    }

    public void setPoint_de_vie(Integer point_de_vie) {
        this.point_de_vie = point_de_vie;
    }

    public Integer getForce_attaque() {
        return force_attaque;
    }

    public void setForce_attaque(Integer force_attaque) {
        this.force_attaque = force_attaque;
    }

    @Override
    public String toString() {
        return "Enemy{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", point_de_vie=" + point_de_vie +
                ", force_attaque=" + force_attaque +
                '}';
    }
}
