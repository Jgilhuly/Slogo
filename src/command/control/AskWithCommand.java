package command.control;

import java.util.List;
import model.TreeInterpreter;
import model.Turtle;
import parser.CommandTreeNode;
import command.Command;

public class AskWithCommand extends Command{
    private TreeInterpreter tree;
    private CommandTreeNode condition;
    private List<CommandTreeNode> subCommands;

    public AskWithCommand (CommandTreeNode node1, CommandTreeNode node2, TreeInterpreter t) {
        tree = t;
        condition = node1.getChildren().get(0);
        subCommands = node2.getChildren();
    }

    public double calculateValue () {
        List<Turtle> turtles = tree.getTurtleList();
        tree.interpretTree(condition);
        for (int i = 0; i < turtles.size(); i++) {
            if((i+1) == (int) condition.getValue()){
                tree.setActiveTurtleIndex(i+1);
                for (int j = 0; j < subCommands.size(); j++) {
                    tree.interpretTree(subCommands.get(j));
                    if (j == (subCommands.size() - 1)) { // return the last command run
                        tree.setActiveTurtleIndex(1); // set the activeTurtleIndex back to 1;
                        return subCommands.get(j).getValue();
                    }
                }
            }
        }
        return 0;
    }
}
