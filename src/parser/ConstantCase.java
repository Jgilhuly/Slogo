package parser;

public class ConstantCase extends TreeGenerator{
	@Override
	protected void helper(CommandTreeNode root) {
		// TODO Auto-generated method stub
		String value = myInput.get(index);
		CommandTreeNode temp = new CommandTreeNode("CONSTANT", "CONSTANT",
				Double.parseDouble(myInput.get(index)), null);
		root.add(temp);

		printTestStatements(value, temp.getType(), root.getName());
		index++;
	}

}
