package errors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public abstract class SLogoException extends RuntimeException {

	/**
	 * Super class for all Exceptions thrown in this SLogo program
	 */
	private static final long serialVersionUID = 1L;
	private static final int X_COOR = 300;
	private static final int Y_COOR = 200;
	private Stage popupStage;

	protected SLogoException(String format) {
		super(String.format(format));
		popupStage = new Stage();
		popup(format);
	}

	/**
	 * Create an exception based on a caught exception with a different message.
	 */
	protected SLogoException(Throwable cause, String message, Object... values) {
		super(String.format(message, values), cause);
	}

	/**
	 * Create an exception based on a caught exception, with no additional
	 * message.
	 */
	protected SLogoException(Throwable exception) {
		super(exception);
	}
	
	/**
	 * disables stack trace
	 */
	@Override
	public Throwable fillInStackTrace() {
		return null;
	}

	/**
	 * A way for the program to display some error message
	 * 
	 * @param message
	 */
	protected void popup(String message) {
		popupStage.setTitle("Error");
		Popup popup = new Popup();
		popup.setX(X_COOR);
		popup.setY(Y_COOR);
		Label prompt = new Label(message);

		Button ok = new Button("OK");
		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popupStage.close();
			}
		});
		HBox layout = new HBox(10);
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(prompt, ok);
		popupStage.setScene(new Scene(layout));
		popupStage.show();
	}
}
