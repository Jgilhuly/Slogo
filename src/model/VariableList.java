package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;


/**
 * 
 * @TODO: Make this more elegant...
 *
 */
public class VariableList {
    private ObservableList<Variable> list;

    public VariableList () {
        list =
                new SimpleListProperty<Variable>(
                                                 javafx.collections.FXCollections
                                                         .observableList(new ArrayList<Variable>()));
    }

    public void add (String name) {
        list.add(new Variable(name, 0.0));
    }

    public Variable get (String name) {
        for (Variable var : list) {
            if (var.getName().equals(name)) { return var; }
        }
        add(name);
        return new Variable(name, 0.0);
    }

    public boolean contains (String name) {
        for (Variable var : list) {
            if (var.getName().equals(name)) { return true; }
        }
        return false;
    }

    public void printThing () {
        for (Variable var : list) {
            System.out.println(var.getName() + var.getValue().toString());
        }
    }

    public ObservableList<Variable> getList () {
        return list;

    }
}
