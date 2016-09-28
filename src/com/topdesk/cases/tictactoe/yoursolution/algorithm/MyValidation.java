package com.topdesk.cases.tictactoe.yoursolution.algorithm;

public class MyValidation {
    public void validate(MyBoard board) {

        if (board.isWiner() == Player.X) {
            throw new IllegalStateException("Board is at illegal state:No moves to suggest. X won already!");
        }
        if (board.isWiner() == Player.O) {
            throw new IllegalStateException("Board is at illegal state:No moves to suggest. O won already!");
        }

        if (board.draw()) {
            System.out.println("exeption throw");
            throw new IllegalStateException(
                    "Board is in illegal state: It is Draw .The board is full, no moves to suggest.");
        }
    }
}
