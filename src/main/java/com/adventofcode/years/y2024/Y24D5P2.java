package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;

public class Y24D5P2 extends Day {

    public Y24D5P2() {
        super(2024, 5, 2);
        String path = "y2024/day5.txt";
        super.readInput(path);
    }

    @Override
    protected Integer logic() {
        int result = 0;

        Map<Integer, ArrayList<Integer>> pageRules = getPageRules();
        ArrayList<List<Integer>> pagesToPrint = getPagesToReorder(pageRules);
        int pageToPrintSize = pagesToPrint.size();

        for (int i = 0; i < pageToPrintSize; i++) {
            List<Integer> test = pagesToPrint.get(i);
            ArrayList<Integer> pages = new ArrayList<>(test);
            int pagesSize = pages.size();
            for (int j = 0; j < pagesSize; j++) {
                for (int k = 0; k < j; k++) {
                    if (pageRules.containsKey(pages.get(j))) {
                        if (pageRules.get(pages.get(j)).contains(pages.get(k))) {
                            int temp = pages.get(j);
                            pages.set(j, pages.get(k));
                            pages.set(k, temp);
                            j = k;
                            break;
                        }
                    }
                }
            }
            result += pages.get(pages.size() / 2);
        }

        return result;
    }

    private Map<Integer, ArrayList<Integer>> getPageRules() {
        Map<Integer, ArrayList<Integer>> pageRules = new HashMap<>();

        for (String line : input) {
            if (line.contains("|")) {
                int key = Integer.parseInt(line.split("\\|")[0]);
                int value = Integer.parseInt(line.split("\\|")[1]);
                if (!pageRules.containsKey(key)) {
                    pageRules.put(key, new ArrayList<>());
                }
                pageRules.get(key).add(value);
            }
        }
        return pageRules;
    }

    private ArrayList<List<Integer>> getPagesToReorder(Map<Integer, ArrayList<Integer>> pageRules) {
        ArrayList<List<Integer>> pagesToPrint = new ArrayList<>();
        ArrayList<List<Integer>> resultPages = new ArrayList<>();

        for (String line : input) {
            if (line.contains(",")) {
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
            if (!isValid) {
                resultPages.add(pages);
            }
        }

        return resultPages;
    }

}
