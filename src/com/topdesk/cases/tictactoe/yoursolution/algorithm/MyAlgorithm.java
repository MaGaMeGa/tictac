package com.topdesk.cases.tictactoe.yoursolution.algorithm;

import java.util.ArrayList;
import java.util.List;

public class MyAlgorithm {

    public MyPosition suggest(MyBoard board) {
        System.out.println(".......................");
        System.out.println(".......................");
        System.out.println("algorithm suggest called :");
        System.out.println(".......................");
        System.out.println(".......................");
        board.printBoard();

        board.sumTriplets();

        Player player = board.whoIsNext();
        MyPosition result = null;

        if (result == null) {
            result = getFirstMove(board);
        }
        if (result == null) {
            result = getSecondMove(board);
        }
        if (result == null) {
            result = getWinner(board, player);
        }
        if (result == null) {
            result = getBlockWinner(board, player);
        }
        if (result == null) {
            result = getFork(board, player);
        }
        if (result == null) {
            result = getBlockFork(board, player);
        }
        if (result == null) {
            result = getCenter(board);
        }
        if (result == null) {
            result = getOppositeCorner(board, player);
        }
        if (result == null) {
            result = getEmptyCorner(board);
        }
        if (result == null) {
            result = getEmptySide(board);
        }
        return result;
    }

    private MyPosition getFirstMove(MyBoard board) {
        System.out.println("---------------------------");
        System.out.println("getFirstMove called...");
        MyPosition result = null;
        if (board.firstMove()) {
            result = new MyPosition(0);
        }

        String s;
        if (result != null) {
            s = "0";
        } else {
            s = "null";
        }
        System.out.println("result: " + s);
        System.out.println("---------------------------");

        return result;
    }

    private MyPosition getSecondMove(MyBoard board) {
        System.out.println("---------------------------");
        System.out.println("getSecundMove called...");
        MyPosition result = null;
        if (board.secondMove() && board.cornerStart()) {
            result = new MyPosition(4);
        } else if (board.secondMove() && board.getPositions(4) != 0) {
            result = new MyPosition(0);
        } else if (board.secondMove() && board.getPositions(4) != 0 && !board.cornerStart()) {
            result = new MyPosition(4);
        }

        String s;
        if (result != null) {
            s = "?";
        } else {
            s = "null";
        }
        System.out.println("result: " + s);
        System.out.println("---------------------------");
        return result;
    }

    private MyPosition getEmptySide(MyBoard board) {
        System.out.println("---------------------------");
        System.out.println("getEmptzySide called");
        System.out.println("---------------------------");

        int position = -1;
        MyPosition result = null;
        if (board.getPositions(1) == 0 && position == -1) {
            position = 1;
        }
        if (board.getPositions(3) == 0 && position == -1) {
            position = 3;
        }
        if (board.getPositions(5) == 0 && position == -1) {
            position = 5;
        }
        if (board.getPositions(7) == 0 && position == -1) {
            position = 7;
        }

        if (position != -1) {
            result = new MyPosition(position);
        }

        System.out.println("---------------------------");
        System.out.println("getEmptzySide : " + position);
        System.out.println("---------------------------");

        return result;
    }

    private MyPosition getEmptyCorner(MyBoard board) {

        System.out.println("---------------------------");
        System.out.println("getEmptzyCorner called");
        System.out.println("---------------------------");
        int position = -1;
        MyPosition result = null;
        if (board.getPositions(0) == 0 && position == -1) {
            position = 0;
        }
        if (board.getPositions(2) == 0 && position == -1) {
            position = 2;
        }
        if (board.getPositions(6) == 0 && position == -1) {
            position = 6;
        }
        if (board.getPositions(8) == 0 && position == -1) {
            position = 8;
        }
        if (position != -1) {
            result = new MyPosition(position);
        }
        System.out.println("---------------------------");
        System.out.println("getEmptzyCorner : " + position);
        System.out.println("---------------------------");
        return result;
    }

    private MyPosition getOppositeCorner(MyBoard board, Player player) {
        System.out.println("---------------------------");
        System.out.println("getOppositC : called ");
        System.out.println("---------------------------");
        int playerIndicator = 0;
        int position = -1;
        MyPosition result = null;

        /*
         * setting playerIndicator
         */
        if (player == Player.X) {
            playerIndicator = 10;
        } else if (player == Player.O) {
            playerIndicator = 1;
        }

        /*
         * Checking all corners for player==playerIndicator and if find one and
         * the opposite corner is empty, and no previous play position was set,
         * then play on the opposite corner
         * 
         */
        if (board.getPositions(0) == playerIndicator && board.getPositions(8) == 0 && position == -1) {
            position = 8;
        }
        if (board.getPositions(2) == playerIndicator && board.getPositions(6) == 0 && position == -1) {
            position = 6;
        }
        if (board.getPositions(6) == playerIndicator && board.getPositions(2) == 0 && position == -1) {
            position = 2;
        }
        if (board.getPositions(8) == playerIndicator && board.getPositions(0) == 0 && position == -1) {
            position = 0;
        }
        if (position > -1) {
            result = new MyPosition(position);
        }

        System.out.println("---------------------------");
        System.out.println("getOppositeC : " + position);
        System.out.println("---------------------------");
        return result;
    }

    private MyPosition getCenter(MyBoard board) {
        System.out.println("---------------------------");
        System.out.println("getCenter : called");
        System.out.println("---------------------------");
        int position = -1;
        MyPosition result = null;
        if (board.getPositions(4) == 0) {
            position = 4;
        }

        if (position != -1) {
            result = new MyPosition(position);
        }
        System.out.println("---------------------------");
        System.out.println("getCenter : " + position);
        System.out.println("---------------------------");
        return result;
    }

    private MyPosition getBlockFork(MyBoard board, Player player) {
        System.out.println("---------------------------");
        System.out.println("getBlockFork : called");
        System.out.println("---------------------------");
        int position = -1;
        MyPosition result = null;
        List<Integer> opponentsForkableTriplets = new ArrayList<Integer>();
        List<Integer> opponentsForkableIntersections = new ArrayList<Integer>();

        int playerIndicator = 0;

        int opponentIndicator = 0;

        /*
         * setting playerIndicator & opponentPlayerindicators
         */
        if (player == Player.X) {
            playerIndicator = 10;
            opponentIndicator = 10;

        } else if (player == Player.O) {
            playerIndicator = 1;
            opponentIndicator = 10;
        }

        /*
         * Checking triplets for opponent possible forkable positions and add
         * all to opponentForkabletriplets
         * 
         * 
         */
        for (int i = 0; i < 8; i++) {
            if (board.getTripletSums(i) + opponentIndicator == opponentIndicator * 2) {
                opponentsForkableTriplets.add(i);

            }

        }

        /*
         * Check opponent's forkable triplets for intersection positions and if
         * position is empty add to opponetForkableIntersections list
         */

        for (int i = 0; i < opponentsForkableTriplets.size() - 1 && position == -1; i++) {
            List<int[]> intersections;
            intersections = board.findTripletsIntersections(opponentsForkableTriplets.get(i),
                    opponentsForkableTriplets.get(i + 1));
            for (int j = 0; j < intersections.size(); j++) {
                for (int k = 0; k < intersections.get(j).length; k++) {
                    if (intersections.get(j)[k] == 0) {
                        opponentsForkableIntersections.add(intersections.get(j)[k]);
                    }
                }

            }
        }

        /*
         * find possible position to force the opponent to blockWin , and add
         * all to the forceOpponentBlockWinPositions list
         * 
         */
        for (int i = 0; i < 8 && position == -1; i++) {
            if (board.getTripletSums(i) + playerIndicator == playerIndicator * 2) {
                for (int j = 0; j < 3; j++) {
                    if (board.getTriplet(i)[j] == 0) {
                        for (int k = 0; k < 3; k++) {
                            if (k == j) {
                                continue;
                            } else {
                                if (board.getTriplet(i)[k] == 0
                                        && !opponentsForkableIntersections.contains(board.getTriplet(i)[k])) {
                                    position = board.getTriplet(i)[k];
                                }
                            }

                        }
                    }

                }
            }

        }

        if (position != -1) {
            result = new MyPosition(position);
        }

        System.out.println("---------------------------");
        System.out.println("getBlockFork : " + position);
        System.out.println("---------------------------");
        return result;

    }

    private MyPosition getFork(MyBoard board, Player player) {
        System.out.println("---------------------------");
        System.out.println("getFork : called");
        System.out.println("---------------------------");
        int position = -1;
        MyPosition result = null;
        List<Integer> forkableTriplets = new ArrayList<Integer>();
        List<Integer> forkableIntersections = new ArrayList<Integer>();
        boolean fork = false;

        int playerIndicator = 0;

        /*
         * setting playerIndicator
         */
        if (player == Player.X) {
            playerIndicator = 10;
        } else if (player == Player.O) {
            playerIndicator = 1;
        }

        /*
         * Checking all 8 possible triplets for possible forkable positions and
         * if found add the triplet to forkableTriplets list
         * 
         * 
         * 
         */
        for (int i = 0; i < 8; i++) {
            System.out.println("in getfork , first loop, i=" + i);
            if (board.getTripletSums(i) + playerIndicator == playerIndicator * 2) {
                forkableTriplets.add(i);
                System.out.println("in getfork: board.sumTriplets()[i] + playerIndicator = " + board.getTripletSums(i));
                for (int j = 0; j < forkableTriplets.size(); j++) {
                    System.out.println("forkline:" + fork);
                }
            }

        }

        /*
         * Find forkable triplets intersections and if it is an empty position
         * add to forkableIntersections
         */

        for (int i = 0; i < forkableTriplets.size() - 1 && !fork; i++) {
            System.out.println("in getfork : 2nd for loop 1st");
            List<int[]> intersections;
            intersections = board.findTripletsIntersections(forkableTriplets.get(i), forkableTriplets.get(i + 1));
            for (int j = 0; j < intersections.size(); j++) {
                for (int k = 0; k < intersections.get(j).length; k++) {
                    System.out.println("in getfork : 2nd for loop 3rd");
                    System.out.println(
                            "-----------------------------intersections.get(j)[k] =" + intersections.get(j)[k]);
                    if (intersections.get(j)[k] == 0) {
                        System.out.println("intersections.get(j)[k] =" + intersections.get(j)[k]);
                        forkableIntersections.add(intersections.get(j)[k]);
                        fork = true;
                        System.out.println("forkableIntersections.get(0) =" + forkableIntersections.get(0));
                    }
                }
            }
        }

        if (fork) {
            position = forkableIntersections.get(0);
        }

        if (position != -1) {
            result = new MyPosition(position);
        }

        System.out.println("---------------------------");
        System.out.println("getFork : " + position);
        System.out.println("---------------------------");
        return result;
    }

    private MyPosition getBlockWinner(MyBoard board, Player player) {
        System.out.println("---------------------------");
        System.out.println("getBlockWiner : called");
        System.out.println("---------------------------");
        int position = -1;
        MyPosition result = null;

        int opponentIndicator = 0;

        /*
         * setting opponentIndicator
         */
        if (player == Player.X) {
            opponentIndicator = 1;
        } else if (player == Player.O) {
            opponentIndicator = 10;
        }

        /*
         * Checking all 8 possible triplets for opponent's possible wining
         * positions until the first found
         * 
         */
        for (int i = 0; i < 8 && position == -1; i++) {
            if (board.getTripletSums(i) == opponentIndicator * 2) {
                /*
                 * find empty position in the current triplet
                 */
                position = board.getTripletPositions(i)[board.findEmptyPositionInTriplet(i)];

            }

        }

        if (position != -1) {
            result = new MyPosition(position);
        }

        System.out.println("---------------------------");
        System.out.println("getBlockWiner : " + position);
        System.out.println("---------------------------");
        return result;
    }

    private MyPosition getWinner(MyBoard board, Player player) {
        System.out.println("---------------------------");
        System.out.println("getWiner : called");
        System.out.println("---------------------------");

        int position = -1;

        MyPosition result = null;

        int playerIndicator = 0;

        /*
         * setting playerIndicator
         */
        if (player == Player.X) {
            playerIndicator = 10;
        } else if (player == Player.O) {
            playerIndicator = 1;
        }

        /*
         * Checking all 8 possible triplets for possible wining positions until
         * the first found
         * 
         * 
         */
        for (int i = 0; (i < 8) && (position == -1); i++) {
            if (board.getTripletSums(i) == playerIndicator * 2) {
                /*
                 * find empty position in the current triplet
                 */
                System.out.println("иииииииииииииииииииииииииииииииииииииииииииииииииииииииии");
                System.out.println("board.getTripletSums(" + i + ") = " + board.getTripletSums(i));

                position = board.findEmptyPositionsInTriplet(i);

            }

        }

        if (position != -1) {
            result = new MyPosition(position);
        }
        System.out.println("---------------------------");
        System.out.println("geWinner : " + position);
        System.out.println("---------------------------");
        return result;
    }
}
