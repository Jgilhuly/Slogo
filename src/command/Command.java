package command;

import java.util.List;

import parser.CommandTreeNode;


public abstract class Command {

    public abstract double calculateValue(List<CommandTreeNode> list);


}
