package com.adventofcode.years.y2024;

import com.adventofcode.years.Day;

import java.util.*;
import java.util.function.Consumer;

public class Y24D17P1 extends Day {

    int[] registers = new int[3];
    int[] numbers;
    List<Integer> output = new ArrayList<>();
    int pointer = 0;
    Map<Integer, Consumer<Integer>> instructions = new HashMap<>();

    public Y24D17P1() {
        super(2024, 17, 1);
        String path = "y2024/day17.txt";
        super.readInput(path);
    }

    @Override
    protected String logic() {
        mapInput();
        runProgram();
        return output.toString();
    }

    private void runProgram() {
        while(pointer < numbers.length - 1) {
            int opcode = numbers[pointer];
            int operand = numbers[pointer + 1];
            instructions.get(opcode).accept(operand);
            pointer += 2;
        }
    }

    private void mapInput() {
        registers[0] = Integer.parseInt(input.get(0).split(" ")[2]);
        registers[1] = Integer.parseInt(input.get(1).split(" ")[2]);
        registers[2] = Integer.parseInt(input.get(2).split(" ")[2]);
        numbers = Arrays.stream(input.get(4).split(" ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
        instructions.put(0, o -> registers[0] = registers[0] / (int) Math.pow(2, gco(o)));
        instructions.put(1, o -> registers[1] = registers[1] ^ o);
        instructions.put(2, o -> registers[1] = gco(o) % 8);
        instructions.put(3, o -> {if (registers[0] != 0) pointer = o - 2;});
        instructions.put(4, o -> registers[1] = registers[1] ^ registers[2]);
        instructions.put(5, o -> output.add(gco(o) % 8));
        instructions.put(6, o -> registers[1] = registers[0] / (int) Math.pow(2, gco(o)));
        instructions.put(7, o -> registers[2] = registers[0] / (int) Math.pow(2, gco(o)));
    }

    private int gco(int operand) {
        return switch(operand) {
            case 0 -> 0;
            case 1 -> 1;
            case 2 -> 2;
            case 3 -> 3;
            case 4 -> registers[0];
            case 5 -> registers[1];
            case 6 -> registers[2];
            default -> -1;
        };
    }
}
