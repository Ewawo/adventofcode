package com.adventofcode.years;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class IDay {
    private int year;
    private int day;
    private int part;
    protected List<String> input;

    public IDay(int year, int day, int part) {
        this.year = year;
        this.day = day;
        this.part = part;
        this.input = new ArrayList<>();
    }

    protected void readInput(String input) {
        try {
            // Use ClassLoader to find the file in the classpath
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("puzzleinput/" + input);

            // Check if the file exists
            if (inputStream == null) {
                System.err.println("Error: File not found in resources: puzzleinput/" + input);
            }

            // Read all lines from the input stream
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                this.input = reader.lines().collect(Collectors.toList());
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., I/O errors)
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void solve() {
        System.out.println("year " + year + ", day " + day + ", part " + part + ", answer: " + logic());
    }

    protected abstract String logic();
}