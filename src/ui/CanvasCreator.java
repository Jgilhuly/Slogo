package ui;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class CanvasCreator {
	/**
	 * Creates the canvas that the turtle lives on
	 * 
	 * @return
	 */
	public static Canvas makeCanvas(double x, double y) {

		Canvas canvas = new Canvas(x, y);

		return canvas;
	}

	public static StackPane makeCanvasHolder(Canvas canvas, Color backgroundColor) {
		StackPane canvasHolder = new StackPane();

		canvasHolder.setBackground(new Background(new BackgroundFill(
				backgroundColor, null, null)));
		return canvasHolder;
	}
}
