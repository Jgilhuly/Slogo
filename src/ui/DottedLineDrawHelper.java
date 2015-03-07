package ui;

import javafx.scene.canvas.Canvas;


public class DottedLineDrawHelper extends DashedLineDrawHelper implements LineDrawHelper{
	
	public void drawLine(Canvas canvas, double x1, double y1, double x2,
			double y2) {
		intervalConstant = 5;
		super.drawLine(canvas, x1, y1, x2, y2);
	}
}
