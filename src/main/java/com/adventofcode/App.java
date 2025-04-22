package com.adventofcode;

import com.adventofcode.years.Day;
import com.adventofcode.years.y2020.Y20D1;

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
//        // 2023
//        addDay(new Y23D2P1());
//        addDay(new Y23D2P2());
//        addDay(new Y23D3P1());
//
//        // 2024
//        addDay(new Y24D1P1());
//        addDay(new Y24D1P2());
//        addDay(new Y24D2P1());
//        addDay(new Y24D2P2());
//        addDay(new Y24D3P1());
//        addDay(new Y24D3P2());
//        addDay(new Y24D4P1());
//        addDay(new Y24D4P2());
//        addDay(new Y24D5P1());
//        addDay(new Y24D5P2());
//        addDay(new Y24D6P1());
//        addDay(new Y24D6P2());
//        addDay(new Y24D7P1());
//        addDay(new Y24D7P2());
//        addDay(new Y24D8P1());
//        addDay(new Y24D8P2());
//        addDay(new Y24D9P1());
//        addDay(new Y24D9P2());
//        addDay(new Y24D10P1());
//        addDay(new Y24D10P2());
//        addDay(new Y24D11P1());
//        addDay(new Y24D11P2());
//        addDay(new Y24D12P1());
//        addDay(new Y24D12P2());
//        addDay(new Y24D13P1());
//        addDay(new Y24D13P2());
//        addDay(new Y24D14P1());
//        addDay(new Y24D14P2());
//        addDay(new Y24D15P1());
//        addDay(new Y24D15P2());
//        addDay(new Y24D16P1());
//        addDay(new Y24D16P2());
//        addDay(new Y24D17P1());
//        addDay(new Y24D17P2());
//        addDay(new Y24D18P1());
//        addDay(new Y24D18P2());
//        addDay(new Y24D19P1());
//        addDay(new Y24D19P2());
//        addDay(new Y24D20P1());
//        addDay(new Y24D20P2());
//        addDay(new Y24D21P1());
//        addDay(new Y24D21P2());
//        addDay(new Y24D22P1());
//        addDay(new Y24D22P2());
//        addDay(new Y24D23P1());
//        addDay(new Y24D23P2());

        addDay(new Y20D1());

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
