package com.example.ApiOffensive.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffensiveDao extends JpaRepository<Offensive, Integer> {
    public List findAllByType(String type);
}
