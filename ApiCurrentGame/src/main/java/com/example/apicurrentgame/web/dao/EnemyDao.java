package com.example.apicurrentgame.web.dao;

import com.example.apicurrentgame.model.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EnemyDao extends JpaRepository<Enemy, Integer>{

}