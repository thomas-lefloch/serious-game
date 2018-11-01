package Game.Hitbox;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircleHitboxTest {

    private CircleHitbox c1;
    private CircleHitbox c2;

    @Before
    public void setUp() {
        c1 = new CircleHitbox(10, 10, 10);
    }

    @Test
    public void intersectTrueNormalCase() {
        c2 = new CircleHitbox(15, 15, 10);
        assertTrue(c1.intersect(c2));
    }

    @Test
    public void intersectFalseNormalCase() {
        c2 = new CircleHitbox(50, 50, 10);
        assertFalse(c1.intersect(c2));
    }


}