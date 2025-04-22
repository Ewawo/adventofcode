package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Y24D19 extends Day {

    String[] towelParts;
    List<String> towels = new ArrayList<>();

    String[] towelParts2;
    List<String> towels2 = new ArrayList<>();
    Map<String, Long> goodCombos2 = new HashMap<>();

    public Y24D19() {
        super(2024, 19);
        String path = "y2024/day19.txt";
        super.readInput(path);
    }

    @Override
    protected Object part1() {
        mapInput();
        return getValidTowelCount();
    }

    @Override
    protected Object part2() {
        mapInput2();
        return getValidTowelCount2();
    }

    private void mapInput2() {
        towelParts2 = input.get(0).split(", ");

        for (int i = 2; i < input.size(); i++) {
            towels2.add(input.get(i));
        }
    }

    private long getValidTowelCount2() {
        long result = 0;

        for (String towel : towels2) {
            result += getTowel2(towel);
        }

        return result;
    }

    private long getTowel2(String towel) {
        if (towel.isEmpty()) {
            return 1;
        }

        long result = 0;

        if (goodCombos2.containsKey(towel)) {
            return goodCombos2.get(towel);
        }

        for (String towelPart : towelParts2) {
            if (towel.startsWith(towelPart)) {
                result += getTowel2(towel.substring(towelPart.length()));
            }
        }

        goodCombos2.put(towel, result);

        return result;
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
