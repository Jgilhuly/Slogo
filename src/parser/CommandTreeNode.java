package parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//http://penguin.ewu.edu/class/class/cscd300/Topic/ExpressionTree/ExpressionTree.java

// important : http://math.hws.edu/javanotes/c9/s4.html
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
//		final CommandTreeNode other = (CommandTreeNode) obj;
//		if (this.myValue==null)) {
//			
//		}
//		if ((this.name == null) ? (other.name != null) : !this.name
//				.equals(other.name)) {
//			return false;
//		}
//		if (this.age != other.age) {
//			return false;
//		}
		return true;
	}

}
