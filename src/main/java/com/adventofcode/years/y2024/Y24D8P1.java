package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Y24D8P1 extends Day {

    private final Map<Character, List<Integer[]>> antennaLocations = new HashMap<>();

    public Y24D8P1() {
        super(2024, 8, 1);
        String path = "y2024/day8.txt";
        super.readInput(path);
    }
    @Override
    protected Number logic() {
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

    private List<Integer[]> calculateAntinodesFromGroup(List<Integer[]> antennaGroups) {
        ArrayList<Integer[]> antinodes = new ArrayList<>();

        for (Integer[] firstAntenna : antennaGroups) {
            for (Integer[] secondAntenna : antennaGroups) {
                if (!(Objects.equals(firstAntenna[0], secondAntenna[0]) && Objects.equals(firstAntenna[1], secondAntenna[1]))) {
                    Integer[] antinode = hasAntinode(firstAntenna, secondAntenna);
                    if (antinode != null) {
                        antinodes.add(antinode);
                    }
                }
            }
        }

        return antinodes;
    }

    private Integer[] hasAntinode(Integer[] firstAntenna, Integer[] secondAntenna) {
        int antinodeRow = firstAntenna[0] - (firstAntenna[0] - secondAntenna[0]) * 2;
        int antinodeCol = firstAntenna[1] - (firstAntenna[1] - secondAntenna[1]) * 2;
        if (isValidLocation(antinodeCol, antinodeRow)) {
            return new Integer[]{antinodeRow, antinodeCol};
        }
        return null;
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

    private void addAntennaToLocation(char antenna, int row, int col) {
        if (!antennaLocations.containsKey(antenna)) {
            antennaLocations.put(antenna, new ArrayList<>());
        }
        antennaLocations.get(antenna).add(new Integer[]{row, col});
    }

}
