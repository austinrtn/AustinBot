Program Name: AustinBot
Author: Austin Kolongowski
Version: 1.2

AustinBot is my attempt of creating a program that allows the user to "talk" to the program and even have conversations.
The goals of AustinBot were to create a simple interface for the user, allow the user to have an ongoing conversation with AustinBot, and making it very
easy for developers to add to the list of things AustinBot would be able to understand and respond to.  Each of these goals were met.  

The program begins in the Start.java file, along with were the GUI is initiated.  The program was made with the intention to keep the GUI and processing
as separated as possible.  From there the user input is taken and proccessed in GenerateResponse.class.  A list of Phrase objects are created (Phrase objects
contain triggers and outputs) by using the file called "AustinBotScript.txt".  A scanner object reads the text file and breaks down the information
by syntax that I created and is explained in the AustinBotScript.txt file 
(Unfortunately, the program is unable to find the file AFTER it is executed into a jar file. Until I am
able to fix this, the file that it reads from is containted in AustinBotScript.java.  It is less effecient but it will work after exported to jar).
The user input is then compared to each 'trigger' in the PhraseList arraylist and if the trigger is contained in the user input, the correspsonding output
will be displayed.  The program is not case sensitive, ignores punctuation, and is able to read contractions as well.  
