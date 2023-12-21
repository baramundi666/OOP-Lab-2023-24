package agh.ics.oop;

import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.MoveDirection;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args) {
        return Stream.of(args).map(OptionsParser::transformToMoveDirection).collect(Collectors.toList());
    }

    private static MoveDirection transformToMoveDirection(String argument) {
        return switch (argument) {
            case "l", "left" -> MoveDirection.LEFT;
            case "r", "right" -> MoveDirection.RIGHT;
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            default -> throw new IllegalArgumentException(argument + " is not legal move specification");
        };
    }
}
