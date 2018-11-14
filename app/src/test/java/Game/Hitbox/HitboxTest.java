package Game.Hitbox;

import org.junit.Test;

import Vector.Vector2D;

import static org.junit.Assert.*;

public class HitboxTest {


    @Test
    public void noIntersectCirRect() {
        CircleHitbox c = new CircleHitbox(new Vector2D(0, 0), 10);
        RectangleHitbox r = new RectangleHitbox(new Vector2D(40, 0), 20, 20);
        assertFalse(Hitbox.intersect(c, r));
    }

    @Test
    public void intersectCirRect() {
        CircleHitbox c = new CircleHitbox(new Vector2D(0, 0), 10);
        RectangleHitbox r = new RectangleHitbox(new Vector2D(0, 0), 20, 20);
        assertTrue(Hitbox.intersect(c, r));
    }

    @Test
    public void limitIntersectCirRect() {
        CircleHitbox c = new CircleHitbox(new Vector2D(0, 0), 1);
        RectangleHitbox r = new RectangleHitbox(new Vector2D(1, 0), 1, 1);
        assertTrue(Hitbox.intersect(c, r));
    }

    @Test
    public void noIntersectCornerCirRect() {
        CircleHitbox c = new CircleHitbox(new Vector2D(0, 0), 100);
        RectangleHitbox r = new RectangleHitbox(new Vector2D(99, 99), 4, 4);
        assertFalse(Hitbox.intersect(c, r));
    }
}