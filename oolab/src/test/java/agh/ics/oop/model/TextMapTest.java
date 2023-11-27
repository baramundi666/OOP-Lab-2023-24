package agh.ics.oop.model;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class TextMapTest {
    @Test
    public void testCanMoveTo() {
        //Given
        int pos1 = -1;
        int pos2 = 20;
        int pos3 = 1;
        int pos4 = 3;
        int pos5 = 4;

        // When
        var map = new TextMap();
        map.place("Ala");
        map.place("ma");
        map.place("kota");
        map.place(".");

        //Then
        assertFalse(map.canMoveTo(pos1));
        assertFalse(map.canMoveTo(pos2));
        assertTrue(map.canMoveTo(pos3));
        assertTrue(map.canMoveTo(pos4));
        assertFalse(map.canMoveTo(pos5));
    }

    @Test
    public void testIsOccupied() {
        //Given
        int pos1 = 0;
        int pos2 = 1;
        int pos3 = 3;


        // When
        var map = new TextMap();
        map.place("animal");
        map.place("dog");

        //Then
        assertTrue(map.isOccupied(pos1));
        assertTrue(map.isOccupied(pos2));
        assertFalse(map.isOccupied(pos3));
    }

    @Test
    public void testPlace() {
        //Given
        var country1 = "Polska";
        var country2 = "Urugwaj";

        // When
        var map = new TextMap();

        //Then
        assertTrue(map.place(country1));
        assertTrue(map.place(country2));
        assertFalse(map.place(null));
        assertTrue(map.getStrings().containsValue(country1));
        assertTrue(map.getStrings().containsValue(country2));
        assertFalse(map.getStrings().containsValue(null));
    }

    @Test
    public void testMove() {
        //Given
        var animal1 = "owl";
        var animal2 = "bear";
        var animal3 = "cat";
        var animal4 = "snake";

        // When
        var map = new TextMap();
        map.place(animal1);
        map.place(animal2);
        map.place(animal3);
        map.place(animal4);
        map.move(animal1, MoveDirection.FORWARD);
        map.move(animal2, MoveDirection.LEFT);
        map.move(animal3, MoveDirection.BACKWARD);
        map.move(animal4, MoveDirection.RIGHT);
        map.move(animal4, MoveDirection.FORWARD);

        //Then
        assertEquals(animal1, map.getStrings().get(2));
        assertEquals(animal2, map.getStrings().get(0));
        assertEquals(animal3, map.getStrings().get(1));
        assertEquals(animal4, map.getStrings().get(3));
    }

    @Test
    public void testObjectAt() {
        //Given
        int pos1 = -2;
        int pos2 = 0;
        int pos3 = 1;
        int pos4 = 10;
        String s1 = "Adieu";
        String s2 = "Sayonara";

        // When
        var map = new TextMap();
        map.place(s1);
        map.place(s2);

        //Then
        assertNull(map.objectAt(pos1));
        assertEquals(s1, map.objectAt(pos2));
        assertEquals(s2, map.objectAt(pos3));
        assertNull(map.objectAt(pos4));
    }
}
