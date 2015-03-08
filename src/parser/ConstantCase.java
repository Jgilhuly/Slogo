package parser;

import java.util.List;

/**
 * A class that deals with when the word is a constant.
 * 
 * @author Kei Yoshikoshi
 *
 */
public class ConstantCase implements Cases {
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
		CommandTreeNode temp = new CommandTreeNode("CONSTANT", "CONSTANT",
				Double.parseDouble(myInput.get(wrapper.getIndex())), null);
		root.add(temp);

		wrapper.printTestStatements(value, temp.getType(), root.getName());
		wrapper.incrementIndex();
	}

}
