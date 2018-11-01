package Game.Hitbox;

public interface Hitbox {
    boolean intersect(Hitbox other) throws HitboxNotRecognizeException;
}
