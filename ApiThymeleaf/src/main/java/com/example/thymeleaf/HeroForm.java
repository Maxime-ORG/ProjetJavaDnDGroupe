package com.example.thymeleaf;

public class HeroForm {
    private String nom;
    private String type;
    private Integer force_attaque;
    private Integer point_de_vie;

    public HeroForm(String nom, String type, Integer point_de_vie, Integer force_attaque) {
        this.nom = nom;
        this.type = type;
        this.point_de_vie = point_de_vie;
        this.force_attaque = force_attaque;
    }
    public HeroForm(){}

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

    public Integer getForce_attaque() {
        return force_attaque;
    }

    public void setForce_attaque(Integer force_attaque) {
        this.force_attaque = force_attaque;
    }

    public Integer getPoint_de_vie() {
        return point_de_vie;
    }

    public void setPoint_de_vie(Integer point_de_vie) {
        this.point_de_vie = point_de_vie;
    }
}
