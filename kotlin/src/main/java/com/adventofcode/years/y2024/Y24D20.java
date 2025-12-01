package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Y24D20 extends Day {

    private char[][] map;
    private int sRow;
    private int sCol;
    private Map<Long, Integer> track = new HashMap<>();
    private int result = 0;
    private boolean[][] visited;

    private char[][] map2;
    private int sRow2;
    private int sCol2;
    private Map<Long, Integer> track2 = new HashMap<>();
    private Map<Long, Boolean> cache2 = new HashMap<>();
    private int result2 = 0;
    private boolean[][] visited2;

    public Y24D20() {
        super(2024, 20);
        String path = "y2024/day20.txt";
        super.readInput(path);
    }

    @Override
    protected Object part1() {
        visited = new boolean[input.size()][input.get(0).length()];
        fillMap();
        mapTracks();
        fillScores();
        return result;
    }


    @Override
    protected Object part2() {
        visited2 = new boolean[input.size()][input.get(0).length()];
        fillMap2();
        mapTracks2();
        fillScores2();
        return result2;
    }

    private void fillScores2() {
        for (int i = 0; i < map2.length; i++) {
            for (int j = 0; j < map2[i].length; j++) {
                if (map2[i][j] == '.') handleBox2(i, j);
            }
        }
    }

    private void handleBox2(int row, int col) {
        int maxDistance = 20;
        List<int[]> cheats = new ArrayList<>();
        for (int i = row - maxDistance; i <= maxDistance * 2 + (row - maxDistance); i++) {
            for (int j = col - maxDistance; j <= maxDistance * 2 + (col - maxDistance); j++) {
                if (Math.abs(row - i) + Math.abs(col - j) <= maxDistance) {
                    if (isDot2(i, j)) {
                        cheats.add(new int[]{i, j});
                    }
                }
            }
        }

        for (int[] cheat : cheats) {
            handleDots2(row, col, cheat[0], cheat[1]);
        }
    }

    private void handleDots2(int r1, int c1, int r2, int c2) {
        if (isDot2(r1, c1) && isDot2(r2, c2)) {
            int s1 = track2.get(key2(r1, c1));
            int s2 = track2.get(key2(r2, c2));
            int score = Math.abs(s1 - s2) - Math.abs(r1 - r2) - Math.abs(c1 - c2);
            if (score  >= 100 && cache2.get(key2(s2, s1)) == null) {
                result2++;
                cache2.put(key2(s1, s2), true);
            }
        }
    }

    private boolean isDot2(int r, int c) {


        if (r < 0 || r >= map2.length || c < 0 || c >= map2[0].length) return false;

        return map2[r][c] == '.';
    }

    private void fillMap2() {
        this.map2 = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) == 'S') {
                    sRow2 = i;
                    sCol2 = j;
                    this.map2[i][j] = '.';
                } else if (input.get(i).charAt(j) == 'E') {
                    this.map2[i][j] = '.';
                } else {
                    this.map2[i][j] = input.get(i).charAt(j);
                }
            }
        }
    }

    private void mapTracks2() {
        int[] next = new int[]{sRow2, sCol2 - 1};
        int score = -1;

        do {
            visited2[next[0]][next[1]] = true;
            score++;
            next = getNextTrack2(next[0], next[1], score);
        } while (next != null);
    }

    private int[] getNextTrack2(int row, int col, int score) {
        // N
        if (checkMove2(row - 1, col)) {
            track2.put(key2(row - 1, col), score);
            return new int[]{row - 1, col};
        }

        // E
        if (checkMove2(row, col + 1)) {
            track2.put(key2(row, col + 1), score);
            return new int[]{row, col + 1};
        }

        // S
        if (checkMove2(row + 1, col)) {
            track2.put(key2(row + 1, col), score);
            return new int[]{row + 1, col};
        }

        // W
        if (checkMove2(row, col - 1)) {
            track2.put(key2(row, col - 1), score);
            return new int[]{row, col - 1};
        }
        return null;
    }

    private boolean checkMove2(int row, int col) {
        if (row < 0 || row >= map2.length || col < 0 || col >= map2[row].length) {
            return false;
        }

        return map2[row][col] == '.' && !visited2[row][col];
    }

    private long key2(int row, int col) {
        return (long) row << 32 | col;
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
