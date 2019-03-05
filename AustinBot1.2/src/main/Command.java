package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Command {


	/*
	 * Command objects are used to execute code when a trigger is matched with the
	 * user input.
	 */

	// This message allows the program to use a number to find the correct Command.
	// This way, commands can be written into the AustinBotScript.txt file with just
	// a number.
	// The number of the Command corresponds with it's index.
	public static final Command getCommandFromList(int key) {

		ArrayList<Command> cmdList = new ArrayList<>();
		HashMap<Integer, Command> map = new HashMap<>();

		cmdList.add(new SetName()); //1
		cmdList.add(new GetName()); //2

		for (int i = 0; i < cmdList.size(); i++) {
			map.put(i, cmdList.get(i));
		}

		return map.get(key);

	}

	public void getCommand(String input, String trigger) { // Overwritten method for classes that extend Command
		System.out.println("getCommand error");
	}
}

//////////////////////////
////////// COMMANDS /////
////////////////////////

// Command to set name in chatbot
class SetName extends Command {

	private static String username;

	public static String getUsername() {
		return username;
	}

	public void getCommand(String name, String trig) {
		name = name.replace(trig, "");
		name = name.replace(" ", "");
		Start.appendOutput(" " + name + "!");
		username = name;
	}
}

// Command to get name in chatbot
class GetName extends Command {

	public void getCommand(String input, String trigger) {
		if (SetName.getUsername() != null)
			Start.appendOutput("Your name is " + SetName.getUsername() + ".");
		else
			Start.appendOutput("You have not told me your name yet!");
	}
}
