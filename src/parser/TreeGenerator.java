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

public class TreeGenerator implements TreeWrapper {
	private Map<Pattern, Cases> handlerMap;
	private List<String> myInput;
	private int index;
	private String language;
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
<<<<<<< HEAD
			myInput = input;
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

			System.out.println("FINAL ROOT VALUE IS: " + myRoot.getValue());
			return myRoot;
=======
			myInput = Arrays.asList(input.split("\\s+"));
			this.language = language;
			handlerMap = createHandlerMap();
			index = 0;
			CommandCase.initiate(language);
			
			System.out.println("FINAL ROOT VALUE IS: "
					+ CommandCase.getRoot().getName());

			return CommandCase.getRoot();
>>>>>>> cb2c6197c6142e0211c3c563c588b54d237c13ef
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			e.printStackTrace();
			if (ListStartCount != ListEndCount)
				throw new UnmatchedBracketException();
			throw new NoInputFoundException();
		}
	}

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

	public void printTestStatements(String value, String type, String name) {
		System.out.println(value);
		System.out.println("Type is: " + type);
		System.out.println("Root value is: " + name);
		System.out.println();
	}

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
				e.printStackTrace();
			}
			// switch (category) {
			// case "ListStart":
			// ret.put(p.getValue(), new ListStartCase((TreeWrapper) this,
			// myInput));
			// break;
			// case "ListEnd":
			// ret.put(p.getValue(), new ListEndCase((TreeWrapper) this,
			// myInput));
			// break;
			// case "Command":
			// CommandCase = new CommandCase((TreeWrapper) this, myInput);
			// ret.put(p.getValue(), CommandCase);
			// break;
			// case "Variable":
			// ret.put(p.getValue(), new VariableCase((TreeWrapper) this,
			// myInput));
			// break;
			// case "Constant":
			// ret.put(p.getValue(), new ConstantCase((TreeWrapper) this,
			// myInput));
			// break;
			// case "GroupStart":
			// ret.put(p.getValue(), new GroupStartCase((TreeWrapper) this,
			// myInput));
			// break;
			// case "GroupEnd":
			// ret.put(p.getValue(), new GroupEndCase((TreeWrapper) this,
			// myInput));
			// break;
			// default:
			// break;
			// }
		}
		return ret;
	}

	public List<String> getMethodsList() {
		return CommandCase.getMethodsList();
	}

	public void incrementIndex() {
		index++;
	}

	public int getIndex() {
		return index;
	}

	public String getLanguage() {
		return language;
	}

	@Override
	public void incrementListStartIndex() {
		ListStartCount++;
	}

	@Override
	public void incrementListEndIndex() {
		ListEndCount++;
	}
}
