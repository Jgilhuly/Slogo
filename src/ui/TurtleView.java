package ui;

import java.util.Observable;
import java.util.Observer;

import model.Turtle;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TurtleView implements Observer {
	private ImageView myImageView;
	private Canvas myCanvas;
	private Color myColor;
	private double canvasCenterX;
	private double canvasCenterY;
	private double myHeading; // in degrees, 0 is north
	private double myWidthOffset;
	private double myHeightOffset;
	private StackPane myCanvasHolder;
	private StackPane turtlePane;

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
		
		myWidthOffset = myCanvas.getWidth()/2;
		myHeightOffset = myCanvas.getHeight()/2;
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

	// public void draw() {
	// // myCanvas.getGraphicsContext2D().rotate(myHeading);
	// myCanvas.getGraphicsContext2D().drawImage(myImageView.getImage(),
	// getCenterX(), getCenterY(), myCanvas.getHeight() / 10,
	// myCanvas.getWidth() / 10);
	// }

	private void draw(double x1, double y1, double x2, double y2) {
		myCanvas.getGraphicsContext2D().setFill(myColor);
		myCanvas.getGraphicsContext2D().setLineWidth(3);
		// minus y since it's flipped in the canvas
		myCanvas.getGraphicsContext2D().strokeLine(x1, y1, canvasCenterX + x2,
				canvasCenterY - y2);
		// move image
		myImageView.setTranslateX(x2);
		myImageView.setTranslateY(-y2);
		
		// rotate image
		myImageView.setRotate(myHeading);
	}

	@Override
	public void update(Observable o, Object arg) {
		Turtle tModel = (Turtle) o;
		double newX = tModel.getX();
		double newY = tModel.getY();
		double newHeading = tModel.getHeading();

		if (myHeading != newHeading) {
			myHeading = newHeading;
			myCanvas.getGraphicsContext2D().save();
			myCanvas.getGraphicsContext2D().rotate(myHeading);
		}
		myHeading = newHeading;
		if (newX != getCenterX() || newY != getCenterY()) {
			draw(myImageView.getX(), myImageView.getY(), newX, newY);
		}
	}

	private double getCenterX() {
		return (myImageView.getX() - (myCanvas.getWidth() / 20)) - myWidthOffset;
	}

	private double getCenterY() {
		return (myImageView.getY() - (myCanvas.getHeight() / 20)) - myHeightOffset;
	}

}
