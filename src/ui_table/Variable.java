package ui_table;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Variable implements TableElements{
	private StringProperty value;
	private StringProperty name;

	public Variable(String var, Double num) {
		value = new SimpleStringProperty(Double.toString(num));
		name = new SimpleStringProperty(var);
	}

	public String getName() {
		return name.getValue();
	}

	public StringProperty valueProperty() {
		if (value == null)
			value = new SimpleStringProperty(0, "value");
		return value;
	}

	public StringProperty nameProperty() {
		if (name == null)
			name = new SimpleStringProperty(this, "name");
		return name;
	}
}