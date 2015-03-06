package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class Pen {
	private Color myColor;
	private boolean penIsDown;
	private Canvas myCanvas;
	private double canvasCenterX;
	private double canvasCenterY;
	
	public Pen(Canvas canvasIn, Color startingColor, boolean penIsDownIn) {
		myCanvas = canvasIn;
		myColor = startingColor;
		penIsDown = penIsDownIn;
		canvasCenterX = myCanvas.getWidth() / 2;
		canvasCenterY = myCanvas.getHeight() / 2;
	}
	
	public void draw(double x1, double y1, double x2, double y2,
			 boolean hasTurtle) {
		myCanvas.getGraphicsContext2D().setStroke(myColor);
		myCanvas.getGraphicsContext2D().setLineWidth(3);

		// minus y since it's flipped in the canvas
		if (penIsDown)
			myCanvas.getGraphicsContext2D().strokeLine(x1, y1,
					canvasCenterX + x2, canvasCenterY - y2);
	}

	public void setPenIsDown(boolean penIsDownIn) {
		penIsDown = penIsDownIn;
	}
	
	public Color getColor() {
		return myColor;
	}

	public void setColor(Color color) {
		myColor = color;
	}
}
