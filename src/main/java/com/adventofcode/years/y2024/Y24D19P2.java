package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;

public class Y24D19P2 extends Day {

    String[] towelParts;
    List<String> towels = new ArrayList<>();
    Map<String, Long> goodCombos = new HashMap<>();

    public Y24D19P2() {
        super(2024, 19, 2);
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

    private long getValidTowelCount() {
        long result = 0;

        for (String towel : towels) {
            result += getTowel(towel);
        }

        return result;
    }

    private long getTowel(String towel) {
        if (towel.isEmpty()) {
            return 1;
        }

        long result = 0;

        if (goodCombos.containsKey(towel)) {
            return goodCombos.get(towel);
        }

        for (String towelPart : towelParts) {
            if (towel.startsWith(towelPart)) {
                result += getTowel(towel.substring(towelPart.length()));
            }
        }

        goodCombos.put(towel, result);

        return result;
    }
}
