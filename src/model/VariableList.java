package model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleListProperty;

/**
 * 
 * @TODO: Make this more elegant...
 *
 */
public class VariableList{
    private List<Variable> list;
    
    public VariableList(){
        list = new SimpleListProperty<Variable>(javafx.collections.FXCollections.observableList(new ArrayList<Variable>()));
    }
    
    public void add(Variable var ){
        list.add(var);
    }
    
    public double indexOf(Variable var){
        return (double) list.indexOf(var);
    }
    
    public Variable get(Double index){
        return list.get(index.intValue());
    }
    
    public boolean contains(String name){
        for(Variable var : list){
            if(var.getName() == name){
                return true;
            }
        }
        return false;
    }
    
    public VariableList getVariableList(){
        return this;
    }

}
