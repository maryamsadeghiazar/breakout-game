import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {

    private final Paddle paddle;
    private final Ball ball;
    private final List<Brick> bricks;
    private final BreakoutCanvas view;
    private volatile boolean leftPressed;
    private volatile boolean rightPressed;
    private volatile boolean launchRequested;
    private GameState state;
    private int lives;
    private int score;
    private int highScore;
    private int remainingBricks;

    public GameEngine(BreakoutCanvas view) {
        this.view = view;
        this.paddle = new Paddle();
        this.ball = new Ball();
        this.bricks = createBricks();
        this.remainingBricks = bricks.size();
        this.state = GameState.READY;
        this.lives = Constants.INITIAL_LIVES;
        view.initBricks(bricks);
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
            view.draw(paddle, ball, bricks);
            view.drawHud(lives, score, highScore);
            waitForNextFrame();
        }
    }

    private List<Brick> createBricks() {
        List<Brick> list = new ArrayList<>();
        for (int row = 0; row < Constants.BRICK_ROWS; row++) {
            for (int col = 0; col < Constants.BRICK_COLS; col++) {
                int x = Constants.BRICKS_LEFT
                        + col * (Constants.BRICK_WIDTH + Constants.BRICK_GAP);
                int y = Constants.BRICKS_TOP
                        + row * (Constants.BRICK_HEIGHT + Constants.BRICK_GAP);
                list.add(new Brick(x, y));
            }
        }
        return list;
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
        handleBrickCollision();
        if (remainingBricks == 0) {
            state = GameState.VICTORY;
        } else if (ball.isLost()) {
            loseLife();
        }
    }

    private void handleBrickCollision() {
        for (Brick brick : bricks) {
            if (ball.hitsBrick(brick)) {
                ball.bounceOffBrick(brick);
                brick.hit();
                score += Constants.BRICK_POINTS;
                remainingBricks--;
                return;
            }
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