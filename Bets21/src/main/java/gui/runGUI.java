package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import businessLogic.BLFacade;
import domain.Erregistratua;

import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;


public class runGUI extends JFrame {
	JFrame nireF=this;
	private JPanel contentPane;
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	runGUI2 R = null;

	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	private int selectedHorse;
	private double money;
	private JLabel lblNewLabel_7;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					runGUI frame = new runGUI(null, null, null);
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
	
	public void setSelectedHorse(int s) {
		this.selectedHorse = s;
	}
	
	public int getSelectedHorse() {
		return this.selectedHorse;
	}
	/**
	 * Create the frame.
	 */
	public runGUI(MainGUILog a ,Erregistratua e, BLFacade B) {
		Erregistratua E = B.getUserFromName(e.getUsername());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnReturn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nireF.setVisible(false);
				a.setDirua(String.valueOf(B.getUserFromName(E.getUsername()).getDirua()));
				
			}			
		});
		btnReturn.setBounds(295, 382, 107, 56);
		contentPane.add(btnReturn);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Horse1"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(59, 31, 113, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Horse2"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(59, 77, 113, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Horse3"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(59, 123, 113, 35);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Horse4"));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(59, 169, 113, 35);
		contentPane.add(lblNewLabel_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(181, 41, 29, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(181, 87, 29, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(181, 133, 29, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("");
		buttonGroup.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(181, 179, 29, 23);
		contentPane.add(rdbtnNewRadioButton_3);
		
		JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quote1"));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(240, 37, 196, 23);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quote2"));
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_1.setBounds(240, 83, 196, 23);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quote3"));
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_2.setBounds(240, 129, 196, 23);
		contentPane.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quote4"));
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4_3.setBounds(240, 175, 196, 23);
		contentPane.add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AmountBet"));
		lblNewLabel_5.setBounds(461, 57, 116, 23);
		contentPane.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(587, 58, 46, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("\u20AC");
		lblNewLabel_6.setBounds(643, 61, 29, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("\u20AC");
		lblNewLabel_6_1.setBounds(643, 91, 29, 14);
		contentPane.add(lblNewLabel_6_1);
		
		JLabel lblNewLabel_5_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("YourMoney"));
		lblNewLabel_5_1.setBounds(461, 87, 97, 23);
		contentPane.add(lblNewLabel_5_1);
		
		lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(587, 91, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_7.setText(String.valueOf(E.getDirua()));
		
	
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ViewRace"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnNewRadioButton.isSelected()) {
					setSelectedHorse(0);
				}else if(rdbtnNewRadioButton_1.isSelected()) {
					setSelectedHorse(1);
				}else if(rdbtnNewRadioButton_2.isSelected()) {
					setSelectedHorse(2);
				}else if(rdbtnNewRadioButton_3.isSelected()) {
					setSelectedHorse(3);
				}else {
					setSelectedHorse(-1);
				}
				String s = textField.getText();
				
				if(isNumeric(s) && getSelectedHorse() > -1) {
					Double apostuKantitatea = Double.parseDouble(s);
					Double gureDirua = E.getDirua();
					B.createHorseApostu(apostuKantitatea, E, getSelectedHorse());
					B.updateVirtualMoney(E, gureDirua - apostuKantitatea);
								
					/**
					 * 
					 * 
					 * 
					 * 
					 * 
					 */
					runGUI2 r = new runGUI2(null,E,getSelectedHorse(),B);
				}
				
				
			}
		});
		btnNewButton.setBounds(181, 332, 323, 47);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("RefreshMoney"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setZureDirua(String.valueOf(B.getUserFromName(e.getUsername()).getDirua()));
			}
		});
		btnNewButton_1.setBounds(461, 133, 184, 23);
		contentPane.add(btnNewButton_1);
		
		
	}
	
	public void setZureDirua(String text) {
		lblNewLabel_7.setText(text);
	}
}
