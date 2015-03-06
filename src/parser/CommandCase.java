package parser;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import util.PatternMapper;
import errors.CommandNotFoundException;

public class CommandCase extends TreeGenerator {
	private List<Entry<String, Pattern>> languagePatternList;
	private static Map<String, String[]> parametersMap;
	private static final int PARAM_INDEX = 0;
	private static final int TYPE_INDEX = 1;

	public CommandCase(String language) {
		languagePatternList = PatternMapper.makePatterns(language);
		parametersMap = createParametersMap();
	}

	public void initiate(CommandTreeNode root) {
		String value = useResourceBundle(myInput.get(index));
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
	protected void helper(CommandTreeNode root) {
		String value = useResourceBundle(myInput.get(index));
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

	private String useResourceBundle(String input) {
		for (Entry<String, Pattern> p : languagePatternList) {
			if (p.getValue().matcher(input).matches())
				return p.getKey();
		}
		// if making procedure, return method call
		// if (makingUserInstruction) {
		// makingUserInstruction = false;
		// methodList.add(input);
		// return input;
		// }
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
		return "COMMAND." + parametersMap.get(key)[TYPE_INDEX];
	}
}
