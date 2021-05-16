package gui;

import java.awt.EventQueue;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

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

public class Login extends JFrame {

	private Login thisFrame = this;

	private JPasswordField passwordField;
	private JTextField txtUser;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static BLFacade facade;
	private String testua="";

	private JLabel etiketa;
	/**
	 * Launch the application.
	 */
	
	public static void setBussinessLogic (BLFacade afi){
		facade = afi;
	} 

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
	public Login() {
		System.out.println(facade + " Login barrun");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setVisible(true);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(187, 140, 86, 20);
		getContentPane().add(passwordField);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel.setBounds(97, 143, 80, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblNewLabel_1.setBounds(97, 97, 80, 14);
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
				int f = facade.userIsLogin(user, password);
				// BLFacadeImplementation facade = new BLFacadeImplementation();
				if (f == 0) {
					JLabel label = new JLabel("Erregistratua");
					label.setBounds(348, 114, 46, 14);
					getContentPane().add(label);
					System.out.println("Erregistratua");

				} else if (f == 1) {
					JLabel label = new JLabel("Langilea");
					label.setBounds(348, 114, 46, 14);
					getContentPane().add(label);
					System.out.println("Langilea");

					LangileEdoAdminDa = true;

				} else if (f == 2) {
					JLabel label = new JLabel("Admin");
					label.setBounds(348, 114, 46, 14);
					getContentPane().add(label);
					System.out.println("Admin");

					LangileEdoAdminDa = true;

				} else if (f == -1) {
					
					testua = "Wrong user or password";
					etiketa.setText(testua);
					JLabel label = new JLabel("Errorea");
					label.setBounds(348, 114, 46, 14);
					getContentPane().add(label);
					System.out.println("Errorea");
				}

				if (f == 1 || f == 2) {
					MainGUILangile.setBussinessLogic(facade);
					MainGUILangile M1 = new MainGUILangile();
					thisFrame.setVisible(false);
					M1.setVisible(true);
					
				} else if (f == 0) {
					MainGUILog.setBussinessLogic(facade);
					MainGUILog L1 = new MainGUILog();
					
					L1.setAccount(facade.getUserIsLogin(user, password));
					L1.setDirua();
					L1.setVisible(true);

					thisFrame.setVisible(false);
				}
			}
		});
		btnConfirm.setBounds(187, 207, 86, 23);
		getContentPane().add(btnConfirm);

		JLabel lblLogin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		lblLogin.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 28));
		lblLogin.setBounds(184, 10, 183, 73);
		getContentPane().add(lblLogin);
		
		etiketa = new JLabel(testua);
		etiketa.setForeground(Color.red);
		etiketa.setBounds(109, 182, 219, 14);
		getContentPane().add(etiketa);

	}
}
