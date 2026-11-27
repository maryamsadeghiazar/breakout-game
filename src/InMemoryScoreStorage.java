public class InMemoryScoreStorage implements HighScoreStorage {

    private int highScore;

    @Override
    public int loadHighScore() {
        return highScore;
    }

    @Override
    public void saveHighScore(int score) {
        highScore = score;
    }
}