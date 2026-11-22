import java.awt.Color;

public final class Constants {

    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 600;
    public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 15;
    public static final int PADDLE_Y = 545;
    public static final int PADDLE_SPEED = 8;
    public static final Color PADDLE_COLOR = Color.BLACK;
    public static final int FRAME_DELAY_MS = 16;

    public static final int BALL_RADIUS = 8;
    public static final int BALL_DIAMETER = BALL_RADIUS * 2;
    public static final double BALL_SPEED = 6.0;
    public static final double BALL_LAUNCH_MAX_ANGLE_DEG = 30.0;
    public static final Color BALL_COLOR = Color.WHITE;
    public static final int WALL_TOP_Y = 0;
    public static final double BALL_MAX_BOUNCE_ANGLE_DEG = 60.0;
    public static final int INITIAL_LIVES = 3;

    public static final int BRICK_ROWS = 5;
    public static final int BRICK_COLS = 8;
    public static final int BRICK_WIDTH = 85;
    public static final int BRICK_HEIGHT = 25;
    public static final int BRICK_GAP = 6;
    public static final int BRICKS_TOP = 60;
    public static final int BRICKS_LEFT = 39;
    public static final int BRICK_POINTS = 100;
    public static final Color[] BRICK_COLORS = {
        Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN
    };

    private Constants() {
    }
}