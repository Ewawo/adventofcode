package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Y24D23 extends Day {

    private Map<String, List<String>> connections = new HashMap<>();

    private Map<String, List<String>> connections2 = new HashMap<>();
    private List<String> result2 = new ArrayList<>();

    public Y24D23() {
        super(2024, 23);
        String path = "y2024/day23.txt";
        super.readInput(path);
    }

    @Override
    protected Object part1() {
        return evaluateConnections();
    }


    @Override
    protected Object part2() {
        evaluateConnections2();
        return result2;
    }

    private void evaluateConnections2() {
        for (String line : input) {
            String[] computers = line.split("-");
            addConnection2(computers[0], computers[1]);
            addConnection2(computers[1], computers[0]);
            checkForParty2(computers[0], computers[1]);
        }
    }

    private void checkForParty2(String c1, String c2) {

        for (String c3 : connections2.get(c1)) {
            boolean x = true;
            for (String c4 : connections2.get(c3)) {
                if (c4.equals(c2)) {
                    x = false;
                    break;
                }
            }

            if (x) {
                if (result2.size() < connections2.get(c1).size()) {
                    result2 = connections2.get(c1);
                }
            }
        }
    }

    private void addConnection2(String computer, String connection) {
        if (!connections2.containsKey(computer)) {
            connections2.put(computer, new ArrayList<>());
        }
        connections2.get(computer).add(connection);
    }

    private int evaluateConnections() {
        int result = 0;
        for (String line : input) {
            String[] computers = line.split("-");
            addConnection(computers[0], computers[1]);
            addConnection(computers[1], computers[0]);
                result += checkForParty(computers[0], computers[1]);
        }
        return result;
    }

    private int checkForParty(String c1, String c2) {

        int result = 0;
        for (String c3 : connections.get(c1)) {
            for (String c4 : connections.get(c3)) {
                if (c4.equals(c2)) {
                    if (c1.charAt(0) == 't' || c2.charAt(0) == 't' || c3.charAt(0) == 't') result++;
                }
            }
        }
        return result;
    }

    private void addConnection(String computer, String connection) {
        if (!connections.containsKey(computer)) {
            connections.put(computer, new ArrayList<>());
        }
        connections.get(computer).add(connection);
    }
}
