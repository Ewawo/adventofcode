package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;

public class Y24D4 extends Day {

    ArrayList<ArrayList<Character>> input = new ArrayList<>();

    public Y24D4() {
        super(2024, 4);
        String path = "y2024/day4.txt";
        super.readInput(path);
    }
    @Override
    protected Integer part1() {
        int result = 0;


        for (String line : super.input) {
            var charArray = line.toCharArray();
            var newArray = new ArrayList<Character>();
            for (char c : charArray) {
                newArray.add(c);
            }
            this.input.add(newArray);
        }

        for (int y = 0; y < this.input.size(); y++) {
            for (int x = 0; x < this.input.get(y).size(); x++) {
                result += howManyXMAS(y, x);
            }
        }

        return result;
    }

    @Override
    protected Integer part2() {
        int result = 0;


        for (String line : super.input) {
            var charArray = line.toCharArray();
            var newArray = new ArrayList<Character>();
            for (char c : charArray) {
                newArray.add(c);
            }
            this.input.add(newArray);
        }

        for (int y = 0; y < this.input.size(); y++) {
            for (int x = 0; x < this.input.get(y).size(); x++) {
                result += howManyXMAS(y, x);
            }
        }

        return result;
    }

    private int howManyXMAS(int y, int x) {
        int result = 0;

        char start = this.input.get(y).get(x);

        if (start != 'X') {
            return result;
        }

        //N
        if (checkLetter(y-1, x, 'M') && checkLetter(y-2, x, 'A') && checkLetter(y-3, x , 'S')) {
            result++;
        }

        //NE
        if (checkLetter(y-1, x+1, 'M') && checkLetter(y-2, x+2, 'A') && checkLetter(y-3, x+3, 'S')) {
            result++;
        }

        //E
        if (checkLetter(y, x+1, 'M') && checkLetter(y, x+2, 'A') && checkLetter(y, x+3, 'S')) {
            result++;
        }

        //SE
        if (checkLetter(y+1, x+1, 'M') && checkLetter(y+2, x+2, 'A') && checkLetter(y+3, x+3, 'S')) {
            result++;
        }

        //S
        if (checkLetter(y+1, x, 'M') && checkLetter(y+2, x, 'A') && checkLetter(y+3, x, 'S')) {
            result++;
        }

        //SW
        if (checkLetter(y+1, x-1, 'M') && checkLetter(y+2, x-2, 'A') && checkLetter(y+3, x-3, 'S')) {
            result++;
        }

        //W
        if (checkLetter(y, x-1, 'M') && checkLetter(y, x-2, 'A') && checkLetter(y, x-3, 'S')) {
            result++;
        }

        //NW
        if (checkLetter(y-1, x-1, 'M') && checkLetter(y-2, x-2, 'A') && checkLetter(y-3, x-3, 'S')) {
            result++;
        }

        return result;
    }

    private boolean checkLetter(int y, int x, char letter) {
        if (y >= this.input.size() || y < 0) {
            return false;
        }

        if (x >= this.input.get(y).size() || x < 0) {
            return false;
        }

        return this.input.get(y).get(x) == letter;
    }
}
