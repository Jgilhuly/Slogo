package parser;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TreeGenerator {
	private Map<String, Integer> parametersMap;
	private int index;
	private int bracketCount = 0;
	private CommandTreeNode myRoot;

	public TreeGenerator() {
		parametersMap = createParametersMap();
	}

	private void printTestStatements(String value, int count, int numParams,
			String type, String name) {
		System.out.println(value);
		// System.out.println("Count is: " + count);
		// System.out.println("numParams is: " + numParams);
		System.out.println("Value is: " + type + name);
		System.out.println();
	}

	public CommandTreeNode createCommands(List<String> input) {
		index = 0;
		myRoot = null;
		helper(input, 0, Integer.MAX_VALUE, myRoot);
		System.out.println("FINAL ROOT VALUE IS: " + myRoot.getType() + myRoot.getName());
		return myRoot;
	}

	private void helper(List<String> input, int count, int numParams,
			CommandTreeNode root) {
		if (index >= input.size()) {
			return;
		}
		if (root == null) {
		        System.out.println("accessed"); //consider moving this outside of the helper method
			root = new CommandTreeNode("COMMAND", input.get(index), 0, null);
			myRoot = root;
			numParams = parametersMap.get(input.get(index));
			printTestStatements(input.get(index), count, numParams, null, root.getName());
			index++;

			for (int i = 0; i < numParams; i++) {
				helper(input, 0, numParams, root);
			}

		} else if (parametersMap.containsKey(input.get(index))) { // command
			numParams = parametersMap.get(input.get(index));
			CommandTreeNode temp = new CommandTreeNode("COMMAND", input.get(index), 0, null);
			root.add(temp);

			printTestStatements(input.get(index), 0, numParams, temp.getType(), temp.getName());

			boolean repeat = input.get(index).equals("Repeat");
			index++;

			if (repeat) {
				while (!input.get(index).equals("]")) {
					helper(input, 0, numParams, temp);
				}
			} else {
				for (int i = 0; i < numParams; i++) {
					helper(input, 0, numParams, temp);
				}
			}

		} else if (input.get(index).equals("[")) {

			CommandTreeNode temp = new CommandTreeNode("BRACKET", input.get(index) + "-" + bracketCount++, 0, null);

			root.add(temp);

			numParams = Integer.MAX_VALUE;
			printTestStatements(input.get(index) + "-" + (bracketCount - 1), count, numParams, temp.getType(), temp.getName());


			index++;

			while (!input.get(index).equals("]")) {
				helper(input, 0, numParams, temp);
			}
			if (input.get(index).equals("]")) {
				index++;
				helper(input, 0, numParams, temp);
			}
		} else if (Pattern.matches(":[a-zA-Z]+", input.get(index))) { // Variable
			CommandTreeNode temp = new CommandTreeNode("VARIABLE", input.get(index), 0,
					null);
			root.add(temp);

			printTestStatements(input.get(index), 0, 1, temp.getType(), temp.getName());

			index++;
			count++;
			return;
		} else if (Pattern.matches("-?[0-9]+\\.?[0-9]*", input.get(index))) { // CONSTANT

			CommandTreeNode temp = new CommandTreeNode("CONSTANT", "CONSTANT", Double.parseDouble(input.get(index)), null);
			root.add(temp);
			count++;

			printTestStatements(input.get(index), count, numParams, temp.getType(), temp.getName());
			index++;
			return;
		}
	}

	private HashMap<String, Integer> createParametersMap() {
		ResourceBundle resources = ResourceBundle.getBundle("parser/parameters");
		Enumeration<String> paramKeys = resources.getKeys();
		HashMap<String, Integer> newMap = new HashMap<>();

		while (paramKeys.hasMoreElements()) {
			String Key = paramKeys.nextElement();
			newMap.put(Key, Integer.parseInt(resources.getString(Key)));
		}
		return newMap;
	}
}
