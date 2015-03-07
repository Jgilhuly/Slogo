package model;




import command.Command;


public class CommandFactory {

	public Class<?> createCommand(String prefix, String commandName) {
//		Command object = null;
	        Class<?> command = null;
		try {
			command = Class.forName(prefix.toLowerCase() + "." + commandName + "Command");
//			object = (Command) command.newInstance();	
		} catch (ClassNotFoundException e) {
		        System.err.println("Error creating Command; Command not found!");
			e.printStackTrace();
		}
		return command;
	}
	
	
	
}
