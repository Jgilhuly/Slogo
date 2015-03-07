package model;

/**
 * Class that contains factory method to create a Command using reflection
 * @author OWNER
 *
 */
public class CommandFactory {

    public Class<?> createCommand (String prefix, String commandName) {
        Class<?> className = null;
        try {
            className = Class.forName(prefix.toLowerCase() + "." + commandName + "Command");
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error creating Command; Command not found!");
            e.printStackTrace();
        }
        return className;
    }

}
