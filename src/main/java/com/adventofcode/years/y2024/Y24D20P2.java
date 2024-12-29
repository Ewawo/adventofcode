package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Y24D20P2 extends Day {

    private char[][] map;
    private int sRow;
    private int sCol;
    private Map<Long, Integer> track = new HashMap<>();
    private Map<Long, Boolean> cache = new HashMap<>();
    private int result = 0;
    private boolean[][] visited;

    public Y24D20P2() {
        super(2024, 20, 2);
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
                if (map[i][j] == '.') handleBox(i, j);
            }
        }
    }

    private void handleBox(int row, int col) {
        int maxDistance = 20;
        List<int[]> cheats = new ArrayList<>();
        for (int i = row - maxDistance; i <= maxDistance * 2 + (row - maxDistance); i++) {
            for (int j = col - maxDistance; j <= maxDistance * 2 + (col - maxDistance); j++) {
                if (Math.abs(row - i) + Math.abs(col - j) <= maxDistance) {
                    if (isDot(i, j)) {
                        cheats.add(new int[]{i, j});
                    }
                }
            }
        }

        for (int[] cheat : cheats) {
            handleDots(row, col, cheat[0], cheat[1]);
        }
    }

    private void handleDots(int r1, int c1, int r2, int c2) {
        if (isDot(r1, c1) && isDot(r2, c2)) {
            int s1 = track.get(key(r1, c1));
            int s2 = track.get(key(r2, c2));
            int score = Math.abs(s1 - s2) - Math.abs(r1 - r2) - Math.abs(c1 - c2);
            if (score  >= 100 && cache.get(key(s2, s1)) == null) {
                result++;
                cache.put(key(s1, s2), true);
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
                    this.map[i][j] = '.';
                } else if (input.get(i).charAt(j) == 'E') {
                    this.map[i][j] = '.';
                } else {
                    this.map[i][j] = input.get(i).charAt(j);
                }
            }
        }
    }

    private void mapTracks() {
        int[] next = new int[]{sRow, sCol - 1};
        int score = -1;

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
