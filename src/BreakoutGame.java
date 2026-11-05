import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import java.awt.event.KeyEvent;

public class BreakoutGame extends GraphicsProgram {

    private Paddle paddle;
    private GRect paddleView;
    private boolean leftPressed;
    private boolean rightPressed;

    @Override
    public void init() {
        setSize(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        setBackground(Constants.BACKGROUND_COLOR);
        addKeyListeners();
    }

    @Override
    public void run() {
        fitCanvasToWindow();

        paddle = new Paddle();
        paddleView = new GRect(Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT);
        paddleView.setFilled(true);
        paddleView.setColor(Constants.PADDLE_COLOR);
        add(paddleView, paddle.getX(), paddle.getY());

        while (true) {
            if (leftPressed) {
                paddle.moveLeft();
            }
            if (rightPressed) {
                paddle.moveRight();
            }
            paddleView.setLocation(paddle.getX(), paddle.getY());
            pause(Constants.FRAME_DELAY_MS);
        }
    }

    private void fitCanvasToWindow() {
        int extraWidth = Constants.CANVAS_WIDTH - getWidth();
        int extraHeight = Constants.CANVAS_HEIGHT - getHeight();
        setSize(Constants.CANVAS_WIDTH + extraWidth,
                Constants.CANVAS_HEIGHT + extraHeight);
    }

    @Override 
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = true;
    }

    @Override 
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) leftPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    public static void main(String[] args) {
        new BreakoutGame().start();
    }
}