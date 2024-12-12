package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D10P1 extends Day {

    private int[][] map;

    public Y24D10P1() {
        super(2024, 10, 1);
        String path = "y2024/day10.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {

        this.map = loadMap(input);

        List<int[]> trailheads = getTrailheads();

        return getValidTrails(trailheads);
    }

    private int getValidTrails(List<int[]> trailheads) {
        int result = 0;
        for (int[] trailhead : trailheads) {
            List<int[]> arr = new ArrayList<>();
            getValidTrails(trailhead[0], trailhead[1], 1, arr);
            result += arr.size();
        }
        return result;
    }

    private void getValidTrails(int row, int col, int height, List<int[]> tops) {
        List<int[]> availableMoves = availableMoves(row, col, height);

        if (height == 9) {
            for (int[] move : availableMoves) {
                if (isNewTop(move[0], move[1], tops)) {
                    tops.add(move);
                }
            }
        }

        availableMoves.forEach(move -> getValidTrails(move[0], move[1], height + 1, tops));
    }

    private boolean isNewTop(int row, int col, List<int[]> tops) {
        for (int[] top : tops) {
            if (top[0] == row && top[1] == col) {
                return false;
            }
        }
        return true;
    }

    private List<int[]> availableMoves(int row, int col, int height) {
        List<int[]> result = new ArrayList<>();

        // N
        addMove(row - 1, col, height, result);

        // E
        addMove(row, col + 1, height, result);

        // S
        addMove(row + 1, col, height, result);

        // W
        addMove(row, col - 1, height, result);


        return result;
    }

    private void addMove(int row, int col, int height, List<int[]> result) {
        if (isValidSpot(row, col)) {
            if (map[row][col] == height) {
                result.add(new int[]{row, col});
            }
        }
    }

    private boolean isValidSpot(int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[row].length;
    }

    private List<int[]> getTrailheads() {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    result.add(new int[]{i, j});
                }
            }
        }
        return result;
    }

    private int[][] loadMap(List<String> input) {
        int[][] map = new int[input.size()][input.get(0).length()];

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                map[i][j] = Integer.parseInt(input.get(i).charAt(j) + "");
            }
        }

        return map;
    }
}
