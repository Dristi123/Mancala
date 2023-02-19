package com.company;

public class Node {
    Board board;
    int value;

    public Node(Board board, int value) {
        this.board = board;
        this.value = value;
    }


    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
