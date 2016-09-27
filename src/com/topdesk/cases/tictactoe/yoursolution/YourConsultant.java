package com.topdesk.cases.tictactoe.yoursolution;

import java.util.Arrays;
import java.util.List;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.Consultant;
import com.topdesk.cases.tictactoe.GameBoard;
import com.topdesk.cases.tictactoe.yoursolution.algorithm.MyAlgorithm;
import com.topdesk.cases.tictactoe.yoursolution.algorithm.MyBoard;
import com.topdesk.cases.tictactoe.yoursolution.algorithm.MyPosition;
import com.topdesk.cases.tictactoe.yoursolution.algorithm.MyValidation;

public class YourConsultant implements Consultant {
    private static int suggestCount = 0;
    private MyAlgorithm algorithm = new MyAlgorithm();;
    private MyValidation validation = new MyValidation();

    @Override
    public CellLocation suggest(GameBoard gameBoard) {
        System.out.println("3¡^3^^3^^3^^3^^3^^3^^3^^3^^3^^3^^3^^");

        System.out.println("Suggest:" + suggestCount++);

        System.out.println("3¡^3^^3^^3^^3^^3^^3^^3^^3^^3^^3^^3^^");

        if (gameBoard == null) {
            throw new NullPointerException("gameboard is null");
        }
        MyBoard myBoard = convert(gameBoard);

        validation.validate(myBoard);

        MyPosition coordinate = suggest(myBoard);

        return convert(coordinate);
    }

    private MyPosition suggest(MyBoard board) {

        return algorithm.suggest(board);
    }

    private MyBoard convert(GameBoard board) {

        int positionXIndicator = 10;
        int positionOIndicator = 1;
        int positionEmptyIndicator = 0;

        MyBoard myBoard = new MyBoard();

        List<CellLocation> listOfPositions = Arrays.asList(CellLocation.values());

        for (int i = 0; i < listOfPositions.size(); i++) {

            System.out.println(i + "--freshList--" + board.getCellState(listOfPositions.get(i)));
        }

        for (int i = 0; i < listOfPositions.size(); i++) {

            switch (board.getCellState(listOfPositions.get(i))) {

            case OCCUPIED_BY_X:
                myBoard.setPositions(i, positionXIndicator);
                break;
            case OCCUPIED_BY_O:
                myBoard.setPositions(i, positionOIndicator);
                break;
            case EMPTY:
                myBoard.setPositions(i, positionEmptyIndicator);
                break;
            }
        }
        myBoard.sumTriplets();

        return myBoard;

    }

    private CellLocation convert(MyPosition coordinate) {
        CellLocation cellLocation = null;
        List<CellLocation> listOfPositions = Arrays.asList(CellLocation.values());

        cellLocation = listOfPositions.get(coordinate.getPosition());

        return cellLocation;
    }
}
