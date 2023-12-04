package com.example.ApiBoard.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Cell> board;

    public Board(){
        board = new ArrayList<Cell>();
    }

    public List<Cell> getBoard() {
        return board;
    }
}