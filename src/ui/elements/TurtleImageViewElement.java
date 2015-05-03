package ui.elements;

import ui.TurtleView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * A new stage where the user can view all the images of the turtles by flipping through a ComboBox.
 * 
 * @author Kei
 *
 */
public class TurtleImageViewElement {
    private Stage s;
    private ObservableList<TurtleView> turtleViewList;
    private BorderPane pane;
    private static final double PROPORTION = 0.70;
    private static final double SIZE = Screen.getPrimary().getBounds().getHeight() * PROPORTION;

    public TurtleImageViewElement (ObservableList<TurtleView> turtleViewList) {
        s = new Stage();
        this.turtleViewList = turtleViewList;
    }

    public void init () {
        pane = new BorderPane();
        pane.setPrefSize(SIZE, SIZE);
        pane.setTop(setUpDropDown());
        s.setScene(new Scene(pane));
        s.show();

    }

    private ComboBox<TurtleView> setUpDropDown () {
        ComboBox<TurtleView> box = new ComboBox<TurtleView>();
        box.setItems(turtleViewList);
        box.valueProperty().addListener( (observable, oldV, newV) -> {
            TurtleView tView = box
                    .getSelectionModel().getSelectedItem();
            TurtleImageClickingElement element =
                    new TurtleImageClickingElement(tView);
            pane.setCenter(element.getObject());
        });
        box.getSelectionModel().getSelectedItem();
        return box;
    }
}
