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
	private List<Entry<String, Pattern>> languagePatternList;
	private List<Entry<String, Pattern>> syntaxPatternList;
	private int ListStartCount = 0;
	private int ListEndCount = 0;
	private boolean makingUserInstruction = false;
	private ArrayList<String> methodList = new ArrayList<>();

	public Parser(String language) {
		languagePatternList = makePatterns(language);
		syntaxPatternList = makePatterns("Syntax");
	}

	/**
	 * Converts inputted string into List using resource bundle
	 * 
	 * @param input
	 *            : String taken from front-end GUI - user syntax
	 * @return ret: List of strings reformatted and standardized using resource
	 *         bundle
	 */
	public List<String> parseList(String input) {
		List<String> ret = new ArrayList<>();
		String[] split = input.split(" ");
		for (String sp : split) {
			for (Entry<String, Pattern> p : syntaxPatternList) {
				if (p.getValue().matcher(sp).matches()) {
					if (p.getKey().equals("Command")) {
						String command = useResourceBundle(sp);
						if (command.equals("MakeUserInstruction"))
							makingUserInstruction = true;
						ret.add(command);
					} else {
						ret.add(sp);
						if (p.getKey().equals("ListStart")) {
							ListStartCount++;
						} else if (p.getKey().equals("ListEnd")) {
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

	private List<Entry<String, Pattern>> makePatterns(String fileName) {
		ResourceBundle resources = ResourceBundle
				.getBundle("resources.languages/" + fileName);
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
		for (Entry<String, Pattern> p : languagePatternList) {
			if (p.getValue().matcher(input).matches())
				return p.getKey();
		}
		// if making procedure, return method call
		if (makingUserInstruction) {
			makingUserInstruction = false;
			methodList.add(input);
			return input;
		}
		// if none found
		throw new CommandNotFoundException(input);
	}

}