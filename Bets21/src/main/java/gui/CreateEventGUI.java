package gui;

import java.awt.BorderLayout;
import domain.Event;
import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import configuration.UtilDate;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class CreateEventGUI extends JFrame {
	private JFrame J1= this;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private BLFacade Facade = MainGUI.getBusinessLogic();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventGUI frame = new CreateEventGUI(new JFrame());
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
	public CreateEventGUI(JFrame thisFrame) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(228, 35, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Date"));
		lblNewLabel.setBounds(95, 38, 123, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Description"));
		lblNewLabel_1.setBounds(95, 81, 123, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(228, 78, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Create"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean b1=false;
				
				String s1 = textField.getText();
				String s2 = textField_1.getText();
				
				String[] sP = s1.split("-");
				
				if(verifyDate(s1)) {
					Event E1 = new Event(s2, UtilDate.newDate(Integer.parseInt(sP[0]), Integer.parseInt(sP[1]), Integer.parseInt(sP[2])));
					Facade.createEvent(E1);
				}else {
					System.out.println("ERROR");
				}
			}
		});
		btnNewButton.setBounds(166, 150, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				J1.setVisible(false);
				thisFrame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(166, 208, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	
	public boolean verifyDate(String str) {
		boolean b1=false;
		
		if (str.matches("\\d{4}-\\d{2}-\\d{2}")) {
		    b1=true;
		}
		
		return b1;
	}
}
