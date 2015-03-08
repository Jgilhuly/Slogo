package parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A TreeNode class for handling the commands
 * @author Kei Yoshikoshi
 *
 */
public class CommandTreeNode {
    private String myType;
    private String myName;
    private Double myValue;
    private List<CommandTreeNode> myChildren;

    public CommandTreeNode (String type, String name, double value, List<CommandTreeNode> children) {
        myType = type;
        myName = name;
        myValue = value;
        myChildren = children;
        myChildren = new ArrayList<>();
    }

    public void add (CommandTreeNode newNode) {
        myChildren.add(newNode);
    }

    public void clearChildren () {
        myChildren = new ArrayList<>();
    }

    public void remove (CommandTreeNode newNode) {
        Iterator<CommandTreeNode> iter = myChildren.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(newNode)) {
                iter.remove();
            }
        }
    }

    public void setValue (double value) {
        myValue = value;
    }

    public double getValue () {
        return myValue;
    }

    public String getType () {
        return myType;
    }

    public String getName () {
        return myName;
    }

    public List<CommandTreeNode> getChildren () {
        return myChildren;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        return true;
    }

    public String toString () {
        return myValue.toString();
    }
}
