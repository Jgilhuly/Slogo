package util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class PatternMapper {
	public static List<Entry<String, Pattern>> makePatterns(String fileName) {
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
}
