package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Y24D12 extends Day {

    private char[][] garden;
    private boolean[][] visited;

    public Y24D12() {
        super(2024, 12);
        String path = "y2024/day12.txt";
        super.readInput(path);
        this.garden = new char[input.size()][input.get(0).length()];
        this.visited = new boolean[input.size()][input.get(0).length()];
    }

    @Override
    protected Number part1() {

        indexGarden();

        return calculateFenceCost();
    }

    @Override
    protected Number part2() {

        indexGarden2();

        return calculateFenceCost2();
    }

    private int calculateFenceCost2() {
        int totalCost = 0;
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(row).length(); col++) {
                totalCost += calculateFenceCost2(row, col);
            }
        }
        return totalCost;
    }

    private int calculateFenceCost2(int row, int col) {
        if (visited[row][col]) {
            return 0;
        }
        Region2 plot = new Region2(garden[row][col]);

        calculatePlot2(row, col, plot);

        return plot.calculateCost();
    }

    private void calculatePlot2(int row, int col, Region2 region) {
        if (moveNotPossible2(row, col)) {
            return;
        }

        if (hasPlot2(region, row, col)) {
            return;
        }

        if (garden[row][col] != region.plant) {
            return;
        }

        visited[row][col] = true;
        region.plots.add(new int[]{row, col});
        region.area++;

        // N
        calculatePlot2(row - 1, col, region);

        // E
        calculatePlot2(row, col + 1, region);

        // S
        calculatePlot2(row + 1, col, region);

        // W
        calculatePlot2(row, col - 1, region);
    }

    private boolean hasPlot2(Region2 region, int row, int col) {
        return region.plots.stream().anyMatch(arr -> Arrays.equals(arr, new int[]{row, col}));
    }

    private int countCorners2(int row, int col, Region2 region) {
        char cur = garden[row][col];
        int result = 0;
        // check outer corners
        // NW
        if (!safeCheckSpot2(row - 1, col, cur) && !safeCheckSpot2(row, col + 1, cur) && !hasPlot2(region, row - 1, col + 1)) {
            result++;
        }

        // SW
        if (!safeCheckSpot2(row + 1, col, cur) && !safeCheckSpot2(row, col + 1, cur ) && !hasPlot2(region, row + 1, col + 1)) {
            result++;
        }

        // SE
        if (!safeCheckSpot2(row + 1, col, cur) && !safeCheckSpot2(row, col - 1, cur) && !hasPlot2(region, row + 1, col - 1)) {
            result++;
        }

        // NE
        if (!safeCheckSpot2(row - 1, col, cur) && !safeCheckSpot2(row, col - 1, cur) && !hasPlot2(region, row - 1, col - 1)) {
            result++;
        }

        // check inner corners (only going right over)
        // N -> E
        if (!safeCheckSpot2(row - 1, col, cur) && safeCheckSpot2(row - 1, col + 1, cur) && hasPlot2(region, row - 1, col + 1)) {
            result++;
        }

        // E -> S
        if (!safeCheckSpot2(row, col + 1, cur) && safeCheckSpot2(row + 1, col + 1, cur) && hasPlot2(region, row + 1, col + 1)) {
            result++;
        }

        // S -> W
        if (!safeCheckSpot2(row + 1, col, cur) && safeCheckSpot2(row + 1, col - 1, cur) && hasPlot2(region, row + 1, col - 1)) {
            result++;
        }

        // W -> N
        if (!safeCheckSpot2(row, col - 1, cur) && safeCheckSpot2(row - 1, col - 1, cur) && hasPlot2(region, row - 1, col - 1)) {
            result++;
        }

        return result;
    }

    private boolean safeCheckSpot2(int row, int col, char c) {
        if (moveNotPossible2(row, col)) {
            return false;
        }
        return garden[row][col] == c;
    }

    private boolean moveNotPossible2(int row, int col) {
        return row < 0 || row >= garden.length || col < 0 || col >= garden[0].length;
    }

    private void indexGarden2() {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                garden[i][j] = input.get(i).charAt(j);
            }
        }
    }

    private class Region2 {
        public int area = 0;
        public char plant;
        public List<int[]> plots = new ArrayList<>();

        public Region2(char plant) {
            this.plant = plant;
        }

        public int calculateCost() {
            int result = 0;
            for (int[] plot : plots) {
                result += countCorners2(plot[0], plot[1], this);
            }
            return result * area;
        }
    }

    private int calculateFenceCost() {
        int totalCost = 0;
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(row).length(); col++) {
                totalCost += calculateFenceCost(row, col);
            }
        }
        return totalCost;
    }

    private int calculateFenceCost(int row, int col) {
        if (visited[row][col]) {
            return 0;
        }
        Region plot = new Region(garden[row][col]);

        calculatePlot(row, col, plot);

        return plot.calculateCost();
    }

    private void calculatePlot(int row, int col, Region region) {
        if (moveNotPossible(row, col)) {
            region.perimiter++;
            return;
        }

        if (region.plots.stream().anyMatch(arr -> Arrays.equals(arr, new int[]{row, col}))) {
            return;
        }

        if (garden[row][col] != region.plant) {
            region.perimiter++;
            return;
        }

        visited[row][col] = true;
        region.plots.add(new int[]{row, col});
        region.area++;

        // N
        calculatePlot(row - 1, col, region);

        // E
        calculatePlot(row, col + 1, region);

        // S
        calculatePlot(row + 1, col, region);

        // W
        calculatePlot(row, col - 1, region);
    }

    private char safeCheckSpot(int row, int col) {
        if (moveNotPossible(row, col)) {
            return '#';
        }

        return garden[row][col];
    }

    private boolean moveNotPossible(int row, int col) {
        return row < 0 || row >= garden.length || col < 0 || col >= garden[0].length;
    }

    private void indexGarden() {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                garden[i][j] = input.get(i).charAt(j);
            }
        }
    }

    private class Region {
        public int area = 0;
        public int perimiter = 0;
        public char plant;
        public List<int[]> plots = new ArrayList<>();

        public Region(char plant) {
            this.plant = plant;
        }

        public int calculateCost() {
            return area * perimiter;
        }
    }
}
