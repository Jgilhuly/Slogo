package ui;

import ui.artists.DashedLineDrawHelper;
import ui.artists.DottedLineDrawHelper;
import ui.artists.LineDrawHelper;
import ui.artists.NormalLineDrawHelper;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.util.Pair;


public class Pen {
    private Color myColor;
    private boolean penIsDown;
    private Canvas myCanvas;
    private double canvasCenterX;
    private double canvasCenterY;
    private LineDrawHelper artists[] = { new NormalLineDrawHelper(), new DashedLineDrawHelper(),
                                        new DottedLineDrawHelper() };
    private LineDrawHelper myArtist;

    public Pen (Canvas canvasIn, Color startingColor, boolean penIsDownIn) {
        myCanvas = canvasIn;
        myColor = startingColor;
        penIsDown = penIsDownIn;
        canvasCenterX = myCanvas.getWidth() / 2;
        canvasCenterY = myCanvas.getHeight() / 2;
        myArtist = artists[0];
    }

    public Pair<Double, Double> draw (double x1, double y1, double x2, double y2,
                                      boolean hasTurtle) {
        myCanvas.getGraphicsContext2D().setStroke(myColor);
        myCanvas.getGraphicsContext2D().setLineWidth(3);

        // minus y since it's flipped in the canvas
        if (penIsDown) { return myArtist.drawLine(myCanvas, x1, y1,
                                                  canvasCenterX + x2, canvasCenterY - y2); }
        return null;
    }

    public void setPenIsDown (boolean penIsDownIn) {
        penIsDown = penIsDownIn;
    }

    public Color getColor () {
        return myColor;
    }

    public void setColor (Color color) {
        myColor = color;
    }

    public void setLineStyle (String ls) {
        if (ls.equals("normal"))
            myArtist = artists[0];
        else if (ls.equals("dashed"))
            myArtist = artists[1];
        if (ls.equals("dotted"))
            myArtist = artists[2];
    }
}
