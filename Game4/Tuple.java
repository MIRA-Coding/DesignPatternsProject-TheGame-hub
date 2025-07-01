package Game4;

/**
 * Represents a 2D coordinate with optional forward components (xf, yf).
 */
public class Tuple {
    
    public int x; 
    public int y; 
    public int xf;
    public int yf;

    /**
     * Constructs a Tuple with x and y values.
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public Tuple(int x, int y) { 
        this.x = x; 
        this.y = y; 
    } 

    /**
     * Changes the current x and y values of this Tuple.
     *
     * @param x The new x-coordinate
     * @param y The new y-coordinate
     */
    public void ChangeData(int x, int y) {
        this.x = x; 
        this.y = y; 
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXf() {
        return xf;
    }

    public int getYf() {
        return yf;
    }
}
