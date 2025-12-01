package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Y24D1 extends Day {

    public Y24D1() {
        super(2024, 1);
        String path = "y2024/day1.txt";
        super.readInput(path);
    }

    @Override
    protected Integer part1() {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (String line : input) {
            String[] split = line.split(" {3}");
            left.add(Integer.parseInt(split[0]));
            right.add(Integer.parseInt(split[1]));
        }

        left = left.stream().sorted().toList();
        right = right.stream().sorted().toList();

        int result = 0;

        for (int i = 0; i < left.size(); i++) {
            result += Math.abs(left.get(i) - right.get(i));
        }


        return result;
    }

    @Override
    protected Object part2() {
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
