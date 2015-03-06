package parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandTreeNode {
	private String myType;
	private String myName;
	private double myValue;
	private List<CommandTreeNode> myChildren;

	public CommandTreeNode(String type, String name, double value, List<CommandTreeNode> Children) {
		this.myType = type;
		this.myName = name;
		this.myValue = value;
		this.myChildren = Children;
		myChildren = new ArrayList<>();
	}
	
	public void add(CommandTreeNode newNode) {
		myChildren.add(newNode);
	}
	
	public void clearChildren(){
	        myChildren = new ArrayList<>();
	}

	public void remove(CommandTreeNode newNode) {
		Iterator<CommandTreeNode> iter = myChildren.iterator();
		while (iter.hasNext()) {
			if (iter.next().equals(newNode)) {
				iter.remove();
			}
		}
	}

	public void setValue(double value) {
		myValue = value;
	}

	public double getValue() {
		return myValue;
	}

	public String getType() {
		return myType;
	}

	public String getName() {
	        return myName;
	}
	public List<CommandTreeNode> getChildren() {
		return myChildren;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		return true;
	}

}
