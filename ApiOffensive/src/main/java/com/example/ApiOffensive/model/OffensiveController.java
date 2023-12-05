package com.example.ApiOffensive.model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OffensiveController {
    private OffensiveDao offensiveDao;

    public OffensiveController(OffensiveDao dao) {
        offensiveDao = dao;
    }

    @GetMapping("/all")
    public List getAllOffensive() {
        return offensiveDao.findAll();
    }

    @GetMapping("all/{type}")
    public List getAllByType(@PathVariable String type) {
        return offensiveDao.findAllByType(type);
    }

    @GetMapping("/{type}/{id}")
    public Offensive getOffensive(@PathVariable String type, @PathVariable int id) {
        return offensiveDao.findAllByTypeAndId(type, id);
    }
}
