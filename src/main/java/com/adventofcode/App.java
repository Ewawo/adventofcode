package com.adventofcode;

import com.adventofcode.years.Day;
import com.adventofcode.years.y2023.Y23D2P1;
import com.adventofcode.years.y2023.Y23D2P2;
import com.adventofcode.years.y2023.Y23D3P1;
import com.adventofcode.years.y2024.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Map<Integer, List<Day>> years;

    public static void main( String[] args )
    {
        years = new HashMap<>();
        // 2023
        addDay(new Y23D2P1());
        addDay(new Y23D2P2());
        addDay(new Y23D3P1());

        // 2024
        addDay(new Y24D1P1());
        addDay(new Y24D1P2());
        addDay(new Y24D2P1());
        addDay(new Y24D2P2());
        addDay(new Y24D3P1());
        addDay(new Y24D3P2());
        addDay(new Y24D4P1());
        addDay(new Y24D4P2());
        addDay(new Y24D5P1());
        addDay(new Y24D5P2());
        addDay(new Y24D6P1());
        addDay(new Y24D6P2());
        addDay(new Y24D7P1());
        addDay(new Y24D7P2());
        addDay(new Y24D8P1());
        addDay(new Y24D8P2());
        addDay(new Y24D9P1());
        addDay(new Y24D9P2());
        addDay(new Y24D10P1());
        addDay(new Y24D10P2());
        addDay(new Y24D11P1());
        addDay(new Y24D11P2());
        addDay(new Y24D12P1());
        addDay(new Y24D12P2());

        System.out.println("-----------------------------------");
        for (int year : years.keySet()) {
            for (Day day : years.get(year)) {
                day.solve();
            }
            System.out.println("-----------------------------------");
        }
    }

    public static void addDay(Day day) {
        years.computeIfAbsent(day.getYear(), key -> new ArrayList<>()).add(day);
    }
}
