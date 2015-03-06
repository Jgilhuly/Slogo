package parser;


public class CommandCase extends TreeGenerator {
	/**
	 * helper method to handle the case when the node is a command
	 *
	 * @param root
	 */
	protected void helper(CommandTreeNode root) {
		String value = myInput.get(index);
		int numParams = obtainNumParams(value);
		CommandTreeNode temp = new CommandTreeNode(obtainSubCommand(value),
				value, 0, null);
		root.add(temp);

		printTestStatements(value, temp.getType(), root.getName());

		boolean repeat = value.equals("Repeat");

		index++;

		if (repeat) {
			while (!myInput.get(index).equals("]")) {
				super.helper(temp);
			}
		} else {
			for (int i = 0; i < numParams; i++) {
				super.helper(temp);
			}
		}
	}

}
