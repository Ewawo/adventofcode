package com.adventofcode.years;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Day {
    private final int year;
    private final int day;
    protected List<String> input;

    protected Day(int year, int day) {
        this.year = year;
        this.day = day;
        this.input = new ArrayList<>();
    }

    protected void readInput(String input) {
        try {
            // Use ClassLoader to find the file in the classpath
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("puzzleinput/" + input);

            // Check if the file exists
            if (inputStream == null) {
                System.out.println("Error: File not found in resources: puzzleinput/ " + input);
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                this.input = reader.lines().collect(Collectors.toList());
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., I/O errors)
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void solve() {
        System.out.println("year " + year + ", day " + day + ", part 1, answer: " + part1());
        System.out.println("year " + year + ", day " + day + ", part 2, answer: " + part2());
    }

    public int getYear() {
        return year;
    }

    protected abstract Object part1();

    protected abstract Object part2();
}