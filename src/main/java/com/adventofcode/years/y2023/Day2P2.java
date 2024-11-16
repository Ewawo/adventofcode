package com.adventofcode.years.y2023;

import com.adventofcode.years.IDay;

public class Day2P2 extends IDay {

    public Day2P2() {
        super(2023, 2, 2);
        String path = "y2023/day2.txt";
        super.readInput(path);
    }

    @Override
    protected String logic() {
        int result = 0;

        for (String game : input) {

            String[] rounds = game.split(":")[1].split(";");

            int blue = 0;
            int red = 0;
            int green = 0;

            for (String round : rounds) {

                String[] cubes = round.split(",");

                for (String cube : cubes) {
                    String color = cube.split(" ")[2];
                    int cubeNumber = Integer.parseInt(cube.split(" ")[1]);
                    if ("green".equals(color)) {
                        green = Math.max(green, cubeNumber);
                    }
                    else if ("blue".equals(color)) {
                        blue = Math.max(blue, cubeNumber);
                    }
                    else if ("red".equals(color)) {
                        red = Math.max(red, cubeNumber);
                    }
                }
            }

            result += blue * red * green;
        }
        return result + "";
    }
}