package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.HashMap;
import java.util.Map;

public class Y24D22 extends Day {

    public Y24D22() {
        super(2024, 22);
        String path = "y2024/day22.txt";
        super.readInput(path);
    }

    @Override
    protected Object part1() {
        long result = 0;
        for (String line : input) {
            result += calculateSecret(Long.parseLong(line), 2000);
        }
        return result;
    }

    @Override
    protected Object part2() {
        Map<Integer, Long> map = new HashMap<>();

        for (String line : input) {
            Map<Integer, Long> temp = new HashMap<>();
            calculateSecret2(Long.parseLong(line), 2000, temp);
            mergeMaps2(map, temp);
        }

        long max = 0L;
        for (int key : map.keySet()) {
            if (map.get(key) > max) {
                max = map.get(key);
            }
        }
        return max;
    }

    private void mergeMaps2(Map<Integer, Long> map1, Map<Integer, Long> map2) {
        for (int key : map2.keySet()) {
            map1.merge(key, map2.get(key), Long::sum);
        }
    }

    private void calculateSecret2(long secret, int times, Map<Integer, Long> map) {
        long[] diff = new long[4];
        long[] act = new long[4];
        int p = 0;
        for (int i = 0; i < times; i++) {
            diff[i % 4] = secret % 10 - act[Math.abs(i - 1) % 4];
            act[i % 4] = secret % 10;

            int key = formatKey2(diff[Math.abs(p - 3) % 4], diff[Math.abs(p - 2) % 4], diff[Math.abs(p - 1) % 4], diff[p % 4]);
            if (map.containsKey(key)) {
                continue;
            }

            map.put(key, secret % 10);

            secret = calculateSecret2(secret);

            p++;
        }
    }

    private long calculateSecret2(long secret) {
        long s = secret * 64L;
        secret = (s ^ secret) % 16777216;
        s = secret / 32;
        secret = (s ^ secret) % 16777216;
        s = secret * 2048;
        return (s ^ secret) % 16777216;
    }

    private int formatKey2(long v1, long v2, long v3, long v4) {
        // Adjust values in case they are negative
        if (v1 < 0) {
            v1 = v1 + 19;
        }
        if (v2 < 0) {
            v2 = v2 + 19;
        }
        if (v3 < 0) {
            v3 = v3 + 19;
        }
        if (v4 < 0) {
            v4 = v4 + 19;
        }

        // Cast each value to int before the bitwise operations
        int intV1 = (int) v1;
        int intV2 = (int) v2;
        int intV3 = (int) v3;
        int intV4 = (int) v4;

        // Perform the bitwise operations with the casted values
        return (intV1 << 24) | (intV2 << 16) | (intV3 << 8) | intV4;
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
