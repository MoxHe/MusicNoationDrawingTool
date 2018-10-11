import marlin.Sandbox.ShapeTrainer;
import marlin.graphicsLib.Window;
import marlin.Sandbox.PaintInk;

public class Main {
    public static void main(String[] args){
//        Window.PANEL = new Squares();
//        Window.PANEL = new PaintInk();
        Window.PANEL = new ShapeTrainer();
        Window.launch();

    }
}
