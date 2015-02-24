package model;

import command.Command;

public class CommandFactory {
	
	public Command createCommand(String commandName) {
		Command object = null;
		try {
			Class command = Class.forName(commandName);
			object = (Command) command.newInstance();
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			// NEED TO THROW ERRORS
			e.printStackTrace();
		}
		return object;
		
	}
	
}
