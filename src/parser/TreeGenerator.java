package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import util.PatternMapper;
import errors.NoInputFoundException;
import errors.UnmatchedBracketException;

public class TreeGenerator {
	private static final int START_INDEX = 0;
	protected static Map<Pattern, TreeGenerator> handlerMap;
	protected static Map<String, String[]> parametersMap;
	protected static List<String> myInput;
	protected static int index;
	protected static CommandTreeNode myRoot;
	private static String language = "English";
	protected static int ListStartCount = 0;
	protected static int ListEndCount = 0;
	
	private static boolean makingUserInstruction = false;
	private static ArrayList<String> methodList = new ArrayList<>();


	private boolean isMethod;


	/**
	 * Create a CommandTreeNode that includes all the commands
	 *
	 * @param input
	 *            : List of strings taken from the parser
	 * @return: CommandTreeNode with commands
	 */
	public CommandTreeNode createCommands(String input) {
		try {
			handlerMap = createHandlerMap();
			myInput = Arrays.asList(input.split("\\s+"));
			index = 0;
			
			String value = myInput.get(index);
			boolean to = value.equals("MakeUserInstruction");
			if (to) {
				isMethod = true;
			}
			helper(myRoot);
			
			System.out.println("FINAL ROOT VALUE IS: " + myRoot.getName());
			
			return myRoot;
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			e.printStackTrace();
			if (ListStartCount != ListEndCount)
				throw new UnmatchedBracketException();
			throw new NoInputFoundException();
		}
	}

	protected void helper(CommandTreeNode root) {
		if (index >= myInput.size()) {
			return;
		}
		if (index==START_INDEX) {
			CommandCase startCase = new CommandCase("English");
			startCase.initiate(root);
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
				ret.put(p.getValue(), new CommandCase(language));
				break;
			case "Variable":
				ret.put(p.getValue(), new VariableCase());
				break;
			case "Constant":
				ret.put(p.getValue(), new ConstantCase());
				break;
			case "GroupStart":
				ret.put(p.getValue(), new GroupStartCase());
				break;
			case "GroupEnd":
				ret.put(p.getValue(), new GroupEndCase());
				break;
			default:
				break;
			}
		}
		return ret;
	}
}
