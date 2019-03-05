package main;

import java.security.SecureRandom;

public class Phrase {

	private String trigger; // Trigger for phrase
	private String output; // Output for phrase
	private typeOfTrig type; // Type of phrase

	public String getTrigger() {
		return trigger;
	}

	public typeOfTrig getType() {
		return type;
	}

	private String triggerArray[]; // Contains possible triggers for conversations
	private String[] outputArray;// Contains possible outputs
	private boolean randomOutput = false; // If true, the phrase contains random outputs

	// The typeOfTrig determines the priority of searching for triggers.
	// Conversation has the highest priority, and partial the lowest.
	public static enum typeOfTrig {
		CONVERSATION, EXACT, PARTIAL
	};

	// The command variable will hold a Command object that will execute code for
	// special phrases (ex. storing and retrieving the name of the user)

	private Command command;

	public Command getCommand() {
		return command;
	}

	/////////////////////////
	// Phrase Constructor///
	////////////////////////

	/*
	 * The constructor takes a String array for triggers, a string array for
	 * outptut, the typeOfTrig, the user input, and a command (if no command it is
	 * left null).
	 */

	public Phrase(String[] triggerAr, String[] outputArray, typeOfTrig type, String input, Command command) {
		this.triggerArray = triggerAr;
		this.outputArray = outputArray;
		this.type = type;

		if (type == typeOfTrig.CONVERSATION) {
			this.trigger = triggerAr[0];
		}

		else {
			if (triggerAr.length > 1) {
				this.trigger = triggerAr[0];
				setMultiTrigger(input);
			} else
				this.trigger = triggerAr[0];

			if (outputArray.length > 1) {
				randomOutput = true;
			} else
				this.output = outputArray[0];
		}

		this.command = command;

	}

	// This method sets the trigger for conversations. This is necessary because it
	// does not allow all the triggers for a conversation to be accessible. 
	private void setMultiTrigger(String input) {
		for (int i = 0; i < triggerArray.length; i++) {
			if (input.contains(triggerArray[i])) {
				this.trigger = triggerArray[i];
				break;
			}

			else
				this.trigger = "NULLPOINTEREXCEPTION";
		}
	}

	// This method compares the user input to the trigger. If the input
	// contains/matches the trigger, it will return the proper output and the
	// GenerateResponse.generateResponse method will select this object as the
	// current phrase. If returned null, the genereateRespsone method will keep
	// looping through all the phrase objects in GenerateResponse.phraseList.

	public String returnOutput(String input) {
		if (input.contains(trigger)) {
			return getOutput(input);
		} else
			return null;
	}

	// This method will return the output for the phrase. If a phrase's randomOutput
	// boolean is true, it will select a random output from the randomOutput array.
	// If it is of type CONVERSATION, it will execute the corresponding method. If
	// neither of those are applicable, the program will return the output variable
	// which was initialized in the constructor.

	private String getOutput(String input) {
		if (randomOutput == true) {
			SecureRandom random = new SecureRandom();
			output = outputArray[random.nextInt(outputArray.length)];
		} else if (type == typeOfTrig.CONVERSATION)
			setConvoTrigger(input);
		return output;
	}

	// If the typeOfTrig for the phrase object in conversation
	public void setConvoTrigger(String input) {

		for (int i = 0; i < triggerArray.length; i++) {
			if (input.contains(triggerArray[i])) {
				trigger = triggerArray[i];
				output = outputArray[i];
				break;
			}
		}
	}
}
