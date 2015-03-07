package util;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;


public class ButtonMaker {

    /**
     * Helper method to create buttons with labels and handlers (Taken from the
     * example_browser)
     * 
     * @param property
     * @param handler
     * @return
     */
    public Button makeButton (String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        // String label = myResources.getString(property);
        result.setText(property);
        result.setOnAction(handler);
        return result;
    }

    public ToggleButton makeToggleButton (String property,
                                          EventHandler<ActionEvent> handler, ToggleGroup myGroup) {
        ToggleButton result = new ToggleButton();
        result.setText(property);
        if (handler != null) {
            result.setOnAction(handler);
        }
        if (myGroup != null) {
            result.setToggleGroup(myGroup);
        }
        return result;
    }
}
