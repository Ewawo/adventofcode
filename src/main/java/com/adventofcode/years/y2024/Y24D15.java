package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D15 extends Day {

    private List<List<Loc>> map = new ArrayList<>();
    private List<Dir> directions = new ArrayList<>();
    private int robotCol = -1;
    private int robotRow = -1;

    private List<List<Loc2>> map2 = new ArrayList<>();
    private List<Dir2> directions2 = new ArrayList<>();
    private int robotCol2 = -1;
    private int robotRow2 = -1;

    public Y24D15() {
        super(2024, 15);
        String path = "y2024/day15.txt";
        super.readInput(path);
    }

    @Override
    protected Number part1() {
        mapInput();
        walk();
        return getGPSCoordinates();
    }


    @Override
    protected Number part2() {
        mapInput2();
        walk2();
        return getGPSCoordinates2();
    }

    private int getGPSCoordinates2() {
        int result = 0;
        for (int i = 0; i < map2.size(); i++) {
            for (int j = 0; j < map2.get(i).size(); j++) {
                if (map2.get(i).get(j) == Loc2.BOX_LEFT) {
                    result += 100 * i + j;
                    j++;
                }
            }
        }
        return result;
    }

    private void walk2() {
        for (Dir2 dir : directions2) {
            move2(dir);
        }
    }

    private void move2(Dir2 dir) {
        if (move2(dir, robotRow2, robotCol2, false)) {
            swap2(dir, robotRow2, robotCol2, false);
        }
    }

    private boolean move2(Dir2 dir, int row, int col, boolean swapped) {
        while(row >= 0 && row < map2.size() && col >= 0 && col < map2.get(row).size()) {
            if (map2.get(row).get(col) == Loc2.EMPTY) {
                return true;
            } else if (map2.get(row).get(col) == Loc2.WALL) {
                return false;
            }


            // check other box side
            boolean isOk = true;
            if ((dir == Dir2.DOWN || dir == Dir2.UP) && !swapped) {
                if (map2.get(row).get(col) == Loc2.BOX_LEFT) {
                    isOk = move2(dir, row, col + 1, true);
                } else if (map2.get(row).get(col) == Loc2.BOX_RIGHT) {
                    isOk = move2(dir, row, col - 1, true);
                }
            }
            swapped = false;
            if (!isOk) {
                return false;
            }

            // up
            if (dir == Dir2.UP) {
                row--;
            } else if (dir == Dir2.DOWN) {
                row++;
            } else if (dir == Dir2.LEFT) {
                col--;
            } else if (dir == Dir2.RIGHT) {
                col++;
            }
        }
        return false;
    }

    private void swap2(Dir2 dir, int row, int col, boolean swapped) {
        if (map2.get(row).get(col) == Loc2.EMPTY) {
            return;
        }

        switch (dir) {
            case LEFT:
                swap2(dir, row, col - 1, false);
                swap2(row, col, row, col - 1);
                break;
            case RIGHT:
                swap2(dir, row, col + 1, false);
                swap2(row, col, row, col + 1);
                break;
            case UP:
                if (map2.get(row).get(col) == Loc2.BOX_LEFT && !swapped) {
                    swap2(dir, row, col + 1, true);
                } else if (map2.get(row).get(col) == Loc2.BOX_RIGHT && !swapped) {
                    swap2(dir, row, col - 1, true);
                }
                swap2(dir, row - 1, col, false);
                swap2(row, col, row - 1, col);
                break;
            case DOWN:
                if (map2.get(row).get(col) == Loc2.BOX_LEFT && !swapped) {
                    swap2(dir, row, col + 1, true);
                } else if (map2.get(row).get(col) == Loc2.BOX_RIGHT && !swapped) {
                    swap2(dir, row, col - 1, true);
                }
                swap2(dir, row + 1, col, false);
                swap2(row, col, row + 1, col);
        }
    }

    private void swap2(int fromRow, int fromCol, int toRow, int toCol) {
        setRobot2(fromRow, fromCol, toRow, toCol);
        Loc2 fromLoc = map2.get(fromRow).get(fromCol);
        Loc2 toLoc = map2.get(toRow).get(toCol);
        map2.get(fromRow).set(fromCol, toLoc);
        map2.get(toRow).set(toCol, fromLoc);
    }

    private void setRobot2(int row, int col, int toRow, int toCol) {
        if (map2.get(row).get(col) == Loc2.ROBOT) {
            robotCol2 = toCol;
            robotRow2 = toRow;
        }
    }

    private void mapInput2() {
        boolean directions = false;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.isEmpty()) {
                directions = true;
            }
            if (directions) {
                for (char c : line.toCharArray()) {
                    this.directions2.add(addDirection2(c));
                }
            } else {
                List<Loc2> locs = new ArrayList<>();
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    addLocation2(c, i, j, locs);
                }
                this.map2.add(locs);
            }
        }
    }

    private void addLocation2(char c, int row, int col, List<Loc2> arr) {
        if (c == '@') {
            this.robotCol2 = col * 2;
            this.robotRow2 = row;
        }
        switch (c) {
            case '@':
                arr.add(Loc2.ROBOT);
                arr.add(Loc2.EMPTY);
                break;
            case '.':
                arr.add(Loc2.EMPTY);
                arr.add(Loc2.EMPTY);
                break;
            case '#':
                arr.add(Loc2.WALL);
                arr.add(Loc2.WALL);
                break;
            case 'O':
                arr.add(Loc2.BOX_LEFT);
                arr.add(Loc2.BOX_RIGHT);
                break;
        }
    }

    private Dir2 addDirection2(char c) {
        return switch (c) {
            case '<' -> Dir2.LEFT;
            case '>' -> Dir2.RIGHT;
            case '^' -> Dir2.UP;
            case 'v' -> Dir2.DOWN;
            default -> null;
        };
    }

    private enum Loc2 {
        WALL,
        BOX_LEFT,
        BOX_RIGHT,
        ROBOT,
        EMPTY
    }

    private enum Dir2 {
        UP,
        DOWN,
        LEFT,
        RIGHT
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
