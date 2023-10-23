package agh.ics.oop.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AnimalTest {

    @Test
    public void testOrientation() {

        var animal = new Animal();


        assertEquals(animal.getOrientation(), MapDirection.NORTH);
    }

    @Test
    public void testMove() {

        var animal = new Animal();
        var position = new Vector2d(1, 2);


        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);


        assertEquals(animal.getPosition(), position);
    }


}
