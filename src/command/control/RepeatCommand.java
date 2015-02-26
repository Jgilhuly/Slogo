package command.control;

import java.util.List;
import parser.CommandTreeNode;
import model.Variable;
import model.VariableList;
import model.TreeInterpreter;
import command.Command;

public class RepeatCommand extends Command {
    public double calculateValue(List<Object> param) {
        
        TreeInterpreter yay = (TreeInterpreter) param.get(2);
//        VariableList variables = (VariableList) yay.getVariableList(); 
        CommandTreeNode node = (CommandTreeNode) param.get(0); 
        CommandTreeNode node2 = (CommandTreeNode) param.get(1);
        for(CommandTreeNode a : node2.getChildren()){
            System.out.println(a.getType() + a.getName());
        }
        yay.interpretTree(node);
        int numTimes = (int) node.getValue();
        for(int i = 0; i < numTimes; i++){
            for(int j = 0; j < node2.getChildren().size(); j++){
                System.out.println("ONE TRAVERSAL");
                yay.interpretTree(node2.getChildren().get(j));
                if( i == (numTimes - 1) && j == (node2.getChildren().size()-1)){
                    return node2.getChildren().get(j).getValue();
                }
            }
            
        }
        return 69.0;
    }

    private void repeat (int numTimes, CommandTreeNode node) {
//        for(int i = 0; i < numTimes; i++){
//            
//        }
        
    }
}
