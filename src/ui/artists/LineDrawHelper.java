package ui.artists;

import javafx.scene.canvas.Canvas;
import javafx.util.Pair;


public interface LineDrawHelper {
    public Pair<Double, Double> drawLine (Canvas canvas, double x1, double y1, double x2, double y2);
}
