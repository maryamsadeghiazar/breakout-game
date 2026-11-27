public class Brick {

    private final int x;
    private final int y;
    private boolean destroyed;

    public Brick(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void hit() { destroyed = true; }

    public boolean isDestroyed() { return destroyed; }
    public int getX() { return x; }
    public int getY() { return y; }

    public void reset() { destroyed = false; }
}