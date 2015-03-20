package parser;

import java.util.List;

/**
 * A class that deals with when the word is the start of a group.
 * 
 * @author Kei Yoshikoshi
 *
 */
public class GroupStartCase implements Cases {
	private static final String TYPE = "GROUP";
	private TreeWrapper wrapper;
	private List<String> myInput;

	public GroupStartCase(TreeWrapper wrapper, List<String> input) {
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
		while (!myInput.get(wrapper.getIndex()).equals(")")) {
			wrapper.recurse(temp);
		}
		wrapper.incrementIndex();
	}
}
