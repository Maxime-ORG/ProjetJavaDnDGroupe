package com.example.apicurrentgame.web.controller;

import aj.org.objectweb.asm.TypeReference;
import com.example.apicurrentgame.model.Board;
import com.example.apicurrentgame.model.Cell;
import com.example.apicurrentgame.web.dao.BoardDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class BoardController {
    private BoardDao boardDao;

    public BoardController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @PostMapping("/ChargerBoard")
    public void ChargerBoard(@RequestBody Board board) {
        boardDao.save(board);
    }

    @GetMapping("/zob")
    public Cell getCell() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        List<Cell> zob = restTemplate.getForObject("http://172.22.114.75:8091/test", List.class);

        return zob.get(0);
    }
}
