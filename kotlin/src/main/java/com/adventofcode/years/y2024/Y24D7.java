package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.math.BigInteger;
import java.util.*;

public class Y24D7 extends Day {

    public Y24D7() {

        super(2024, 7);
        String path = "y2024/day7.txt";
        super.readInput(path);
    }

    @Override
    protected BigInteger part1() {
        Map<BigInteger, Deque<BigInteger>> calibrations = new HashMap<>();

        for (String line : input) {
            calibrations.put(new BigInteger(line.split(": ")[0]), parseArray(line.split(": ")[1].split(" ")));
        }

        BigInteger result = new BigInteger("0");

        for (BigInteger key : calibrations.keySet()) {
            if (numbersAddsUp(key, calibrations.get(key))) {
                result = result.add(key);
            }
        }

        return result;
    }

    @Override
    protected Long part2() {
        Map<Long, Deque<Long>> calibrations = new HashMap<>();

        for (String line : input) {
            calibrations.put(Long.parseLong(line.split(": ")[0]), parseArrayLong(line.split(": ")[1].split(" ")));
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

    private boolean numbersAddsUp(BigInteger number, Deque<BigInteger> queue) {
        if (queue.size() < 2 || number.signum() < 0) {
            return false;
        }

        BigInteger val1 = queue.removeLast();
        BigInteger val2 = queue.removeLast();
        BigInteger add = add(val1, val2);
        BigInteger multiply = multiply(val1, val2);

        if (test(number, add, new LinkedList<>(queue))) {
            return true;
        } else if (test(number, multiply, new LinkedList<>(queue))) {
            return true;
        }

        return false;
    }

    private boolean test(BigInteger goal, BigInteger current, Deque<BigInteger> queue) {
        if (Objects.equals(goal, current)) {
            return true;
        } else if (queue.isEmpty() || current.compareTo(goal) > 0) {
            return false;
        }

        BigInteger val1 = queue.removeLast();
        BigInteger add = add(val1, current);
        BigInteger multiply = multiply(val1, current);

        if (test(goal, add, new LinkedList<>(queue))) {
            return true;
        } else if (test(goal, multiply, new LinkedList<>(queue))) {
            return true;
        }
        return false;
    }

    private BigInteger add(BigInteger val1, BigInteger val2) {
        return val1.add(val2);
    }

    private BigInteger multiply(BigInteger val1, BigInteger val2) {
        return val1.multiply(val2);
    }

    private Deque<BigInteger> parseArray(String[] strings) {
        Deque<BigInteger> queue = new LinkedList<>() {
        };
        Arrays.stream(strings).forEach(i -> queue.addFirst(new BigInteger(i)));
        return queue;
    }

    private Deque<Long> parseArrayLong(String[] strings) {
        Deque<Long> queue = new LinkedList<>();
        Arrays.stream(strings).forEach(i -> queue.addFirst(Long.parseLong(i)));
        return queue;
    }
}
