package com.example.thymeleaf;

import org.springframework.objenesis.strategy.SingleInstantiatorStrategy;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List board;

    public Board() {
        board = new ArrayList();
        initBoard();
    }

    public void initBoard() {
        for (int i = 0; i < 12; i++) {
            board.add(i);
        }

    }


}
