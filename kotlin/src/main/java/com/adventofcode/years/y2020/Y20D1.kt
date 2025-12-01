package com.adventofcode.years.y2020

import com.adventofcode.years.Day

class Y20D1 : Day(2020, 1) {
    init {
        val path = "y2020/day1.txt"
        super.readInput(path)
    }

    override fun part1(): Int? {
        val entries = input.map { it.toInt() }.toSet()
        val seen = mutableSetOf<Int>()

        // my own solution O(n)
//        return entries.firstNotNullOfOrNull {
//            val diff = 2020 - it
//            if (seen.contains(diff))
//                return it * diff
//            else
//                seen.add(it)
//                null
//        }

        // reformed solution from gpt answer on part 2 O(n)
        entries.forEach {
            val diff = 2020 - it
            if (entries.contains(diff))
                return diff * it
        }
        return null
    }

    override fun part2(): Int? {
        val entries = input.map { it.toInt() }.toSet()
        val seen = mutableSetOf<Int>()

        // gpt formed solution O(n²)
        entries.forEachIndexed { i, first ->
            entries.drop(i + 1).forEach { second ->
                val third = 2020 - first - second
                if (entries.contains(third)) {
                    return first * second * third
                }
            }
        }
        return null

//        // my own solution 1 O(n²)
//        for ((i, first) in entries.withIndex()) {
//            for (j in (i + 1) until entries.size) {
//                val second = entries[j]
//                val diff = 2020 - first - second
//                if (seen.contains(diff)) return first * second * diff
//            }
//            seen.add(first);
//        }
//        return null
//
//        // my own solution 2 O(n²)
//        return entries.firstNotNullOfOrNull { first ->
//            val result = entries.firstNotNullOfOrNull {
//                val diff = 2020 - it - first
//                if (seen.contains(diff))
//                    it * first * diff
//                else
//                    null
//            }
//
//            if (result == null)
//                seen.add(first)
//
//            result
//        }
    }
}