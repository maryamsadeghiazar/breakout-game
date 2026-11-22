import acm.util.RandomGenerator;

public class Ball {

    private double x;
    private double y;
    private double vx;
    private double vy;

    public void placeOnPaddle(Paddle paddle) {
        x = paddle.getX() + Constants.PADDLE_WIDTH / 2.0 - Constants.BALL_RADIUS;
        y = paddle.getY() - Constants.BALL_DIAMETER;
        vx = 0;
        vy = 0;
    }

    public void launch() {
        double maxAngle = Constants.BALL_LAUNCH_MAX_ANGLE_DEG;
        double angle = Math.toRadians(
                RandomGenerator.getInstance().nextDouble(-maxAngle, maxAngle));
        vx = Constants.BALL_SPEED * Math.sin(angle);
        vy = -Constants.BALL_SPEED * Math.cos(angle);
    }

    public void move() {
        x += vx;
        y += vy;
    }

    public void bounceOffWalls() {
        if (x < 0) {
            x = 0;
            reverseX();
        } else if (x + Constants.BALL_DIAMETER > Constants.CANVAS_WIDTH) {
            x = Constants.CANVAS_WIDTH - Constants.BALL_DIAMETER;
            reverseX();
        }
        if (y < Constants.WALL_TOP_Y) {
            y = Constants.WALL_TOP_Y;
            reverseY();
        }
    }

    public boolean isLost() {
        return y + Constants.BALL_DIAMETER > Constants.CANVAS_HEIGHT;
    }

        public boolean hitsPaddle(Paddle paddle) {
        boolean movingDown = vy > 0;
        boolean overlapsX = x + Constants.BALL_DIAMETER > paddle.getX()
                && x < paddle.getX() + Constants.PADDLE_WIDTH;
        boolean overlapsY = y + Constants.BALL_DIAMETER >= paddle.getY()
                && y < paddle.getY() + Constants.PADDLE_HEIGHT;
        return movingDown && overlapsX && overlapsY;
    }

    public void bounceOffPaddle(Paddle paddle) {
        double paddleCenter = paddle.getX() + Constants.PADDLE_WIDTH / 2.0;
        double ballCenter = x + Constants.BALL_RADIUS;
        double halfWidth = Constants.PADDLE_WIDTH / 2.0;

        double offset = (ballCenter - paddleCenter) / halfWidth;
        offset = Math.max(-1.0, Math.min(1.0, offset));

        double angle = Math.toRadians(offset * Constants.BALL_MAX_BOUNCE_ANGLE_DEG);
        double speed = Math.hypot(vx, vy);

        vx = speed * Math.sin(angle);
        vy = -Math.abs(speed * Math.cos(angle));
        y = paddle.getY() - Constants.BALL_DIAMETER;
    }

    public void reverseX() { vx = -vx; }
    public void reverseY() { vy = -vy; }

    public double getX() { return x; }
    public double getY() { return y; }
    public int getRadius() { return Constants.BALL_RADIUS; }
    public double getVx() { return vx; }
    public double getVy() { return vy; }
    public void setVx(double vx) { this.vx = vx; }
    public void setVy(double vy) { this.vy = vy; }
    public void setY(double y) { this.y = y; }
}