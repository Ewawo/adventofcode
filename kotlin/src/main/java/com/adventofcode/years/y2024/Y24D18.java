package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;

public class Y24D18 extends Day {

    private final int W = 71;
    private final int H = 71;
    private final int LOOPS = 1024;

    private int loops = 1024;

    // row, col
    Move2[][] map2 = new Move2[W][H];
    boolean[][] closedList2 = new boolean[W][H];

    // row, col
    Move[][] map = new Move[W][H];
    boolean[][] closedList = new boolean[W][H];

    public Y24D18() {
        super(2024, 18);
        String path = "y2024/day18.txt";
        super.readInput(path);
    }

    @Override
    protected Object part1() {
        fillMap();
        var m = traverse();
        return m.g;
    }


    @Override
    protected Object part2() {
        fillMap2();
        var m = traverse2();

        while (m != null) {
            loops++;
            closedList2 = new boolean[W][H];
            fillMap2();
            m = traverse2();
        }
        return input.get(loops - 1);
    }

    private Move2 traverse2() {
        closedList2[0][0] = true;

        map2[0][0] = new Move2(0, 0, 0, calculateH2(0, 0), null);
        List<Move2> moves = getNextMoves2(map2[0][0]);

        PriorityQueue<Move2> queue = new PriorityQueue<>(moves);

        while(!queue.isEmpty()) {
            Move2 move = queue.remove();
            closedList2[move.row][move.col] = true;

            if (move.row == W - 1 && move.col == H - 1) {
                return move;
            }

            queue.addAll(getNextMoves2(move));
        }
        return null;
    }

    private List<Move2> getNextMoves2(Move2 move) {
        List<Move2> moves = new ArrayList<>();
        int curRow = move.row;
        int curCol = move.col;

        // N
        if (checkPos2(curRow - 1, curCol, move)) {
            moves.add(map2[curRow - 1][curCol]);
        }

        // E
        if (checkPos2(curRow, curCol + 1, move)) {
            moves.add(map2[curRow][curCol + 1]);
        }

        // S
        if (checkPos2(curRow + 1, curCol, move)) {
            moves.add(map2[curRow + 1][curCol]);
        }

        // W
        if (checkPos2(curRow, curCol - 1, move)) {
            moves.add(map2[curRow][curCol - 1]);
        }

        return moves;
    }

    private boolean checkPos2(int row, int col, Move2 parent) {
        if (row < 0 || row >= W || col < 0 || col >= H) {
            return false;
        }
        if (closedList2[row][col]) {
            return false;
        }
        if (map2[row][col] == null) {
            map2[row][col] = new Move2(row, col, parent.g + 1, calculateH2(row, col), parent);
        }
        return true;
    }

    private int calculateH2(int row, int col) {
        return (W - 1 - row) + (H - 1 - col);
    }

    private void fillMap2() {
        for (int i = 0; i < loops; i++) {
            int col = Integer.parseInt(input.get(i).split(",")[0]);
            int row = Integer.parseInt(input.get(i).split(",")[1]);
            closedList2[row][col] = true;
        }
    }

    private class Move2 implements Comparable<Move2> {

        public int row, col, g, h;
        public Move2 parent;

        public Move2(int row, int col, int g, int h, Move2 parent) {
            this.row = row;
            this.col = col;
            this.g = g;
            this.h = h;
            this.parent = parent;
        }


        @Override
        public int compareTo(Move2 o) {
            return Integer.compare(g + h, o.g + o.h);
        }
    }

    private Move traverse() {

        map[0][0] = new Move(0, 0, 0, calculateH(0, 0), null);
        List<Move> moves = getNextMoves(map[0][0]);

        PriorityQueue<Move> queue = new PriorityQueue<>(moves);

        while(!queue.isEmpty()) {
            Move move = queue.remove();
            if (closedList[move.row][move.col]) {
                continue;
            }
            closedList[move.row][move.col] = true;

            if (move.row == W - 1 && move.col == H - 1) {
                return move;
            }

            queue.addAll(getNextMoves(move));
        }
        return null;
    }

    private List<Move> getNextMoves(Move move) {
        List<Move> moves = new ArrayList<>();
        int curRow = move.row;
        int curCol = move.col;

        // N
        if (checkPos(curRow - 1, curCol, move)) {
            moves.add(map[curRow - 1][curCol]);
        }

        // E
        if (checkPos(curRow, curCol + 1, move)) {
            moves.add(map[curRow][curCol + 1]);
        }

        // S
        if (checkPos(curRow + 1, curCol, move)) {
            moves.add(map[curRow + 1][curCol]);
        }

        // W
        if (checkPos(curRow, curCol - 1, move)) {
            moves.add(map[curRow][curCol - 1]);
        }

        return moves;
    }

    private boolean checkPos(int row, int col, Move parent) {
        if (row < 0 || row >= W || col < 0 || col >= H) {
            return false;
        }
        if (closedList[row][col]) {
            return false;
        }
        if (map[row][col] == null) {
            map[row][col] = new Move(row, col, parent.g + 1, calculateH(row, col), parent);
        }
        return true;
    }

    private int calculateH(int row, int col) {
        return (W - 1 - row) + (H - 1 - col);
    }

    private void fillMap() {
        for (int i = 0; i < LOOPS; i++) {
            int col = Integer.parseInt(input.get(i).split(",")[0]);
            int row = Integer.parseInt(input.get(i).split(",")[1]);
            closedList[row][col] = true;
        }
    }

    private class Move implements Comparable<Move> {

        public int row, col, g, h;
        public Move parent;

        public Move(int row, int col, int g, int h, Move parent) {
            this.row = row;
            this.col = col;
            this.g = g;
            this.h = h;
            this.parent = parent;
        }


        @Override
        public int compareTo(Move o) {
            return Integer.compare(g + h, o.g + o.h);
        }
    }
}
