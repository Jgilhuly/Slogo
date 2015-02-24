package ui;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


public class TurtleView implements Observer {
	ImageView myImageView;
	Color myColor;
	
	public TurtleView(Image imageIn, Color colorIn, double xIn, double yIn) {
		myImageView = new ImageView();
		myImageView.setImage(imageIn);
		myImageView.setX(xIn);
		myImageView.setY(yIn);
		myColor = colorIn;
		
		myImageView.setFitWidth(10);
		myImageView.setPreserveRatio(true);
		myImageView.setSmooth(true);
	}
	
	public ImageView getImageView() {
		return myImageView;
	}
	
	@Override
	public void update(Observable o, Object arg) {		
	}

}
