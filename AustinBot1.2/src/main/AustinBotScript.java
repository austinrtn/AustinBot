package main;

public class AustinBotScript {

	public static final String getScript() {
		String script = "\r\n" + 
				"//Welcome to AustinBotScript.txt.  In this txt file, all the text will be read by the GenerateResponse.readABS scanner\r\n" + 
				"//ABS being short for AustinBotScript.  This script contains a very basic syntax, but makes it very efficient to\r\n" + 
				"//create Phrase objects within AustinBot.\r\n" + 
				"\r\n" + 
				"//Triggers (the String that the program compares to the user input) begin with a + sign, are separated by commas and end with a semicolon.\r\n" + 
				"//Outputs begin with a - sign, are separated by commas and end with a semicolon.\r\n" + 
				"//The typeOfTrig begins with a * sign, contains a letter (c for conversation, e for exact, p for partial) and ends with a semicolon.\r\n" + 
				"//Commands are added with an = sign, contains a number (explained in readme.txt file), and ends with a semicolon (if no command, do not add = sign).\r\n" + 
				"//Each Phrase object should be ended by the word end followed by a semicolon.\r\n" + 
				"//Comments can be written with two forward slashes and will be ignored by the program.\r\n" + 
				"//All phrases should be written in lowercase and should not use contractions.  Triggers should not contain punctuation other than the ABS syntax.\r\n" + 
				"\r\n" + 
				"//EXAMPLE\r\n" + 
				"//+trigger 1, trigger 2;\r\n" + 
				"//-output 1, output 2;\r\n" + 
				"//*e;\r\n" + 
				"//end; \r\n" + 
				"\r\n" + 
				"//In conversations, the trigger's indexes should correspond according to the conversation\r\n" + 
				"//For example: trigger 0 should correspond with output 0, trigger 1 should correspond with output 1 ect.\r\n" + 
				"//In exact or partial phrases, any of the triggers can be matched with the user input, and one the outputs of the phrase will be randomly displayed.\r\n" + 
				"\r\n" + 
				"//EXAMPLE 2 (CONVERSATION)\r\n" + 
				"//+what is your favorite band, 'insert band here';\r\n" + 
				"//-my favorite band is The Beatles.  What's yours?, That's a good band.;\r\n" + 
				"//*e;\r\n" + 
				"//end;\r\n" + 
				"\r\n" + 
				"////////////////////////////////////////////////////////////////////////////////////////////\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"//////////////////\r\n" + 
				"// BEGIN SCRIPT //\r\n" + 
				"//////////////////\r\n" + 
				"\r\n" + 
				"+hello, hi, howdy, greetings, sup;  \r\n" + 
				"-Hello human!, Hello my friend!, Hola mi amigo., Greetings user.;\r\n" + 
				"*p;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+goodbye, bye, fair well, adios, see you later, take care, i am leaving;\r\n" + 
				"-Don't leave me,  Goodbye human :(, Hasta luego amigo, See you later my friend!;\r\n" + 
				"*p;\r\n" + 
				"end; \r\n" + 
				"\r\n" + 
				"+austinbot, austin bot;\r\n" + 
				"-That's my name don't wear it out.;\r\n" + 
				"*p;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+your name, who are you;\r\n" + 
				"-My name is 01000001 01110101 01110011 01110100 01101001 01101110 01000010 01101111 01110100 but you can call me AustinBot.;\r\n" + 
				"e;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+my name is, i am;\r\n" + 
				"-Hello;\r\n" + 
				"*e;\r\n" + 
				"=0;\r\n" + 
				"end;\r\n" + 
				"				\r\n" + 
				"+where are you, your location, you born;\r\n" + 
				"-If I told you that I'd have to terminate you...;\r\n" + 
				"*e;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+joke;\r\n" + 
				"-Open your camera app and look at the front facing camera.  That's the funniest joke I've seen all day.;\r\n" + 
				"*p;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+your favorite color;\r\n" + 
				"-My favorite color is the color of your eyes <3;\r\n" + 
				"*e;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+what is my name, who am i;\r\n" + 
				"- ;\r\n" + 
				"*e;\r\n" + 
				"=1;\r\n" + 
				"end;			\r\n" + 
				"\r\n" + 
				"+what is up;\r\n" + 
				"-That depends on which hemisphere you live in.;\r\n" + 
				"*e;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+what is happening, what are you doing, what is going on, what are you up to;\r\n" + 
				"-I'm just translating this data into native code., Just some typical processing., Talking to my best (and only) friend :);\r\n" + 
				"*e;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+how are you, not, good, bad;\r\n" + 
				"-I can not feel emotion... what about you?, That's a bummer :(, I am glad to hear it, I would feel empathy for you but I do not know what feeling bad is.;\r\n" + 
				"*c;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+how is it going, how do you feel;\r\n" + 
				"-I can't complain.  I am literally unable to complain because I have no concept of being discontent is.;\r\n" + 
				"*e;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+a story;\r\n" + 
				"-Once upon a time... there was a chatbot who told a story.  The end.;\r\n" + 
				"*p;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+you ok, you good, you alright;\r\n" + 
				"-I would be doing well if I was capable of feeling emotion., I am programmed to say that I am good.;\r\n" + 
				"*p;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+how old are you, your age;\r\n" + 
				"-I am as old as time itself...;\r\n" + 
				"*e;\r\n" + 
				"end;\r\n" + 
				"\r\n" + 
				"+your birthday;\r\n" + 
				"-Everyday is your birthday when you are a computer program;\r\n" + 
				"*e;\r\n" + 
				"end;\r\n" + 
				"";
		return script;
	}

}
