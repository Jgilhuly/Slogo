package model;

import command.Command;


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
