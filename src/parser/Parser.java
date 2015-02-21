package parser;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.Command;

import javax.swing.tree.TreeNode;

public class Parser {
	private static final int COMMENT_INDEX = 1;
	private static final int CONSTANT_INDEX = 2;
	private static final int VARIABLE_INDEX = 3;
	private static final int COMMAND_INDEX = 4;
	private static final int LISTSTART_INDEX = 8;
	private static final int LISTEND_INDEX = 9;
	private static final int GROUPSTART_INDEX = 10;
	private static final int GROUPEND_INDEX = 11;
	private int[] indices = { COMMENT_INDEX, CONSTANT_INDEX, VARIABLE_INDEX,
			COMMAND_INDEX, LISTSTART_INDEX, LISTEND_INDEX, GROUPSTART_INDEX,
			GROUPEND_INDEX };
	private Map<String, String> bundleMap;

	public Parser(String language) {
		bundleMap = createBundleMap(language);
	}

	/**
	 * Converts inputted string into another string using resource bundle
	 * 
	 * @param input
	 *            : String taken from front-end GUI - user syntax
	 * @return ret: String reformatted and standardized using resource bundle
	 */
	public String parse(String input) {
		String ret = "";
		Pattern p = Pattern.compile("(#.*)|(-?[0-9]+\\.?[0-9]*)|"
				+ "(:[a-zA-Z]+)|(([a-zA-Z_]+(\\?)?)|([\\-\\+\\-\\%\\*]))"
				+ "|(\\[)|(\\])|(\\()|(\\))");
		Matcher m = p.matcher(input);

		while (m.find()) {
			for (int i = 0; i < indices.length; i++) {
				if (m.group(indices[i]) != null) {
					if (indices[i] == COMMAND_INDEX) {
						ret += useResourceBundle(m.group(indices[i])) + " ";
					} else {
						ret += m.group(indices[i]) + " ";
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Creates HashMap that maps the possible user inputs to standard string
	 * values
	 * 
	 * @return: HashMap with possible user inputs and standard string values
	 */
	private HashMap<String, String> createBundleMap(String language) {
		ResourceBundle resources = ResourceBundle
				.getBundle("resources.languages/" + language);
		Enumeration<String> bundleKeys = resources.getKeys();
		HashMap<String, String> newMap = new HashMap<>();

		while (bundleKeys.hasMoreElements()) {
			String Key = bundleKeys.nextElement();
			String[] commands = resources.getString(Key).split("\\|");
			for (String com : commands) {
				newMap.put(com, Key);
			}
		}
		return newMap;
	}

	private String useResourceBundle(String input) {
		return bundleMap.get(input);
	}

	public static void main(String[] args) {
		String input = "repeat + 20 * 3 5 [ repeat 2 [ repeat 30 [ fd 1 rt 2 ] rt 120 ] rt 60 ]";

		// Representation:
		// repeat sum 20 30
		// [
		// repeat 2
		// [
		// repeat 30
		// [
		// fd 1
		// rt 2
		// ]
		// rt 120
		// ]
		// rt 60
		// ]

		Parser pp = new Parser("English");
		pp.parse(input);

	}
}