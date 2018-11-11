package Game.Actor;

import org.junit.Test;

import java.util.Vector;

import Vector.Vector2D;

import static org.junit.Assert.*;

public class GarbageTest {
    
    private Garbage getGarbage() {
        return new Garbage(0, 0, 0, 200, 200);
    }

    @Test
    public void setLocation() {
        Garbage g = getGarbage();
        g.setLocation(100, 100);
        assertEquals(100, g.getPos().getX(), 0);
        assertEquals(100, g.getPos().getY(), 0);
    }

    @Test
    public void applyForce() {
        Garbage g = getGarbage();
        g.applyForce(1, new Vector2D(0, 1));
        assertEquals(0, g.getAcc().getX(), 0);
        assertEquals(1, g.getAcc().getY(), 0);
    }

    @Test
    public void applyForce0Power() {
        Garbage g3 = getGarbage();
        g3.applyForce(0, new Vector2D(1, 1).normalize());
        assertEquals(0, g3.getAcc().getX(), 0);
        assertEquals(0, g3.getAcc().getY(), 0);
    }

    @Test(expected = AssertionError.class)
    public void applyForceZeroVector() {
        Garbage g2 = getGarbage();
        g2.applyForce(1, new Vector2D(0, 0));
    }

    @Test(expected = AssertionError.class)
    public void applyForceNonNormalizedDirection() {
        Garbage g2 = getGarbage();
        g2.applyForce(1, new Vector2D(15, 0));
    }

    @Test
    public void updateLimitedYDirection() {
        Garbage g4 = getGarbage();
        g4.applyForce(1, new Vector2D(0, 1));
        g4.update();
        assertEquals(0, g4.getPos().getX(), 0);
        assertEquals(1, g4.getPos().getY(), 0);
        assertEquals(0, g4.getVel().getX(), 0);
        assertEquals(0, g4.getVel().getY(), 0);
    }

    @Test
    public void updateFullPowerMinusYDirection() {
        Garbage g3 = getGarbage();
        g3.applyForce(15, new Vector2D(0, -1));
        g3.update();
        assertEquals(0, g3.getPos().getX(), 0);
        assertEquals(-10, g3.getPos().getY(), 0);
        assertEquals(0, g3.getVel().getX(), 0);
        assertEquals(-5, g3.getVel().getY(), 0);
    }

    @Test
    public void updateFullPowerMinusXDirection() {
        Garbage g2 = getGarbage();
        g2.applyForce(15, new Vector2D(-1, 0));
        g2.update();
        assertEquals(-10, g2.getPos().getX(), 0);
        assertEquals(0, g2.getPos().getY(), 0);
        assertEquals(-5, g2.getVel().getX(), 0);
        assertEquals(0, g2.getVel().getY(), 0);

    }

    @Test
    public void updateLimitedPowerXDirection() {
        Garbage g = getGarbage();
        g.applyForce(1, new Vector2D(1, 0));
        g.update();
        assertEquals(1, g.getPos().getX(), 0);
        assertEquals(0, g.getPos().getY(), 0);
        assertEquals(0, g.getVel().getX(), 0);
        assertEquals(0, g.getVel().getY(), 0);
    }

    @Test
    public void updateRealValue() {
        Garbage g4 = getGarbage();
        double power = 15;
        double GarbageMaxSpeed = 10;
        g4.applyForce(power, new Vector2D(1, 1).normalize());
        g4.update();
        assertEquals((Math.sqrt(2)/2)*GarbageMaxSpeed, g4.getPos().getX(), 0.001);
        assertEquals((Math.sqrt(2)/2)*GarbageMaxSpeed, g4.getPos().getY(), 0.001);
        assertEquals((Math.sqrt(2)/2)*(power-GarbageMaxSpeed), g4.getVel().getX(), 0.001);
        assertEquals((Math.sqrt(2)/2)*(power-GarbageMaxSpeed), g4.getVel().getY(), 0.001);
    }

    @Test
    public void move() {
        Garbage g = getGarbage();
        g.move(1, -1);
        assertEquals(1, g.getPos().getX(), 0);
        assertEquals(-1, g.getPos().getY(), 0);
        Garbage g2 = getGarbage();
        g2.move(-1, 1);
        assertEquals(-1, g2.getPos().getX(), 0);
        assertEquals(1, g2.getPos().getY(), 0);
    }

    @Test
    public void isOutOfLevelBounds() {
        Garbage g = getGarbage();
        g.setLocation(-50, -50);
        assertTrue(g.isOutOfLevelBounds());
        g.setLocation(100, 100);
        assertFalse(g.isOutOfLevelBounds());
    }

    @Test
    public void isOutOfLevelBoundsLimitCases() {
        Garbage g = getGarbage();
        g.setLocation(0, 0);
        assertFalse(g.isOutOfLevelBounds());
        g.setLocation(200, 200);
        assertTrue(g.isOutOfLevelBounds());
    }
}