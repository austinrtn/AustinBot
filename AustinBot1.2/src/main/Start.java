/*
 * Program Name: AustinBot
 * Author: Austin Kolongowski
 * Version: 1.2
 */

package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Start extends JFrame implements KeyListener {

	/*
	 * The start class contains the Main method and also creates the GUI. The user
	 * types into the text field at the bottom of the GUI and either clicks the
	 * submit button or hits enter. Once the user submits their input, the
	 * 'generateResponse' method is ran in the GenerateResponse class, where much of
	 * the processing is handled.
	 */

	private JFrame frmAustinbot; // Frame for GUI
	private static JTextArea txtAreaOutput = new JTextArea(); // The output textfield
	private static JTextField txtAreaInput = new JTextField();// The Input textfield

	private static int inputCounter = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frmAustinbot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Initializes GUI and its components 
	public Start() {

		initialize();

		getContentPane().setLayout(null);

		ImageIcon title = new ImageIcon(this.getClass().getResource("/title.jpg"));
		frmAustinbot.getContentPane().setLayout(null);

		JScrollPane outScrollPane = new JScrollPane();
		outScrollPane.setBounds(46, 138, 502, 342);
		frmAustinbot.getContentPane().add(outScrollPane);

		txtAreaOutput.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtAreaOutput.setEditable(false);
		txtAreaOutput.setLineWrap(true);
		txtAreaOutput.setWrapStyleWord(true);
		txtAreaOutput.setText("Hello, I am AustinBot.  You can use the text box below to ask me questions."); // Intro
																												// Message
		txtAreaOutput.setFont(new Font("Monospaced", Font.PLAIN, 17));
		outScrollPane.setViewportView(txtAreaOutput);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubmit.addActionListener(new ActionListener() { // Submit button action listener

			@Override
			public void actionPerformed(ActionEvent arg0) {
				submitInput();
			}
		});
		btnSubmit.setBounds(459, 513, 89, 25);
		frmAustinbot.getContentPane().add(btnSubmit);

		txtAreaInput.setSize(new Dimension(65, 23));
		txtAreaInput.setBounds(46, 514, 403, 23);
		txtAreaInput.addKeyListener(this);
		frmAustinbot.getContentPane().add(txtAreaInput);
		txtAreaInput.setColumns(10);

		JLabel backGround = new JLabel("");
		backGround.setIcon(title);
		backGround.setBounds(0, 0, 594, 571);
		frmAustinbot.getContentPane().add(backGround);

		frmAustinbot.setLocationRelativeTo(null);

	}

	private void initialize() {
		frmAustinbot = new JFrame();
		frmAustinbot.setResizable(false);
		frmAustinbot.setTitle("AustinBot");
		frmAustinbot.setBounds(0, 0, 600, 600);
		frmAustinbot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		txtAreaOutput.getCaret().setDot(txtAreaOutput.getDocument().getLength());

	}
	
	// Takes the input from text field and sends it to generateResponse method
	public void submitInput() {
		if (inputCounter == 0)
			txtAreaOutput.setText("");

		appendInput(txtAreaInput.getText());
		GenerateResponse generate = new GenerateResponse();
		generate.outputResponse(txtAreaInput.getText()); // Begins the generateResponse method
		inputCounter++;
	}

	public static void appendInput(String input) {
		String name;
		if (SetName.getUsername() != null)
			name = SetName.getUsername();
		else
			name = "User";
		txtAreaOutput.append(name + ": " + input + "\n");
	}

	public static void appendOutput(String output) {
		txtAreaOutput.append(output);
	}

	// Key Listener

	public void keyPressed(KeyEvent e) {

		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_ENTER) {
			submitInput();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_ENTER) {

			txtAreaInput.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
