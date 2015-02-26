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

		myHeading = 0;
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

<<<<<<< HEAD
	public void draw() {
	        System.out.println(myCanvas.getLayoutX());
	        System.out.println(myCanvas.getLayoutY());
//		myCanvas.getGraphicsContext2D().rotate(myHeading);
		myCanvas.getGraphicsContext2D().drawImage(myImageView.getImage(),
				getCenterX(), getCenterY(), myCanvas.getHeight() / 10,
				myCanvas.getWidth() / 10);
=======
	public ImageView getImageView() {
		return myImageView;
>>>>>>> 0fd6b3ec70d9b29fee476d321381f65888672a6c
	}

	private void draw(double x1, double y1, double x2, double y2,
			boolean hasLine) {
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
	}

	@Override
	public void update(Observable o, Object arg) {
<<<<<<< HEAD
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
		 System.out.println(getCenterX());
		 System.out.println(getCenterY());
		 System.out.println(newX);
		 System.out.println(newY);
		 drawLine(getCenterX(), getCenterY(), newX, newY);

		 myImageView.setX(newX);
		 myImageView.setY(newY);
	         draw();
		 }
=======
		Turtle tModel = (Turtle) o;
		double newX = tModel.getX();
		double newY = tModel.getY();
		double newHeading = tModel.getHeading();
		myHeading = newHeading;
		if (newX != getCenterX() || newY != getCenterY()) {
			draw(myImageView.getX(), myImageView.getY(), newX, newY,
					tModel.getLine());
		}
		if (tModel.getCleared()) {
			myCanvas.getGraphicsContext2D().clearRect(0, 0,
					myCanvas.getWidth(), myCanvas.getHeight());
		}
>>>>>>> 0fd6b3ec70d9b29fee476d321381f65888672a6c
	}

	private double getCenterX() {
		return myImageView.getX() - (myCanvas.getWidth() / 20);
	}

	private double getCenterY() {
		return myImageView.getY() - (myCanvas.getHeight() / 20);
	}

}
