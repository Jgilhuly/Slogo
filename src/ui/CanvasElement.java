package ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class CanvasElement {
	StackPane canvasHolder;
	Canvas canvas;
	
	public CanvasElement (Color color, double width, double height) {
		canvasHolder = new StackPane();
		canvas = new Canvas(width / 1.5, height / 1.5);

		canvasHolder.getChildren().add(canvas);

		canvasHolder.setBackground(new Background(new BackgroundFill(color, null, null)));
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public StackPane getBaseNode() {
		return canvasHolder;
	}

	public void setBackground(Background background) {
		canvasHolder.setBackground(background);
	}
}
