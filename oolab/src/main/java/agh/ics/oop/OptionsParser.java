package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        var result = new MoveDirection[args.length];
        int id=0;
        for(String arg : args) {
            switch(arg) {
                case "l" -> {
                    result[id] = MoveDirection.LEFT;
                    id++;
                }
                case "r" -> {
                    result[id] = MoveDirection.RIGHT;
                    id++;
                }
                case "f" -> {
                    result[id] = MoveDirection.FORWARD;
                    id++;
                }
                case "b" -> {
                    result[id] = MoveDirection.BACKWARD;
                    id++;
                }
                default -> System.out.println("Nieznana komenda");
            }
        }
        result = Arrays.copyOfRange(result,0,id);
        return result;
    }
}
