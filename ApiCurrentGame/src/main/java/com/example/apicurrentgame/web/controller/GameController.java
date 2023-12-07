package com.example.apicurrentgame.web.controller;

import java.util.*;

import com.example.apicurrentgame.model.*;
import com.example.apicurrentgame.web.dao.GameDao;
import com.example.apicurrentgame.web.dao.HeroDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.json.JSONException;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class GameController {
    private GameDao gameDao;
    private HeroDao heroDao;
    private int dado;

    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @GetMapping(value = "/game/{id}")
    public Optional<Game> afficherUnGame(@PathVariable int id) {
        return gameDao.findById(id);
    }

    @GetMapping("/games")
    public MappingJacksonValue getBoards() {
        Iterable<Game> game = gameDao.findAll();
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("life");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);
        MappingJacksonValue gameFiltres = new MappingJacksonValue(game);
        gameFiltres.setFilters(listDeNosFiltres);
        return gameFiltres;
    }

    @GetMapping(value = "/play/{gameID}")
    public String play(@PathVariable int gameID) throws JSONException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        dado = de(7, 1);

        Game game = restTemplate.getForObject("http://localhost:8082/game/" + gameID, com.example.apicurrentgame.model.Game.class);

        Hero hero = restTemplate.getForObject("http://localhost:8082/hero/" + game.getIdHero(), com.example.apicurrentgame.model.Hero.class);

        Board board = restTemplate.getForObject("http://localhost:8082/board/" + game.getIdBoard(), Board.class);

        restTemplate.put("http://localhost:8082/avancerPersonnage/" + hero.getId() + "/" + dado, hero);
        hero.setPosition(hero.getPosition() + dado);
        String cellsString = board.getCells();
        ObjectMapper mapper = new ObjectMapper();
        List<Cell> cell = mapper.readValue(cellsString, new TypeReference<List<Cell>>() {
        });


        if (cell.get(hero.getPosition()).getType().equalsIgnoreCase("gobelin") || cell.get(hero.getPosition()).getType().equalsIgnoreCase("dragon") || cell.get(hero.getPosition()).getType().equalsIgnoreCase("sorcier")) {
            Enemy enemy = restTemplate.getForObject("http://172.22.114.55:8081/api/enemy/" + cell.get(hero.getPosition()).getId(), Enemy.class);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(cell.get(hero.getPosition()));
            restTemplate.getForObject("http://localhost:8082/play/" + gameID + "/fight", String.class);
            return json;
        } else if (cell.get(hero.getPosition()).getType().equalsIgnoreCase("empty cell")) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(cell.get(hero.getPosition()));
            return json;
        } else if (cell.get(hero.getPosition()).getType().equalsIgnoreCase("potion") || cell.get(hero.getPosition()).getType().equalsIgnoreCase("spell") || cell.get(hero.getPosition()).getType().equalsIgnoreCase("weapon")) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(cell.get(hero.getPosition()));
            return json;
        }
        return "???";
    }

    @GetMapping("/play/{gameID}/fight")
    public String getBoards(@PathVariable int gameID) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        Game game = restTemplate.getForObject("http://localhost:8082/game/" + gameID, com.example.apicurrentgame.model.Game.class);
        Hero hero = restTemplate.getForObject("http://localhost:8082/hero/" + game.getIdHero(), com.example.apicurrentgame.model.Hero.class);
        Board board = restTemplate.getForObject("http://localhost:8082/board/" + game.getIdBoard(), Board.class);


        String cellsString = board.getCells();
        ObjectMapper mapper = new ObjectMapper();
        List<Cell> cell = mapper.readValue(cellsString, new TypeReference<List<Cell>>() {
        });
        Enemy enemy = restTemplate.getForObject("http://172.22.114.55:8081/api/enemy/" + cell.get(hero.getPosition()).getId(), Enemy.class);

        while (enemy.getPoint_de_vie() > 0 && hero.getPoint_de_vie() > 0) {
            enemy.setPoint_de_vie(enemy.getPoint_de_vie() - hero.getForce_attaque());
            if (enemy.getPoint_de_vie() > 0) {
                restTemplate.put("http://localhost:8082/hero/" + game.getIdHero() + "/dmg/" + enemy.getForce_attaque(), Hero.class);
            }
        }



        return enemy.toString();
    }

    @GetMapping(value = "/play/{gameID}/{dado}")
    public String playCase(@PathVariable int gameID, @PathVariable int dado) throws JSONException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Game game = restTemplate.getForObject("http://localhost:8082/game/" + gameID, com.example.apicurrentgame.model.Game.class);
        Hero hero = restTemplate.getForObject("http://localhost:8082/hero/" + game.getIdHero(), com.example.apicurrentgame.model.Hero.class);
        Board board = restTemplate.getForObject("http://localhost:8082/board/" + game.getIdBoard(), Board.class);
        restTemplate.put("http://localhost:8082/avancerPersonnage/" + hero.getId() + "/" + dado, hero);
        hero.setPosition(hero.getPosition() + dado);
        String cellsString = board.getCells();
        ObjectMapper mapper = new ObjectMapper();
        List<Cell> cell = mapper.readValue(cellsString, new TypeReference<List<Cell>>() {
        });


        if (cell.get(hero.getPosition()).getType().equalsIgnoreCase("gobelin") || cell.get(hero.getPosition()).getType().equalsIgnoreCase("dragon") || cell.get(hero.getPosition()).getType().equalsIgnoreCase("sorcier")) {
            Enemy enemy = restTemplate.getForObject("http://172.22.114.55:8081/api/enemy/" + cell.get(hero.getPosition()).getId(), Enemy.class);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(cell.get(hero.getPosition()));
            return json;
        } else if (cell.get(hero.getPosition()).getType().equalsIgnoreCase("empty cell")) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(cell.get(hero.getPosition()));
            return json;
        } else if (cell.get(hero.getPosition()).getType().equalsIgnoreCase("potion") || cell.get(hero.getPosition()).getType().equalsIgnoreCase("spell") || cell.get(hero.getPosition()).getType().equalsIgnoreCase("weapon")) {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(cell.get(hero.getPosition()));
            return json;
        }
        return "???";
    }

    @GetMapping("/start/{gameID}/{boardID}/{heroID}")
    public String Start(@PathVariable int boardID, @PathVariable int heroID, @PathVariable int gameID) {
        RestTemplate restTemplate = new RestTemplate();
        Hero hero = restTemplate.getForObject("http://localhost:8082/hero/" + heroID, com.example.apicurrentgame.model.Hero.class);
        restTemplate.put("http://localhost:8082/PersonnagePosition0/" + heroID, hero);
        Game game = new Game(gameID, boardID, heroID);
        System.out.println(game);
        gameDao.save(game);
        return "To play do /play/gameID, The id of the game you started is : " + gameID;
    }

    @GetMapping("/start/board")
    public String StartBoard() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String allBoard = restTemplate.getForObject("http://localhost:8082/board/6", String.class);
        //Choose hero id and board id (/start/gameID/boardID/heroID)
        return allBoard;
    }

    @GetMapping("/start/hero")
    public Hero StartHero() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Hero hero = restTemplate.getForObject("http://localhost:8082/hero/1", Hero.class);

        return hero;
    }

    private int de(int upper, int lower) {
        return (int) (Math.random() * (upper - lower)) + lower;
    }

    @GetMapping("/{gameID}/life")
    public int getLife(@PathVariable int gameID) {
        RestTemplate restTemplate = new RestTemplate();
        Game game = restTemplate.getForObject("http://localhost:8082/game/" + gameID, com.example.apicurrentgame.model.Game.class);
        Hero hero = restTemplate.getForObject("http://localhost:8082/hero/" + game.getIdHero(), com.example.apicurrentgame.model.Hero.class);
        return hero.getPoint_de_vie();
    }

    @GetMapping("/{gameID}/position")
    public int getPosition(@PathVariable int gameID) {
        RestTemplate restTemplate = new RestTemplate();
        Game game = restTemplate.getForObject("http://localhost:8082/game/" + gameID, com.example.apicurrentgame.model.Game.class);
        Hero hero = restTemplate.getForObject("http://localhost:8082/hero/" + game.getIdHero(), com.example.apicurrentgame.model.Hero.class);
        return hero.getPosition();
    }

    @GetMapping("/{gameID}/reboot")
    public void reboot(@PathVariable int gameID) {
        RestTemplate restTemplate = new RestTemplate();
        Game game = restTemplate.getForObject("http://localhost:8082/game/" + gameID, com.example.apicurrentgame.model.Game.class);
        Hero hero = restTemplate.getForObject("http://localhost:8082/hero/" + game.getIdHero() + "/reboot", com.example.apicurrentgame.model.Hero.class);
    }

    @GetMapping("/dado")
    public int getDado() {
        return dado;
    }

    private void fight() {
        System.out.println("1V1 sans règles");
    }
}
