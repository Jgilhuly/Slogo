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
     
     
     public Double getValue(){
         return (Double) value.getValue();
     }
     public String getName() {
    	 return name.getValue();
     }
     
     public void setValue(Double val){
         value = new SimpleDoubleProperty(val);
     }

	public DoubleProperty valueProperty() {
		if (value == null)
			value = new SimpleDoubleProperty(0);
		return value;
	}
	public StringProperty nameProperty() {
		if (name == null)
			name = new SimpleStringProperty(this, "name");
		return name;
	}
	public String toString(){
	    return this.getName() + this.getValue().toString();
	}
}
