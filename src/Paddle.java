public class Paddle {
    
    private static final int MIN_X = 0;
    private static final int MAX_X = Constants.CANVAS_WIDTH - Constants.PADDLE_WIDTH;

    private int x;
    private final int y;

    public Paddle() {
        x = (Constants.CANVAS_WIDTH - Constants.PADDLE_WIDTH) / 2;
        y = Constants.PADDLE_Y;
    }

    public void moveLeft() {
        x = Math.max(MIN_X, x - Constants.PADDLE_SPEED);
    }

    public void moveRight() {
        x = Math.min(MAX_X, x + Constants.PADDLE_SPEED);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}