package ui.elements;

import java.io.File;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ui.TurtleView;

public class TurtleViewsElement {
    private Scene window;
    private ListView<Node> myTurtles;
    private Node activeTurtle;
    private ObservableList<Node> tv;
    private VBox myV;
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 400;
    private static final int X_LOC = 60;
    private static final int Y_LOC = 300;
    private static final String DEFAULT_IMAGE = "resources/images/image_placeholder.png";

    public TurtleViewsElement (ObservableList<Node> t) {
        tv = t;

    }

    public void initialize () {
        createWindow();
        configureItemView();
        setListViewListener();
    }

    private void createWindow () {
        Stage s = new Stage();
        s.setWidth(WINDOW_WIDTH);
        s.setHeight(WINDOW_HEIGHT);
        s.setAlwaysOnTop(true);
        s.setX(X_LOC);
        s.setY(Y_LOC);
        BorderPane sp = new BorderPane();

        VBox v = new VBox();
        myTurtles = new ListView<Node>();
        v.getChildren().add(myTurtles);
        sp.setRight(v);

        myV = new VBox();
        ImageView x = new ImageView(DEFAULT_IMAGE);
        formatImageView(x);

        VBox labeler = new VBox();
        Label label = new Label("click to change turtle image");
        labeler.getChildren().add(label);
        v.getChildren().addAll(labeler);

        myV.getChildren().addAll(x);
        sp.setLeft(myV);

        window = new Scene(sp, s.getWidth(), s.getHeight());
        s.setScene(window);
        s.show();

    }

    private void configureItemView () {
        myTurtles.setCellFactory(callback -> new ListCell<Node>() {
            @Override
            public void updateItem (Node node, boolean empty) {
                super.updateItem(node, empty);
                if (node != null) {
                    setText("turtle " + tv.indexOf(node));
                }

            }

        });
        myTurtles.setItems(tv);
    }

    private void setListViewListener () {
        myTurtles.getSelectionModel().selectedItemProperty()
                .addListener( (observable, older, newer) -> {
                    updateActiveTurtleImage(newer);

                });
    }

    private void formatImageView (ImageView change) {
        change.setOnMouseClicked(selectImage(change));
        change.setFitHeight(100);
        change.setFitWidth(100);
    }

    private EventHandler<MouseEvent> selectImage (ImageView myImage) {
        return event -> {
            try {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(new Stage());
                String filePath = file.toURI().getPath();
                ((TurtleView) activeTurtle).setImage(new Image(filePath));
            }
            catch (NullPointerException e) {
                // do nothing
            }
        };
    }

    private void updateActiveTurtleImage (Node turtle) {
        if (activeTurtle != null) {
            Image t = ((TurtleView) turtle).getImageView().getImage();
            myV.getChildren().clear();
            ImageView newer = new ImageView(t);
            myV.getChildren().add(newer);
            formatImageView(newer);
        }

    }
}
