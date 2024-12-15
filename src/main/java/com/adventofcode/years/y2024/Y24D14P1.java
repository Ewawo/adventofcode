package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D14P1 extends Day {
    
    final int WIDTH = 101;
    final int HEIGHT = 103;

    public Y24D14P1() {
        super(2024, 14, 1);
        String path = "y2024/day14.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {
        return validateQuadrants(mapRobots());
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

    private int validateQuadrants(List<Robot> robots) {
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;

        for (Robot r : robots) {
            r.move();
            if (r.x < WIDTH / 2 && r.y < (HEIGHT) / 2) {
                q1++;
            } else if (r.x > WIDTH / 2 && r.y > HEIGHT / 2) {
                q2++;
            } else if (r.x < WIDTH / 2 && r.y > HEIGHT / 2) {
                q3++;
            } else if (r.x > WIDTH / 2 && r.y < HEIGHT / 2) {
                q4++;
            }
        }
        return q1 * q2 * q3 * q4;
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
            this.x = Math.abs((this.x + this.vx * 100)) % WIDTH;
            this.y = Math.abs((this.y + this.vy * 100)) % HEIGHT;

            if (vy < 0 && y != 0) {
                this.y = Math.abs(this.y - HEIGHT);
            } if (vx < 0 && x != 0) {
                this.x = Math.abs(this.x - WIDTH);
            }
        }
    }
}
