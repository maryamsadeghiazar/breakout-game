public class Brick {

    private final int x;
    private final int y;
    private final int maxHits;
    private int hitsLeft;

    public Brick(int x, int y, int maxHits) {
        this.x = x;
        this.y = y;
        this.maxHits = maxHits;
        this.hitsLeft = maxHits;
    }

    public boolean hit() {
        hitsLeft--;
        return hitsLeft <= 0;
    }

    public void reset() { hitsLeft = maxHits; }

    public boolean isDestroyed() { return hitsLeft <= 0; }
    public boolean isStrong() { return hitsLeft > 1; }
    public int getX() { return x; }
    public int getY() { return y; }
}