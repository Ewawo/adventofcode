package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Y24D2P2 extends Day {

    public Y24D2P2() {
        super(2024, 2, 2);
        String path = "y2024/day2.txt";
        super.readInput(path);
    }

    @Override
    protected int logic() {
        int result = 0;

        for (String line : input) {
            ArrayList<Integer> report = new ArrayList<>();
            Arrays.stream(line.split(" ")).forEach(number -> report.add(Integer.parseInt(number)));

            boolean inclining = doesIncline(report.get(0), report.get(1), report.get(2), report.get(3));

            int skippedIndex = -1;

            result++;

            for (int i = 0; i < report.size() - 1; i++) {
                if (skippedIndex == i) {
                    continue;
                }

                if (!isValid(report.get(i), report.get(i + 1), inclining)) {
                    if (skippedIndex != -1) {
                        result--;
                        break;
                    }

                    if (i != report.size() - 2) {
                        if (isValid(report.get(i), report.get(i + 2), inclining)) {
                            skippedIndex = i + 1;
                        } else if (isValid(report.get(i + 1), report.get(i + 2), inclining) && i == 0) {
                            skippedIndex = i;
                        } else {
                            result--;
                            break;
                        }
                    }
                }

            }
        }
        return result;
    }

    private boolean isValid(int curVal, int nextVal, boolean inclining) {
        return inclining && curVal < nextVal && curVal + 3 >= nextVal || !inclining && curVal > nextVal && curVal - 3 <= nextVal;
    }

    private boolean doesIncline(int val1, int val2, int val3, int val4) {
        int result = 0;
       result += val1 < val2 ? 1 : -1;
       result += val2 < val3 ? 1 : -1;
       result += val3 < val4 ? 1 : -1;
       return result > 0;
    }
}
