package parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.Command;

public class CommandFactory {
	private Map<String, Integer> parametersMap;
	private int index;

	public CommandFactory() {
		parametersMap = createParametersMap();
	}

	List<Command> listOfCommands = new ArrayList<Command>();

	public void createCommands(List<String> input, int count, int numParams,
			CommandTreeNode root) {
		if (count == numParams) {
			System.out.println("base case params");
			return;
		}
		if (input.get(index).equals("]"))
			return;

		if (root == null) {
			root = new CommandTreeNode(input.get(index), 0, null);
			numParams = parametersMap.get(input.get(index));

			System.out.println(input.get(index));
			System.out.println("Count is: " + count);
			System.out.println("numParams is: " + numParams);
			System.out.println("Root value is: " + root.getType());
			System.out.println();
			index++;

			createCommands(input, 0, numParams, root);
		}
		else if (parametersMap.containsKey(input.get(index))) { // command
			CommandTreeNode temp = new CommandTreeNode(input.get(index), 0,
					null);
			root.add(temp);
			numParams = parametersMap.get(input.get(index));

			System.out.println(input.get(index));
			System.out.println("Count is: " + 0);
			System.out.println("numParams is: " + numParams);
			System.out.println("Root value is: " + root.getType());
			System.out.println();
			index++;

			createCommands(input, 0, numParams, temp);

			if (++count == numParams) {
				System.out.println("base case params");
				return;
			}
			if (input.get(index).equals("]"))
				System.out.println("base case ]");
				return;

		}
		if (input.get(index).equals("[")) {
			CommandTreeNode temp = new CommandTreeNode(input.get(index), 0,
					null);
			root.add(temp);

			numParams = Integer.MAX_VALUE;
			System.out.println(input.get(index));
			System.out.println("Count is: " + count);
			System.out.println("numParams is: " + numParams);
			System.out.println("Root value is: " + root.getType());
			System.out.println();
			index++;

			createCommands(input, 0, numParams, temp);

			if (++count==numParams) {
				System.out.println("base case params");
				return;
			}
			if (input.get(index).equals("]"))
				System.out.println("base case ]");
				return;

		} else if (Pattern.matches("-?[0-9]+\\.?[0-9]*", input.get(index))) { //CONSTANT
			CommandTreeNode temp = new CommandTreeNode("Constant",
					Double.parseDouble(input.get(index)), null);
			root.add(temp);
			count++;
			System.out.println(input.get(index));
			System.out.println("Count is:" + count);
			System.out.println("numParams is: " + numParams);
			System.out.println("Root value is: " + root.getType());
			System.out.println();
			index++;

			createCommands(input, count, numParams, root);

			if (++count==numParams) {
				System.out.println("base case params");
				return;
			}
			if (input.get(index).equals("]"))
				System.out.println("base case ]");
				return;
		}
		System.out.println("END OF LEVEL");
	}
	
	
	
	//	public void createCommands(List<String> input, int count, int numParams,
	//			CommandTreeNode root) {
	//		if (count == numParams) {
	//			System.out.println("base case");
	//			return;
	//		} else if (input.get(index).equals("]"))
	//			return;
	//
	//		if (root == null) {
	//			root = new CommandTreeNode(input.get(index), 0, null);
	//			numParams = parametersMap.get(input.get(index));
	//			count = 0;
	//			System.out.println(input.get(index));
	//			System.out.println("Count is: " + count);
	//			System.out.println("numParams is: " + numParams);
	//			System.out.println();
	//			index++;
	//
	//		} else if (parametersMap.containsKey(input.get(index))) { // command
	//			CommandTreeNode temp = new CommandTreeNode(input.get(index), 0,
	//					null);
	//			root.add(temp);
	//			numParams = parametersMap.get(input.get(index));
	//			count = 0;
	//			root = temp;
	//			System.out.println(input.get(index));
	//			System.out.println("Count is: " + 0);
	//			System.out.println("numParams is: " + numParams);
	//			System.out.println("Root value is: " + root.getType());
	//			System.out.println();
	//			index++;
	//		} else if (input.get(index).equals("[")) {
	//			CommandTreeNode temp = new CommandTreeNode(input.get(index), 0,
	//					null);
	//			root.add(temp);
	//			root = temp;
	//			count = 0;
	//			numParams = Integer.MAX_VALUE;
	//			System.out.println(input.get(index));
	//			System.out.println("Count is: " + count);
	//			System.out.println("numParams is: " + numParams);
	//			System.out.println("Root value is: " + root.getType());
	//			System.out.println();
	//			index++;
	//
	//		} else if (Pattern.matches("-?[0-9]+\\.?[0-9]*", input.get(index))) { // CONSTANT
	//			CommandTreeNode temp = new CommandTreeNode("Constant",
	//					Double.parseDouble(input.get(index)), null);
	//			root.add(temp);
	//			count++;
	//			System.out.println(input.get(index));
	//			System.out.println("Count is:" + count);
	//			System.out.println("numParams is: " + numParams);
	//			System.out.println("Root value is: " + root.getType());
	//			System.out.println();
	//			index++;
	//		}
	//		createCommands(input, count, numParams, root);
	//		System.out.println("reached end of level");
	//
	//		// return;
	//	}


	private HashMap<String, Integer> createParametersMap() {
		ResourceBundle resources = ResourceBundle
				.getBundle("parser/parameters");
		Enumeration<String> paramKeys = resources.getKeys();
		HashMap<String, Integer> newMap = new HashMap<>();

		while (paramKeys.hasMoreElements()) {
			String Key = paramKeys.nextElement();
			newMap.put(Key, Integer.parseInt(resources.getString(Key)));
		}
		return newMap;
	}

	public static void main(String[] args) {
		String input = "chongfu + 20 * 3 5 [ chongfu 2 [ chongfu 30 [ qj 1 yz 2 ] qj 120 ] yz 60 ]";
		// String input = "repeat + 20 10 [ fd 1 rt 2]";
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

		Parser pp = new Parser("Chinese");
		System.out.println(pp.parseList(input));
		CommandFactory cf = new CommandFactory();

		CommandTreeNode root = null;
		cf.createCommands(pp.parseList(input), 0, Integer.MAX_VALUE, root);
	}

}
