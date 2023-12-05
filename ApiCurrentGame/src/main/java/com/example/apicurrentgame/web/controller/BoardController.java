package com.example.apicurrentgame.web.controller;

import com.example.apicurrentgame.model.Board;
import com.example.apicurrentgame.model.Cell;
import com.example.apicurrentgame.web.dao.BoardDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
public class BoardController {
    private BoardDao boardDao;


    public BoardController(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @PostMapping("/chargerBoard")
    public void ChargerBoard(@RequestBody Board board) {
        boardDao.save(board);
    }

    @GetMapping("/boards")
    public MappingJacksonValue getBoards(){
        Iterable<Board> board = boardDao.findAll();
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("life");

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue boardFiltres = new MappingJacksonValue(board);

        boardFiltres.setFilters(listDeNosFiltres);

        return boardFiltres;
    }

    @GetMapping(value = "/board/{id}")
    public Optional<Board> afficherUnBoard(@PathVariable int id)
    {
        return boardDao.findById(id);
    }



    @GetMapping("/getDistantBoard/{id}")
    public void getBoard(@PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        String listCellString = restTemplate.getForObject("http://172.22.114.75:8091/board/"+id, String.class);
        List<Cell> listCell = restTemplate.getForObject("http://172.22.114.75:8091/board/"+id, List.class);
        System.out.println(listCell);
        System.out.println(listCellString);
        Board board = new Board(listCellString);
        boardDao.save(board);
    }

}
