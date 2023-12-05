package com.example.apicurrentgame.web.controller;

import com.example.apicurrentgame.model.Personnage;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import com.example.apicurrentgame.web.dao.PersonnageDao;

import java.util.Optional;

@RestController
public class PersonnageController {
    private PersonnageDao personnageDao;
    public PersonnageController(PersonnageDao personnageDao) {
        this.personnageDao = personnageDao;
    }

    @GetMapping("/Personnages")
    public MappingJacksonValue listePersonnages() {
        Iterable<Personnage> personnage = personnageDao.findAll();
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("life");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
        MappingJacksonValue personnageFiltres = new MappingJacksonValue(personnage);
        personnageFiltres.setFilters(listDeNosFiltres);

        return personnageFiltres;
    }

    @PostMapping("/ChargerPersonnage")
    public void ChargerPersonnage(@RequestBody Personnage personnage) {
        personnageDao.save(personnage);
    }

    @PutMapping("/avancerPersonnage/{id}/{valeurDee}")
    public void avancerPersonnage(@PathVariable int id, @PathVariable int valeurDee) {
        Optional<Personnage> personnage1 = personnageDao.findById(id);
        System.out.println(personnage1.get().getType());
        personnage1.get().setPosition(personnage1.get().getPosition()+valeurDee);
        personnageDao.save(personnage1.get());
    }

    @PutMapping("/reculerPersonnage/{id}/{valeurDee}")
    public void reculerPersonnage(@PathVariable int id, @PathVariable int valeurDee) {
        Optional<Personnage> personnage1 = personnageDao.findById(id);
        System.out.println(personnage1.get().getType());
        personnage1.get().setPosition(personnage1.get().getPosition()-valeurDee);
        personnageDao.save(personnage1.get());
    }

    @PutMapping("/PersonnagePrendsDegats/{id}/{degats}")
    public void PersonnagePrendsDegats(@PathVariable int id, @PathVariable int degats) {
        Optional<Personnage> personnage1 = personnageDao.findById(id);
        System.out.println(personnage1.get().getType());
        personnage1.get().setLife(personnage1.get().getLife()-degats);
        personnageDao.save(personnage1.get());
    }

    @PutMapping("/PersonnageRecupereVie/{id}/{vie}")
    public void PersonnageRecupereVie(@PathVariable int id, @PathVariable int vie) {
        Optional<Personnage> personnage1 = personnageDao.findById(id);
        System.out.println(personnage1.get().getType());
        personnage1.get().setLife(personnage1.get().getLife()+vie);
        personnageDao.save(personnage1.get());
    }

    @GetMapping(value = "/test")
    public String getTest(){
        return "test";
    }
}
