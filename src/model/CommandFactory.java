package model;

import command.Command;
import model.*;

public class CommandFactory {
        private VariableList variableList;
        
	public CommandFactory(VariableList vList){
	    variableList = vList;
	}
	public Command createCommand(String prefix, String commandName) {
		Command object = null;
		try {
			Class<?> command = Class.forName("command.turtle." + commandName);
			object = (Command) command.newInstance();
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			// NEED TO THROW ERRORS
			e.printStackTrace();
		}
		return object;
		
	}

    public void createVariable (String name, VariableList variables) {
        if(!variables.contains(name)){
            variables.addNewVariable(name);
        }
    }

    public Command createBracket (String name) {
        // TODO Auto-generated method stub
        return null;
    }
	
	
}
