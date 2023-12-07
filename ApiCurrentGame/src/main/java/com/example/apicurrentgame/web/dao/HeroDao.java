package com.example.apicurrentgame.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apicurrentgame.model.Hero;

public interface HeroDao extends JpaRepository<Hero, Integer>{
//    public void modify(Personnage personnage, int idParam);

}
