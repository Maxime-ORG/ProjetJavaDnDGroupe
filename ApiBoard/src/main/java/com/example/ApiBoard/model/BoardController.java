package com.example.ApiBoard.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
public class BoardController {
    private Board board;
    private RestTemplate restTemplate;
    private ObjectMapper mapper;

    public BoardController() {
        restTemplate = new RestTemplate();
        board = new Board();
        mapper = new ObjectMapper();
    }

    @GetMapping("/board")
    public Board getBoard() {
        return board;
    }

    @GetMapping(value = "/board/{nbreOfCells}", produces = "application/json")
    public List<Cell> testBoard(@PathVariable int nbreOfCells) {
        createBoard(nbreOfCells);
        return board.getBoard();
    }

    public void addOffensive(String type) {
        List<Offensive> response = restTemplate.getForObject("http://localhost:8090/all/" + type, List.class);

        List<Offensive> list = mapper.convertValue(response, new TypeReference<List<Offensive>>() {
        });

        for (Offensive offensive : list) {
            board.getBoard().add(offensive);
        }
    }

    public void createBoard(int nbrOfCells) {
        addOffensive("potion");
        addOffensive("weapon");
        addOffensive("spell");
        addEnnemy();
        int nbrOfEmptyCases = nbrOfCells - board.getBoard().size();
        for (int i = 0; i < nbrOfEmptyCases; i++) {
            addEmptyCell();
        }
        Collections.shuffle(board.getBoard());
    }


    public void addEnnemy() {
        List<Enemy> response = restTemplate.getForObject("http://172.22.114.55:8081/api/enemy", List.class);
        List<Enemy> list = mapper.convertValue(response, new TypeReference<List<Enemy>>() {
        });

        for (Enemy enemy : list) {
            board.getBoard().add(enemy);
        }

    }

    public void addEmptyCell() {
        board.getBoard().add(new EmptyCell());
    }

}
