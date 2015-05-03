package ui;

import java.util.Observable;
import java.util.Observer;
import model.Turtle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;


public class TurtleView implements Observer {
    private ImageView myImageView;
    private Canvas myCanvas;
    private double canvasCenterX;
    private double canvasCenterY;
    private DoubleProperty myHeading; // in degrees, 0 is north
    private StackPane myCanvasHolder;
    private boolean turtleIsVisible;
    private Pen myPen;
    private int counter;

    public TurtleView (Image imageIn, Canvas canvasIn, double xIn, double yIn,
                       StackPane canvasHolder, Pen penIn, int count) {
        counter = count;
        myImageView = new ImageView();
        myCanvas = canvasIn;
        myImageView.setImage(imageIn);
        myImageView.setX(xIn);
        myImageView.setY(yIn);
        myPen = penIn;
        turtleIsVisible = true;
        myHeading = new SimpleDoubleProperty();
        canvasCenterX = myCanvas.getWidth() / 2;
        canvasCenterY = myCanvas.getHeight() / 2;

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
     *        : ID of turtle
     * @param turtle
     *        : Image of turtle
     */
    private void addTurtle (ImageView turtle) {
        myImageView.setScaleX(0.1);
        myImageView.setScaleY(0.1);
        myCanvasHolder.getChildren().add(turtle);
    }

    /**
     * Draws the Turtle and Line
     * 
     * @param x1
     *        : Old X value
     * @param y1
     *        : Old Y value
     * @param x2
     *        : New X value
     * @param y2
     *        : New Y value
     * @param hasLine
     *        : Pen is down
     * @param hasTurtle
     *        : Turtle is visible
     */
    private void draw (double x1, double y1, double x2, double y2,
                       boolean hasTurtle) {
        Pair<Double, Double> newCoordinates = myPen.draw(x1, y1, x2, y2,
                                                         hasTurtle);

        // move image
        // myImageView.setTranslateX(newCoordinates.getKey() - x1); These lines
        // should provide wrapping, but we were unable to fix a bug with the y position
        // myImageView.setTranslateY(newCoordinates.getValue() - y1);
        myImageView.setTranslateX(x2);
        myImageView.setTranslateY(-y2);

        // set values - different coordinates
        myImageView.setX(newCoordinates.getKey());
        myImageView.setY(newCoordinates.getValue());

        System.out.println("X: " + myImageView.getX());
        System.out.println("Y: " + myImageView.getY());

        // rotate image
        updateRotation(hasTurtle);
    }

    private void updateRotation (boolean hasTurtle) {
        myImageView.setRotate(myHeading.doubleValue());
        myImageView.setVisible(hasTurtle && turtleIsVisible);
    }

    @Override
    /**
     * Observer update method
     */
    public void update (Observable o, Object arg) {
        Turtle tModel = (Turtle) o;

        double newX = tModel.getX();
        double newY = tModel.getY();
        double newHeading = tModel.getHeading();

        if (newX != (canvasCenterX - myImageView.getX())
            || newY != (canvasCenterY - myImageView.getY())) {
            draw(myImageView.getX(), myImageView.getY(), newX, newY,
                 tModel.getVisibility());
        }
        if (myHeading.getValue() != newHeading) {
            myHeading.set(newHeading);
            myHeading.set(myHeading.doubleValue() % 360);
            updateRotation(tModel.getVisibility());
        }
        if (tModel.getCleared()) {
            myCanvas.getGraphicsContext2D().clearRect(0, 0,
                                                      myCanvas.getWidth(), myCanvas.getHeight());
        }

    }

    public ImageView getImageView () {
        return myImageView;
    }

    /**
     * Re-sets up the imageView with a new Image, used when a new image is
     * selected
     * 
     * @param image
     */
    public void setImage (Image image) {
        myCanvasHolder.getChildren().remove(myImageView);

        double oldX = myImageView.getX();
        double oldY = myImageView.getY();
        myImageView = new ImageView(image);
        myImageView.setX(oldX);
        myImageView.setY(oldY);

        myImageView.setFitWidth(30);
        myImageView.setFitHeight(40);

        myCanvasHolder.getChildren().add(myImageView);
    }

    public void setTurtleVisible (boolean invisible) {
        turtleIsVisible = !invisible;
    }

    public DoubleProperty getHeading () {
        return myHeading;
    }
    
    @Override
    public String toString () {
        return "Turtle " + counter;
    }
}
