package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.List;

public class Y24D9P2 extends Day {

    public Y24D9P2() {
        super(2024, 9, 2);
        String path = "y2024/day9.txt";
        super.readInput(path);
    }

    @Override
    protected Number logic() {

        String line = input.get(0);

        return calculateFileSystemCheckSum(reArrangeDataBlocks(getDataBlocks(line)));
    }

    private long calculateFileSystemCheckSum(List<DiskSpace> dataBlocks) {
        long result = 0;
        long index = 0;

        for (int i = 0; i < dataBlocks.size(); i++) {
            DiskSpace diskSpace = dataBlocks.get(i);
            result += diskSpace.calculateSpaces(index);
            index += diskSpace.spaces;
        }

        return result;
    }

    private List<DiskSpace> reArrangeDataBlocks(List<DiskSpace> dataBlocks) {
        int rightPointer = dataBlocks.size() - 1;

        while (rightPointer > 0) {
            DiskSpace diskSpace = dataBlocks.get(rightPointer);
            diskSpace.handled = true;

            if (diskSpace.isEmpty) {
                rightPointer--;
                continue;
            }

            for (int i = 0; i < rightPointer; i++) {
                DiskSpace curLeftDiskSpace = dataBlocks.get(i);
                if (curLeftDiskSpace.isEmpty) {
                    long emptyLength = curLeftDiskSpace.spaces;
                    if (emptyLength >= diskSpace.spaces) {
                        long remainingLength = emptyLength - diskSpace.spaces;

                        diskSpace.isEmpty = true;
                        dataBlocks.add(i, new DiskSpace(diskSpace.spaces, diskSpace.id, false, true));

                        if (remainingLength == 0) {
                            dataBlocks.remove(curLeftDiskSpace);
                        } else {
                            curLeftDiskSpace.spaces = remainingLength;
                        }
                        break;
                    }
                }
            }

            rightPointer = getLastNotMoved(dataBlocks);
        }

        return dataBlocks;
    }

    private int getLastNotMoved(List<DiskSpace> dataBlocks) {
        for (int i = dataBlocks.size() - 1; i >= 0; i--) {
            if (!dataBlocks.get(i).isEmpty && !dataBlocks.get(i).handled) {
                return i;
            }
        }
        return -1;
    }

    private List<DiskSpace> getDataBlocks(String input) {
        List<DiskSpace> result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            int spaces = Integer.parseInt(input.charAt(i) + "");
            if (spaces > 0) {
                result.add(i % 2 == 0 ? new DiskSpace(spaces, i / 2, false, false) : new DiskSpace(spaces, i / 2, true, false));
            }
        }

        return result;
    }

    private class DiskSpace {
        public long spaces;
        public long id;
        public boolean isEmpty;
        public boolean handled;

        public DiskSpace(long spaces, long id, boolean isEmpty, boolean handled) {
            this.spaces = spaces;
            this.id = id;
            this.isEmpty = isEmpty;
            this.handled = handled;
        }

        public long calculateSpaces(long startIndex) {
            if (isEmpty || id == 0) {
                return 0;
            }

            long result = 0;

            for (int i = 0; i < spaces; i++) {
                result += id * startIndex;
                startIndex++;
            }

            return result;
        }
    }
}
