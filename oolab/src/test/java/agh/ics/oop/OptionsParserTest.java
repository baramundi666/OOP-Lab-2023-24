package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OptionsParserTest {


    @Test
    public void testParse() {
            //Given
            String[] args1 = {"l", "r", "lr", "...Fiodor",
                             "f", "kot?", "Male!.nczuk", "b", "b"};

            String[] args2 = {"l", "r", "forward",
                    "f", "b", "backward", "r", "left"};

            //When
            List<MoveDirection> expected = List.of(MoveDirection.LEFT, MoveDirection.RIGHT,
            MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD,
                    MoveDirection.RIGHT, MoveDirection.LEFT);

            //Then
            assertEquals(expected, OptionsParser.parse(args2));
            Exception thrown = assertThrows(IllegalArgumentException.class, () -> OptionsParser.parse(args1));
            assertEquals("lr is not legal move specification", thrown.getMessage());
    }
}
