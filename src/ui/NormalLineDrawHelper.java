package ui;

import javafx.scene.canvas.Canvas;
import javafx.util.Pair;

public class NormalLineDrawHelper implements LineDrawHelper{

	@Override
	public Pair<Double, Double> drawLine(Canvas canvas, double x1, double y1, double x2,
			double y2) {
		canvas.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
	}

}
