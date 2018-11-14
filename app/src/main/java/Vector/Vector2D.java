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
     * adds x and y to the x and y of the current vector
     * @param x offset
     * @param y offset
     */
    public void add(double x, double y) {
       this.x += x;
       this.y += y;
    }

    /**
     * adds 1 scalar to the current vector
     * @param scalar the scalar to be added
     */
    public void add(double scalar) {
        x += scalar;
        y += scalar;
    }

    /**
     * @return the non-squared length of the current vector
     */
    public double getMagnitude() {
        return x * x + y * y;
    }

    /**
     * @return the squared length of the current vector
     */
    public double getSquaredMagnitude() {
        return Math.sqrt(getMagnitude());
    }

    /**
     * divide the current vector by a scalar
     * @param scalar the number to divide the vector with
     * */
    public void divide (double scalar) throws ArithmeticException {
        if (scalar == 0) {
            throw new ArithmeticException("cannot divide by zero");
        }
        y = y / scalar;
        x = x / scalar;
    }

    /**
     * return a normalize version of this Vector2D
     * @return the normalize vector
     */
    public Vector2D normalize() {
        Vector2D normalized = clone();
        normalized.divide(normalized.getSquaredMagnitude());
        return normalized;
    }

    /**
     * @return true if the current vector is a zero vector (coordinates equals to 0)
     */
    public boolean isAZeroVector() {
        return (x == 0 && y == 0);
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

    public void multiply(double scalar) {
        this.multiplyX(scalar);
        this.multiplyY(scalar);
    }

    public void multiplyX(double scalar) {
        this.x *= scalar;
    }

    public void multiplyY(double scalar) {
        this.y *= scalar;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setValue(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
}
