import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.graphics.GRect;
import java.util.ArrayList;
import java.util.List;

public class BreakoutCanvas {

    private final GCanvas canvas;
    private final GRect paddleView;
    private final GOval ballView;
    private final List<GRect> brickViews = new ArrayList<>();

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
}