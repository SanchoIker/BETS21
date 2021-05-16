package gui;

import java.awt.EventQueue;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Erregistratua;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;

public class ConfirmGUI extends JFrame {

	private ConfirmGUI thisFrame = this;

	private JPasswordField passwordField;
	private JTextField txtUser;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private BLFacade facade;
	private String testua="";
	private JLabel lblNewLabel_2;
	private JLabel etiketa;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();// new BLFacadeImplementation());
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	/**
	 * Initialize the contents of the frame.
	 */
	public ConfirmGUI(BLFacade facade, JFrame T, Erregistratua E, JFrame A, boolean b1) {

		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(187, 140, 86, 20);
		getContentPane().add(passwordField);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel.setBounds(77, 143, 100, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblNewLabel_1.setBounds(77, 97, 100, 14);
		getContentPane().add(lblNewLabel_1);

		txtUser = new JTextField();
		txtUser.setText("");
		txtUser.setBounds(187, 94, 86, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);

		JButton btnConfirm = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Confirm"));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean LangileEdoAdminDa = false;
				String user = txtUser.getText();
				@SuppressWarnings("deprecation")
				String password = passwordField.getText();
				
				if(user.equals(E.getUsername()) && password.equals(E.getPassword())) {
					thisFrame.setVisible(false);
					
					if(b1) {
						((CombinatedGUI) A).setDirua(facade, E);
					}else {
						((MainGUILog) A).setDirua();
					}
					//T.setVisible(true);
				}else {
					lblNewLabel_2.setText("Wrong account");
					lblNewLabel_2.setForeground(Color.red);
				}
			}
		});
		
		btnConfirm.setBounds(184, 187, 100, 23);
		getContentPane().add(btnConfirm);

		JLabel lblLogin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ConfirmSesion"));
		lblLogin.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 28));
		lblLogin.setBounds(77, 11, 347, 73);
		getContentPane().add(lblLogin);
		
		etiketa = new JLabel(testua);
		etiketa.setForeground(Color.red);
		etiketa.setBounds(109, 182, 219, 14);
		getContentPane().add(etiketa);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(131, 225, 177, 25);
		getContentPane().add(lblNewLabel_2);
	

	}
}
