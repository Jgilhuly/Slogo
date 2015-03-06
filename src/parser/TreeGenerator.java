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

public class TreeGenerator {

	protected void printTestStatements(String value, String type, String name) {
		System.out.println(value);
		System.out.println("Type is: " + type);
		System.out.println("Root value is: " + name);
		System.out.println();
	}
	
	protected List<String> myInput;
	protected int index;
	
	public List<String> getMethodsList() {
		return CommandCase.getMethodsList();
	}
}
