import java.awt.event.KeyEvent;

public class GameEngine {

    private final Paddle paddle;
    private final BreakoutCanvas view;
    private volatile boolean leftPressed;
    private volatile boolean rightPressed;

    public GameEngine(BreakoutCanvas view) {
        this.view = view;
        this.paddle = new Paddle();
    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = true;
        if (keyCode == KeyEvent.VK_RIGHT) rightPressed = true;
    }

    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = false;
        if (keyCode == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    private void waitForNextFrame() {
        try {
            Thread.sleep(Constants.FRAME_DELAY_MS);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void run() {
        while (true) {
            update();
            view.draw(paddle);
            waitForNextFrame();
        }
    }

    private void update() {
        if (leftPressed) {
            paddle.moveLeft();
        }
        if (rightPressed) {
            paddle.moveRight();
        }
    }
}