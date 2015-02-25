package parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.AbstractMap.SimpleEntry;

import errors.CommandNotFoundException;
import errors.UnmatchedBracketException;

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
	private List<Entry<String, Pattern>> patternList;
	private int ListStartCount = 0;
	private int ListEndCount = 0;

	public Parser(String language) {
		patternList = makePatterns(language);
	}

	/**
	 * Converts inputted string into List using resource bundle
	 * 
	 * @param input
	 *            : String taken from front-end GUI - user syntax
	 * @return ret: List of strings reformatted and standardized using resource
	 *         bundle
	 */
	private List<String> parseList(String input) {
		List<String> ret = new ArrayList<>();
		Pattern p = Pattern.compile("(#.*)|(-?[0-9]+\\.?[0-9]*)|"
				+ "(:[a-zA-Z]+)|(([a-zA-Z_]+(\\?)?)|([\\-\\+\\-\\%\\*]))"
				+ "|(\\[)|(\\])|(\\()|(\\))");
		Matcher m = p.matcher(input);
		while (m.find()) {
			for (int i = 0; i < indices.length; i++) {
				if (m.group(indices[i]) != null) {
					if (indices[i] == COMMAND_INDEX) {
						ret.add(useResourceBundle(m.group(indices[i])));
					} else {
						ret.add(m.group(indices[i]));
						if (indices[i] == LISTSTART_INDEX) {
							ListStartCount++;
						} else if (indices[i] == LISTEND_INDEX) {
							ListEndCount++;
						}
					}
				}
			}
		}
		if (ListStartCount != ListEndCount)
			throw new UnmatchedBracketException();
		return ret;
	}

	private List<Entry<String, Pattern>> makePatterns(String language) {
		ResourceBundle resources = ResourceBundle
				.getBundle("resources.languages/" + language);
		List<Entry<String, Pattern>> patterns = new ArrayList<>();
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			patterns.add(new SimpleEntry<String, Pattern>(key, Pattern.compile(
					regex, Pattern.CASE_INSENSITIVE)));
		}
		return patterns;
	}

	private String useResourceBundle(String input) {
		for (Entry<String, Pattern> p : patternList) {
			if (p.getValue().matcher(input).matches())
				return p.getKey();
		}
		// if not found

		throw new CommandNotFoundException(input);
		// return this message to the GUI.
	}

	public CommandTreeNode makeTree(String input) {
		List<String> translate = parseList(input);
		TreeGenerator tg = new TreeGenerator();
		
		return tg.createCommands(translate);
	}
}