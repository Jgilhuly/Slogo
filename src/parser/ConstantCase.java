// This entire file is part of my masterpiece.
// Kei Yoshikoshi

package parser;

import java.util.List;

/**
 * A class that deals with when the word is a constant.
 * 
 * @author Kei Yoshikoshi
 *
 */
public class ConstantCase implements Cases {
	private static final String TYPE = "CONSTANT";
	private TreeWrapper wrapper;
	private List<String> myInput;

	public ConstantCase(TreeWrapper wrapper, List<String> input) {
		this.wrapper = wrapper;
		myInput = input;
	}

	/**
	 * recursive method to generate the Tree
	 * 
	 * @param root
	 */
	public void recurse(CommandTreeNode root) {
		String value = myInput.get(wrapper.getIndex());
		CommandTreeNode temp = new CommandTreeNode(TYPE, TYPE,
				Double.parseDouble(value));
		root.add(temp);

		wrapper.incrementIndex();
	}

}
