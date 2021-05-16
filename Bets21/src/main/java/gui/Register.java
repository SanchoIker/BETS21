package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Erregistratua;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;

public class Register extends JFrame {
	
	private JFrame thisFrame = this;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private BLFacade facade;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel A2;
	private JLabel A1;
	private JLabel A3;
	private String testua = "No problems for now";
	private String testua1 = "No problems for now";
	private String testua2 = "No problems for now";
	private String testua3 = "No problems for now";
	private JPasswordField textField_1;
	private JLabel A4;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register(new BLFacadeImplementation(), new JFrame());
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
	public Register(BLFacade facade, JFrame nireFrame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(150, 40, 130, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(150, 102, 130, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(150, 133, 130, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(150, 165, 130, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		/////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////
		JButton btnConfirm = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Confirm"));
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean b1 =true;
				boolean b2 =true;
				boolean b3 =true;
				boolean b4 =true;
				boolean b5 =true;
				testua="";
				testua1="";
				testua2="";
				testua3="";
				
				String DNI="";
				String DNI_ = textField_6.getText();
				String DNI_Lettre = textField_8.getText();
				if(!validateDNI(DNI_, DNI_Lettre)) {
					b1=false;
					System.out.println("The DNI must have 8 numbers and one lettre");
					testua = "The DNI must have 8 numbers and one lettre";
					A1.setForeground(Color.red);
					A1.setText(testua);
					
				}else {
					DNI = createDNI(DNI_, DNI_Lettre);
					A1.setText("");
				}
				
				String Email= textField_5.getText();
				if(!validateEmail(Email)) {
					b4=false;
					System.out.println("wrong email address");
					testua3 = "wrong email address";
					A4.setForeground(Color.red);
					A4.setText("wrong email address");
					
				}else {
					A4.setText("");
				}
				String User= textField.getText();
				
				String Password=textField_1.getText();
				if(!isSecure(Password)) {
					b2=false;
					System.out.println("Password must have at least one uppercase, lowercase and number");
					testua1 = "Password must have at least one uppercase, lowercase and number";
					A2.setForeground(Color.red);
					A2.setText(testua1);
				}else {
					A2.setText("");
				}
				
				String Izena=textField_2.getText();
				String Abizena=textField_3.getText();
				String Bankua= textField_4.getText();
				String Adina=textField_7.getText();
				if(!isOlderThan18(Adina)) {
					b3=false;
					System.out.println("you must be older than 18 to entry this site");
					testua2 = "you must be older than 18 to entry this site";
					A3.setForeground(Color.red);
					A3.setText(testua2);
				}
				else {
					A3.setText("");
				}
				if(validateBank(Bankua)) {
				}else {
					b5 = false;
				}
				if(b1&b2&b3&b4&b5) {
					Erregistratua A= new Erregistratua(User, Password, Izena, Abizena, DNI, Email, Adina,Double.parseDouble(Bankua));
					System.out.println("");
					System.out.println(A.getIzena());
					facade.createUser(User, Password, Izena, Abizena, DNI, Email, Adina,Double.parseDouble(Bankua));
					thisFrame.setVisible(false);
					nireFrame.setVisible(true);
					
				}else {
					System.out.println("error");
				}
				
				
			}
		});
		/////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////
		btnConfirm.setBounds(258, 246, 470, 66);
		contentPane.add(btnConfirm);
		
		JLabel lblUser = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User"));
		lblUser.setBounds(55, 43, 85, 14);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPassword.setBounds(55, 74, 85, 14);
		contentPane.add(lblPassword);
		
		JLabel lblIzena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name"));
		lblIzena.setBounds(55, 105, 85, 14);
		contentPane.add(lblIzena);
		
		JLabel lblAbizena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Surname"));
		lblAbizena.setBounds(55, 136, 85, 14);
		contentPane.add(lblAbizena);
		
		JLabel lblBankuKontua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BankMoney"));
		lblBankuKontua.setBounds(55, 168, 85, 14);
		contentPane.add(lblBankuKontua);
		
		textField_5 = new JTextField();
		textField_5.setBounds(150, 7, 130, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblEmail = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("E-mail"));
		lblEmail.setBounds(55, 10, 85, 14);
		contentPane.add(lblEmail);
		
		textField_6 = new JTextField();
		textField_6.setToolTipText("");
		textField_6.setBounds(150, 196, 98, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel DNI = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ID"));
		DNI.setBounds(55, 199, 46, 14);
		contentPane.add(DNI);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Age"));
		lblNewLabel.setBounds(55, 278, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField_7 = new JTextField();
		textField_7.setBounds(111, 266, 112, 39);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(258, 196, 22, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Numbers"));
		lblNewLabel_1.setBounds(150, 220, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Letter"));
		lblNewLabel_2.setBounds(258, 220, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		A2 = new JLabel(testua1);
		A2.setBounds(317, 56, 437, 51);
		contentPane.add(A2);
		
		A1 = new JLabel(testua);
		A1.setBounds(317, 102, 437, 50);
		contentPane.add(A1);
		
		A3 = new JLabel(testua2);
		A3.setBounds(317, 151, 437, 49);
		contentPane.add(A3);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(150, 71, 130, 20);
		contentPane.add(textField_1);
		
		A4 = new JLabel(testua3);
		A4.setBounds(317, 11, 437, 49);
		contentPane.add(A4);
	}
	
	public boolean isSecure(String psswrd) {
	
		return psswrd.length()>7;
	}
	
	public boolean validateEmail(String email) {
		Pattern EmailExp=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m1 = EmailExp.matcher(email);
		return m1.find();
	}
	
	public boolean isOlderThan18(String age) {
		boolean emaitza = false;
		try{
		int a = Integer.parseInt(age);
			if(a>17) {
				emaitza=true;
			}
		}catch (Exception e) {
			//System.out.println("The DNI must have 8 numbers and one lettre");
			testua = "The DNI must have 8 numbers and one lettre";
			A1.setText(testua);
		}
		
		
		return emaitza;
	}
	
	public boolean validateDNI(String DNI, String DNI_L) {
		Boolean b =false;
		Boolean b2= false;
		if(DNI.length()==8) {
			b=true;
		}
		if(DNI_L.matches("[A-Z]")) {
			b2=true;
		}
		return b & b2;
	}
	public boolean validateBank(String bank) {
		try {
			Double.parseDouble(bank);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public String createDNI(String DNI, String DNI_L) {
		return DNI+DNI_L;
	}
}
