package parser;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class TreeGenerator {
	private Map<String, Integer> parametersMap;
	private int index;
	private int bracketCount = 0;

	public TreeGenerator() {
		parametersMap = createParametersMap();
	}

	private void printTestStatements(String value, int count, int numParams,
			String type) {
		System.out.println(value);
		System.out.println("Count is: " + count);
		System.out.println("numParams is: " + numParams);
		System.out.println("Root value is: " + type);
		System.out.println();
	}

	public void createCommands(List<String> input, int count, int numParams,
			CommandTreeNode root) {
		if (index >= input.size()) {
			return;
		}
		if (root == null) {
			root = new CommandTreeNode(input.get(index), 0, null);
			numParams = parametersMap.get(input.get(index));

			printTestStatements(input.get(index), count, numParams, null);
			index++;

			for (int i = 0; i < numParams; i++) {
				createCommands(input, 0, numParams, root);
			}

		} else if (parametersMap.containsKey(input.get(index))) { // command
			numParams = parametersMap.get(input.get(index));
			CommandTreeNode temp = new CommandTreeNode(input.get(index), 0,
					null);
			root.add(temp);

			printTestStatements(input.get(index), 0, numParams, root.getType());

			boolean repeat = input.get(index).equals("Repeat");
			index++;

			if (repeat) {
				while (!input.get(index).equals("]")) {
					createCommands(input, 0, numParams, temp);
				}
			} else {
				for (int i = 0; i < numParams; i++) {
					createCommands(input, 0, numParams, temp);
				}
			}

		} else if (input.get(index).equals("[")) {
			CommandTreeNode temp = new CommandTreeNode(input.get(index)
					+ bracketCount++, 0, null);
			root.add(temp);

			numParams = Integer.MAX_VALUE;

			printTestStatements(input.get(index) + (bracketCount - 1), count,
					numParams, root.getType());

			index++;

			while (!input.get(index).equals("]")) {
				createCommands(input, 0, numParams, temp);
			}
			if (input.get(index).equals("]")) {
				index++;
				createCommands(input, 0, numParams, temp);
			}
		} else if (Pattern.matches("-?[0-9]+\\.?[0-9]*", input.get(index))) { // CONSTANT

			CommandTreeNode temp = new CommandTreeNode("Constant",
					Double.parseDouble(input.get(index)), null);
			root.add(temp);
			count++;

			printTestStatements(input.get(index), count, numParams,
					root.getType());
			index++;
			return;
		}
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
		String input = "chongfu + 20 * 3 5 [ chongfu 2 [ chongfu 30 [ qj 1 yz 2 ] qj 120 ] yz 60 ]";
		// String input = "repeat + 20 10 [ fd 1 rt 2]";
		
		// Representation to first input string:
		// repeat sum 20 30 product 3 5 
		// [
		//   repeat 2
		//   [
		//     repeat 30
		//     [
		//       fd 1
		//       rt 2
		//     ]
		//     fd 120
		//   ]
		//   rt 60
		// ]

		Parser pp = new Parser("Chinese");
		System.out.println(pp.parseList(input));
		TreeGenerator cf = new TreeGenerator();

		CommandTreeNode root = null;
		cf.createCommands(pp.parseList(input), 0, Integer.MAX_VALUE, root);
	}

}
