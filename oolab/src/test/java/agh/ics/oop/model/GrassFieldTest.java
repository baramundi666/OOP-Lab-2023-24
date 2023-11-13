package agh.ics.oop.model;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class GrassFieldTest {

    @Test
    public void testCanMoveTo() {
        //Given
        var pos1 = new Vector2d(-1, 0);
        var pos2 = new Vector2d(5, 5);
        var pos3 = new Vector2d(5, 6);
        var pos4 = new Vector2d(0, 0);
        var animal = new Animal(new Vector2d(2, 2));

        // When
        var map = new RectangularMap(5, 5);
        map.place(animal);

        //Then
        assertFalse(map.canMoveTo(pos1));
        assertTrue(map.canMoveTo(pos2));
        assertFalse(map.canMoveTo(pos3));
        assertTrue(map.canMoveTo(pos4));
        assertFalse(map.canMoveTo(animal.getPosition()));
    }

    @Test
    public void testIsOccupied() {
        //Given
        var pos1 = new Vector2d(1, 5);
        var pos2 = new Vector2d(-1, 5);
        var animal1 = new Animal(new Vector2d(2, 2));
        var animal2 = new Animal(new Vector2d(0, 0));


        // When
        var map = new RectangularMap(5, 5);
        map.place(animal1);
        map.place(animal2);

        //Then
        assertFalse(map.isOccupied(pos1));
        assertFalse(map.isOccupied(pos2));
        assertTrue(map.isOccupied(animal1.getPosition()));
        assertTrue(map.isOccupied(animal2.getPosition()));
    }

    @Test
    public void testPlace() {
        //Given
        var animal1 = new Animal(new Vector2d(2, 2));
        var animal2 = new Animal(new Vector2d(0, 0));
        var animal3 = new Animal(new Vector2d(2, 2));
        var animal4 = new Animal(new Vector2d(10, 2));

        // When
        var map = new RectangularMap(5, 5);

        //Then
        assertTrue(map.place(animal1));
        assertTrue(map.place(animal2));
        assertFalse(map.place(animal3));
        assertFalse(map.place(animal4));
        assertTrue(map.getAnimals().containsValue(animal1));
        assertTrue(map.getAnimals().containsValue(animal1));
    }

    @Test
    public void testMove() {
        //Given
        var animal1 = new Animal(new Vector2d(2, 2));
        var animal2 = new Animal(new Vector2d(0, 0));
        var animal3 = new Animal(new Vector2d(5, 5));
        var animal4 = new Animal(new Vector2d(1, 1));

        // When
        var map = new RectangularMap(5, 5);
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        map.place(animal4);
        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.LEFT);
        map.move(animal3, MoveDirection.BACKWARD);
        map.move(animal4, MoveDirection.RIGHT);
        map.move(animal4, MoveDirection.BACKWARD);

        //Then
        assertEquals(animal1, map.getAnimals().get(new Vector2d(2, 3)));
        assertEquals(animal2, map.getAnimals().get(new Vector2d(0, 0)));
        assertEquals(animal3, map.getAnimals().get(new Vector2d(5, 4)));
        assertEquals(animal4, map.getAnimals().get(new Vector2d(0, 1)));
    }

    @Test
    public void testObjectAt() {
        //Given
        var pos1 = new Vector2d(2, 2);
        var pos2 = new Vector2d(3, 4);
        var pos3 = new Vector2d(5, 2);
        var animal1 = new Animal(pos1);
        var animal2 = new Animal(pos2);

        // When
        var map = new RectangularMap(5, 5);
        map.place(animal1);
        map.place(animal2);

        //Then
        assertEquals(animal1, map.objectAt(pos1));
        assertEquals(animal2, map.objectAt(pos2));
        assertNull(map.objectAt(pos3));
    }

    @Test
    public void testGetElements() {
        //Given
        var pos1 = new Vector2d(2, 2);
        var pos2 = new Vector2d(3, 4);
        var pos3 = new Vector2d(15, 2);
        var animal1 = new Animal(pos1);
        var animal2 = new Animal(pos2);
        var animal3 = new Animal(pos3);

        // When
        var map = new RectangularMap(5, 5);
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        var elements = map.getElements();

        //Then
        assertTrue(elements.containsValue(animal1));
        assertTrue(elements.containsValue(animal2));
        assertFalse(elements.containsValue(animal3));
    }
}
