package parser;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
		if (count == numParams+1) {
			System.out.println("base case");
			return;
		}
		if (input.get(index).equals("]"))
			return;
		System.out.println(input.get(index));
		System.out.println("Count is:" + count);
		System.out.println("numParams is: " + numParams);
		System.out.println();
		index++;
		if (root == null) {
			root = new CommandTreeNode(input.get(index), 0, null);
			numParams = parametersMap.get(input.get(index));
			createCommands(input, 0, numParams, root);
			
			System.out.println("back to top");
		} else if (parametersMap.containsKey(input.get(index))) { // command
			count = 0;
			CommandTreeNode temp = new CommandTreeNode(input.get(index), 0,
					null);
			root.add(temp);
			numParams = parametersMap.get(input.get(index));
			createCommands(input, count++, numParams, temp);
		} else if (input.get(index).equals("[")) {
			CommandTreeNode temp = new CommandTreeNode(input.get(index), 0, null);
			root.add(temp);
			createCommands(input, ++count, numParams, temp);
		} else {
			CommandTreeNode temp = new CommandTreeNode("Constant",
					Double.parseDouble(input.get(index)), null);
			root.add(temp);
			createCommands(input, ++count, numParams, temp);
		}
		System.out.println("coming back up");
	}
	
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
		// String input =
		// "chongfu + 20 * 3 5 [ chongfu 2 [ chongfu 30 [ qj 1 yz 2 ] qj 120 ] yz 60 ]";
		String input = "repeat + 20 10 [ fd 1 ]";
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
		System.out.println(pp.parseList(input));
		CommandFactory cf = new CommandFactory();

		CommandTreeNode root = null;
		cf.createCommands(pp.parseList(input), 0, Integer.MAX_VALUE, root);
	}
}
