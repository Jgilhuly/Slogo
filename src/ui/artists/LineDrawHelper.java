package ui.artists;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;

public abstract class LineDrawHelper {
	public abstract Pair<Double, Double> drawLine(Canvas canvas, double x1, double y1, double x2, double y2);
	
	public void checkEdges(Canvas canvas, GraphicsContext gContext, double x, double y) {
		if (x > canvas.getWidth()) {
			x = 0;
			gContext.moveTo(x, y);
		}
		if (x < 0) {
			x = canvas.getWidth();
			gContext.moveTo(x, y);
		}
		if (y > canvas.getHeight()) {
			y = 0;
			gContext.moveTo(x, y);
		}
		if (y < 0) {
			y = canvas.getHeight();
			gContext.moveTo(x, y);
		}
	}
}
