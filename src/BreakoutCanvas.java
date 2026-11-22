import acm.graphics.GCanvas;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class BreakoutCanvas {

    private final GRect paddleView;
    private final GOval ballView;

    public BreakoutCanvas(GCanvas canvas) {
        paddleView = new GRect(Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);
        paddleView.setFilled(true);
        paddleView.setColor(Constants.PADDLE_COLOR);
        canvas.add(paddleView);

        ballView = new GOval(Constants.BALL_DIAMETER, Constants.BALL_DIAMETER);
        ballView.setFilled(true);
        ballView.setColor(Constants.BALL_COLOR);
        canvas.add(ballView);
    }

    public void draw(Paddle paddle, Ball ball) {
        paddleView.setLocation(paddle.getX(), paddle.getY());
        ballView.setLocation(ball.getX(), ball.getY());
    }
}