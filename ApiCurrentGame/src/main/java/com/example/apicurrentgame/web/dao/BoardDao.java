package com.example.apicurrentgame.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apicurrentgame.model.Board;

public interface BoardDao extends JpaRepository<Board, Integer>{
//    public void modify(Personnage personnage, int idParam);

}


//