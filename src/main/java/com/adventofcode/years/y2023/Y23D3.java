package com.adventofcode.years.y2023;

import com.adventofcode.years.Day;

public class Y23D3 extends Day {

    public Y23D3() {
        super(2023, 3);
        String path = "y2023/day2.txt";
        super.readInput(path);
    }

    @Override
    protected Integer part1() {

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

        return result;
    }

    @Override
    protected Integer part2() { return null; }
}