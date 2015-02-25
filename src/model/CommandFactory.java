package model;

import command.Command;

public class CommandFactory {
	
	public Command createCommand(String commandName) {
		Command object = null;
		try {
			Class<?> command = Class.forName("command.math."+commandName);
			object = (Command) command.newInstance();
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			// NEED TO THROW ERRORS
			e.printStackTrace();
		}
		return object;
		
	}

    public Variable createVariable (String name) {
        // TODO Auto-generated method stub
        return null;
    }

    public Command createBracket (String name) {
        // TODO Auto-generated method stub
        return null;
    }
	
	
}
