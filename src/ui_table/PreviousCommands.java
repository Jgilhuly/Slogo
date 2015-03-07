package ui_table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PreviousCommands implements TableElements {

	private StringProperty name;

	public PreviousCommands(String name) {
		this.name = new SimpleStringProperty(name);
	}


	public StringProperty nameProperty() {
		if (name == null)
			name = new SimpleStringProperty(this, "name");
		return name;
	}

	@Override
	public StringProperty valueProperty() {
		// TODO Auto-generated method stub
		return null;
	}

	
	// public String toString() {
	// return this.getName() + this.getValue().toString();
	// }

}
