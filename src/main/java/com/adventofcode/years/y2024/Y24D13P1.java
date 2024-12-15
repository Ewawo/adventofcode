package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D13P1 extends Day {

    public Y24D13P1() {
        super(2024, 13, 1);
        String path = "y2024/day13.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {
        return mapInput().stream().mapToInt(ClawMachine::calculate).sum();
    }

    private List<ClawMachine> mapInput() {
        List<ClawMachine> claws = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 4) {
            String line1 = input.get(i);
            String line2 = input.get(i+1);
            String line3 = input.get(i+2);

            ClawMachine clawMachine = new ClawMachine();

            clawMachine.aX = Integer.parseInt(line1.split(" ")[2].split("\\+")[1].split(",")[0]);
            clawMachine.aY = Integer.parseInt(line1.split(" ")[3].split("\\+")[1]);
            clawMachine.bX = Integer.parseInt(line2.split(" ")[2].split("\\+")[1].split(",")[0]);
            clawMachine.bY = Integer.parseInt(line2.split(" ")[3].split("\\+")[1]);
            clawMachine.pX = Integer.parseInt(line3.split(" ")[1].split("=")[1].split(",")[0]);
            clawMachine.pY = Integer.parseInt(line3.split(" ")[2].split("=")[1]);
            claws.add(clawMachine);
        }
        return claws;
    }

    private class ClawMachine {
        public int aX;
        public int bX;
        public int aY;
        public int bY;
        public int pX;
        public int pY;

        public int calculate() {
            float a = (pX * bY - bX * pY) / (float) (aX * bY - bX * aY);
            float b = (pY - aY * a) / bY;

            if (a > 100 || b > 100 || a < 0 || b < 0 || a % 1 != 0 || b % 1 != 0) {
                return 0;
            }
            return (int) (a * 3 + b);
        }
    }
}
