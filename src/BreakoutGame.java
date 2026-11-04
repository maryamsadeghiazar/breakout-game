import acm.program.GraphicsProgram;


public class BreakoutGame extends GraphicsProgram {
     
    @Override
    public void init() {
        setSize(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        setBackground(Constants.BACKGROUND_COLOR);
    }

    @Override
    public void run() {
        fitCanvasToWindow();
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