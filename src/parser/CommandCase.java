package parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import errors.CommandNotFoundException;

public class CommandCase extends TreeGenerator implements Cases {
	private Map<String, String[]> parametersMap;
	private static final int PARAM_INDEX = 0;
	private static final int TYPE_INDEX = 1;
	private boolean makingUserInstruction;
	private List<String> methodList = new ArrayList<>();

	public CommandCase() {
		parametersMap = createParametersMap();
	}

	public void initiate(CommandTreeNode root, String language) {
		String value = useResourceBundle(myInput.get(index));
		makingUserInstruction = value.equals("MakeUserInstruction");

		myRoot = new CommandTreeNode(obtainSubCommand(value), value, 0, null);
		int numParams = obtainNumParams(value);
		printTestStatements(value, obtainSubCommand(value), null);
		index++;
		for (int i = 0; i < numParams; i++) {
			super.helper(myRoot);
		}
	}

	/**
	 * helper method to handle the case when the node is a command
	 *
	 * @param root
	 */
	public void helper(CommandTreeNode root) {
		String value = useResourceBundle(myInput.get(index));
		index++;
		if (makingUserInstruction) {
			makingUserInstruction = false;
			makingInstructionsCase(root, value);
			return;
		}
		int numParams = obtainNumParams(value);

		CommandTreeNode temp = new CommandTreeNode(obtainSubCommand(value),
				value, 0, null);
		root.add(temp);

		printTestStatements(value, temp.getType(), root.getName());

		if (value.equals("Repeat")) {
			while (!myInput.get(index).equals("]")) {
				super.helper(temp);
			}
		} else {
			for (int i = 0; i < numParams; i++) {
				super.helper(temp);
			}
		}
	}

	private void makingInstructionsCase(CommandTreeNode root, String value) {
		CommandTreeNode temp = new CommandTreeNode(obtainSubCommand(value),
				value, 0, null);
		root.add(temp);
		printTestStatements(value, temp.getType(), root.getName());
		super.helper(root);
	}

	private String useResourceBundle(String input) {
		for (Entry<String, Pattern> p : languagePatternList) {
			if (p.getValue().matcher(input).matches())
				return p.getKey();
		}
		// if making procedure, return method call
		if (makingUserInstruction) {
			methodList.add(input);
			return input;
		}
		// if none found
		throw new CommandNotFoundException(input);
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
	 * 
	 * @param key
	 * @return
	 */
	protected int obtainNumParams(String key) {
		return Integer.parseInt(parametersMap.get(key)[PARAM_INDEX]);
	}

	/**
	 * Obtains the subcommand type given the key
	 * 
	 * @param key
	 * @return
	 */
	protected String obtainSubCommand(String key) {
		try {
			return "COMMAND." + parametersMap.get(key)[TYPE_INDEX];
		} catch (NullPointerException e) {
			return "COMMAND.USER";
		}

	}
	
	public List<String> getMethodsList() {
		return methodList;
	}
}
