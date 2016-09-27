package com.topdesk.cases.tictactoe.yoursolution.algorithm;

public class MyValidation {
    public void validate(MyBoard board) {
        System.out.println("Validation start");

        if (board.hasWinner()) {
            throw new IllegalStateException();
        }

        if (board.draw()) {
            System.out.println("exeption throw");
            throw new IllegalStateException(
                    "Board is in illegal state: It is Draw .The board is full, no moves to make.");
        }

        System.out.println("Validation ok");
    }
}
