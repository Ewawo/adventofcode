package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;

public class Y24D8 extends Day {

    private final Map<Character, List<Integer[]>> antennaLocations = new HashMap<>();

    public Y24D8() {
        super(2024, 8);
        String path = "y2024/day8.txt";
        super.readInput(path);
    }
    @Override
    protected Number part1() {
        mapAntennas();

        return getAntinodes();
    }

    private int getAntinodes() {
        List<Integer[]> antinodes = new ArrayList<>();
        for (Character antennaGroup : antennaLocations.keySet()) {

            antinodes.addAll(calculateAntinodesFromGroup(antennaLocations.get(antennaGroup)));
        }

        // TODO: filter antinodes for doubles

        List<Integer[]> resultSet = new ArrayList<>();

        for (Integer[] antinode : antinodes) {
            if (resultSet.stream().noneMatch(a -> Objects.equals(a[0], antinode[0]) && Objects.equals(a[1], antinode[1]))) {
                resultSet.add(antinode);
            }
        }
        return resultSet.size();
    }

    @Override
    protected Number part2() {
        mapAntennas();

        return getAntinodes();
    }

    private List<Integer[]> calculateAntinodesFromGroup(List<Integer[]> antennaGroups) {
        ArrayList<Integer[]> antinodes = new ArrayList<>();

        for (Integer[] firstAntenna : antennaGroups) {
            for (Integer[] secondAntenna : antennaGroups) {
                if (!(Objects.equals(firstAntenna[0], secondAntenna[0]) && Objects.equals(firstAntenna[1], secondAntenna[1]))) {
                    List<Integer[]> antinodeGroup = hasAntinodes(firstAntenna, secondAntenna);
                    if (!antinodeGroup.isEmpty()) {
                        antinodes.addAll(antinodeGroup);
                    }
                }
            }
        }

        return antinodes;
    }

    private List<Integer[]> hasAntinodes(Integer[] firstAntenna, Integer[] secondAntenna) {
        List<Integer[]> antinodes = new ArrayList<>();

        hasAntinode(firstAntenna, secondAntenna, 1, antinodes);

        return antinodes;
    }

    private void hasAntinode(Integer[] firstAntenna, Integer[] secondAntenna, int increment, List<Integer[]> nodes) {
        int antinodeRow = firstAntenna[0] - (firstAntenna[0] - secondAntenna[0]) * increment;
        int antinodeCol = firstAntenna[1] - (firstAntenna[1] - secondAntenna[1]) * increment;

        if (isValidLocation(antinodeCol, antinodeRow)) {
            nodes.add(new Integer[]{antinodeRow, antinodeCol});
            hasAntinode(firstAntenna, secondAntenna, increment + 1, nodes);
        }
    }

    private boolean isValidLocation(int x, int y) {
        return x >= 0 && x < input.size() && y >= 0 && y < input.size();
    }

    private void mapAntennas() {
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(row).length(); col++) {
                char location = input.get(row).charAt(col);
                if (location != '.') {
                    addAntennaToLocation(location, row, col);
                }
            }
        }
    }

    private Integer[] hasAntinode(Integer[] firstAntenna, Integer[] secondAntenna) {
        int antinodeRow = firstAntenna[0] - (firstAntenna[0] - secondAntenna[0]) * 2;
        int antinodeCol = firstAntenna[1] - (firstAntenna[1] - secondAntenna[1]) * 2;
        if (isValidLocation(antinodeCol, antinodeRow)) {
            return new Integer[]{antinodeRow, antinodeCol};
        }
        return null;
    }

    private void addAntennaToLocation(char antenna, int row, int col) {
        if (!antennaLocations.containsKey(antenna)) {
            antennaLocations.put(antenna, new ArrayList<>());
        }
        antennaLocations.get(antenna).add(new Integer[]{row, col});
    }

}
