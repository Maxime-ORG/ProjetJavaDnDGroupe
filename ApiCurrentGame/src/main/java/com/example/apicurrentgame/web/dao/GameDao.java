package com.example.apicurrentgame.web.dao;

import com.example.apicurrentgame.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDao extends JpaRepository<Game, Integer> {
}
