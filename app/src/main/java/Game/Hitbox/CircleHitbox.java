package Game.Hitbox;

import Vector.Vector2D;

public class CircleHitbox implements Hitbox {

    private Vector2D pos;
    private int radius;

    public CircleHitbox(int x, int y, int radius) {

        this.pos = new Vector2D(x,y);
        this.radius = Math.abs(radius);
    }

    @Override
    public boolean intersect(Hitbox other) throws HitboxNotRecognizeException {
        if (other instanceof CircleHitbox) {
            return intersectCircle((CircleHitbox) other);
        }
        else {
            throw new HitboxNotRecognizeException(other.getClass().toString() + "is not supported");
        }
    }

    private boolean intersectCircle(CircleHitbox other) {
        float distBetweenCircles = Math.sqrt(()) // faire une methode entre vector ???
        return ;
    }
}
