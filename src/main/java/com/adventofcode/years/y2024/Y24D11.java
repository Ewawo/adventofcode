package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;

public class Y24D11 extends Day {

    private List<Long> stones = new ArrayList<>();
    private Map<Hash, Long> hashmap = new HashMap<>();

    public Y24D11() {
        super(2024, 11);
        String path = "y2024/day11.txt";
        super.readInput(path);
    }

    @Override
    protected Number part1() {
        getStonesInLine();
        blink(25);
        return stones.size();
    }


    @Override
    protected Number part2() {
        getStonesInLine2();
        return blink2(75);
    }

    private long blink2(int times) {
        long result = 0;
        for (Long stone : stones) {
            result += evolveStone2(stone, 0, times);
        }
        return result;
    }

    private long evolveStone2(long stone, long blink, int maxBlink) {

        Hash hash = new Hash(stone, blink);

        if (hashmap.containsKey(hash)) {
            return hashmap.get(hash);
        }

        if (blink >= maxBlink) {
            return 1;
        }

        long futureStones = 0;

        if (stone == 0) {
            futureStones += evolveStone2(1, blink + 1, maxBlink);
        } else if (Long.toString(stone).length() % 2 == 0) {
            int initialStoneLength = Long.toString(stone).length();
            String leftStone = Long.toString(stone).substring(0, initialStoneLength / 2);
            String rightStone = Long.toString(stone).substring(initialStoneLength / 2);
            futureStones += evolveStone2(Long.parseLong(leftStone), blink + 1, maxBlink);
            futureStones += evolveStone2(Long.parseLong(rightStone), blink + 1, maxBlink);
        } else {
            futureStones += evolveStone2(stone * 2024, blink + 1, maxBlink);
        }

        hashmap.put(hash, futureStones);
        return futureStones;
    }

    private void getStonesInLine2() {
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

    private void blink(long times) {
        for (int i = 0; i < times; i++) {
            List<Long> stonesMock = new ArrayList<>(stones);
            stones.clear();

            for (int j = 0; j < stonesMock.size(); j++) {
                evolveStone(stonesMock.get(j), j);
            }
        }
    }

    private void evolveStone(long stone, int index) {
        if (stone == 0) {
            stones.add(1L);
        } else if (Long.toString(stone).length() % 2 == 0) {
            int initialStoneLength = Long.toString(stone).length();
            String leftStone = Long.toString(stone).substring(0, initialStoneLength / 2);
            String rightStone = Long.toString(stone).substring(initialStoneLength / 2);
            stones.add(Long.parseLong(leftStone));
            stones.add(Long.parseLong(rightStone));
        } else {
            stones.add(stone * 2024); // Add transformed stone
        }
    }

    private void getStonesInLine() {
        String line = input.get(0);
        for (String stone : line.split(" ")) {
            stones.add(Long.parseLong(stone));
        }
    }
}
