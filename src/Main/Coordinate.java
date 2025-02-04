package Main;

public class Coordinate {
    private int x;
    private int y;
    private int screenWidth;
    private int screenHeight;

    public Coordinate(int x, int y, int canvasWidth, int canvasHeight) {
        this.x = x;
        this.y = y;
        this.screenWidth = canvasWidth;
        this.screenHeight = canvasHeight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Coordinate c) {
        return x == c.getX() && y == c.getY();
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean checkBounds() {
        return this.x >= 0 && this.x < screenWidth && this.y >= 0 && this.y < screenHeight;
    }
}
