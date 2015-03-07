package model;

import command.Command;

/**
 * Class that contains factory method to create a Command using reflection
 * @author OWNER
 *
 */
public class CommandFactory {

    public Class<?> createCommand (String prefix, String commandName) {
        Class<?> command = null;
        try {
            command = Class.forName(prefix.toLowerCase() + "." + commandName + "Command");
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error creating Command; Command not found!");
            e.printStackTrace();
        }
        return command;
    }

}
