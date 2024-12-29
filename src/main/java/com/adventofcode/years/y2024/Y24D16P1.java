package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;

public class Y24D16P1 extends Day {

    int startRow;
    int startCol;
    int endRow;
    int endCol;

    boolean[][] visited;
    char[][] maze;

    public Y24D16P1() {
        super(2024, 16, 1);
        String path = "y2024/day16.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {
        startRow = input.size() - 2;
        startCol = 1;
        endRow = 1;
        endCol = input.get(0).length() - 2;

        maze = input.stream().map(String::toCharArray).toList().toArray(new char[input.size()][]);
        visited = new boolean[maze.length][maze[0].length];
        return walkMaze();
    }

    private int walkMaze() {
        int dx = 1;
        int dy = 0;

        List<Move> moves = getNextMoves(new Move(startRow, startCol, dx, dy, 0));

        PriorityQueue<Move> queue = new PriorityQueue<>(moves);

        while(!queue.isEmpty()) {
            Move move = queue.remove();
            if (visited[move.row][move.col]) {
                continue;
            }
            visited[move.row][move.col] = true;
            if (move.row == endRow && move.col == endCol) {
                return move.score();
            }
            queue.addAll(getNextMoves(move));
        }
        return -1;
    }

    private List<Move> getNextMoves(Move move) {
        List<Move> moves = new ArrayList<>();
        int dx = move.dx;
        int dy = move.dy;
        int curRow = move.row;
        int curCol = move.col;
        int curScore = move.score;

        // Rechtdoor [dy, dx]
        if (checkPos(curRow + dy, curCol + dx)) {
            moves.add(new Move(curRow + dy, curCol + dx, dx, dy, curScore + 1));
        }

        // Links af [-dx, dy]
        if (checkPos(curRow - dx, curCol + dy)) {
            moves.add(new Move(curRow - dx, curCol + dy, dy, -dx, curScore + 1001));
        }

        // Rechts af [dx, -dy]
        if (checkPos(curRow + dx, curCol - dy)) {
            moves.add(new Move(curRow + dx, curCol - dy, -dy, dx, curScore + 1001));
        }

        return moves;
    }

    private boolean checkPos(int row, int col) {
        return maze[row][col] != '#' && !visited[row][col];
    }

    private record Move(int row, int col, int dx, int dy, int score) implements Comparable<Move> {

        @Override
        public int compareTo(Move o) {
            return Integer.compare(score, o.score);
        }
    }
}
