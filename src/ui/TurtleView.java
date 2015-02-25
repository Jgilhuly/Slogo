package ui;
import java.util.Observable;
import java.util.Observer;

import model.Turtle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class TurtleView implements Observer {
	private ImageView myImageView;
	private Color myColor;
	private int myHeading; // in degrees, 0 is north
	
	public TurtleView(Image imageIn, Color colorIn, double xIn, double yIn) {
		myImageView = new ImageView();
		myImageView.setImage(imageIn);
		myImageView.setX(xIn);
		myImageView.setY(yIn);
		myColor = colorIn;
		
		myHeading = 0;
	}
	
	public ImageView getImageView() {
		return myImageView;
	}
	
	@Override
	public void update(Observable o, Object arg) {
//		Turtle tModel = (Turtle) o;
//		int newX = tModel.getX();
//		int newY = tModel.getY();
//		int newHeading = tModel.getHeading();
//		
//		myHeading = newHeading;
//		
	}

}
