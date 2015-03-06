package parser;

public class ListStartCase extends TreeGenerator {
	private int bracketCount = 0;
	
	@Override
	protected void helper(CommandTreeNode root) {
		String value = myInput.get(index);
		ListStartCount++;
		CommandTreeNode temp = new CommandTreeNode("BRACKET", value + "-"
				+ bracketCount++, 0, null);

		root.add(temp);

		printTestStatements(value + "-" + (bracketCount - 1), temp.getType(),
				root.getName());

		index++;
		while (!myInput.get(index).equals("]")) {
			super.helper(temp);
		}
		ListEndCount++;
		index++;
	}

}