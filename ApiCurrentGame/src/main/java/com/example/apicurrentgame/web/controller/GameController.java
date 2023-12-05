package com.example.apicurrentgame.web.controller;

import java.util.*;
import com.example.apicurrentgame.model.Board;
import com.example.apicurrentgame.model.Cell;
import com.example.apicurrentgame.model.Game;
import com.example.apicurrentgame.model.Hero;
import com.example.apicurrentgame.web.dao.GameDao;
import com.example.apicurrentgame.web.dao.HeroDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.gson.Gson;
import org.json.JSONException;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Optional;

@RestController
public class GameController {
    private GameDao gameDao;
    private HeroDao heroDao;
    public GameController(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @GetMapping(value = "/game/{id}")
    public Optional<Game> afficherUnGame(@PathVariable int id)
    {
        return gameDao.findById(id);
    }

    @GetMapping(value = "/play/{gameID}")
    public String play(@PathVariable int gameID) throws JSONException, JsonProcessingException {
        int de = de(6, 1);

        RestTemplate restTemplate = new RestTemplate();
        Game game = restTemplate.getForObject("http://localhost:8082/game/"+gameID, com.example.apicurrentgame.model.Game.class);
        Hero hero = restTemplate.getForObject("http://localhost:8082/hero/"+game.getIdHero(), com.example.apicurrentgame.model.Hero.class);
        Board board = restTemplate.getForObject("http://localhost:8082/board/"+game.getIdBoard(), Board.class);
        restTemplate.put("http://localhost:8082/avancerPersonnage/"+hero.getId()+"/"+de, hero);
        hero.setPosition(hero.getPosition()+de);
        String cellsString = board.getCells();

        Gson gson = new Gson();
        List<Cell> cell = gson.fromJson(cellsString, List.class);

        return (hero.toString()+"avance de : "+de+" cases et arrive sur "+ cell.get(hero.getPosition()));
    }

    @GetMapping("/games")
    public MappingJacksonValue getBoards(){
        Iterable<Game> game = gameDao.findAll();
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("life");

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue gameFiltres = new MappingJacksonValue(game);

        gameFiltres.setFilters(listDeNosFiltres);

        return gameFiltres;
    }
    @GetMapping("/start/{gameID}/{boardID}/{heroID}")
    public String Start(@PathVariable int boardID, @PathVariable int heroID, @PathVariable int gameID){
        Game game = new Game(gameID, boardID, heroID);
        System.out.println(game);
        gameDao.save(game);

        return "To play do /play/gameID, The id of the game you started is : "+gameID;
    }

    @GetMapping("/start")
    public String Start(){
        RestTemplate restTemplate = new RestTemplate();
        String allBoard = restTemplate.getForObject("http://localhost:8082/boards", String.class);
        String allHero = restTemplate.getForObject("http://localhost:8082/heros", String.class);
        return "Choose hero id and board id (/start/gameID/boardID/heroID)"+allBoard+allHero;
    }

    private int de(int upper, int lower){
        return (int) (Math.random() * (upper - lower)) + lower;
    }

    private void fight(){
        System.out.println("1V1 sans règles");
    }
}
