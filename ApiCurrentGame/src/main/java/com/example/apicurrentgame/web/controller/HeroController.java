package com.example.apicurrentgame.web.controller;

import com.example.apicurrentgame.model.Board;
import com.example.apicurrentgame.model.Game;
import com.example.apicurrentgame.model.Hero;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import com.example.apicurrentgame.web.dao.HeroDao;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class HeroController {
    private HeroDao heroDao;
    public HeroController(HeroDao heroDao) {
        this.heroDao = heroDao;
    }

    @GetMapping("/heros")
    public MappingJacksonValue listePersonnages() {
        Iterable<Hero> personnage = heroDao.findAll();
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("life");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
        MappingJacksonValue personnageFiltres = new MappingJacksonValue(personnage);
        personnageFiltres.setFilters(listDeNosFiltres);

        return personnageFiltres;
    }

    @GetMapping(value = "/hero/{id}")
    public Optional<Hero> afficherUnHero(@PathVariable int id)
    {
        return heroDao.findById(id);
    }

    @PostMapping("/ChargeHero/{id}")
    public void ChargerPersonnage(@PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        Hero hero = restTemplate.getForObject("http://172.22.114.55:8081/api/hero/"+id, com.example.apicurrentgame.model.Hero.class);
        System.out.println(hero);
        heroDao.save(hero);
    }

    @PutMapping("/avancerPersonnage/{id}/{valeurDee}")
    public void avancerPersonnage(@PathVariable int id, @PathVariable int valeurDee) {
        Optional<Hero> personnage1 = heroDao.findById(id);
        personnage1.get().setPosition(personnage1.get().getPosition()+valeurDee);
        heroDao.save(personnage1.get());
    }

    @PutMapping("/reculerPersonnage/{id}/{valeurDee}")
    public void reculerPersonnage(@PathVariable int id, @PathVariable int valeurDee) {
        Optional<Hero> personnage1 = heroDao.findById(id);
        personnage1.get().setPosition(personnage1.get().getPosition()-valeurDee);
        heroDao.save(personnage1.get());
    }

    @PutMapping("/hero/{id}/dmg/{degats}")
    public void PersonnagePrendsDegats(@PathVariable int id, @PathVariable int degats) {
        Optional<Hero> personnage1 = heroDao.findById(id);
        personnage1.get().setPoint_de_vie(personnage1.get().getPoint_de_vie()-degats);
        heroDao.save(personnage1.get());
    }

    @PutMapping("/PersonnageRecupereVie/{id}/{vie}")
    public void PersonnageRecupereVie(@PathVariable int id, @PathVariable int vie) {
        Optional<Hero> personnage1 = heroDao.findById(id);
        personnage1.get().setPoint_de_vie(personnage1.get().getPoint_de_vie()+vie);
        heroDao.save(personnage1.get());
    }



    @GetMapping("/hero/{id}/reboot")
    public void PersonnageRecupereVie(@PathVariable int id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String allHero = restTemplate.getForObject("http://172.22.114.55:8081/api/hero", String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Hero> heroes = mapper.readValue(allHero, new TypeReference<List<Hero>>() {
        });

        Hero lasthero = heroes.getLast();
        lasthero.setId(id);
        heroDao.save(lasthero);
    }

    @GetMapping(value = "/test")
    public String getTest(){
        return "test";
    }
}
