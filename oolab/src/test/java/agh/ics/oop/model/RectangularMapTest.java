package agh.ics.oop.model;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


public class RectangularMapTest {

    @Test
    public void testGetOrderedAnimals() throws PositionAlreadyOccupiedException {
        //Given
        var position1 = new Vector2d(2, 3);
        var position2 = new Vector2d(1, 3);
        var position3 = new Vector2d(0, 3);
        var position4 = new Vector2d(1, 2);
        var animal1 = new Animal(position1);
        var animal2 = new Animal(position2);
        var animal3 = new Animal(position3);
        var animal4 = new Animal(position4);
        var expectedList = List.of(animal3, animal4, animal2, animal1);

        //When
        var map = new RectangularMap(10, 10);
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        map.place(animal4);

        //Then
        assertEquals(expectedList, map.getOrderedAnimals());
    }

    @Test
    public void testCanMoveTo() throws PositionAlreadyOccupiedException {
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
    public void testIsOccupied() throws PositionAlreadyOccupiedException {
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
        assertDoesNotThrow(() -> map.place(animal1));
        assertDoesNotThrow(() -> map.place(animal2));
        Exception thrown1 = assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animal3));
        assertEquals("Position (2, 2) is already occupied", thrown1.getMessage());
        Exception thrown2 = assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animal4));
        assertEquals("Position (10, 2) is already occupied", thrown2.getMessage());
        assertTrue(map.getAnimals().containsValue(animal1));
        assertTrue(map.getAnimals().containsValue(animal1));
    }

    @Test
    public void testMove() throws PositionAlreadyOccupiedException {
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
    public void testObjectAt() throws PositionAlreadyOccupiedException {
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
    public void testGetElements() throws PositionAlreadyOccupiedException {
        //Given
        var pos1 = new Vector2d(2, 2);
        var pos2 = new Vector2d(3, 4);
        var animal1 = new Animal(pos1);
        var animal2 = new Animal(pos2);

        // When
        var map = new RectangularMap(5, 5);
        map.place(animal1);
        map.place(animal2);
        var elements = map.getElements();

        //Then
        assertTrue(elements.containsValue(animal1));
        assertTrue(elements.containsValue(animal2));
    }
}
