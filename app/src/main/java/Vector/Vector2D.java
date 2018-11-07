package Vector;

public class Vector2D implements Cloneable{

    private double x;
    private double y;

    public Vector2D(double x, double y) {
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

    /**
     * adds 1 scalar to the current vector
     * @param scalar the scalar to be added
     */
    public void addScalar(double scalar) {
        this.x += scalar;
        this.y += scalar;
    }

    /**
     * @return the non-squared length of the current vector
     */
    public double getMagnitude() {
        return this.x * this.x + this.y * this.y;
    }

    /**
     * @return the squared length of the current vector
     */
    public double getSquaredMagnitude() {
        return Math.sqrt(this.getMagnitude());
    }

    /**
     * divide the current vector by a scalar
     * @param scalar the number to divide the vector with
     * */
    public void divide (double scalar) {
        assert scalar != 0 : "cannot divide by 0";
        this.x = this.x / scalar;
        this.y = this.y / scalar;
    }

    /**
     * return a normalize version of this Vector2D
     * @return the normalize vector
     */
    public Vector2D normalize() {
        Vector2D normalized = this.clone();
        normalized.divide(normalized.getSquaredMagnitude());
        return normalized;
    }

    /**
     * clone the current Vector2D
     * @return the clone of the Vector2D
     */
    @Override
    public Vector2D clone() {
        Vector2D newV = null;
        try {
            newV = (Vector2D) super.clone();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newV;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
