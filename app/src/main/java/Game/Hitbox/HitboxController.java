package Game.Hitbox;


import Vector.Vector2D;

public class HitboxController {

    public static boolean intersect(CircleHitbox circle, RectangleHitbox rect) {
        Vector2D rectCenter = rect.getCenter();
        Vector2D length = new Vector2D(circle.getX() - rectCenter.getX(), circle.getY() - rectCenter.getY());
        if (length.isAZeroVector()) {
            return true;
        }
        Vector2D direction = length.normalize();

        boolean checkX = Math.abs(length.getX()) < Math.abs(circle.getRadius() * direction.getX()) + rect.getWidth()/2;
        boolean checkY = Math.abs(length.getY()) < Math.abs(circle.getRadius() * direction.getY()) + rect.getHeight()/2;
        return ( checkX && checkY );
    }
}
