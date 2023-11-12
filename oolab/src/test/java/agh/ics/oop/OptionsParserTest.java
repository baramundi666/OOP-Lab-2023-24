package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class OptionsParserTest {


    @Test
    public void testParse() {
        String[] args = {"l", "r", "...Fiodor",
                         "f", "kot?", "Male!.nczuk", "b", "b"};


        List<MoveDirection> expected = List.of(MoveDirection.LEFT, MoveDirection.RIGHT,
        MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD);


        assertEquals(expected, OptionsParser.parse(args));
    }
}
