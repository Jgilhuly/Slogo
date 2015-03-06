package ui;

import javafx.scene.canvas.Canvas;
import javafx.util.Pair;


public class DottedLineDrawHelper extends DashedLineDrawHelper implements LineDrawHelper{
	
	public Pair<Double, Double> drawLine(Canvas canvas, double x1, double y1, double x2,
			double y2) {
		intervalConstant = 5;
		return super.drawLine(canvas, x1, y1, x2, y2);
	}
}
