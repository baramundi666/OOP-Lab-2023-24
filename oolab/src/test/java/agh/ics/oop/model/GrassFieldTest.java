package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


public class GrassFieldTest {

    @Test
    public void testCanMoveTo() throws PositionAlreadyOccupiedException {
        //Given
        var pos1 = new Vector2d(-1, 0);
        var pos2 = new Vector2d(5, 6);
        var pos3 = new Vector2d(0, 0);
        var animal = new Animal(pos2);

        // When
        var map = new GrassField(10);
        map.place(animal);

        //Then
        assertTrue(map.canMoveTo(pos1));
        assertFalse(map.canMoveTo(pos2));
        assertTrue(map.canMoveTo(pos3));
    }

    @Test
    public void testIsOccupied() throws PositionAlreadyOccupiedException {
        //Given
        var pos1 = new Vector2d(-11, 50);
        var pos2 = new Vector2d(-1, 5);
        var animal1 = new Animal(new Vector2d(2, 2));
        var animal2 = new Animal(new Vector2d(0, 0));

        // When
        var map = new GrassField(15);
        map.place(animal1);
        map.place(animal2);
        var grassPositions = map.getGrass().keySet();

        //Then
        assertFalse(map.isOccupied(pos1));
        assertFalse(map.isOccupied(pos2));
        assertTrue(map.isOccupied(animal1.getPosition()));
        assertTrue(map.isOccupied(animal2.getPosition()));
        for (Vector2d grassPosition : grassPositions) {
            assertTrue(map.isOccupied(grassPosition));
        }
    }

    @Test
    public void testPlace() {
        //Given
        var animal1 = new Animal(new Vector2d(2, 2));
        var animal2 = new Animal(new Vector2d(0, 0));
        var animal3 = new Animal(new Vector2d(2, 2));

        // When
        var map = new GrassField(9);

        //Then
        assertDoesNotThrow(() -> map.place(animal1));
        assertDoesNotThrow(() -> map.place(animal2));
        Exception thrown = assertThrows(PositionAlreadyOccupiedException.class, () -> map.place(animal3));
        assertEquals("Position (2, 2) is already occupied", thrown.getMessage());
    }

    @Test
    public void testMove() throws PositionAlreadyOccupiedException {
        //Given
        var animal1 = new Animal(new Vector2d(2, 2));
        var animal2 = new Animal(new Vector2d(0, 0));
        var animal3 = new Animal(new Vector2d(55, 55));
        var animal4 = new Animal(new Vector2d(1, 1));

        // When
        var map = new GrassField(7);
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
        assertEquals(animal3, map.getAnimals().get(new Vector2d(55, 54)));
        assertEquals(animal4, map.getAnimals().get(new Vector2d(0, 1)));
    }

    @Test
    public void testObjectAt() throws PositionAlreadyOccupiedException {
        //Given
        var pos1 = new Vector2d(2, 2);
        var pos2 = new Vector2d(3, 4);
        var animal1 = new Animal(pos1);
        var animal2 = new Animal(pos2);

        // When
        var map = new GrassField(15);
        var grassPositions = map.getGrass().keySet();
        map.place(animal1);
        map.place(animal2);

        //Then
        assertEquals(animal1, map.objectAt(pos1));
        assertEquals(animal2, map.objectAt(pos2));
        for (Vector2d grassPosition : grassPositions) {
            assertEquals("*", map.objectAt(grassPosition).toString());
        }
    }

    @Test
    public void testGetElements() throws PositionAlreadyOccupiedException {
        //Given
        var pos1 = new Vector2d(27, 29);
        var pos2 = new Vector2d(30, 40);
        var animal1 = new Animal(pos1);
        var animal2 = new Animal(pos2);

        // When
        var map = new GrassField(4);
        var grassPositions = map.getGrass().keySet();
        map.place(animal1);
        map.place(animal2);
        var elements = map.getElements();

        //Then
        assertTrue(elements.containsValue(animal1));
        assertTrue(elements.containsValue(animal2));
        for (Vector2d grassPosition : grassPositions) {
            assertEquals("*", elements.get(grassPosition).toString());
        }
    }
}
