package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Substitute {

	private static ArrayList<String> punctuationList = new ArrayList<String>(); // List of punctuation
	private static ArrayList<String> subWordList = new ArrayList<String>(); // List of contractions
	private static Map<String, String> subDict = new HashMap<String, String>(); // Dictionary to map a contraction to
																				// its non contracted from.

	public static String subChars(String input) {
		input = input.toLowerCase();
		Substitute s = new Substitute();
		s.createSubLists();

		// Replaces all punctuation with empty strings so that punctuation so that it
		// can be compared to triggers.
		for (int i = 0; i < punctuationList.size(); i++) {
			String punctuation = punctuationList.get(i);
			if (input.contains(punctuation))
				input = input.replace(punctuation, "");
		}

		// Replaces contractions with their non contracted forms so that the user can
		// use contractions and still match the triggers
		for (int i = 0; i < subDict.size(); i++) {
			String key = subWordList.get(i);
			String value = subDict.get(key);
			if (subDict.containsKey(key))
				input = input.replace(key, value);
		}
		return input;
	}

	// Adds punctuation to list and maps contractions/non contractions together
	private void createSubLists() {

		// Remove Punctuation
		punctuationList.add("?");
		punctuationList.add(".");
		punctuationList.add("!");
		punctuationList.add("/");
		punctuationList.add(",");
		punctuationList.add("'");

		// Sub words
		subWordList.add("im");
		subDict.put("im", "I am");

		subWordList.add("hows");
		subDict.put("hows", "how is");

		subWordList.add("whos");
		subDict.put("whos", "who is");

		subWordList.add("whats");
		subDict.put("whats", "what is");
	}
}
