import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import java.util.ArrayList;
import java.util.List;

public class BreakoutCanvas {

    private final GCanvas canvas;
    private final GRect paddleView;
    private final GOval ballView;
    private final List<GRect> brickViews = new ArrayList<>();
    private final GLabel livesLabel;
    private final GLabel scoreLabel;
    private final GLabel highScoreLabel;
    private final GLabel messageLabel;
    private final GLabel hintLabel;

    public BreakoutCanvas(GCanvas canvas) {
        this.canvas = canvas;

        paddleView = new GRect(Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);
        paddleView.setFilled(true);
        paddleView.setColor(Constants.PADDLE_COLOR);
        canvas.add(paddleView);

        ballView = new GOval(Constants.BALL_DIAMETER, Constants.BALL_DIAMETER);
        ballView.setFilled(true);
        ballView.setColor(Constants.BALL_COLOR);
        canvas.add(ballView);

        GRect hudBar = new GRect(0, 0, Constants.CANVAS_WIDTH, Constants.HUD_HEIGHT);
        hudBar.setFilled(true);
        hudBar.setColor(Constants.HUD_BACKGROUND_COLOR);
        canvas.add(hudBar);

        livesLabel = createHudLabel(canvas);
        scoreLabel = createHudLabel(canvas);
        highScoreLabel = createHudLabel(canvas);
        messageLabel = createMessageLabel(canvas, Constants.MESSAGE_FONT);
        hintLabel = createMessageLabel(canvas, Constants.MESSAGE_HINT_FONT);
    }

    public void initBricks(List<Brick> bricks) {
        for (int i = 0; i < bricks.size(); i++) {
            Brick brick = bricks.get(i);
            int row = i / Constants.BRICK_COLS;
            GRect view = new GRect(brick.getX(), brick.getY(),
                    Constants.BRICK_WIDTH, Constants.BRICK_HEIGHT);
            view.setFilled(true);
            view.setColor(Constants.BRICK_COLORS[row]);
            canvas.add(view);
            brickViews.add(view);
        }
    }

    public void draw(Paddle paddle, Ball ball, List<Brick> bricks) {
        paddleView.setLocation(paddle.getX(), paddle.getY());
        ballView.setLocation(ball.getX(), ball.getY());
        for (int i = 0; i < bricks.size(); i++) {
            brickViews.get(i).setVisible(!bricks.get(i).isDestroyed());
        }
    }

        public void drawHud(int lives, int score, int highScore) {
        livesLabel.setLabel("Lives: " + lives);
        scoreLabel.setLabel("Score: " + score);
        highScoreLabel.setLabel("Best: " + highScore);

        placeLabel(livesLabel, Constants.HUD_MARGIN);
        placeLabel(scoreLabel,
                (Constants.CANVAS_WIDTH - scoreLabel.getWidth()) / 2);
        placeLabel(highScoreLabel,
                Constants.CANVAS_WIDTH - highScoreLabel.getWidth() - Constants.HUD_MARGIN);
    }

    private GLabel createHudLabel(GCanvas canvas) {
        GLabel label = new GLabel("");
        label.setFont(Constants.HUD_FONT);
        label.setColor(Constants.HUD_TEXT_COLOR);
        canvas.add(label);
        return label;
    }

    private void placeLabel(GLabel label, double x) {
        double y = (Constants.HUD_HEIGHT + label.getAscent()) / 2;
        label.setLocation(x, y);
    }

    public void drawMessage(GameState state, int score) {
        switch (state) {
            case PAUSED:
                showMessage(Constants.MSG_PAUSED, Constants.HINT_PAUSED);
                break;
            case GAME_OVER:
                showMessage(Constants.MSG_GAME_OVER,
                    "Score: " + score + " - " + Constants.HINT_RESTART
                );
                break;
            case VICTORY:
                showMessage(Constants.MSG_VICTORY,
                    "Score: " + score + " - " + Constants.HINT_RESTART
                );
                break;
            default:
                showMessage("", "");
                break;
        }
    }

    private void showMessage(String title, String hint) {
        messageLabel.setLabel(title);
        hintLabel.setLabel(hint);
        centerLabel(messageLabel, Constants.CANVAS_HEIGHT / 2.0);
        centerLabel(hintLabel,
            Constants.CANVAS_HEIGHT / 2.0 + Constants.MESSAGE_HINT_OFFSET
        );
    }

    private void centerLabel(GLabel label, double y) {
        label.setLocation((Constants.CANVAS_WIDTH - label.getWidth()) / 2, y);
    }

    private GLabel createMessageLabel(GCanvas canvas, String font) {
        GLabel label = new GLabel("");
        label.setFont(font);
        label.setColor(Constants.MESSAGE_COLOR);
        canvas.add(label);
        return label;
    }
}