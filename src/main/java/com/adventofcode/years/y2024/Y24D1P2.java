package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Y24D1P2 extends Day {

    public Y24D1P2() {
        super(2024, 1, 1);
        String path = "y2024/day1.txt";
        super.readInput(path);
    }

    @Override
    protected int logic() {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (String line : input) {
            String[] split = line.split(" {3}");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }


        int result = 0;

        for (int number : left) {
            int occurences = Collections.frequency(right, number);
            result += number * occurences;
        }

        return result;
    }
}