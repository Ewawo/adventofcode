package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Y24D3P1 extends Day {

    public Y24D3P1() {
        super(2024, 3, 1);
        String path = "y2024/day3.txt";
        super.readInput(path);
    }

    @Override
    protected Integer logic() {
        Pattern globalRegexer = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        Pattern innerRegexer = Pattern.compile("[0-9]{1,3}");
        int result = 0;

        for (String line : input) {
            Matcher matcher = globalRegexer.matcher(line);
            while (matcher.find()) {
                String match = matcher.group();
                Matcher innerMatcher = innerRegexer.matcher(match);
                ArrayList<Integer> numbers = new ArrayList<>();
                while (innerMatcher.find()) {
                    String innerMatch = innerMatcher.group();
                    numbers.add(Integer.parseInt(innerMatch));
                }
                result += mul(numbers.get(0), numbers.get(1));
            }
        }

        return result;
    }

    private int mul(int x, int y) {
        return x * y;
    }
}
