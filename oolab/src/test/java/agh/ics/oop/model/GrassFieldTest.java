package agh.ics.oop.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


public class GrassFieldTest {

    @Test
    public void testGetOrderedAnimals() throws PositionAlreadyOccupiedException {
        //Given
        var position1 = new Vector2d(2, 3);
        var position2 = new Vector2d(1, 3);
        var position3 = new Vector2d(-1, 3);
        var position4 = new Vector2d(1, 2);
        var animal1 = new Animal(position1);
        var animal2 = new Animal(position2);
        var animal3 = new Animal(position3);
        var animal4 = new Animal(position4);
        var expectedList = List.of(animal3, animal4, animal2, animal1);

        //When
        var map = new GrassField(10);
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
    public void testObjectAt() throws PositionAlreadyOccupiedException, NoSuchElementException {
        //Given
        var pos1 = new Vector2d(2, 2);
        var pos2 = new Vector2d(3, 4);
        var pos3 = new Vector2d(20, 20);
        var animal1 = new Animal(pos1);
        var animal2 = new Animal(pos2);

        // When
        var map = new GrassField(15);
        var grassPositions = map.getGrass().keySet();
        map.place(animal1);
        map.place(animal2);

        //Then
        assertEquals(animal1, map.objectAt(pos1).get());
        assertEquals(animal2, map.objectAt(pos2).get());
        assertFalse(map.objectAt(pos3).isPresent());
        for (Vector2d grassPosition : grassPositions) {
            if (grassPosition!=pos1 && grassPosition!=pos2) {
                assertEquals("*", map.objectAt(grassPosition).get().toString());
            }
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
