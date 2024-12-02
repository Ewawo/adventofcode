package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.Arrays;

public class Y24D2P1 extends Day {

    public Y24D2P1() {
        super(2024, 2, 1);
        String path = "y2024/day2.txt";
        super.readInput(path);
    }

    @Override
    protected int logic() {
        int result = 0;

        for (String line : input) {
            ArrayList<Integer> report = new ArrayList<>();
            Arrays.stream(line.split(" ")).forEach(number -> report.add(Integer.parseInt(number)));

            boolean inclining = report.get(0) < report.get(report.size() - 1);

            result++;

            for (int i = 0; i < report.size() - 1; i++) {
                if (!(inclining && report.get(i) < report.get(i + 1) && report.get(i) + 3 >= report.get(i + 1) ||
                        !inclining && report.get(i) > report.get(i + 1) && report.get(i) - 3 <= report.get(i + 1))) {
                    result--;
                    break;
                }
            }
        }

        return result;
    }
}
