import acm.program.GraphicsProgram;
import java.awt.event.KeyEvent;

public class BreakoutGame extends GraphicsProgram {

    private GameEngine engine;

    @Override
    public void init() {
        setSize(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        setBackground(Constants.BACKGROUND_COLOR);
        addKeyListeners();
    }

    @Override
    public void run() {
        fitCanvasToWindow();
        BreakoutCanvas view = new BreakoutCanvas(getGCanvas());
        engine = new GameEngine(view);
        engine.run();
    }

    @Override 
    public void keyPressed(KeyEvent e) {
        if (engine != null) {
            engine.keyPressed(e.getKeyCode());
        }
    }

    @Override 
    public void keyReleased(KeyEvent e) {
        if (engine != null) {
            engine.keyReleased(e.getKeyCode());
        }
    }

    private void fitCanvasToWindow() {
        int extraWidth = Constants.CANVAS_WIDTH - getWidth();
        int extraHeight = Constants.CANVAS_HEIGHT - getHeight();
        setSize(Constants.CANVAS_WIDTH + extraWidth,
                Constants.CANVAS_HEIGHT + extraHeight);
    }

       public static void main(String[] args) {
        new BreakoutGame().start();
    }
}