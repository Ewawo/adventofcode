package com.adventofcode.years.y2024;

import com.adventofcode.App;
import com.adventofcode.years.Day;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Y24D7P1 extends Day {

    public Y24D7P1() {

        super(2024, 7, 1);
        String path = "y2024/day7.txt";
        super.readInput(path);
    }

    @Override
    protected BigInteger logic() {
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
}
