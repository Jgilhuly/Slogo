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
	private StackPane myCanvasHolder;
	private Image image;

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
		myCanvasHolder = canvasHolder;

		myHeading = 50;
		myImageView.setPreserveRatio(true);
		myImageView.setSmooth(true);
	}

	public ImageView getImageView() {
		return myImageView;
	}
	// remove duplicate code - refactor
	//********************************
	public void draw() {
		myCanvas.getGraphicsContext2D().drawImage(myImageView.getImage(),
				getCenterX(), getCenterY(), myCanvas.getHeight() / 10,
				myCanvas.getWidth() / 10);
	}

	private void redraw(double newx, double newy) {
		myCanvas = CanvasCreator.makeCanvas(myCanvas.getWidth(),  myCanvas.getHeight());
		myCanvas.getGraphicsContext2D().drawImage(myImageView.getImage(),
				newx, newy, myCanvas.getHeight() / 10,
				myCanvas.getWidth() / 10);
	}
	//********************************
	public void updateImage(double newx, double newy) {
		myImageView = new ImageView();
		myImageView.setImage(image);
		myImageView.setX(canvasCenterX + newx);
		myImageView.setY(canvasCenterY + newy);
		redraw(canvasCenterX + newx, canvasCenterY + newy);
	}

	private void drawLine(double x1, double y1, double x2, double y2) {
		myCanvas.getGraphicsContext2D().setFill(myColor);
		myCanvas.getGraphicsContext2D().setLineWidth(3);
		// minus y since it's flipped in the canvas
		myCanvas.getGraphicsContext2D().strokeLine(x1, y1, canvasCenterX + x2,
				canvasCenterY - y2);
		updateImage(x2, -y2);
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
			drawLine(myImageView.getX(), myImageView.getY(), newX, newY);
			// draw();
		}
	}

	private double getCenterX() {
		return myImageView.getX() - (myCanvas.getWidth() / 20);
	}

	private double getCenterY() {
		return myImageView.getY() - (myCanvas.getHeight() / 20);
	}

}
