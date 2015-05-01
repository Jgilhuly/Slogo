package ui.elements;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.TurtleView;


public class TurtleImageSelectorView {

    private Stage stage;
    private Consumer<TurtleView> clickHandler;

    public TurtleImageSelectorView (ResourceBundle myResources, Consumer<TurtleView> clickHandler) {
        stage = new Stage();
        stage.setTitle(myResources.getString("HelpPageTitle"));
        this.clickHandler = clickHandler;
    }

    /**
     * Shows the turtle image view window
     * @param tViews
     */
    public void showWindow (List<TurtleView> tViews) {
        VBox root = new VBox();

        loadTurtleImages(tViews, root);

        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }

    /**
     * Initializes and loads the turtle images
     * @param tViews
     * @param root
     */
    private void loadTurtleImages (List<TurtleView> tViews, VBox root) {
        for (TurtleView tv : tViews) {
            ImageView container = new ImageView(tv.getImageView().getImage());
            container.scaleXProperty().bind(tv.getImageView().scaleXProperty());
            container.scaleYProperty().bind(tv.getImageView().scaleYProperty());
            container.setOnMouseClicked(e -> imageClicked(tv));
            
            System.out.println(tv.toString());

            root.getChildren().add(container);
        }
    }

    /**
     * Uses a consumer to call the image selection method from TPropertiesElement
     * @param tv
     */
    private void imageClicked (TurtleView tv) {
        stage.close();
        clickHandler.accept(tv);
    }
}
