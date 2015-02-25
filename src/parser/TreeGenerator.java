package parser;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import errors.NoInputFoundException;

public class TreeGenerator {
	private static final int PARAM_INDEX = 0;
	private static final int TYPE_INDEX = 1;
	private Map<String, String[]> parametersMap; // toDo: implement the correct
													// parametersMap
	private int index;
	private int bracketCount = 0;
	private CommandTreeNode myRoot;
	private boolean isMethod = false;

	public TreeGenerator() {
		parametersMap = createParametersMap();
	}

	private void printTestStatements(String value, int count, int numParams,
			String type, String name) {
		System.out.println(value);
		// // System.out.println("Count is: " + count);
		// // System.out.println("numParams is: " + numParams);
		System.out.println("Type is: " + type);
		System.out.println("Root value is: " + name);
		System.out.println();
	}

	public CommandTreeNode createCommands(List<String> input) {
		try {
			index = 0;
			myRoot = null;
			helper(input, 0, Integer.MAX_VALUE, myRoot);
			System.out.println("FINAL ROOT VALUE IS: " + myRoot.getName());
			return myRoot;
		} catch (NullPointerException e) {
			throw new NoInputFoundException();
		}
	}

	private void helper(List<String> input, int count, int numParams,
			CommandTreeNode root) {
		if (index >= input.size()) {
			return;
		}
		if (root == null) {
			String value = input.get(index);
			root = new CommandTreeNode(obtainSubCommand(value), value, 0, null);
			myRoot = root;
			numParams = obtainNumParams(value);
			printTestStatements(value, count, numParams,
					obtainSubCommand(value), null);
			index++;
			boolean to = value.equals("MakeUserInstruction");
			if (to) {
				isMethod = true;
			}
			for (int i = 0; i < numParams; i++) {
				helper(input, 0, numParams, root);
			}

		} else if (parametersMap.containsKey(input.get(index))) { // command
			String value = input.get(index);
			numParams = obtainNumParams(value);
			CommandTreeNode temp = new CommandTreeNode(obtainSubCommand(value),
					value, 0, null);
			root.add(temp);

			printTestStatements(value, 0, numParams, temp.getType(),
					root.getName());

			boolean repeat = value.equals("Repeat");

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
			String value = input.get(index);
			CommandTreeNode temp = new CommandTreeNode("Bracket", value + "-"
					+ bracketCount++, 0, null);

			root.add(temp);

			numParams = Integer.MAX_VALUE;
			printTestStatements(value + "-" + (bracketCount - 1), count,
					numParams, temp.getType(), root.getName());

			index++;

			while (!input.get(index).equals("]")) {
				helper(input, 0, numParams, temp);
			}
			if (input.get(index).equals("]")) {
				index++;
				helper(input, 0, numParams, temp);
			}
		} else if (Pattern.matches(":[a-zA-Z]+", input.get(index))) { // Variable
			String value = input.get(index);
			CommandTreeNode temp = new CommandTreeNode("Variable", value, 0,
					null);
			root.add(temp);

			printTestStatements(value, 0, 1, temp.getType(), root.getName());
			index++;
			count++;
		} else if (Pattern.matches("-?[0-9]+\\.?[0-9]*", input.get(index))) { // CONSTANT
			String value = input.get(index);
			CommandTreeNode temp = new CommandTreeNode("Constant", "Constant",
					Double.parseDouble(input.get(index)), null);
			root.add(temp);
			count++;

			printTestStatements(value, count, numParams, temp.getType(),
					root.getName());
			index++;
		} else if (isMethod) {
			System.out.println("Method name is: " + input.get(index));
			System.out.println();
			isMethod = false;
			index++;
		}
	}
	private HashMap<String, String[]> createParametersMap() {
		ResourceBundle resources = ResourceBundle
				.getBundle("parser/parameters");
		Enumeration<String> paramKeys = resources.getKeys();
		HashMap<String, String[]> newMap = new HashMap<>();

		while (paramKeys.hasMoreElements()) {
			String Key = paramKeys.nextElement();
			newMap.put(Key, resources.getString(Key).split(","));
		}
		return newMap;
	}

	private int obtainNumParams(String key) {
		return Integer.parseInt(parametersMap.get(key)[PARAM_INDEX]);
	}

	private String obtainSubCommand(String key) {
		return "command." + parametersMap.get(key)[TYPE_INDEX];
	}
}
