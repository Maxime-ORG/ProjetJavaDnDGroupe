package com.example.thymeleaf;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {
    private RestTemplate restTemplate;

    GameController() {
        restTemplate = new RestTemplate();
    }

    @GetMapping("/home")
    String getGame(Model model) {
        model.addAttribute("title", "Donjons & Dragons");
        model.addAttribute("welcome", "Bienvenue sur le jeu DND de Massimo, Nathalie et Maxime.");

        model.addAttribute("start", "Start");
        model.addAttribute("create_player", "Créer un joueur");
        model.addAttribute("offensive_list", "Afficher la liste des équipements offensifs");

        return "home";
    }

    @GetMapping("/offensive_list")
    String getOffensiveList(Model model) {
        model.addAttribute("offensive_list_welcome_message", "Liste des équipements");
        List response = restTemplate.getForObject("http://localhost:8090/all", List.class);

        model.addAttribute("offensive_list", response);
        return "offensive_list";
    }

    @GetMapping("/heroes_list")
    String getHeroesList(Model model) {
        model.addAttribute("heroes_list_welcome_message", "Liste des héros");
        List response = restTemplate.getForObject("http://172.22.114.55:8081/api/hero", List.class);

        model.addAttribute("heroes_list", response);
        return "heroes_list";
    }

    @GetMapping("/create_player")
    String createPlayer(Model model) {
        HeroForm heroForm = new HeroForm();
        model.addAttribute("create_player_message", "Créer un joueur");
        model.addAttribute("heroForm", heroForm);
        return "create_player";
    }

    @PostMapping("/home")
    public String putInTheMFDatabase(Model model, @ModelAttribute("heroForm") HeroForm heroForm) {
        model.addAttribute("created_player_title", "Personnage créé");
        model.addAttribute("heroForm", heroForm);

            HeroForm response = restTemplate.postForObject("http://172.22.114.55:8081/api/hero", heroForm, HeroForm.class);

        return "created_player";
    }

    @GetMapping("/start")
    public String start(Model model){
        model.addAttribute("start_game_title", "Voici votre héros :");
        ResponseEntity<String> response = restTemplate.getForEntity("http://172.22.114.69:8082/start/hero", String.class);
        model.addAttribute("hero", response.getBody());
        return "start";
    }

}
