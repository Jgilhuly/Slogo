package parser;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import util.PatternMapper;
import errors.NoInputFoundException;

public class TreeGenerator {
	protected static Map<Pattern, TreeGenerator> handlerMap;
	protected static Map<String, String[]> parametersMap;
	protected static List<String> myInput;
	protected static int index;

	private static final int PARAM_INDEX = 0;
	private static final int TYPE_INDEX = 1;
	private CommandTreeNode myRoot;
	private boolean isMethod;

	public TreeGenerator() {
		parametersMap = createParametersMap();
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
			handlerMap = createHandlerMap();
			myInput = input;
			index = 0;
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
			e.printStackTrace();
			throw new NoInputFoundException();
		}
	}

	protected void helper(CommandTreeNode root) {
		if (index >= myInput.size()) {
			return;
		}
		for (Pattern pt : handlerMap.keySet()) {
			if (pt.matcher(myInput.get(index)).matches()) {
				TreeGenerator sc = handlerMap.get(pt);
				sc.helper(root);
				break;
			}
		}
	}

	/**
	 * Just to print out the tree and its parent node for testing
	 * 
	 * @param value
	 * @param type
	 * @param name
	 */
	protected void printTestStatements(String value, String type, String name) {
		System.out.println(value);
		System.out.println("Type is: " + type);
		System.out.println("Root value is: " + name);
		System.out.println();
	}

	private Map<Pattern, TreeGenerator> createHandlerMap() {
		List<Entry<String, Pattern>> syntaxPatternList = PatternMapper
				.makePatterns("Syntax");
		Map<Pattern, TreeGenerator> ret = new HashMap<>();
		for (Entry<String, Pattern> p : syntaxPatternList) {
			String category = p.getKey();
			switch (category) {
			case "ListStart":
				ret.put(p.getValue(), new ListStartCase());
				break;
			case "Command":
				ret.put(p.getValue(), new CommandCase());
				break;
			case "Variable":
				ret.put(p.getValue(), new VariableCase());
				break;
			case "Constant":
				ret.put(p.getValue(), new ConstantCase());
				break;
			}
		}
		return ret;
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
