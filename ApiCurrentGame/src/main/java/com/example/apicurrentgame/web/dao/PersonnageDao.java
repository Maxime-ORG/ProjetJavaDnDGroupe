package com.example.apicurrentgame.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.apicurrentgame.model.Personnage;
import org.springframework.stereotype.Repository;
public interface PersonnageDao extends JpaRepository<Personnage, Integer>{
//    public void modify(Personnage personnage, int idParam);

}
