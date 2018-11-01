package com.example.thomas.angrygarbage;

import org.junit.Test;

import Vector.Vector2D;

import static org.junit.Assert.*;

public class Vector2DTest {
    @Test
    public void creationTestNotNull() {
        Vector2D v = new Vector2D(1,1);
        assertEquals(1, v.getX());
        assertEquals(1, v.getY());
    }

    @Test
    public void addTest() {
        Vector2D v = new Vector2D(1, 1);
        v.add(new Vector2D(1, 1));
        assertEquals(2, v.getX());
        assertEquals(2, v.getY());
    }

    @Test
    public void subtractTest() {
        Vector2D v = new Vector2D(-1, 1);
        v.add(new Vector2D(-1, -1));
        assertEquals(-2, v.getX());
        assertEquals(0, v.getY());
    }

    @Test(expected = AssertionError.class)
    public void addNullTest() {
        Vector2D v = new Vector2D(1, 1);
        v.add(null);
    }

    @Test
    public void addScalarTest() {
        Vector2D v = new Vector2D(1,1);
        v.addScalar(5);
        assertEquals(6,v.getX());
        assertEquals(6,v.getY());
    }



}