package com.example.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {
    private RestTemplate restTemplate;
    GameController(){

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
    String getOffensiveList(Model model){
        List response = restTemplate.getForObject("http://localhost:8090/all", List.class);
        model.addAttribute("offensive_list_welcome_message", "Liste des équipements");
        model.addAttribute("offensive_list", ArrayList(response));
        return "offensive_list";
    }

}
