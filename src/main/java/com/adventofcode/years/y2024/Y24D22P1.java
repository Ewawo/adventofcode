package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

public class Y24D22P1 extends Day {

    public Y24D22P1() {
        super(2024, 22, 1);
        String path = "y2024/day22.txt";
        super.readInput(path);
    }

    @Override
    protected Object logic() {
        long result = 0;
        for (String line : input) {
            result += calculateSecret(Long.parseLong(line), 2000);
        }
        return result;
    }

    private long calculateSecret(long secret, int times) {
        for (int i = 0; i < times; i++) {
            secret = calculateSecret(secret);
        }
        return secret;
    }

    private long calculateSecret(long secret) {
        long s = secret * 64L;
        secret = (s ^ secret) % 16777216;
        s = secret / 32;
        secret = (s ^ secret) % 16777216;
        s = secret * 2048;
        return (s ^ secret) % 16777216;
    }
}
