import acm.graphics.GCanvas;
import acm.graphics.GRect;

public class BreakoutCanvas {

    private final GRect paddleView;

    public BreakoutCanvas(GCanvas canvas) {
        paddleView = new GRect(Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);
        paddleView.setFilled(true);
        paddleView.setColor(Constants.PADDLE_COLOR);
        canvas.add(paddleView);
    }

    public void draw(Paddle paddle) {
        paddleView.setLocation(paddle.getX(), paddle.getY());
    }
}