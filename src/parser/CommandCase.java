package parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import util.PatternMapper;
import errors.CommandNotFoundException;

/**
 * A class that deals with when the word is a command.
 * 
 * @author Kei Yoshikoshi
 *
 */
public class CommandCase implements Cases {
	private Map<String, String[]> parametersMap;
	private static final int PARAM_INDEX = 0;
	private static final int TYPE_INDEX = 1;
	private boolean makingUserInstruction;
	private List<String> methodList;
	private List<Entry<String, Pattern>> languagePatternList;
	private TreeWrapper wrapper;
	private List<String> myInput;
	private CommandTreeNode myRoot;

	public CommandCase(TreeWrapper wrapper, List<String> input) {
		this.wrapper = wrapper;
		methodList = new ArrayList<String>();
		parametersMap = createParametersMap();
		myInput = input;
	}

	/**
	 * Method used to initialize the tree generation
	 * 
	 * @param language
	 */
	public void initialize(String language) {
		languagePatternList = PatternMapper.makePatterns(language);
		String value = useResourceBundle(myInput.get(wrapper.getIndex()));
		makingUserInstruction = value.equals("MakeUserInstruction");

		myRoot = new CommandTreeNode(obtainSubCommand(value), value, 0);
		int numParams = obtainNumParams(value);
		wrapper.incrementIndex();
		for (int i = 0; i < numParams; i++) {
			wrapper.recurse(myRoot);
		}
	}

	/**
	 * recursive method to generate the tree
	 *
	 * @param root
	 */
	public void recurse(CommandTreeNode root) {
		String value = useResourceBundle(myInput.get(wrapper.getIndex()));
		wrapper.incrementIndex();
		if (makingUserInstruction) {
			makingUserInstruction = false;
			makingInstructionsCase(root, value);
			return;
		}
		int numParams = obtainNumParams(value);

		CommandTreeNode temp = new CommandTreeNode(obtainSubCommand(value),
				value, 0);
		root.add(temp);

		for (int i = 0; i < numParams; i++) {
			wrapper.recurse(temp);
		}
	}
	
	/**
	 * recursive case when the command is to make an instruction
	 * 
	 * @param root
	 * @param value
	 */
	private void makingInstructionsCase(CommandTreeNode root, String value) {
		CommandTreeNode temp = new CommandTreeNode(obtainSubCommand(value),
				value, 0);
		root.add(temp);
		wrapper.recurse(root);
	}

	/**
	 * Looks up whether the command is in the resource bundle and throws an
	 * exception if not
	 * 
	 * @param input
	 * @return
	 */
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
	private int obtainNumParams(String key) {
		return Integer.parseInt(parametersMap.get(key)[PARAM_INDEX]);
	}

	/**
	 * Obtains the subcommand type given the key
	 * 
	 * @param key
	 * @return
	 */
	private String obtainSubCommand(String key) {
		try {
			return "COMMAND." + parametersMap.get(key)[TYPE_INDEX];
		} catch (NullPointerException e) {
			return "COMMAND.USER";
		}

	}

	public CommandTreeNode getRoot() {
		return myRoot;
	}

	public List<String> getMethodsList() {
		return methodList;
	}

}
