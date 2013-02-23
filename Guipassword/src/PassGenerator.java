/**
 * Copyright (c) 2013 Zetta Tips
 *
 * See the file "LICENSE" for the full license governing this code.
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Button;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JCheckBox;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PassGenerator {
	private JFrame frmPasswordGenerator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PassGenerator window = new PassGenerator();
					window.frmPasswordGenerator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PassGenerator() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswordGenerator = new JFrame();
		frmPasswordGenerator.setTitle("Password Generator");
		frmPasswordGenerator.setBounds(100, 100, 422, 317);
		frmPasswordGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPasswordGenerator.getContentPane().setLayout(null);
		
		// Object are final in order to use in the do loop 
		final TextField textFieldGen = new TextField();
		final TextField textFieldPass = new TextField();
		final JCheckBox chckbxDigit = new JCheckBox("Numeric digits (0-9)");
		final JCheckBox chckbxUppercase = new JCheckBox("Uppercase letters (A-Z)");
		final JCheckBox chckbxLowercase = new JCheckBox("Lowercase letters (a-z)");
		final JCheckBox chckbxSpecialCharacters = new JCheckBox("Special characters ");
		
		textFieldPass.setBounds(74, 64, 149, 19);
		frmPasswordGenerator.getContentPane().add(textFieldPass);
				
		Label labelPass = new Label("Password Length:");
		labelPass.setBounds(74, 37, 177, 21);
		frmPasswordGenerator.getContentPane().add(labelPass);
		
		Button genButton = new Button("Generate");
		genButton.setBounds(262, 60, 86, 23);
		frmPasswordGenerator.getContentPane().add(genButton);
		
		// Add action listener to button
		genButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// Execute when button is pressed
				textFieldGen.setText(""); // reset the value of textFieldGen
				String getTxt = textFieldPass.getText();
				// do loop for exception handling 
				boolean y = true;
				do{
					try{
						int c = Integer.parseInt(getTxt);
						Random rd = new Random();
						char lowerChars[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();
						char upperChars[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
						char numbers[] = "0123456789".toCharArray();
						char specialChars[] = "~!@#$%^&*()-_=+[{]}|;:<>/?".toCharArray();
					
						 List<Character> pwdLst = new ArrayList<Character>();
						 // check, at least one checkbox must be selected by user.
						 if ( chckbxDigit.isSelected() == false && chckbxLowercase.isSelected() == false &&
							  chckbxUppercase.isSelected() == false &&  chckbxSpecialCharacters.isSelected() == false)
						 {
							 int messageType = JOptionPane.PLAIN_MESSAGE;
							 JOptionPane.showMessageDialog(null, "Please tick at least one checkbox", "Error!!", messageType);
							 break;
						 }
						 // for loop to generate the password 
						 for (int g = 1; g <= 4 ; g++)
						 {
							 for (int z = 0; z < 1; z++)
							 {
								 if (g == 1)
								 {
									boolean selectedDigit = chckbxDigit.isSelected();
									if (selectedDigit){
										pwdLst.add(numbers[rd.nextInt(10)]);
									}
								 }
								 else if (g == 2)
								 {
									boolean selectedLower = chckbxLowercase.isSelected();
									if (selectedLower){
										pwdLst.add(lowerChars[rd.nextInt(26)]);
									} 
								 }
								 else if (g == 3)
								 {
									boolean selectedUpper = chckbxUppercase.isSelected();
									if (selectedUpper){
										pwdLst.add(upperChars[rd.nextInt(26)]);
									}	 
								 }
								 else if (g == 4)
								 {
									boolean selectedSpecial = chckbxSpecialCharacters.isSelected();
									if (selectedSpecial){
										pwdLst.add(specialChars[rd.nextInt(26)]);
									} 
								 }		 
							 }
							 if (pwdLst.size() == c)
							 {
								 break;
							 }
							 if (g + 1 == 5)
							 {
								 g = (int) Math.random() * 5;
							 } 
						 }
						 
						 StringBuilder password = new StringBuilder();
						 Collections.shuffle(pwdLst);
						 for (int cd= 0; cd < pwdLst.size(); cd++)
						 {
							 password.append(pwdLst.get(cd));
						 }
						 	
						 System.out.println(password);
						 textFieldGen.setText(password.toString());
						 
 						y = false;
					}
					catch (NumberFormatException e1 ){
						int messageType = JOptionPane.PLAIN_MESSAGE;
						JOptionPane.showMessageDialog(null, "Please Enter an Integer only", "Error!!", messageType);
						y = false;
					}
				}while (y);
			}
		});			 
														
		Label labelGen = new Label("Generated Password:");
		labelGen.setBounds(74, 212, 149, 21);
		frmPasswordGenerator.getContentPane().add(labelGen);
		
		textFieldGen.setBounds(74, 239, 149, 19);
		frmPasswordGenerator.getContentPane().add(textFieldGen);
		
		Button copyButton = new Button("Copy");
		copyButton.setBounds(262, 235, 86, 23);
		frmPasswordGenerator.getContentPane().add(copyButton);
		
		chckbxDigit.setBounds(74, 96, 177, 23);
		chckbxDigit.setSelected(true);
		frmPasswordGenerator.getContentPane().add(chckbxDigit);
		
		chckbxUppercase.setBounds(74, 123, 205, 23);
		chckbxUppercase.setSelected(true);
		frmPasswordGenerator.getContentPane().add(chckbxUppercase);
		
		chckbxLowercase.setBounds(74, 150, 205, 23);
		chckbxLowercase.setSelected(true);
		frmPasswordGenerator.getContentPane().add(chckbxLowercase);
		
		chckbxSpecialCharacters.setBounds(74, 176, 188, 23);
		chckbxSpecialCharacters.setSelected(true);
		frmPasswordGenerator.getContentPane().add(chckbxSpecialCharacters);
		
		// copy the password to clip board
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// Execute when button is pressed
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection selection = new StringSelection(textFieldGen.getText());
				clipboard.setContents(selection, null);		
			}
		});	
	}
}

