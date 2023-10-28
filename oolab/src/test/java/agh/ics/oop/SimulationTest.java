package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SimulationTest {

    @Test
    public void testDataInput() {
        //Given
        String[] arguments = {"f", "fr", "lr", "b", "l", "r", "f", "f"
                , "f", "l", "f", "r", "vt", "rb", "flr", "b", "b"};
        List<MoveDirection> expected_directions = List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD,
                MoveDirection.LEFT, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.BACKWARD);

        List<MoveDirection> directions = OptionsParser.parse(arguments);
        List<Vector2d> positions = List.of(new Vector2d(0,0), new Vector2d(4,4));
        var simulation = new Simulation(directions, positions);

        //When
        simulation.run();

        //Then
        assertEquals(expected_directions, simulation.getDirections());
        assertTrue(simulation.getAnimals().get(0).isAt(new Vector2d(1, 1)));
        assertTrue(simulation.getAnimals().get(1).isAt(new Vector2d(3, 3)));
    }
}
