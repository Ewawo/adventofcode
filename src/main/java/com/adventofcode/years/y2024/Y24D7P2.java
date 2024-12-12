package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;

public class Y24D7P2 extends Day {

    public Y24D7P2() {

        super(2024, 7, 2);
        String path = "y2024/day7.txt";
        super.readInput(path);
    }

    @Override
    protected Long logic() {
        Map<Long, Deque<Long>> calibrations = new HashMap<>();

        for (String line : input) {
            calibrations.put(Long.parseLong(line.split(": ")[0]), parseArray(line.split(": ")[1].split(" ")));
        }

        return calibrations.keySet().parallelStream()
                .filter(key -> numbersAddsUp(key, calibrations.get(key)))
                .mapToLong(Long::longValue)
                .sum();
    }

    private boolean numbersAddsUp(Long number, Deque<Long> queue) {
        if (queue.size() < 2 || number < 0) {
            return false;
        }

        long val1 = queue.removeLast();
        long val2 = queue.removeLast();
        long add = add(val1, val2);
        long multiply = multiply(val1, val2);
        long concat = concat(val1, val2);

        if (test(number, add, new LinkedList<>(queue))) {
            return true;
        } else if (test(number, multiply, new LinkedList<>(queue))) {
            return true;
        } else if (test(number, concat, new LinkedList<>(queue))) {
            return true;
        }

        return false;
    }

    private boolean test(long goal, long current, Deque<Long> queue) {
        if (Objects.equals(goal, current)) {
            return true;
        } else if (queue.isEmpty() || current > goal) {
            return false;
        }

        long val1 = queue.removeLast();
        long add = add(val1, current);
        long multiply = multiply(val1, current);
        long concat = concat(current, val1);

        if (test(goal, add, new LinkedList<>(queue))) {
            return true;
        } else if (test(goal, multiply, new LinkedList<>(queue))) {
            return true;
        } else if (test(goal, concat, new LinkedList<>(queue))) {
            return true;
        }
        return false;
    }

    private long add(long val1, long val2) {
        return val1 + val2;
    }

    private long multiply(long val1, long val2) {
        return val1 * val2;
    }

    private long concat(long val1, long val2) {
        return Long.parseLong(val1 + Long.toString(val2));
    }

    private Deque<Long> parseArray(String[] strings) {
        Deque<Long> queue = new LinkedList<>();
        Arrays.stream(strings).forEach(i -> queue.addFirst(Long.parseLong(i)));
        return queue;
    }
}
