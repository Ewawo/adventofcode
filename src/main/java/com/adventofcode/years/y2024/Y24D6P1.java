package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;

public class Y24D6P1 extends Day {
    private ArrayList<ArrayList<Boolean>> map = new ArrayList<>();

    public Y24D6P1() {
        super(2024, 6, 1);
        String path = "y2024/day6.txt";
        super.readInput(path);
    }

    @Override
    protected Integer logic() {
        int x = 0;
        int y = 0;

        for (int i = 0; i < input.size(); i++) {
            ArrayList<Boolean> row = new ArrayList<>();
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                row.add(line.charAt(j) == '#');
                if (line.charAt(j) == '^') {
                    x = j;
                    y = i;
                }
            }
            map.add(row);
        }

        return getDistinctMoves(x, y);

    }

    private boolean hasObstacle(int x, int y) {
        return map.get(y).get(x);
    }

    private boolean isOutOfRange(int x, int y) {
        if (y < 0 || y >= map.size()) {
            return true;
        }
        if (x < 0 || x >= map.get(y).size()) {
            return true;
        }
        return false;
    }

    private int rotate(int currentDirection) {
        return (currentDirection + 1) % 4;
    }

    private int[] incrementDirection(int x, int y, int direction) {
        return switch (direction) {
            case 0 -> new int[]{x, y - 1};
            case 1 -> new int[]{x + 1, y};
            case 2 -> new int[]{x, y + 1};
            case 3 -> new int[]{x - 1, y};
            default -> new int[]{x, y};
        };
    }

    private int[] decrementDirection(int x, int y, int direction) {
        return switch (direction) {
            case 0 -> new int[]{x, y + 1};
            case 1 -> new int[]{x - 1, y};
            case 2 -> new int[]{x, y - 1};
            case 3 -> new int[]{x + 1, y};
            default -> new int[]{x, y};
        };
    }

    private boolean alreadyBinHere(int x, int y, ArrayList<Integer[]> v) {
        return v.stream().anyMatch(c -> c[0] == x && c[1] == y);
    }

    private int getDistinctMoves(int x, int y) {
        int result = 0;

        // 0 -> North, 1 -> East, 2 -> South, 3 -> West
        int direction = 0;

        ArrayList<Integer[]> visitedPositions = new ArrayList<>();

        while (!isOutOfRange(x, y)) {
            if (hasObstacle(x, y)) {
                int[] oldDirection = decrementDirection(x, y, direction);
                x = oldDirection[0];
                y = oldDirection[1];
                direction = rotate(direction);
            } else if (!alreadyBinHere(x, y, visitedPositions)){
                visitedPositions.add(new Integer[]{x, y});
                result++;
            }

            int[] newDirection = incrementDirection(x, y, direction);
            x = newDirection[0];
            y = newDirection[1];
        }

        return result;
    }
}
