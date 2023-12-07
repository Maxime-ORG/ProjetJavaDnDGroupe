package com.example.apicurrentgame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Hero {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String type;
    private Integer point_de_vie;
    private Integer force_attaque;
    private Integer position;
    public Hero() {
        position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

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

    public int getPoint_de_vie() {
        return point_de_vie;
    }

    public void setPoint_de_vie(int life) {
        this.point_de_vie = life;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getForce_attaque() {
        return force_attaque;
    }

    public void setForce_attaque(int force_attaque) {
        this.force_attaque = force_attaque;
    }

    @Override
    public String toString() {
        return "Personnage{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", point_de_vie=" + point_de_vie +
                ", force_attaque=" + force_attaque +
                ", position=" + position +
                '}';
    }
}
