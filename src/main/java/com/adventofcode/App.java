package com.adventofcode;

import com.adventofcode.years.IDay;
import com.adventofcode.years.y2023.Day2P1;
import com.adventofcode.years.y2023.Day2P2;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ArrayList<IDay> y2023 = new ArrayList<>();
        y2023.add(new Day2P1());
        y2023.add(new Day2P2());

        for (IDay day : y2023) {
            day.solve();
        }
    }
}
