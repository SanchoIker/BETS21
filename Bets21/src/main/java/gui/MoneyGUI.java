package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Erregistratua;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class MoneyGUI extends JFrame {
	public JFrame nireFrame =this;
	private JPanel contentPane;
	private JTextField textField;
	JTextArea exception;
	BLFacade BL;
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoneyGUI frame = new MoneyGUI(new BLFacadeImplementation(), new MainGUILog(), new Erregistratua("", "", "", "", "", "", "", 0.0), true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
	
	public MoneyGUI(BLFacade B, JFrame T, Erregistratua e, boolean b1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(200, 114, 151, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblBankukoDirua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BankMoney"));
		lblBankukoDirua.setBounds(75, 37, 125, 35);
		contentPane.add(lblBankukoDirua);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InsertMoney"));
		lblNewLabel.setBounds(31, 120, 169, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Confirm"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Double SartuBeharrekoa=0.0;
				
				if(isNumeric(textField.getText())) {
					SartuBeharrekoa = Double.parseDouble(textField.getText());
				
				
				Double Daukadana = e.getBank();
				
				if(Daukadana > SartuBeharrekoa) {
					double D = Daukadana - SartuBeharrekoa;
					BL= B;
					BL.updateMoney(e, D, SartuBeharrekoa);
					JFrame Confirmation = new ConfirmGUI(B, nireFrame, e, T, b1);
					Confirmation.setVisible(true);
					nireFrame.setVisible(false);
					
					
				}else {
					String errorea = "You don´t have enough money";
					exception.setText(errorea);
					exception.setForeground(Color.RED);
				}
				}else {
					String errorea = "That isn´t a valid number";
					exception.setText(errorea);
					exception.setForeground(Color.RED);
				}
			}
		});
		btnNewButton.setBounds(126, 166, 174, 51);
		contentPane.add(btnNewButton);
		
		exception = new JTextArea();
		exception.setBounds(126, 228, 174, 22);
		contentPane.add(exception);
		
		JLabel lblNewLabel_1 = new JLabel("\u20AC");
		lblNewLabel_1.setBounds(361, 47, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u20AC");
		lblNewLabel_1_1.setBounds(361, 120, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		String text =  String.valueOf(e.getBank());
		
		JLabel Dirua = new JLabel("");
		Dirua.setText(text);
		
		Dirua.setBounds(200, 47, 151, 14);
		contentPane.add(Dirua);
		
		
	}
}
