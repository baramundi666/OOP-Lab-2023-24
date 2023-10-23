package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> result = new ArrayList<>();
        for(String arg : args) {
            switch(arg) {
                case "l" -> {
                    result.add(MoveDirection.LEFT);
                }
                case "r" -> {
                    result.add(MoveDirection.RIGHT);
                }
                case "f" -> {
                    result.add(MoveDirection.FORWARD);
                }
                case "b" -> {
                    result.add(MoveDirection.BACKWARD);
                }
                default -> {
                    System.out.println("Nieznana komenda");
                }
            }
        }
        return result;
    }
}
