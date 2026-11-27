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
    private volatile boolean pauseRequested;
    private volatile boolean resetRequested;
    private volatile boolean pauseKeyDown;
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
        if (keyCode == KeyEvent.VK_R) resetRequested = true;
        if (keyCode == KeyEvent.VK_P && !pauseKeyDown) {
            pauseKeyDown = true;
            pauseRequested = true;
        }
    }

    public void keyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) leftPressed = false;
        if (keyCode == KeyEvent.VK_RIGHT) rightPressed = false;
        if (keyCode == KeyEvent.VK_P) pauseKeyDown = false;
    }

    public void run() {
        while (true) {
            update();
            view.draw(paddle, ball, bricks);
            view.drawHud(lives, score, highScore);
            view.drawMessage(state, score);
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
        if (resetRequested) {
            resetRequested = false;
            resetGame();
            return;
        }
        handlePauseRequest();
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

    private void handlePauseRequest() {
        if (!pauseRequested) return;
        pauseRequested = false;
        if (state == GameState.RUNNING) {
            state = GameState.PAUSED;
        } else if (state == GameState.PAUSED) {
            state = GameState.RUNNING;
        }
    }

    private void resetGame() {
        paddle.reset();
        for (Brick brick : bricks) {
            brick.reset();
        }
        remainingBricks = bricks.size();
        lives = Constants.INITIAL_LIVES;
        score = 0;
        launchRequested = false;
        ball.placeOnPaddle(paddle);
        state = GameState.READY;
    }

    private void movePaddle() {
        if (leftPressed) paddle.moveLeft();
        if (rightPressed) paddle.moveRight();
    }

    private void updateReady() {
        movePaddle();
        ball.placeOnPaddle(paddle);
        if (launchRequested) {
            launchRequested = false;
            ball.launch();
            state = GameState.RUNNING;
        }
    }

    private void updateRunning() {
        launchRequested = false;
        movePaddle();
        ball.move();
        ball.bounceOffWalls();
        if (ball.hitsPaddle(paddle)) {
            bounceBallOffPaddle();
        }
        handleBrickCollision();
        if (remainingBricks == 0) {
            state = GameState.VICTORY;
        } else if (ball.isLost()) {
            loseLife();
        }
    }

    private void bounceBallOffPaddle() {
        // Calculate horizontal offset and half-width as separate variables
        double paddleCenter = paddle.getX() + paddle.getWidth() / 2.0;
        double ballCenter = ball.getX() + ball.getRadius();
        double deltaX = ballCenter - paddleCenter;
        double halfWidth = paddle.getWidth() / 2.0;

        double offset = deltaX / halfWidth;
        offset = Math.max(-1.0, Math.min(1.0, offset));

        // Calculate bounce angle (max 60 deg) and preserve total velocity
        double angle = offset * Math.toRadians(Constants.BALL_MAX_BOUNCE_ANGLE_DEG);
        double speed = Math.hypot(ball.getVx(), ball.getVy());

        // Apply new velocity components (always deflect upwards)
        ball.setVx(speed * Math.sin(angle));
        ball.setVy(-Math.abs(speed * Math.cos(angle)));

        // Prevent the ball from sticking inside the paddle
        ball.setY(paddle.getY() - ball.getRadius() * 2);
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