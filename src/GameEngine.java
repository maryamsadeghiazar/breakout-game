import java.awt.event.KeyEvent;

public class GameEngine {

    private final Paddle paddle;
    private final Ball ball;
    private final BreakoutCanvas view;
    private volatile boolean leftPressed;
    private volatile boolean rightPressed;
    private volatile boolean launchRequested;
    private GameState state;
    private int lives;

    public GameEngine(BreakoutCanvas view) {
        this.view = view;
        this.paddle = new Paddle();
        this.ball = new Ball();
        this.state = GameState.READY;
        this.lives = Constants.INITIAL_LIVES;
    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = true;
        if (keyCode == KeyEvent.VK_RIGHT) rightPressed = true;
        if (keyCode == KeyEvent.VK_SPACE) launchRequested = true;
    }

    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = false;
        if (keyCode == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    public void run() {
        while (true) {
            update();
            view.draw(paddle, ball);
            waitForNextFrame();
        }
    }

    private void update() {
        movePaddle();
        switch (state) {
            case READY:
                updateReady();
                break;
            case RUNNING:
                updateRunning();
                break;
            default:
                break;
        }
    }

    private void movePaddle() {
        if (leftPressed) paddle.moveLeft();
        if (rightPressed) paddle.moveRight();
    }

    private void updateReady() {
        ball.placeOnPaddle(paddle);
        if (launchRequested) {
            launchRequested = false;
            ball.launch();
            state = GameState.RUNNING;
        }
    }

        private void updateRunning() {
        launchRequested = false;
        ball.move();
        ball.bounceOffWalls();
        if (ball.hitsPaddle(paddle)) {
            ball.bounceOffPaddle(paddle);
        }
        if (ball.isLost()) {
            loseLife();
        }
    }

    private void loseLife() {
        lives--;
        if (lives > 0) {
            state = GameState.READY;
            ball.placeOnPaddle(paddle);
        } else {
            state = GameState.GAME_OVER;
        }
    }

    private void waitForNextFrame() {
        try {
            Thread.sleep(Constants.FRAME_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}