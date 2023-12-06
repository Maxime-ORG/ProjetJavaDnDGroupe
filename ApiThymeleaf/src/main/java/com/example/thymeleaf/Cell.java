package com.example.thymeleaf;

public class Cell {
    private int id;
    private String nom;
    private String type;

    private int point_de_vie;
    private int force_attaque;
    private int position;

    private int value;

    Cell() {
    }

    // Empty cell
    public Cell(int id, String type) {
        this.id = id;
        this.type = type;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type='" + type + '\'' +
                ", point_de_vie=" + point_de_vie +
                ", force_attaque=" + force_attaque +
                ", position=" + position +
                ", value=" + value +
                '}';
    }
}
