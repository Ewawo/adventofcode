package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;

public class Y24D4P2 extends Day {

    ArrayList<ArrayList<Character>> input = new ArrayList<>();

    public Y24D4P2() {
        super(2024, 4, 2);
        String path = "y2024/day4.txt";
        super.readInput(path);
    }
    @Override
    protected Integer logic() {
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

        if (start != 'A') {
            return result;
        }

        //lt rb
        if (checkLetter(y-1, x-1, 'M') && checkLetter(y+1, x+1, 'S')) {
            result++;
        }

        //rt lb
        if (checkLetter(y-1, x+1, 'M') && checkLetter(y+1, x-1, 'S')) {
            result++;
        }

        //lb rt
        if (checkLetter(y+1, x-1, 'M') && checkLetter(y-1, x+1, 'S')) {
            result++;
        }

        // rb lt
        if (checkLetter(y+1, x+1, 'M') && checkLetter(y-1, x-1, 'S')) {
            result++;
        }

        return result / 2;
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
