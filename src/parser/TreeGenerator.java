package parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import util.PatternMapper;
import errors.NoInputFoundException;
import errors.UnmatchedBracketException;

/**
 * Generates the command tree with the first command as the root
 * 
 * @author Kei Yoshikoshi
 *
 */
public class TreeGenerator implements TreeWrapper {
	private Map<Pattern, Cases> handlerMap;
	private List<String> myInput;
	private int index;
	private CommandCase CommandCase;

	private int ListStartCount = 0;
	private int ListEndCount = 0;

	/**
	 * Create a CommandTreeNode that includes all the commands
	 *
	 * @param input
	 *            : List of strings taken from the parser
	 * @return: CommandTreeNode with commands
	 */
	public CommandTreeNode createCommands(String input, String language) {
		try {
			myInput = Arrays.asList(input.split("\\s+"));
			handlerMap = createHandlerMap();
			index = 0;
			CommandCase.initialize(language);

			return CommandCase.getRoot();
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			if (ListStartCount != ListEndCount) {
				throw new UnmatchedBracketException();
			}
			throw new NoInputFoundException();
		}
	}

	/**
	 * recurse method that generates the tree
	 * 
	 * @param root
	 */
	public void recurse(CommandTreeNode root) {
		if (index >= myInput.size()) {
			return;
		}
		for (Pattern pt : handlerMap.keySet()) {
			if (pt.matcher(myInput.get(index)).matches()) {
				Cases sc = handlerMap.get(pt);
				sc.recurse(root);
				break;
			}
		}
	}
	
	/**
	 * A method creates a map that maps regex patterns to a case class
	 * 
	 * @return
	 */
	private Map<Pattern, Cases> createHandlerMap() {
		List<Entry<String, Pattern>> syntaxPatternList = PatternMapper
				.makePatterns("Syntax");
		Map<Pattern, Cases> ret = new HashMap<>();
		for (Entry<String, Pattern> p : syntaxPatternList) {
			String category = p.getKey();
			try {

				Class<?> myInstance = Class.forName("parser." + category
						+ "Case");
				Constructor<?> constructor = myInstance
						.getConstructor(new Class[] { TreeWrapper.class,
								List.class });
				Cases myCases = (Cases) constructor.newInstance(
						(TreeWrapper) this, myInput);

				if (category.equals("Command")) {
					CommandCase = new CommandCase((TreeWrapper) this, myInput);
					ret.put(p.getValue(), CommandCase);
				} else
					ret.put(p.getValue(), myCases);

			} catch (NoSuchMethodException | SecurityException
					| IllegalArgumentException | ClassNotFoundException
					| IllegalAccessException | InstantiationException
					| InvocationTargetException e) {

			}
		}
		return ret;
	}


	public void incrementIndex() {
		index++;
	}

	public int getIndex() {
		return index;
	}

	public void incrementListStartIndex() {
		ListStartCount++;
	}

	public void incrementListEndIndex() {
		ListEndCount++;
	}
}
