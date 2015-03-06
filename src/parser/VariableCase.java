package parser;

public class VariableCase extends TreeGenerator implements Cases{

	@Override
	public void helper(CommandTreeNode root) {
		// TODO Auto-generated method stub
		String value = myInput.get(index);
		CommandTreeNode temp = new CommandTreeNode("VARIABLE", value, 0, null);
		root.add(temp);

		printTestStatements(value, temp.getType(), root.getName());
		index++;
	}

}
