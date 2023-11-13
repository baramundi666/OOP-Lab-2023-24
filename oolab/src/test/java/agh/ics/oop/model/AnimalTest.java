package agh.ics.oop.model;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void testOrientation() {
        //Given
        var animal1 = new Animal();
        var animal2 = new Animal();
        var animal3 = new Animal();

        //When
        var map = new RectangularMap(4, 4);
        animal2.move(MoveDirection.RIGHT, map);
        animal3.move(MoveDirection.FORWARD, map);

        //Then
        assertEquals(MapDirection.NORTH, animal1.getOrientation());
        assertEquals(MapDirection.EAST, animal2.getOrientation());
        assertEquals(MapDirection.NORTH, animal3.getOrientation());
    }

    @Test
    public void testMove() {
        //Given
        var animal = new Animal(new Vector2d(0, 0));
        var position = new Vector2d(1, 2);

        //When
        var map = new RectangularMap(4, 4);
        animal.move(MoveDirection.BACKWARD, map);
        animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.LEFT, map);
        animal.move(MoveDirection.LEFT, map);
        animal.move(MoveDirection.BACKWARD, map);
        animal.move(MoveDirection.RIGHT, map);
        animal.move(MoveDirection.BACKWARD, map);


        //Then
        assertTrue(animal.isAt(position));
    }

    @Test
    public void testOutOfBounds() {
        //Given
        var animal = new Animal(new Vector2d(0, 0));
        var expected_position = new Vector2d(-10,10);

        //When
        var map = new RectangularMap(4, 4);
        for (int i=0; i<10; i++) animal.move(MoveDirection.FORWARD, map);
        animal.move(MoveDirection.LEFT, map);
        for (int i=0; i<10; i++) animal.move(MoveDirection.FORWARD, map);


        //Then
        assertFalse(animal.isAt(expected_position));
    }
}
