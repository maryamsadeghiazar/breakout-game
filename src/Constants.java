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

    public static final int HUD_HEIGHT = 35;
    public static final int HUD_MARGIN = 10;
    public static final String HUD_FONT = "SansSerif-BOLD-16";
    public static final Color HUD_BACKGROUND_COLOR = new Color(255, 214, 224);
    public static final Color HUD_TEXT_COLOR = Color.BLACK;

    public static final int BALL_RADIUS = 8;
    public static final int BALL_DIAMETER = BALL_RADIUS * 2;
    public static final double BALL_SPEED = 6.0;
    public static final double BALL_LAUNCH_MAX_ANGLE_DEG = 30.0;
    public static final Color BALL_COLOR = Color.WHITE;
    public static final int WALL_TOP_Y = HUD_HEIGHT;
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
        new Color(255, 154, 162),
        new Color(255, 183, 133),
        new Color(255, 236, 153),
        new Color(181, 234, 170),
        new Color(158, 224, 224)
    };

    public static final String MESSAGE_FONT = "SansSerif-BOLD-40";
    public static final String MESSAGE_HINT_FONT = "SansSerif-18";
    public static final Color MESSAGE_COLOR = Color.WHITE;
    public static final int MESSAGE_HINT_OFFSET = 35;
    public static final String MSG_PAUSED = "PAUSED";
    public static final String MSG_GAME_OVER = "GAME OVER";
    public static final String MSG_VICTORY = "YOU WIN!";
    public static final String HINT_PAUSED = "Press P to resume";
    public static final String HINT_RESTART = "Press R to restart";

    public static final int STRONG_BRICK_ROWS = 1;
    public static final int STRONG_BRICK_HITS = 2;
    public static final Color STRONG_BRICK_COLOR = new Color(176, 160, 190);

    private Constants() {
    }

}