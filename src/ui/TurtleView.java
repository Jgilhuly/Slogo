package ui;

import java.util.Observable;
import java.util.Observer;

import model.Turtle;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TurtleView implements Observer {
	private ImageView myImageView;
	private Canvas myCanvas;
	private Color myColor;
	private double canvasCenterX;
	private double canvasCenterY;
	private double myHeading; // in degrees, 0 is north
	private StackPane myCanvasHolder;

	public TurtleView(Image imageIn, Canvas canvasIn, Color colorIn,
			double xIn, double yIn, StackPane canvasHolder) {
		myImageView = new ImageView();
		myCanvas = canvasIn;
		myImageView.setImage(imageIn);
		canvasCenterX = myCanvas.getWidth() / 2;
		canvasCenterY = myCanvas.getHeight() / 2;
		myImageView.setX(xIn);
		myImageView.setY(yIn);
		myColor = colorIn;
		myCanvas.toBack();

		// I added an ImageView of the turtle onto StackPane
		myCanvasHolder = canvasHolder;
		addTurtle(myImageView);

		myHeading = 50;
		myImageView.setPreserveRatio(true);
		myImageView.setSmooth(true);
	}

	/**
	 * 
	 * @param ID
	 *            : ID of turtle
	 * @param turtle
	 *            : Image of turtle
	 */
	private void addTurtle(ImageView turtle) {
		myImageView.setScaleX(0.1);
		myImageView.setScaleY(0.1);
		myCanvasHolder.getChildren().add(turtle);
	}

	public ImageView getImageView() {
		return myImageView;
	}

	private void draw(double x1, double y1, double x2, double y2,
			boolean hasLine, boolean hasTurtle) {
		myCanvas.getGraphicsContext2D().setStroke(myColor);
		myCanvas.getGraphicsContext2D().setLineWidth(3);
		// minus y since it's flipped in the canvas
		if (hasLine)
			myCanvas.getGraphicsContext2D().strokeLine(x1, y1,
					canvasCenterX + x2, canvasCenterY - y2);
		// move image
		myImageView.setTranslateX(x2);
		myImageView.setTranslateY(-y2);
		// set values - different coordinates
		myImageView.setX(canvasCenterX + x2);
		myImageView.setY(canvasCenterY - y2);

		// rotate image
		myImageView.setRotate(myHeading);
		myImageView.setVisible(hasTurtle);
	}

	@Override
	public void update(Observable o, Object arg) {
		Turtle tModel = (Turtle) o;
		double newX = tModel.getX();
		double newY = tModel.getY();
		double newHeading = tModel.getHeading();
		myHeading = newHeading;
		if (newX != getCenterX() || newY != getCenterY()) {
			draw(myImageView.getX(), myImageView.getY(), newX, newY,
					tModel.getLine(), tModel.getVisibility());
		}
		if (tModel.getCleared()) {
			myCanvas.getGraphicsContext2D().clearRect(0, 0,
					myCanvas.getWidth(), myCanvas.getHeight());
		}

	}

	private double getCenterX() {
		return myImageView.getX() - (myCanvas.getWidth() / 20);
	}

	private double getCenterY() {
		return myImageView.getY() - (myCanvas.getHeight() / 20);
	}

}
