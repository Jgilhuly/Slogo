package ui.elements;

import java.io.File;
import ui.TurtleView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Class to implement the new feature for the final analysis. Takes in a TurtleView and returns a
 * stackPane of the turtle, as well as a file chooser implementation
 * 
 * @author Kei
 *
 */
public class TurtleImageClickingElement {
    private TurtleView turtle;

    public TurtleImageClickingElement (TurtleView tView) {
        turtle = tView;
    }

    public Pane getObject () {
        StackPane stack = new StackPane();
        ImageView newImage = new ImageView(turtle.getImageView().getImage());
        stack.getChildren().add(newImage);
        stack.setOnMouseClicked(e -> {
            try {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(new Stage());
                Image image = new Image("file:" + file.toURI().getPath());
                turtle.setImage(image);
                stack.getChildren().clear();
                stack.getChildren().add(new ImageView(image));
            }
            catch (NullPointerException nullpointer) {
                // means they didn't choose a file
            }
        });
        return stack;
    }
}
