package com.topdesk.cases.tictactoe.yoursolution.algorithm;

import java.util.Arrays;
import java.util.List;

import com.topdesk.cases.tictactoe.CellLocation;
import com.topdesk.cases.tictactoe.GameBoard;

public class MyBoard {

    private int[] positions = new int[9];

    private int[][] triplets = { { positions[0], positions[1], positions[2] },
            { positions[3], positions[4], positions[5] }, { positions[6], positions[7], positions[8] },
            { positions[0], positions[3], positions[6] }, { positions[1], positions[4], positions[7] },
            { positions[2], positions[5], positions[8] }, { positions[0], positions[4], positions[8] },
            { positions[2], positions[4], positions[6] } };

    private int[][] tripletPositions = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
            { 0, 4, 8 }, { 2, 4, 6 } };

    private int[] tripletSums = new int[8];

    public MyBoard(GameBoard board) {

        fillPositions(board);
        sumTriplets();

    }

    public void setPositions(int givenPosition, int myCellState) {
        positions[givenPosition] = myCellState;

    }

    boolean firstMove() {

        return (countEmpty() == 9);
    }

    boolean secondMove() {

        return (countEmpty() == 8);
    }

    boolean draw() {

        return (countEmpty() == 0);
    }

    boolean cornerStart() {
        boolean cornerIsTaken = false;

        if (getPositions(0) != 0 || getPositions(2) != 0 || getPositions(6) != 0 || getPositions(8) != 0) {
            cornerIsTaken = true;
        }
        return cornerIsTaken;

    }

    int getTripletSums(int tripletNum) {
        int sum = tripletSums[tripletNum];

        return sum;
    }

    int getPositions(int position) {
        int positionInPositions = positions[position];

        return positionInPositions;
    }

    int getTripletPosition(int tripletNum, int numInTipletNum) {
        int position = tripletPositions[tripletNum][numInTipletNum];

        return position;
    }

    int[] getArrayOfPositionsInTriplet(int tripletNum) {
        int[] position = tripletPositions[tripletNum];

        return position;
    }

    int[] getTriplet(int tripletNum) {
        int[] triplet = triplets[tripletNum];

        return triplet;
    }

    int findEmptyPositionsInTriplet(int tripletNum) {
        int emptyPositionsInTriplet = -1;

        for (int i = 0; i < 3; i++) {
            if (positions[getTripletPosition(tripletNum, i)] == 0) {
                emptyPositionsInTriplet = getTripletPosition(tripletNum, i);
            }
        }

        return emptyPositionsInTriplet;
    }

    List<int[]> findTripletsIntersections(int firstTripletsNum, int secundTripletsNum) {
        int[] positionsInFirstTriplet = getArrayOfPositionsInTriplet(firstTripletsNum);
        int[] positionsInSecondTriplet = getArrayOfPositionsInTriplet(secundTripletsNum);

        int[] intersec = Arrays.stream(positionsInFirstTriplet)
                .filter(x -> Arrays.stream(positionsInSecondTriplet).anyMatch(y -> y == x)).toArray();

        return Arrays.asList(intersec);
    }

    Player whoIsNext() {
        int countX = countX();
        int countO = countO();

        Player result = null;
        if (countX == countO) {
            result = Player.X;
        } else if (countX > countO) {
            result = Player.O;
        }

        return result;
    }

    Player isWiner() {
        Player winer = null;

        for (int i = 0; i < 8 && winer == null; i++) {
            if (getTripletSums(i) == 30) {
                winer = Player.X;
            }
            if (getTripletSums(i) == 3) {
                winer = Player.O;
            }
        }

        return winer;
    }

    private int countX() {
        int x = 0;

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 10) {
                x++;
            }
        }

        return x;
    }

    private int countO() {
        int x = 0;

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 1) {
                x++;
            }
        }

        return x;
    }

    private int countEmpty() {
        int x = 0;

        for (int i = 0; i < positions.length; i++) {
            if (positions[i] == 0) {
                x++;
            }
        }

        return x;
    }

    private void fillPositions(GameBoard board) {

        int positionXIndicator = 10;
        int positionOIndicator = 1;
        int positionEmptyIndicator = 0;

        List<CellLocation> listOfPositions = Arrays.asList(CellLocation.values());

        for (int i = 0; i < listOfPositions.size(); i++) {

            switch (board.getCellState(listOfPositions.get(i))) {

            case OCCUPIED_BY_X:
                setPositions(i, positionXIndicator);
                break;
            case OCCUPIED_BY_O:
                setPositions(i, positionOIndicator);
                break;
            case EMPTY:
                setPositions(i, positionEmptyIndicator);
                break;
            }
        }
    }

    private void sumTriplets() {

        for (int i = 0; i < 8; i++) {
            tripletSums[i] = 0;
            for (int j = 0; j < 3; j++) {
                tripletSums[i] += getPositions(tripletPositions[i][j]);

            }
        }
    }

}
