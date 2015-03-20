package parser;

import java.util.List;

/**
 * A class that deals with when the word is the start of a list.
 * 
 * @author Kei Yoshikoshi
 *
 */
public class ListStartCase implements Cases {
	private static final String TYPE = "BRACKET";
	private int bracketCount = 0;
	private TreeWrapper wrapper;
	private List<String> myInput;

	public ListStartCase(TreeWrapper wrapper, List<String> input) {
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
		wrapper.incrementListStartIndex();
		CommandTreeNode temp = new CommandTreeNode(TYPE, value + "-"
				+ bracketCount++, 0);

		root.add(temp);

		wrapper.incrementIndex();
		while (!myInput.get(wrapper.getIndex()).equals("]")) {
			wrapper.recurse(temp);
		}
		wrapper.incrementListEndIndex();
		wrapper.incrementIndex();
	}

}
