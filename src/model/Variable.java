package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * container class for user defined variables
 *
 */
public class Variable {
     private DoubleProperty value;
     private StringProperty name;
     
     public Variable(String var, Double num){
    	 value = new SimpleDoubleProperty(num);
         name = new SimpleStringProperty(var);
     }
     
     
     private Double getValue(){
         return value.getValue();
     }
     private String getName() {
    	 return name.getValue();
     }


	public DoubleProperty valueProperty() {
		if (value == null)
			value = new SimpleDoubleProperty(this, "value");
		return value;
	}
	public StringProperty nameProperty() {
		if (name == null)
			name = new SimpleStringProperty(this, "name");
		return name;
	}
}
