package ui;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class HTMLHelpPage {
	Stage stage;

	public HTMLHelpPage (ResourceBundle myResources, String url) {
		WebView browser = new WebView();
		WebEngine webEngine = browser.getEngine();
		webEngine
				.load(url);

		VBox helpRoot = new VBox();
		helpRoot.getChildren().add(browser);

		stage = new Stage();
		stage.setTitle(myResources.getString("HelpPageTitle"));
		stage.setScene(new Scene(helpRoot, 800, 800));
	}

	public void show() {
		stage.show();
	}
}
