package model;

import java.util.ArrayList;
import java.util.List;
import command.Command;


public class CommandFactory {

	public Class<?> createCommand(String prefix, String commandName) {
//		Command object = null;
	        Class<?> command = null;
		try {
			command = Class.forName(prefix.toLowerCase() + "." + commandName + "Command");
//			object = (Command) command.newInstance();	
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
		        System.err.println("Error creating Command");
			e.printStackTrace();
		}
		return command;
		
	}


    public Command createBracket (String name) {
        // TODO Auto-generated method stub
        return null;
    }
	
}
