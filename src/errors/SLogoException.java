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
/**
 * Super class for all Exceptions thrown in this SLogo program
 */
public abstract class SLogoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Stage popupStage;

	protected SLogoException(String format) {
		super(String.format(format));
		fillInStackTrace();
		popupStage = new Stage();
		popup(format);
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
		popup.setX(300);
		popup.setY(200);
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
