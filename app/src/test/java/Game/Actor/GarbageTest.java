package Game.Actor;

import com.example.thomas.angrygarbage.Vector2DTest;

import org.junit.Test;

import java.util.Vector;

import Vector.Vector2D;

import static org.junit.Assert.*;

public class GarbageTest {


    private final double slowingFactor = Garbage.getSlowingFactor();

    private Garbage getGarbage() {
        return new Garbage(0, 0, 0, 200, 200, null);
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
    public void updateSimple() {
        Garbage g = getGarbage();
        int power = 10;
        Vector2D dir = new Vector2D(1, 0);
        g.applyForce(power, dir);
        g.update();
        assertEquals(power * dir.getX(), g.getPos().getX(), 0.0001);
        assertEquals(power * dir.getY(), g.getPos().getY(), 0.0001);
        assertEquals(power * dir.getX() * slowingFactor, g.getVel().getX(), 0.0001);
        assertEquals(power * dir.getY() * slowingFactor, g.getVel().getY(), 0.0001);
    }

    @Test
    public void updateLimit() {
        Garbage g = getGarbage();
        int power = 10;
        Vector2D dir = new Vector2D(-1, 0);
        g.applyForce(power, dir);
        g.update();
        assertEquals(power * -dir.getX(), g.getPos().getX(), 0.0001);
        assertEquals(power * dir.getY(), g.getPos().getY(), 0.0001);
        assertEquals(power * dir.getX() * slowingFactor, g.getVel().getX(), 0.0001);
        assertEquals(power * dir.getY() * slowingFactor, g.getVel().getY(), 0.0001);
    }

    @Test
    public void updateVelocitySimple() {
        Garbage g = getGarbage();
        int power = 10;
        g.setVel(new Vector2D(power, power));
        g.updateVelocity();
        assertEquals(power * slowingFactor, g.getVel().getX(), 0.001);
        assertEquals(power * slowingFactor, g.getVel().getY(), 0.001);
    }

    @Test
    public void updateVelocityNegativeDir() {
        Garbage g = getGarbage();
        int power = -10;
        g.setVel(new Vector2D(power, power));
        g.updateVelocity();
        assertEquals(power * slowingFactor, g.getVel().getX(), 0.001);
        assertEquals(power * slowingFactor, g.getVel().getY(), 0.001);
    }

    @Test
    public void updateVelocityLimit() {
        Garbage g = getGarbage();
        double power = 0.49;
        g.setVel(new Vector2D(power, power));
        g.updateVelocity();
        assertEquals(0, g.getVel().getX(), 0.001);
        assertEquals(0, g.getVel().getY(), 0.001);
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
}