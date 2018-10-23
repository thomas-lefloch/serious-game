package Vector;

public class Vector2D {

    private int x;
    private int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * adds 1 vector to the current vector
     * @param other the vector to be added
     */
    public void add(Vector2D other) {
        assert other != null : "v is null";
        this.x += other.x;
        this.y += other.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}