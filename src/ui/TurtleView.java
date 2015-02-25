package ui;

import java.util.Observable;
import java.util.Observer;

import model.Turtle;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class TurtleView implements Observer {
	private ImageView myImageView;
	private Canvas myCanvas;
	private Color myColor;
	private int myHeading; // in degrees, 0 is north

	public TurtleView(Image imageIn, Canvas canvasIn, Color colorIn,
			double xIn, double yIn) {
		myImageView = new ImageView();
		myCanvas = canvasIn;
		myImageView.setImage(imageIn);
		myImageView.setX(xIn);
		myImageView.setY(yIn);
		myColor = colorIn;

		myHeading = 0;
		myImageView.setPreserveRatio(true);
		myImageView.setSmooth(true);
	}

	public ImageView getImageView() {
		return myImageView;
	}

	public void draw() {
		myCanvas.getGraphicsContext2D().drawImage(myImageView.getImage(),
				myImageView.getX(), myImageView.getY(),
				myCanvas.getHeight() / 10, myCanvas.getWidth() / 10);
	}

	private void drawLine(double x1, double y1, double x2, double y2) {
		myCanvas.getGraphicsContext2D().setFill(myColor);
		myCanvas.getGraphicsContext2D().fillRect(x1, y1, Math.abs(x2 - x1), Math.abs(y2 - y1));;
	}

	@Override
	public void update(Observable o, Object arg) {
		 Turtle tModel = (Turtle) o;
		 int newX = tModel.getX();
		 int newY = tModel.getY();
		 int newHeading = tModel.getHeading();
		
		 myHeading = newHeading;
		
		 if (newX != myImageView.getX() || newY != myImageView.getY()) {
			 drawLine(myImageView.getX(), myImageView.getY(), newX, newY);
//			 draw();
			 myImageView.setX(newX);
			 myImageView.setY(newY);
		 }
	}

}
