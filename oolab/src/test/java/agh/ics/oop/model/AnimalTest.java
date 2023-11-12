package agh.ics.oop.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void testOrientation() {
        //Given
        var animal1 = new Animal();
        var animal2 = new Animal();
        var animal3 = new Animal();

        //When
        animal2.move(MoveDirection.RIGHT);
        animal3.move(MoveDirection.FORWARD);

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
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.BACKWARD);


        //Then
        assertTrue(animal.isAt(position));
    }

    @Test
    public void testOutOfBounds() {
        //Given
        var animal = new Animal(new Vector2d(0, 0));
        var expected_position = new Vector2d(-10,10);

        //When
        for (int i=0; i<10; i++) animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.LEFT);
        for (int i=0; i<10; i++) animal.move(MoveDirection.FORWARD);


        //Then
        assertFalse(animal.isAt(expected_position));
    }
}
