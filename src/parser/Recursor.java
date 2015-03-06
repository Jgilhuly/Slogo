package parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import util.PatternMapper;
import errors.NoInputFoundException;
import errors.UnmatchedBracketException;

public class Recursor {
	TreeGenerator[] list = { new CommandCase(), new CommentCase(),
			new ConstantCase(), new GroupEndCase(), new GroupStartCase(),
			new ListStartCase(), new VariableCase() };
	private Map<Pattern, Cases> handlerMap;
	protected List<Entry<String, Pattern>> languagePatternList;
	protected List<String> myInput;
	protected int index;
	protected CommandTreeNode myRoot;
	private String language;
	protected int ListStartCount = 0;
	protected int ListEndCount = 0;
	private static final int START_INDEX = 0;

	private final CommandCase CommandCase = new CommandCase();
	private final CommentCase CommentCase = new CommentCase();
	private final ConstantCase ConstantCase = new ConstantCase();
	private final GroupEndCase GroupEndCase = new GroupEndCase();
	private final GroupStartCase GroupStartCase = new GroupStartCase();
	private final ListStartCase ListStartCase = new ListStartCase();
	private final VariableCase VariableCase = new VariableCase();

	/**
	 * Create a CommandTreeNode that includes all the commands
	 *
	 * @param input
	 *            : List of strings taken from the parser
	 * @return: CommandTreeNode with commands
	 */
	public CommandTreeNode createCommands(String input, String language) {
		try {
			handlerMap = createHandlerMap();
			languagePatternList = PatternMapper.makePatterns(language);
			myInput = Arrays.asList(input.split("\\s+"));
			index = 0;

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
		if (index == START_INDEX) {
			CommandCase.initiate(root, language);
			return;
		}
		for (Pattern pt : handlerMap.keySet()) {
			if (pt.matcher(myInput.get(index)).matches()) {
				Cases cases = handlerMap.get(pt);
				cases.helper(root);
				break;
			}
		}
	}

	private Map<Pattern, Cases> createHandlerMap() {
		List<Entry<String, Pattern>> syntaxPatternList = PatternMapper
				.makePatterns("Syntax");
		Map<Pattern, Cases> ret = new HashMap<>();
		for (Entry<String, Pattern> p : syntaxPatternList) {
			String category = p.getKey();
			switch (category) {
			case "ListStart":
				ret.put(p.getValue(), ListStartCase);
				break;
			case "Command":
				ret.put(p.getValue(), CommandCase);
				break;
			case "Variable":
				ret.put(p.getValue(), VariableCase);
				break;
			case "Constant":
				ret.put(p.getValue(), ConstantCase);
				break;
			case "GroupStart":
				ret.put(p.getValue(), GroupStartCase);
				break;
			case "GroupEnd":
				ret.put(p.getValue(), GroupEndCase);
				break;
			default:
				break;
			}
		}
		return ret;
	}
}

// private final CommandCase CommandCase = new CommandCase();
// private final CommentCase CommentCase = new CommentCase();
// private final ConstantCase ConstantCase = new ConstantCase();
// private final GroupEndCase GroupEndCase = new GroupEndCase();
// private final GroupStartCase GroupStartCase = new GroupStartCase();
// private final ListStartCase ListStartCase = new ListStartCase();
// private final VariableCase VariableCase = new VariableCase();