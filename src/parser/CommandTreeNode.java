package parser;

import java.util.ArrayList;

import javax.swing.tree.TreeNode;
import command.Command;
//http://penguin.ewu.edu/class/class/cscd300/Topic/ExpressionTree/ExpressionTree.java

// important : http://math.hws.edu/javanotes/c9/s4.html
public class CommandTreeNode
{
	public Command myValue;
	public CommandTreeNode myLeft; // holds smaller tree nodes
	public CommandTreeNode myRight; // holds larger tree nodes
	private CommandTreeNode root = null;

	public CommandTreeNode(Command command)
	{
		myValue = command;
	}

	public void add(Command newValue)
	{
		if(root == null)
			root = new CommandTreeNode(newValue);
		else
			add(newValue, root);
	}
	public void add(Command newValue, CommandTreeNode current) {
		if (newValue.compareTo(current.myValue) < 0) {
			if (current.myLeft == null) {
				current.myLeft = new CommandTreeNode(newValue);
			} else {
				add(newValue, current.myLeft);
			}
		} else {
			// newValue >= myValue
			if (current.myRight == null) {
				current.myRight = new CommandTreeTreeNode(newValue);
			} else {
				add(newValue, current.myRight);
			}
		}
	}
}

