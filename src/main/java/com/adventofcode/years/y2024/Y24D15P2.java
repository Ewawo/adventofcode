package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D15P2 extends Day {

    private List<List<Loc>> map = new ArrayList<>();
    private List<Dir> directions = new ArrayList<>();
    private int robotCol = -1;
    private int robotRow = -1;

    public Y24D15P2() {
        super(2024, 15, 2);
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
                if (map.get(i).get(j) == Loc.BOX_LEFT) {
                    result += 100 * i + j;
                    j++;
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
        if (move(dir, robotRow, robotCol, false)) {
            swap(dir, robotRow, robotCol, false);
        }
    }

    private boolean move(Dir dir, int row, int col, boolean swapped) {
        while(row >= 0 && row < map.size() && col >= 0 && col < map.get(row).size()) {
            if (map.get(row).get(col) == Loc.EMPTY) {
                return true;
            } else if (map.get(row).get(col) == Loc.WALL) {
                return false;
            }


            // check other box side
            boolean isOk = true;
            if ((dir == Dir.DOWN || dir == Dir.UP) && !swapped) {
                if (map.get(row).get(col) == Loc.BOX_LEFT) {
                    isOk = move(dir, row, col + 1, true);
                } else if (map.get(row).get(col) == Loc.BOX_RIGHT) {
                    isOk = move(dir, row, col - 1, true);
                }
            }
            swapped = false;
            if (!isOk) {
                return false;
            }

            // up
            if (dir == Dir.UP) {
                row--;
            } else if (dir == Dir.DOWN) {
                row++;
            } else if (dir == Dir.LEFT) {
                col--;
            } else if (dir == Dir.RIGHT) {
                col++;
            }
        }
        return false;
    }

    private void swap(Dir dir, int row, int col, boolean swapped) {
        if (map.get(row).get(col) == Loc.EMPTY) {
            return;
        }

        switch (dir) {
            case LEFT:
                swap(dir, row, col - 1, false);
                swap(row, col, row, col - 1);
                break;
            case RIGHT:
                swap(dir, row, col + 1, false);
                swap(row, col, row, col + 1);
                break;
            case UP:
                if (map.get(row).get(col) == Loc.BOX_LEFT && !swapped) {
                    swap(dir, row, col + 1, true);
                } else if (map.get(row).get(col) == Loc.BOX_RIGHT && !swapped) {
                    swap(dir, row, col - 1, true);
                }
                swap(dir, row - 1, col, false);
                swap(row, col, row - 1, col);
                break;
            case DOWN:
                if (map.get(row).get(col) == Loc.BOX_LEFT && !swapped) {
                    swap(dir, row, col + 1, true);
                } else if (map.get(row).get(col) == Loc.BOX_RIGHT && !swapped) {
                    swap(dir, row, col - 1, true);
                }
                swap(dir, row + 1, col, false);
                swap(row, col, row + 1, col);
        }
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
                    addLocation(c, i, j, locs);
                }
                this.map.add(locs);
            }
        }
    }

    private void addLocation(char c, int row, int col, List<Loc> arr) {
        if (c == '@') {
            this.robotCol = col * 2;
            this.robotRow = row;
        }
        switch (c) {
            case '@':
                arr.add(Loc.ROBOT);
                arr.add(Loc.EMPTY);
                break;
            case '.':
                arr.add(Loc.EMPTY);
                arr.add(Loc.EMPTY);
                break;
            case '#':
                arr.add(Loc.WALL);
                arr.add(Loc.WALL);
                break;
            case 'O':
                arr.add(Loc.BOX_LEFT);
                arr.add(Loc.BOX_RIGHT);
                break;
        }
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
        BOX_LEFT,
        BOX_RIGHT,
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
