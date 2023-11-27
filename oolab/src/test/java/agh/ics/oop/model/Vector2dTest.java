package agh.ics.oop.model;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class Vector2dTest {


    @Test
    public void testEquals() {
        int x = 2;
        int y = 2;


        var v1 = new Vector2d(x, y);
        var v2 = new Vector2d(x, y);
        var v3 = new Vector2d(x+3, y-3);


        assertTrue(v1.equals(v2));
        assertFalse(v1.equals(v3));
    }


    @Test
    public void testToString() {
        int x = 2;
        int y = 3;


        var vector = new Vector2d(x, y);


        assertEquals("(2, 3)", vector.toString());
    }


    @Test
    public void testPrecedes() {
        var other = new Vector2d(3, 2);
        var vector = new Vector2d(other.getX()-1, other.getY()-2);


        assertTrue(vector.precedes(other));
    }


    @Test
    public void testFollows() {
        var other = new Vector2d(3, 2);
        var vector = new Vector2d(other.getX()+1, other.getY());


        assertTrue(vector.follows(other));
    }


    @Test
    public void testUpperRight() {
        var v1 = new Vector2d(3, 2);
        var v2 = new Vector2d(1, 5);
        var v3 = new Vector2d(3, 5);


        assertEquals(v1.upperRight(v2), v3);
    }

    @Test
    public void testLowerLeft() {
        var v1 = new Vector2d(3, 2);
        var v2 = new Vector2d(1, 5);
        var v3 = new Vector2d(1, 2);


        assertEquals(v1.lowerLeft(v2), v3);
    }


    @Test
    public void testAdd() {
        var v1 = new Vector2d(3, 2);
        var v2 = new Vector2d(1, 2);
        var v3 = new Vector2d(4, 4);


        assertEquals(v1.add(v2), v3);
    }


    @Test
    public void testSubtract() {
        var v1 = new Vector2d(3, 2);
        var v2 = new Vector2d(1, 2);
        var v3 = new Vector2d(2, 0);


        assertEquals(v1.subtract(v2), v3);
    }


    @Test
    public void testOpposite() {
        var v1 = new Vector2d(3, -2);
        var v2 = new Vector2d(-3, 2);


        assertEquals(v1.opposite(), v2);
    }


}
