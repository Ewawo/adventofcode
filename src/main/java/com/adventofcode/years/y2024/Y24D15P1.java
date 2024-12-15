package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D15P1 extends Day {

    private List<List<Loc>> map = new ArrayList<>();
    private List<Dir> directions = new ArrayList<>();
    private int robotCol = -1;
    private int robotRow = -1;

    public Y24D15P1() {
        super(2024, 15, 1);
        String path = "y2024/day15.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {
        mapInput();
        walk();
        return getGPSCoordinates();
    }

    private int getGPSCoordinates() {
        int result = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == Loc.BOX) {
                    result += 100 * i + j;
                }
            }
        }
        return result;
    }

    private void walk() {
        for (Dir dir : directions) {
            move(dir);
        }
    }

    private void move(Dir dir) {
        move(dir, robotRow, robotCol);
    }

    private boolean move(Dir dir, int row, int col) {
        if (row < 0 || row >= map.size() || col < 0 || col >= map.get(row).size()) {
            return false;
        }
        if (map.get(row).get(col) == Loc.WALL) {
            return false;
        } else if (map.get(row).get(col) == Loc.EMPTY) {
            return true;
        }

        // up
        if (dir == Dir.UP) {
            return move(dir, row, col, row - 1, col);
        } else if (dir == Dir.DOWN) {
            return move(dir, row, col, row + 1, col);
        } else if (dir == Dir.LEFT) {
            return move(dir, row, col, row, col - 1);
        } else if (dir == Dir.RIGHT) {
            return move(dir, row, col, row, col + 1);
        } else {
            return false;
        }
    }

    private boolean move(Dir dir, int row, int col, int toRow, int toCol) {
        if (move(dir, toRow, toCol)) {
            swap(row, col, toRow, toCol);
            return true;
        }
        return false;
    }

    private void swap(int fromRow, int fromCol, int toRow, int toCol) {
        setRobot(fromRow, fromCol, toRow, toCol);
        Loc fromLoc = map.get(fromRow).get(fromCol);
        Loc toLoc = map.get(toRow).get(toCol);
        map.get(fromRow).set(fromCol, toLoc);
        map.get(toRow).set(toCol, fromLoc);
    }

    private void setRobot(int row, int col, int toRow, int toCol) {
        if (map.get(row).get(col) == Loc.ROBOT) {
            robotCol = toCol;
            robotRow = toRow;
        }
    }

    private void mapInput() {
        boolean directions = false;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.isEmpty()) {
                directions = true;
            }
            if (directions) {
                for (char c : line.toCharArray()) {
                    this.directions.add(addDirection(c));
                }
            } else {
                List<Loc> locs = new ArrayList<>();
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    locs.add(addLocation(c, i, j));
                }
                this.map.add(locs);
            }
        }
    }

    private Loc addLocation(char c, int row, int col) {
        if (c == '@') {
            this.robotCol = col;
            this.robotRow = row;
        }
        return switch (c) {
            case '#' -> Loc.WALL;
            case '.' -> Loc.EMPTY;
            case '@' -> Loc.ROBOT;
            case 'O' -> Loc.BOX;
            default -> null;
        };
    }

    private Dir addDirection(char c) {
        return switch (c) {
            case '<' -> Dir.LEFT;
            case '>' -> Dir.RIGHT;
            case '^' -> Dir.UP;
            case 'v' -> Dir.DOWN;
            default -> null;
        };
    }

    private enum Loc {
        WALL,
        BOX,
        ROBOT,
        EMPTY
    }

    private enum Dir {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}
