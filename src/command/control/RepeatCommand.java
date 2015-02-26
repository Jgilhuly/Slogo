package command.control;

import java.util.List;
import parser.CommandTreeNode;
import model.TreeInterpreter;
import command.Command;

public class RepeatCommand extends Command {
    
	public double calculateValue(List<Object> param) {
        TreeInterpreter yay = (TreeInterpreter) param.get(2);
        CommandTreeNode num = (CommandTreeNode) param.get(0); 
        CommandTreeNode subCommands = (CommandTreeNode) param.get(1);
        
        yay.interpretTree(num); //Retrieves the number of times to be traversed
        int numTimes = (int) num.getValue();

        for(int i = 0; i < numTimes; i++){
            for(int j = 0; j < subCommands.getChildren().size(); j++){
                yay.interpretTree(subCommands.getChildren().get(j));
                if( i == (numTimes - 1) && j == (subCommands.getChildren().size()-1)){
                    return subCommands.getChildren().get(j).getValue();
                }
            }
        }
        return 69.0;
    }
}
