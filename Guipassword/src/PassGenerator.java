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
		frmPasswordGenerator.setBounds(100, 100, 419, 229);
		frmPasswordGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPasswordGenerator.getContentPane().setLayout(null);
		
		final TextField textFieldGen = new TextField();
		final TextField textFieldPass = new TextField();
		
		textFieldPass.setBounds(74, 60, 149, 19);
		frmPasswordGenerator.getContentPane().add(textFieldPass);
				
		Label labelPass = new Label("Password Length:");
		labelPass.setBounds(74, 33, 177, 21);
		frmPasswordGenerator.getContentPane().add(labelPass);
		
		Button genButton = new Button("Generate");
		genButton.setBounds(262, 60, 86, 23);
		frmPasswordGenerator.getContentPane().add(genButton);
		// Add action listener to button
		genButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// Execute when button is pressed
			//System.out.println("You clicked the button");
				textFieldGen.setText("");
				String getTxt = textFieldPass.getText();
				boolean y = true;
				
				do{
					try{
						int c = Integer.parseInt(getTxt);
						Random r = new Random();
						String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","w","x","y","z"};
						int nc = 0-c;
						int c2 = c/2;
						int nc2 = 0-c2;
						int ncm = (nc+1)/2;
						
						if (c%2 == 0){
							for(int x = nc2; x<0;x++){
								int alphaNum = r.nextInt(26);
								//System.out.print(alphabet[alphaNum]);
								String alpha = alphabet[alphaNum];
								textFieldGen.setText (textFieldGen.getText()  + alpha.toString());
								int numNum = r.nextInt(10);
								//System.out.print(numNum);
								textFieldGen.setText (textFieldGen.getText()  + Integer.toString(numNum));
							}
						}
						else {
							for(int x = ncm; x<0;x++){
								int alphaNum = r.nextInt(26);
								//System.out.print(alphabet[alphaNum]);
								String alpha = alphabet[alphaNum];
								textFieldGen.setText (textFieldGen.getText ()  + alpha.toString());
								int numNum = r.nextInt(10);
								//System.out.print(numNum);
								textFieldGen.setText (textFieldGen.getText () + Integer.toString(numNum));
							}
							int numNum = r.nextInt(10);
							//System.out.print(numNum);
							textFieldGen.setText (textFieldGen.getText ()  + Integer.toString(numNum));
						}
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
		labelGen.setBounds(74, 109, 149, 21);
		frmPasswordGenerator.getContentPane().add(labelGen);
		
		
		textFieldGen.setBounds(74, 136, 149, 19);
		frmPasswordGenerator.getContentPane().add(textFieldGen);
		
		Button copyButton = new Button("Copy");
		copyButton.setBounds(262, 136, 86, 23);
		frmPasswordGenerator.getContentPane().add(copyButton);
		
		// copy the password to clipboard
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// Execute when button is pressed
			//System.out.println("You clicked the button")
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection selection = new StringSelection(textFieldGen.getText());
				clipboard.setContents(selection, null);	
				
			}
		});
		
		
	}
	
}


// helloworld