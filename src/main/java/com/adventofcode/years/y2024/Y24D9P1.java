package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Y24D9P1 extends Day {

    public Y24D9P1() {
        super(2024, 9, 1);
        String path = "y2024/day9.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {

        String line = input.get(0);

        return calculateFileSystemCheckSum(reArrangeDataBlocks(getDataBlocks(line)));
    }

    private BigInteger calculateFileSystemCheckSum(List<Integer> dataBlocks) {
        BigInteger result = new BigInteger("0");

        for (int i = 0; i < dataBlocks.size(); i++) {
            if (dataBlocks.get(i) != null) {
                result = result.add(new BigInteger(dataBlocks.get(i) + "").multiply(new BigInteger(i + "")));
            }
        }

        return result;
    }

    private List<Integer> reArrangeDataBlocks(List<Integer> dataBlocks) {
        int dataBlockSize = dataBlocks.size();

        int dataBlockPointer = getLastValueNonNull(dataBlocks);
        
        for (int i = 0; i < dataBlockSize; i++) {
            Integer dataBlock = dataBlocks.get(i);
            if (dataBlock == null) {
                dataBlocks.set(i, dataBlocks.get(dataBlockPointer));
                dataBlocks.set(dataBlockPointer, null);
                dataBlockPointer = getLastValueNonNull(dataBlocks.subList(0, dataBlockPointer + 1));
            }

            if (dataBlockPointer <= i) {
                break;
            }
        }
        
        return dataBlocks.stream().filter(Objects::nonNull).toList();
    }

    private int getLastValueNonNull(List<Integer> dataBlocks) {
        for (int i = dataBlocks.size() - 1; i >= 0; i--) {
            if (dataBlocks.get(i) != null) {
                return i;
            }
        }
        return -1;
    }

    private List<Integer> getDataBlocks(String input) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            int currentDataBlockId = Integer.parseInt(input.charAt(i) + "");

            for (int j = 0; j < currentDataBlockId; j++) {
                result.add(i % 2 == 0 ? i / 2 : null);
            }
        }

        return result;
    }
}
