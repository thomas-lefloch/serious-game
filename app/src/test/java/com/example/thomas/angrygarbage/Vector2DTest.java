package com.example.thomas.angrygarbage;

import org.junit.Test;

import Vector.Vector2D;

import static org.junit.Assert.*;

public class Vector2DTest {
    @Test
    public void creationTestNotNull() {
        Vector2D v = new Vector2D(1,1);
        assertEquals(1, v.getX(), 0);
        assertEquals(1, v.getY(), 0);
    }

    @Test
    public void addTest() {
        Vector2D v = new Vector2D(1, 1);
        v.add(new Vector2D(1, 1));
        assertEquals(2, v.getX(), 0);
        assertEquals(2, v.getY(), 0);
    }

    @Test
    public void subtractTest() {
        Vector2D v = new Vector2D(-1, 1);
        v.add(new Vector2D(-1, -1));
        assertEquals(-2, v.getX(), 0);
        assertEquals(0, v.getY(), 0);
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
        assertEquals(6,v.getX(), 0);
        assertEquals(6,v.getY(), 0);
    }

    @Test
    public void testGetMagnitude() {
        assertEquals(2, new Vector2D(1, 1).getMagnitude(), 0);
        assertEquals(2, new Vector2D(-1, -1).getMagnitude(), 0);
        assertEquals(25, new Vector2D(-4, 3).getMagnitude(), 0);
    }

    @Test
    public void testGetSquaredMagnitude() {
        assertEquals(5, new Vector2D(4, 3).getSquaredMagnitude(), 0);
        assertEquals(5, new Vector2D(4, -3).getSquaredMagnitude(), 0);
        assertEquals(5, new Vector2D(-4, -3).getSquaredMagnitude(), 0);
    }

    @Test
    public void testDivide() {
        Vector2D v = new Vector2D(5,5);
        v.divide(5);
        assertEquals(1, v.getX(), 0);
        assertEquals(1, v.getY(), 0);
        Vector2D v2 = new Vector2D(5,5);
        v2.divide(-5);
        assertEquals(-1, v2.getX(), 0);
        assertEquals(-1, v2.getY(), 0);

    }

    @Test(expected = AssertionError.class)
    public void divideByZero() {
        Vector2D v = new Vector2D(5,5);
        v.divide(0);
    }

    @Test
    public void testNormalize() {
        Vector2D v = new Vector2D(3, 4).normalize();
        assertEquals(0.6, v.getX(), 0);
        assertEquals(0.8, v.getY(), 0);
    }

    @Test
    public void testClone() {
        Vector2D v = new Vector2D(3,4);
        assertTrue(v.clone() != v);
        assertTrue(v.clone().getClass() == v.getClass());
        assertTrue(v.clone().getX() == v.getX());
        assertTrue(v.clone().getY() == v.getY());
    }



}