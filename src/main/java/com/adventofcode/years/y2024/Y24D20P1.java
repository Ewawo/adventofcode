package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.HashMap;
import java.util.Map;

public class Y24D20P1 extends Day {

    private char[][] map;
    private int sRow;
    private int sCol;
    private Map<Long, Integer> track = new HashMap<>();
    private int result = 0;
    private boolean[][] visited;

    public Y24D20P1() {
        super(2024, 20, 1);
        String path = "y2024/day20.txt";
        super.readInput(path);
    }

    @Override
    protected Object logic() {
        visited = new boolean[input.size()][input.get(0).length()];
        fillMap();
        mapTracks();
        fillScores();
        return result;
    }

    private void fillScores() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                handleBox(i, j);
            }
        }
    }

    private void handleBox(int row, int col) {
        if (map[row][col] != '#') {
            return;
        }

        if (isDot(row - 1, col) && isDot(row + 1, col)) {
            int s1 = track.get(key(row - 1, col));
            int s2 = track.get(key(row + 1, col));
            int score = Math.abs(s1 - s2) - 2;
            if (score >= 100) {
                result++;
            }
        } else if (isDot(row, col - 1) && isDot(row, col + 1)) {
            int s1 = track.get(key(row, col - 1));
            int s2 = track.get(key(row, col + 1));

            int score = Math.abs(s1 - s2) - 2;
            if (score >= 100) {
                result++;
            }
        }
    }

    private boolean isDot(int r, int c) {


        if (r < 0 || r >= map.length || c < 0 || c >= map[0].length) return false;

        return map[r][c] == '.';
    }

    private void fillMap() {
        this.map = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'S') {
                    sRow = i;
                    sCol = j;
                    this.map[i][j] = '#';
                } else if (input.get(i).charAt(j) == 'E') {
                    this.map[i][j] = '.';
                } else {
                    this.map[i][j] = input.get(i).charAt(j);
                }
            }
        }
    }

    private void mapTracks() {
        int[] next = new int[]{sRow, sCol};
        int score = 0;

        do {
            visited[next[0]][next[1]] = true;
            score++;
            next = getNextTrack(next[0], next[1], score);
        } while (next != null);
    }

    private int[] getNextTrack(int row, int col, int score) {
        // N
        if (checkMove(row - 1, col)) {
            track.put(key(row - 1, col), score);
            return new int[]{row - 1, col};
        }

        // E
        if (checkMove(row, col + 1)) {
            track.put(key(row, col + 1), score);
            return new int[]{row, col + 1};
        }

        // S
        if (checkMove(row + 1, col)) {
            track.put(key(row + 1, col), score);
            return new int[]{row + 1, col};
        }

        // W
        if (checkMove(row, col - 1)) {
            track.put(key(row, col - 1), score);
            return new int[]{row, col - 1};
        }
        return null;
    }

    private boolean checkMove(int row, int col) {
        if (row < 0 || row >= map.length || col < 0 || col >= map[row].length) {
            return false;
        }

        return map[row][col] == '.' && !visited[row][col];
    }

    private long key(int row, int col) {
        return (long) row << 32 | col;
    }
}
