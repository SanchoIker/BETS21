package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.objectdb.o.BFR;

import businessLogic.BLFacade;
import domain.Erregistratua;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class CopyBetsGUI extends JFrame {
	private JFrame nireF= this;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CopyBetsGUI frame = new CopyBetsGUI(null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}
	/**
	 * Create the frame.
	 */
	public CopyBetsGUI(BLFacade B, JFrame jF, Erregistratua user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EnterNameCopy"));
		lblNewLabel.setBounds(10, 11, 282, 23);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(342, 12, 126, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(342, 53, 91, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PercentageOfBet"));
		lblNewLabel_1.setBounds(10, 52, 356, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("%");
		lblNewLabel_2.setBounds(443, 56, 25, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel errorMsg = new JLabel("");
		errorMsg.setBounds(57, 86, 185, 18);
		contentPane.add(errorMsg);
		errorMsg.setForeground(Color.RED);
		
		JLabel errorMsg_1 = new JLabel("");
		errorMsg_1.setForeground(Color.RED);
		errorMsg_1.setBounds(248, 86, 185, 18);
		contentPane.add(errorMsg_1);
		
		JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Disclamer"));
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 159, 200, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NotEnoughtMoney"));
		lblNewLabel_5.setForeground(new Color(139, 0, 0));
		lblNewLabel_5.setBounds(10, 193, 493, 23);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TheOportunity"));
		lblNewLabel_6.setForeground(new Color(139, 0, 0));
		lblNewLabel_6.setBounds(10, 216, 414, 14);
		contentPane.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Confirm"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				errorMsg.setText("");
				errorMsg_1.setText("");
				
				String UserName  = textField.getText();
				String percent = textField_1.getText();
				
				if(B.getUserFromName(UserName)!=null){
					if(isNumeric(percent)) {
						int per = Integer.parseInt(percent);
						System.out.println("Hau da porcentajea: " + per);
							B.copyBets(user,UserName,per);
						nireF.setVisible(false);
						jF.setVisible(true);
						
					}else {
						errorMsg.setText("Not numeric value");
					}
				}else {
					errorMsg_1.setText("The user does not exists");
				}
			}
		});
		btnNewButton.setBounds(10, 115, 486, 23);
		contentPane.add(btnNewButton);
		
	
	}
}
