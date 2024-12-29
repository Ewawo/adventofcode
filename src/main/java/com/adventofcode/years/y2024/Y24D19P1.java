package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D19P1 extends Day {

    String[] towelParts;
    List<String> towels = new ArrayList<>();

    public Y24D19P1() {
        super(2024, 19, 1);
        String path = "y2024/day19.txt";
        super.readInput(path);
    }

    @Override
    protected Object logic() {
        mapInput();
        return getValidTowelCount();
    }

    private void mapInput() {
        towelParts = input.get(0).split(", ");

        for (int i = 2; i < input.size(); i++) {
            towels.add(input.get(i));
        }
    }

    private int getValidTowelCount() {
        int result = 0;

        for (String towel : towels) {
            if (getTowel(towel)) result++;
        }

        return result;
    }

    private boolean getTowel(String towel) {
        if (towel.isEmpty()) {
            return true;
        }
        for (String towelPart : towelParts) {
            if (towelPart.length() > towel.length()) {
                continue;
            }
            if (towel.startsWith(towelPart)) {
                var x = getTowel(towel.substring(towelPart.length()));
                if (x) {
                    return true;
                }
            }
        }

        return false;
    }
}
