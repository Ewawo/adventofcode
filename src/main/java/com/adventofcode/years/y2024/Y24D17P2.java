package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;
import java.util.function.Consumer;

public class Y24D17P2 extends Day {
    Map<Integer, Consumer<Integer>> instructions = new HashMap<>();

    public Y24D17P2() {
        super(2024, 17, 2);
        String path = "y2024/day17.txt";
        super.readInput(path);
    }

    @Override
    protected Long logic() {
        return runProgram();
    }

    private long runProgram() {
        List<Integer> numbers = Arrays.stream(input.get(4)
                        .split(" ")[1] // Splitting by space and taking the second part
                        .split(","))   // Splitting the result by commas
                .mapToInt(Integer::parseInt) // Parsing each part as an integer
                .boxed()      // Converting the IntStream to Stream<Integer>
                .toList();
        return find(numbers, 0);
    }

    private Long find(List<Integer> program, long answer) {
        if (program.isEmpty()) {
            return answer;
        }
        for (int i = 0; i < 8; i++) {
            long a = answer << 3 | i;
            long b = a % 8;
            b = b ^ 1;
            long c = a >> b;
            b = b ^ 5;
            b = b ^ c;
            if (b % 8 == program.get(program.size() - 1)) {
                Long sub = find(program.subList(0, program.size() - 1), a);
                if (sub != null) {
                    return sub;
                }
            }
        }
        return null;
    }
}
