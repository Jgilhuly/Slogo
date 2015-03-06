package ui;

import java.util.Observable;
import java.util.Observer;

import model.Turtle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class TurtleView implements Observer {
	private ImageView myImageView;
	private Color myColor;
	private Canvas myCanvas;
	private double canvasCenterX;
	private double canvasCenterY;
	private DoubleProperty myHeading; // in degrees, 0 is north
	private double myWidthOffset;
	private double myHeightOffset;
	private StackPane myCanvasHolder;
	private boolean penIsDown;
	private boolean turtleIsVisible;

	public TurtleView(Image imageIn, Canvas canvasIn, Color colorIn,
			double xIn, double yIn, StackPane canvasHolder) {
		myImageView = new ImageView();
		myCanvas = canvasIn;
		myImageView.setImage(imageIn);
		myImageView.setX(xIn);
		myImageView.setY(yIn);
		myColor = colorIn;
		penIsDown = true;
		turtleIsVisible = true;
		myHeading = new SimpleDoubleProperty();
		
		canvasCenterX = myCanvas.getWidth() / 2;
		canvasCenterY = myCanvas.getHeight() / 2;
		myWidthOffset = canvasCenterX;
		myHeightOffset = canvasCenterY;
		myCanvas.toBack();

		myCanvasHolder = canvasHolder; // used to hold the imageView within the
										// canvas
		addTurtle(myImageView);

		myImageView.setPreserveRatio(true);
		myImageView.setSmooth(true);
	}

	/**
	 * Adds the imageView to the CanvasHolder
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

	/**
	 * Draws the Turtle and Line
	 * 
	 * @param x1
	 *            : Old X value
	 * @param y1
	 *            : Old Y value
	 * @param x2
	 *            : New X value
	 * @param y2
	 *            : New Y value
	 * @param hasLine
	 *            : Pen is down
	 * @param hasTurtle
	 *            : Turtle is visible
	 */
	private void draw(double x1, double y1, double x2, double y2,
			boolean hasLine, boolean hasTurtle) {
		myCanvas.getGraphicsContext2D().setStroke(myColor);
		myCanvas.getGraphicsContext2D().setLineWidth(3);

		// minus y since it's flipped in the canvas
		if (hasLine && penIsDown)
			myCanvas.getGraphicsContext2D().strokeLine(x1, y1,
					canvasCenterX + x2, canvasCenterY - y2);
		// move image
		myImageView.setTranslateX(x2);
		myImageView.setTranslateY(-y2);

		// set values - different coordinates
		myImageView.setX(canvasCenterX + x2);
		myImageView.setY(canvasCenterY - y2);

		// rotate image
		myImageView.setRotate(myHeading.doubleValue());
		myImageView.setVisible(hasTurtle && turtleIsVisible);
	}

	@Override
	/**
	 * Observer update method
	 */
	public void update(Observable o, Object arg) {
		Turtle tModel = (Turtle) o;
		double newX = tModel.getX();
		double newY = tModel.getY();
		double newHeading = tModel.getHeading();
		myHeading.set(newHeading);
		if (newX != getCenterX() || newY != getCenterY()) {
			draw(myImageView.getX(), myImageView.getY(), newX, newY,
					tModel.getLine(), tModel.getVisibility());
		}
		if (tModel.getCleared()) {
			myCanvas.getGraphicsContext2D().clearRect(0, 0,
					myCanvas.getWidth(), myCanvas.getHeight());
		}

	}

	/**
	 * Gets the Center X of the imageView
	 * 
	 * @return
	 */
	private double getCenterX() {
		return (myImageView.getX() - (myCanvas.getWidth() / 20))
				- myWidthOffset;
	}

	/**
	 * Gets the Center Y of the imageView
	 * 
	 * @return
	 */
	private double getCenterY() {
		return (myImageView.getY() - (myCanvas.getHeight() / 20))
				- myHeightOffset;
	}

	public Color getColor() {
		return myColor;
	}

	public void setColor(Color color) {
		myColor = color;
	}

	public ImageView getImageView() {
		return myImageView;
	}

	/**
	 * Re-sets up the imageView with a new Image, used when a new image is
	 * selected
	 * 
	 * @param image
	 */
	public void setImage(Image image) {
		myCanvasHolder.getChildren().remove(myImageView);

		double oldX = myImageView.getX();
		double oldY = myImageView.getY();
		myImageView = new ImageView(image);
		myImageView.setX(oldX);
		myImageView.setY(oldY);
		myCanvasHolder.getChildren().add(myImageView);
	}

	public void setPen(ToggleButton penUpDown) {
		penIsDown = !penUpDown.isSelected();
	}

	public void setTurtleVisible(ToggleButton turtleVisible) {
		turtleIsVisible = !turtleVisible.isSelected();
	}

	public DoubleProperty getHeading() {
		return myHeading;
	}

}
