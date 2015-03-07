package ui.artists;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Pair;

public class NormalLineDrawHelper implements LineDrawHelper{

	private double intervalConstant = 10;

	@Override
	public Pair<Double, Double> drawLine(Canvas canvas, double x1, double y1, double x2,
			double y2) {
		GraphicsContext gContext = canvas.getGraphicsContext2D();
		gContext.beginPath();
		
		gContext.moveTo(x1, y1);
		int numIntervals = (int) (Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2))/ intervalConstant) ;
		double dx = (x1 - x2) / numIntervals;
		double dy = (y1 - y2) / numIntervals;
		double x = x1;
		double y = y1;
		
		for (int i = 0; i < numIntervals; i++) {
//			if (x > canvas.getWidth()) {
//				x = 0;
//				gContext.moveTo(x, y);
//			}
//			if (x < 0) {
//				x = canvas.getWidth();
//				gContext.moveTo(x, y);
//			}
//			if (y > canvas.getHeight()) {
//				y = 0;
//				gContext.moveTo(x, y);
//			}
//			if (y < 0) {
//				y = canvas.getHeight();
//				gContext.moveTo(x, y);
//			}
			
			gContext.lineTo(x - dx, y - dy);

			x -= dx;
			y -= dy;
		}
		
		gContext.closePath();
		gContext.stroke();
		
		return new Pair<Double, Double>(x, y);		
	}

}
