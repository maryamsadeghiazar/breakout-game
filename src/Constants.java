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

    private Constants() {
    }
}