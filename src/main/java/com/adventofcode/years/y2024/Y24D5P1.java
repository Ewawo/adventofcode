package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;

public class Y24D5P1 extends Day {

    public Y24D5P1() {
        super(2024, 5, 1);
        String path = "y2024/day5.txt";
        super.readInput(path);
    }

    @Override
    protected int logic() {
        int result = 0;

        Map<Integer, ArrayList<Integer>> pageRules = new HashMap<>();
        ArrayList<List<Integer>> pagesToPrint = new ArrayList<>();

        for (String line : input) {
            if (line.contains("|")) {
                int key = Integer.parseInt(line.split("\\|")[0]);
                int value = Integer.parseInt(line.split("\\|")[1]);
                if (!pageRules.containsKey(key)) {
                    pageRules.put(key, new ArrayList<>());
                }
                pageRules.get(key).add(value);
            } else if (line.contains(",")) {
                String[] pages = line.split(",");
                pagesToPrint.add(Arrays.stream(pages).map(Integer::parseInt).toList());
            }
        }

        for (List<Integer> pages : pagesToPrint) {
            ArrayList<Integer> correctPages = new ArrayList<>();
            boolean isValid = true;
            for (int page : pages) {
                if (!pageRules.containsKey(page)) {
                    pageRules.put(page, new ArrayList<>());
                }
                ArrayList<Integer> pagesAfter = pageRules.get(page);

                for (int correctPage : correctPages) {
                    if (pagesAfter.contains(correctPage)) {
                        isValid = false;
                        break;
                    }
                }
                if (!isValid) {
                    break;
                } else {
                    correctPages.add(page);
                }
            }
            if (isValid) {
                result += pages.get(pages.size() / 2);
            }
        }

        return result;
    }
}
