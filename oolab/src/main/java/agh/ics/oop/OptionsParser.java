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
                }
                case "r" -> {
                    result[id] = MoveDirection.RIGHT;
                }
                case "f" -> {
                    result[id] = MoveDirection.FORWARD;
                }
                case "b" -> {
                    result[id] = MoveDirection.BACKWARD;
                }
                default -> {
                    System.out.println("Nieznana komenda");
                    id--;
                }
            }
            id++;
        }
        result = Arrays.copyOfRange(result,0,id);
        return result;
    }
}
