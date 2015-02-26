package model;

import java.util.Map;

import javafx.collections.ObservableList;
import parser.CommandTreeNode;

public class CommandList {
	private Map<String, CommandTreeNode> previousCommands;
	
	public void addCommand(String name, CommandTreeNode c) {
		previousCommands.put(name, c);
	}
	

	
	
}
