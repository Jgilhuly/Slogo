package parser;

import java.util.List;

/**
 * A class that deals with when the word is a variable.
 * 
 * @author Kei Yoshikoshi
 *
 */
public class VariableCase implements Cases {
	private static final String TYPE = "VARIABLE";
	private TreeWrapper wrapper;
	private List<String> myInput;

	public VariableCase(TreeWrapper wrapper, List<String> input) {
		this.wrapper = wrapper;
		myInput = input;
	}
	
	/**
	 * recurse method to generate the tree
	 * 
	 * @param root
	 */
	public void recurse(CommandTreeNode root) {
		String value = myInput.get(wrapper.getIndex());
		CommandTreeNode temp = new CommandTreeNode(TYPE, value, 0);
		root.add(temp);
		wrapper.incrementIndex();
	}

}
