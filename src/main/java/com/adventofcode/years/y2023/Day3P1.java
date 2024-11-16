package com.adventofcode.years.y2023;

import com.adventofcode.years.IDay;

public class Day3P1 extends IDay {

    public Day3P1() {
        super(2023, 2, 1);
        String path = "y2023/day2.txt";
        super.readInput(path);
    }

    @Override
    protected String logic() {

        int result = 0;

        for (String line : input) {
            int gameId = Integer.parseInt(line.split(":")[0].split(" ")[1]);
            boolean validGame = true;

            String[] games = line.split(":")[1].split(";");

            for (String game : games) {

                String[] cubes = game.split(",");
                for (String cube : cubes) {
                    String color = cube.split(" ")[2];
                    int cubeNumber = Integer.parseInt(cube.split(" ")[1]);
                    if ("green".equals(color) && cubeNumber > 13) {
                        validGame = false;
                    }
                    else if ("blue".equals(color) && cubeNumber > 14) {
                        validGame = false;
                    }
                    else if ("red".equals(color) && cubeNumber > 12) {
                        validGame = false;
                    }
                }
            }

            if (validGame) {
                result += gameId;
            }
        }

        return result + "";
    }
}