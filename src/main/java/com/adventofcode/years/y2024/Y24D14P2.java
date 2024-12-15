package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Y24D14P2 extends Day {

    final int WIDTH = 101;
    final int HEIGHT = 103;
    int count = 0;

    public Y24D14P2() {
        super(2024, 14, 2);
        String path = "y2024/day14.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {
        List<Robot> robots = mapRobots();
        count++;
        validateQuadrants(robots);
        return 6446;
    }

    private List<Robot> mapRobots() {
        List<Robot> robots = new ArrayList<>();
        for (String line : input) {
            int x = Integer.parseInt(line.split(" ")[0].split("=")[1].split(",")[0]);
            int y = Integer.parseInt(line.split(" ")[0].split("=")[1].split(",")[1]);
            int vx = Integer.parseInt(line.split(" ")[1].split("=")[1].split(",")[0]);
            int vy = Integer.parseInt(line.split(" ")[1].split("=")[1].split(",")[1]);
            Robot robot = new Robot(x, y, vx, vy);
            robots.add(robot);
        }
        return robots;
    }

    private void validateQuadrants(List<Robot> robots) {
        int[][] map = new int[HEIGHT][WIDTH];

        for (Robot r : robots) {
            r.move();
            map[r.y][r.x]++;
        }

//        for (int i = 0; i < HEIGHT; i++) {
//            for (int j = 0; j < WIDTH; j++) {
//                System.out.print(map[i][j] == 0 ? "." : "#");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    private class Robot {
        public int x;
        public int y;
        public int vx;
        public int vy;

        public Robot(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }

        public void move() {
            this.x = Math.floorMod(this.x + this.vx * 6446, WIDTH);
            this.y = Math.floorMod(this.y + this.vy * 6446, HEIGHT);
        }
    }
}
