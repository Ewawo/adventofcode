package com.adventofcode;

import com.adventofcode.years.IDay;
import com.adventofcode.years.y2023.Day2;

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
        y2023.add(new Day2());

        for (IDay day : y2023) {
            day.solve();
        }
    }
}
