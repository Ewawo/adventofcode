package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;

public class Y24D11P2 extends Day {

    private List<Long> stones = new LinkedList<>();

    private Map<Hash, Long> hashmap = new HashMap<>();

    public Y24D11P2() {
        super(2024, 11, 2);
        String path = "y2024/day11.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {
        getStonesInLine();
        return blink(75);
    }

    private long blink(int times) {
        long result = 0;
        for (Long stone : stones) {
            result += evolveStone(stone, 0, times);
        }
        return result;
    }

    private long evolveStone(long stone, long blink, int maxBlink) {

        Hash hash = new Hash(stone, blink);

        if (hashmap.containsKey(hash)) {
            return hashmap.get(hash);
        }

        if (blink >= maxBlink) {
            return 1;
        }

        long futureStones = 0;

        if (stone == 0) {
            futureStones += evolveStone(1, blink + 1, maxBlink);
        } else if (Long.toString(stone).length() % 2 == 0) {
            int initialStoneLength = Long.toString(stone).length();
            String leftStone = Long.toString(stone).substring(0, initialStoneLength / 2);
            String rightStone = Long.toString(stone).substring(initialStoneLength / 2);
            futureStones += evolveStone(Long.parseLong(leftStone), blink + 1, maxBlink);
            futureStones += evolveStone(Long.parseLong(rightStone), blink + 1, maxBlink);
        } else {
            futureStones += evolveStone(stone * 2024, blink + 1, maxBlink);
        }

        hashmap.put(hash, futureStones);
        return futureStones;
    }

    private void getStonesInLine() {
        String line = input.get(0);
        for (String stone : line.split(" ")) {
            stones.add(Long.parseLong(stone));
        }
    }

    private class Hash {

        public long stone;
        public long blink;

        public Hash(long stone, long blink) {
            this.stone = stone;
            this.blink = blink;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true; // Check if they are the same object
            if (o == null || getClass() != o.getClass()) return false; // Null check and type check
            Hash hash = (Hash) o; // Cast the object to Hash
            return stone == hash.stone && blink == hash.blink; // Compare the fields
        }

        @Override
        public int hashCode() {
            return Objects.hash(stone, blink); // Generate hash code based on the fields
        }
    }
}
