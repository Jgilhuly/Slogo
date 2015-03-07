package model;

import java.util.ArrayList;
import java.util.List;
import command.Command;


public class CommandFactory {

	public Command createCommand(String prefix, String commandName) {
		Command object = null;
		try {
			Class<?> command = Class.forName(prefix.toLowerCase() + "." + commandName + "Command");
			object = (Command) command.newInstance();	
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
		        System.err.println("Error creating Command");
			e.printStackTrace();
		}
		return object;
		
	}


    public Command createBracket (String name) {
        // TODO Auto-generated method stub
        return null;
    }
	
}
