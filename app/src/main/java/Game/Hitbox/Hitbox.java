package Game.Hitbox;


import Vector.Vector2D;

public class Hitbox {

    public static boolean intersect(CircleHitbox circle, RectangleHitbox rect) {
        Vector2D rectCenter = rect.getCenter();
        Vector2D direction = new Vector2D(circle.getX() - rectCenter.getX(), circle.getY() - rectCenter.getY()).normalize();

        return (circle.getX() + Math.abs(circle.getRadius() * direction.getX()) >= rect.getX() &&
                circle.getX() - Math.abs(circle.getRadius() * direction.getX()) <= rect.getX() + rect.getWidth() &&
                circle.getY() + Math.abs(circle.getRadius() * direction.getY()) >= rect.getY() &&
                circle.getY() - Math.abs(circle.getRadius() * direction.getY()) <= rect.getY() + rect.getHeight()
        );
    }
}
