package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import main.Phrase.typeOfTrig;

public class GenerateResponse {
	private String input;
	private Scanner searchABS;
	private ArrayList<Phrase> phraseList = new ArrayList<>(); // ArrayList that holds all the 'phrases' objects.
	private static boolean inConvo = false; // When true, the user is currently in a conversation with chatbot.
	private Phrase currentPhrase;
	private static Phrase currentConvo; // If the user and the chatbot are in a conversation, the corresponding phrase
										// object will be stored here.

	/*
	 * The generateResponse method first takes the user input, then the user input
	 * is passed through the Substitutes.subChars() method which removes punctuation
	 * and handles contractions. After that, the AustinBotScript.txt file is opened
	 * and searched through. The information from the file is used to create Phrase
	 * objects and will be added to phraseList (Phrase objects contain triggers and
	 * corresponding outputs). Once added, the list will be cycled in a for loop and
	 * each Phrase object's trigger will be compared to the user input until the
	 * user input matches a trigger or the list is empty. If a Phrase's trigger has
	 * been matched, the corresponding output will be displayed. If the phrase's
	 * Command object is not null, the corresponding command will be ran at the end
	 * of the outputResponse method.
	 */

	public void outputResponse(String input) {
		input = Substitute.subChars(input);
		this.input = input;

		openFile("austinbot/AustinBotScript.txt");
		searchFile();
		searchResponse();
		outputResponse();
	}

	private void openFile(String fileName) {
		this.searchABS = new Scanner(AustinBotScript.getScript());
		/* try { this.searchABS = new Scanner(new File("./AustinBotScript.txt")); }
		  catch (FileNotFoundException e) { Start.appendOutput("Error: " + e); }
		 */
	}

	// This method breaks down the text within the text file and separates the
	// information by syntax. It takes the information, and uses it to create a
	// phrase object which is then added to Phrase list.
	private void searchFile() {

		Phrase phrase;
		String[] trigArray = null;
		String[] outputArray = null;
		typeOfTrig type = null;
		Command cmd = null;

		while (searchABS.hasNext() == true) {
			String str = searchABS.next();

			if (str.contains("//")) // Skips to next line of file if it's a comment
				str = searchABS.nextLine();

			else if (str.contains("+")) { // Looks for triggers
				str = str.replace("+", "");
				trigArray = createArray(str);

			} else if (str.contains("-")) { // Looks for output
				str = str.replace("-", "");
				outputArray = createArray(str);
			}

			else if (str.contains("*")) { // Looks for typeOfTrig
				str = str.replace("*", "");
				str = str.replace(";", "");
				type = convertToType(str);
			}

			else if (str.contains("=")) { // Looks for commands
				str = str.replace("=", "");
				str = str.replace(";", "");
				int intConvert = Integer.parseInt(str);
				cmd = Command.getCommandFromList(intConvert);
			}

			if (str.contains("end;")) { // Creates phrase and adds it to phraseList.
				phrase = new Phrase(trigArray, outputArray, type, input, cmd);
				phraseList.add(phrase);
				cmd = null;

			}
		}
	}

	// This method breaks down the information by syntax and creates an array which
	// is then returned and used to created a Phrase object.
	private String[] createArray(String str) {
		ArrayList<String> indexList = new ArrayList<String>();
		boolean doLoop = true;
		while (doLoop == true) {

			if (str.contains(";")) {
				doLoop = false;
				str = str.replace(";", "");
				indexList.add(str);
				break;
			}

			if (str.contains(",")) {
				str = str.replace(",", "");
				indexList.add(str);
				str = searchABS.next();
			}

			else
				str += " " + searchABS.next();
		}

		int size = indexList.size();
		String[] array = new String[size];

		for (int i = 0; i < size; i++)
			array[i] = indexList.get(i);

		return array;

	}

	// Converts string From AustinBotScript to typeOfTrig
	private typeOfTrig convertToType(String t) {
		typeOfTrig type = null;

		if (t.equals("c"))
			type = typeOfTrig.CONVERSATION;
		if (t.equals("e"))
			type = typeOfTrig.EXACT;
		else if (t.equals("p"))
			type = typeOfTrig.PARTIAL;

		return type;
	}
	
	
	/*
	 * This method compares the triggers from Phrases of phraseList to the user input and then passes the output
	 * to the Start.java class to append it to the console.
	 */
	public void searchResponse() {

		Phrase.typeOfTrig type = Phrase.typeOfTrig.CONVERSATION;

		for (int x = 0; x < 3; x++) {
			for (int i = 0; i < phraseList.size(); i++) {
				currentPhrase = phraseList.get(i);

				// Checks to see if currentPhrase is typeOfTrig.Conversation. If so, that
				// conversation will be "active"
				if (inConvo == true) {
					currentConvo.setConvoTrigger(input); // Sets trigger for conversation
					// If the trigger is not part of the conversation, the conversation will no
					// longer be active.
					if (currentConvo.returnOutput(input) == null)
						inConvo = false;
					currentPhrase = currentConvo;
					break;
				}

				// Will set conversation if the phrase is type CONVERSATION
				if (currentPhrase.returnOutput(input) != null
						&& currentPhrase.getType() == Phrase.typeOfTrig.CONVERSATION) {
					inConvo = true;
					currentConvo = currentPhrase;
					break;
				}

				// If the input matches one of the triggers from a Phrase obj in the phrase
				// list,
				// the loop will break and the currentPhrase will be passed.
				else if (currentPhrase.returnOutput(input) != null && currentPhrase.getType() == type) {
					inConvo = false;
					break;
				}

			}
			if (inConvo == true)
				break;

			// Changes 'type' variable so that the Conversation and Exact typeOfTrig is
			// prioritized.
			if (currentPhrase.returnOutput(input) == null && x == 0)
				type = Phrase.typeOfTrig.EXACT;
			else if (currentPhrase.returnOutput(input) == null)
				type = Phrase.typeOfTrig.PARTIAL;
		}
	}

	/*
	 * If a trigger is matched, the corresponding output will be displayed to the
	 * user. If not, a standard response will be displayed telling the user that it
	 * does not understand the input.
	 */

	private void outputResponse() {

		if (input.isEmpty() == true) // If input is empty
			Start.appendOutput("AustinBot: Try inputing something next time.");

		else if (currentPhrase.returnOutput(input) == null) // If the input did not match a Phrase's trigger
			Start.appendOutput("AustinBot: Sorry, I don't understand.");

		else { // If the input matches a trigger it will append the corresponding output
			Start.appendOutput("AustinBot: " + currentPhrase.returnOutput(input));
			if (currentPhrase.getCommand() != null) // If currentPhrase object has a command, execute.
				currentPhrase.getCommand().getCommand(input, currentPhrase.getTrigger());
		}
		Start.appendOutput("\n\n");
	}
}
