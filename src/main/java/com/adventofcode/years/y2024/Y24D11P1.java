package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D11P1 extends Day {

    private List<Long> stones = new ArrayList<>();

    public Y24D11P1() {
        super(2024, 11, 1);
        String path = "y2024/day11.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {
        getStonesInLine();
        blink(25);
        return stones.size();
    }

    private void blink(long times) {
        for (int i = 0; i < times; i++) {
            List<Long> stonesMock = new ArrayList<>(stones);
            stones.clear();

            for (int j = 0; j < stonesMock.size(); j++) {
                evolveStone(stonesMock.get(j), j);
            }
        }
    }

    private void evolveStone(long stone, int index) {
        if (stone == 0) {
            stones.add(1L);
        } else if (Long.toString(stone).length() % 2 == 0) {
            int initialStoneLength = Long.toString(stone).length();
            String leftStone = Long.toString(stone).substring(0, initialStoneLength / 2);
            String rightStone = Long.toString(stone).substring(initialStoneLength / 2);
            stones.add(Long.parseLong(leftStone));
            stones.add(Long.parseLong(rightStone));
        } else {
            stones.add(stone * 2024); // Add transformed stone
        }
    }

    private void getStonesInLine() {
        String line = input.get(0);
        for (String stone : line.split(" ")) {
            stones.add(Long.parseLong(stone));
        }
    }
}
