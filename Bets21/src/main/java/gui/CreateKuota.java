package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;

public class CreateKuota extends JFrame {
	public JFrame nireFrame = this;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	JLabel Exceptions;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateKuota frame = new CreateKuota(null, new JFrame(), null, null);
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
	
	public CreateKuota(BLFacade BL, JFrame JF, Question q, Event e1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton kuotaBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote"));
		kuotaBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String desk= textField.getText();
				String kount= textField_1.getText();
				
				if(isNumeric(kount)) {
					double K = Double.parseDouble(textField_1.getText());
						if(Double.compare(K, 0.0) > 0) {
							System.out.println(e1.getEventNumber());
							System.out.println(q.getQuestionNumber());
							System.out.println(desk);
							System.out.println(K);
							BL.addKuota(e1, q, desk, K);
							nireFrame.setVisible(false);
							JF.setVisible(true);

				}else {
					Exceptions.setText("That is a negative number");
					Exceptions.setForeground(Color.RED);
				}}else {
					Exceptions.setText("That isn´t a valid number");
					Exceptions.setForeground(Color.RED);
				}
			}
		});
		kuotaBotoia.setBounds(153, 190, 119, 23);
		contentPane.add(kuotaBotoia);
		
		textField = new JTextField();
		textField.setBounds(191, 63, 195, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(191, 103, 43, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDeskripzioa = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Description"));
		lblDeskripzioa.setBounds(62, 66, 119, 14);
		contentPane.add(lblDeskripzioa);
		
		JLabel lblKuota = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quote"));
		lblKuota.setBounds(62, 106, 119, 14);
		contentPane.add(lblKuota);
		
		Exceptions = new JLabel("");
		Exceptions.setBounds(143, 165, 147, 14);
		contentPane.add(Exceptions);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nireFrame.setVisible(false);
			}
		});
		btnNewButton.setBounds(177, 224, 72, 42);
		contentPane.add(btnNewButton);
	}
}
