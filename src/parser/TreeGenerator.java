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
	private int index = 0;
	private int bracketCount = 0;
	private CommandTreeNode myRoot;
	private List<String> myInput;
	private boolean isMethod = false;

	public TreeGenerator() {
		parametersMap = createParametersMap();
	}

	/**
	 * Just to print out the tree and its parent node for testing
	 * 
	 * @param value
	 * @param type
	 * @param name
	 */
	private void printTestStatements(String value, String type, String name) {
		System.out.println(value);
		System.out.println("Type is: " + type);
		System.out.println("Root value is: " + name);
		System.out.println();
	}

	/**
	 * Create a CommandTreeNode that includes all the commands
	 * 
	 * @param input
	 *            : List of strings taken from the parser
	 * @return: CommandTreeNode with commands
	 */
	public CommandTreeNode createCommands(List<String> input) {
		try {
			myInput = input;

			String value = myInput.get(index);
			myRoot = new CommandTreeNode(obtainSubCommand(value), value, 0,
					null);
			int numParams = obtainNumParams(value);
			printTestStatements(value, obtainSubCommand(value), null);
			index++;
			boolean to = value.equals("MakeUserInstruction");
			if (to) {
				isMethod = true;
			}
			for (int i = 0; i < numParams; i++) {
				helper(myRoot);
			}

			System.out.println("FINAL ROOT VALUE IS: " + myRoot.getName());
			return myRoot;
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			throw new NoInputFoundException();
		}
	}

	/*
	 * THIS METHOD REQUIRES FIXING - Prof Duvall suggests inheritance hierachy
	 * (?)
	 */
	/**
	 * Recursive helper method that creates the tree
	 * 
	 * @param count
	 * @param root
	 */
	private void helper(CommandTreeNode root) {
		if (index >= myInput.size()) {
			return;
		}
		if (parametersMap.containsKey(myInput.get(index))) { // command
			commandCase(root);
		} else if (myInput.get(index).equals("[")) {
			bracketCase(root);
		} else if (Pattern.matches(":[a-zA-Z]+", myInput.get(index))) { // Variable
			variableCase(root);
		} else if (Pattern.matches("-?[0-9]+\\.?[0-9]*", myInput.get(index))) { // CONSTANT
			constantCase(root);
		} else if (isMethod) {
			System.out.println("Method name is: " + myInput.get(index));
			System.out.println();
			isMethod = false;
			index++;
		}
	}

	/**
	 * helper method to handle the case when the node is a command
	 * 
	 * @param root
	 */
	private void commandCase(CommandTreeNode root) {
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
				helper(temp);
			}
		} else {
			for (int i = 0; i < numParams; i++) {
				helper(temp);
			}
		}
	}

	/**
	 * helper method to handle the case when the node is a forward bracket
	 * 
	 * @param root
	 */
	private void bracketCase(CommandTreeNode root) {
		String value = myInput.get(index);
		CommandTreeNode temp = new CommandTreeNode("BRACKET", value + "-"
				+ bracketCount++, 0, null);

		root.add(temp);

		printTestStatements(value + "-" + (bracketCount - 1), temp.getType(),
				root.getName());

		index++;

		while (!myInput.get(index).equals("]")) {
			helper(temp);
		}
		if (myInput.get(index).equals("]")) {
			index++;
			helper(temp);
		}
	}

	/**
	 * helper method to handle the case when the node is a variable
	 * 
	 * @param root
	 */
	private void variableCase(CommandTreeNode root) {
		String value = myInput.get(index);
		CommandTreeNode temp = new CommandTreeNode("VARIABLE", value, 0, null);
		root.add(temp);

		printTestStatements(value, temp.getType(), root.getName());
		index++;
	}

	/**
	 * helper method to handle the case when the node is a constant
	 * 
	 * @param root
	 */
	private void constantCase(CommandTreeNode root) {
		String value = myInput.get(index);
		CommandTreeNode temp = new CommandTreeNode("CONSTANT", "CONSTANT",
				Double.parseDouble(myInput.get(index)), null);
		root.add(temp);

		printTestStatements(value, temp.getType(), root.getName());
		index++;
	}

	/**
	 * Creates a map that maps the Key to the number of parameters and its
	 * subcommand type
	 * 
	 * @return
	 */
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
	/** 
	 * Obtains the number of parameters given the key
	 * @param key
	 * @return
	 */
	private int obtainNumParams(String key) {
		return Integer.parseInt(parametersMap.get(key)[PARAM_INDEX]);
	}
	/**
	 * Obtains the subcommand type given the key
	 * @param key
	 * @return
	 */
	private String obtainSubCommand(String key) {
		return "COMMAND." + parametersMap.get(key)[TYPE_INDEX];
	}
}
