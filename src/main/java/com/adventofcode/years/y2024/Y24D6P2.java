package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;

public class Y24D6P2 extends Day {
    private ArrayList<ArrayList<Position>> map = new ArrayList<>();

    public Y24D6P2() {
        super(2024, 6, 2);
        String path = "y2024/day6.txt";
        super.readInput(path);
    }

    @Override
    protected Integer logic() {
        int x = 0;
        int y = 0;

        for (int i = 0; i < input.size(); i++) {
            ArrayList<Position> row = new ArrayList<>();
            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                row.add(line.charAt(j) == '#' ? Position.OBSTACLE : Position.EMPTY);
                if (line.charAt(j) == '^') {
                    x = j;
                    y = i;
                }
            }
            map.add(row);
        }

        ArrayList<ArrayList<Integer>> takenSteps = fillMap(x, y);
        int result = 0;
        boolean first = true;
        for (ArrayList<Integer> step : takenSteps) {
            int stepX = step.get(0);
            int stepY = step.get(1);

            if (first) {
                first = false;
                continue;
            }

            map.get(stepY).set(stepX, Position.CORNER);
            if (doesItLoop(x, y, Direction.NORTH)) {
                result++;
            }
            map.get(stepY).set(stepX, Position.EMPTY);
        }
        return result;
    }

    private boolean doesItLoop(int x, int y, Direction direction) {
        boolean[] beenHere = new boolean[map.size() * map.get(y).size() * 4];

        while (!isOutOfRange(x, y)) {
            if (isOnCorner(x, y)) {
                direction = rotate(direction);
            } else if (hasObstacle(x, y)) {
                int[] oldDirection = decrementDirection(x, y, direction);
                x = oldDirection[0];
                y = oldDirection[1];
                direction = rotate(direction);
            }

            if (!beenHere[(map.size() * y + x) * mapDirection(direction)]) {
                beenHere[(map.get(y).size() * y + x) * mapDirection(direction)] = true;
            } else {
                return true;
            }

            int[] newDirection = incrementDirection(x, y, direction);
            x = newDirection[0];
            y = newDirection[1];
        }

        return false;
    }

    private boolean isOnCorner(int x, int y) {
        return map.get(y).get(x) == Position.CORNER;
    }

    private int mapDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> 1;
            case EAST -> 2;
            case SOUTH -> 3;
            case WEST -> 4;
            default -> 0;
        };
    }

    private boolean hasObstacle(int x, int y) {
        return map.get(y).get(x) == Position.OBSTACLE;
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

    private Direction rotate(Direction currentDirection) {
        return switch (currentDirection) {
            case NORTH -> Direction.EAST;
            case EAST -> Direction.SOUTH;
            case SOUTH -> Direction.WEST;
            case WEST -> Direction.NORTH;
            default -> currentDirection;
        };
    }

    private int[] incrementDirection(int x, int y, Direction direction) {
        return switch (direction) {
            case NORTH -> new int[]{x, y - 1};
            case EAST -> new int[]{x + 1, y};
            case SOUTH -> new int[]{x, y + 1};
            case WEST -> new int[]{x - 1, y};
            default -> new int[]{x, y};
        };
    }

    private int[] decrementDirection(int x, int y, Direction direction) {
        return switch (direction) {
            case NORTH -> new int[]{x, y + 1};
            case EAST -> new int[]{x - 1, y};
            case SOUTH -> new int[]{x, y - 1};
            case WEST -> new int[]{x + 1, y};
            default -> new int[]{x, y};
        };
    }

    private ArrayList<ArrayList<Integer>> fillMap(int x, int y) {

        Direction direction = Direction.NORTH;
        ArrayList<ArrayList<Integer>> walkedPath = new ArrayList<>();

        while (!isOutOfRange(x, y)) {
            if (hasObstacle(x, y)) {
                int[] oldDirection = decrementDirection(x, y, direction);
                x = oldDirection[0];
                y = oldDirection[1];
                direction = rotate(direction);
                this.map.get(y).set(x, Position.CORNER);
            }
            ArrayList<Integer> point = new ArrayList<>();
            point.add(x);
            point.add(y);
            walkedPath.add(point);

            int[] newDirection = incrementDirection(x, y, direction);
            x = newDirection[0];
            y = newDirection[1];
        }
        return walkedPath;
    }

    private enum Position{
        EMPTY,
        CORNER,
        OBSTACLE,
    }

    private enum Direction{
        NORTH,
        EAST,
        SOUTH,
        WEST
    }
}
