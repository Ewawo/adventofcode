package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Y24D23P2 extends Day {

    private Map<String, List<String>> connections = new HashMap<>();
    private List<String> result = new ArrayList<>();

    public Y24D23P2() {
        super(2024, 23, 2);
        String path = "y2024/day23.txt";
        super.readInput(path);
    }

    @Override
    protected Object logic() {
        evaluateConnections();
        return result;
    }

    private void evaluateConnections() {
        for (String line : input) {
            String[] computers = line.split("-");
            addConnection(computers[0], computers[1]);
            addConnection(computers[1], computers[0]);
            checkForParty(computers[0], computers[1]);
        }
    }

    private void checkForParty(String c1, String c2) {

        for (String c3 : connections.get(c1)) {
            boolean x = true;
            for (String c4 : connections.get(c3)) {
                if (c4.equals(c2)) {
                    x = false;
                    break;
                }
            }

            if (x) {
                if (result.size() < connections.get(c1).size()) {
                    result = connections.get(c1);
                }
            }
        }
    }

    private void addConnection(String computer, String connection) {
        if (!connections.containsKey(computer)) {
            connections.put(computer, new ArrayList<>());
        }
        connections.get(computer).add(connection);
    }
}
