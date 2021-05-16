package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erregistratua;
import domain.Event;
import domain.Kuota;
import domain.Question;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class viewKuotakGUI extends JFrame {
	JFrame NireF = this;
	BLFacade BL;
	Erregistratua er;
	
	private DefaultComboBoxModel<Kuota> KutaKontainer = new DefaultComboBoxModel<Kuota>();
	//private DefaultListModel<Kuota> KuotaInfo = new DefaultListModel<Kuota>();

	private JPanel contentPane;
	private Collection<Kuota> KuotaCollection;
	
	private Kuota selectedConcretKuota;
	private JButton bET;

	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	
	JLabel Errorea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BetGUI frame = new BetGUI(null, null, null, null);
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
	public viewKuotakGUI(Question Q, BLFacade B, JFrame J, Event E, Erregistratua Er) {
		BL=B;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println(comboBox.getSelectedItem());
				
				selectedConcretKuota = (Kuota) comboBox.getSelectedItem();
				
				System.out.println(selectedConcretKuota);
				
				bET.setEnabled(true);
				
			}
		});
		
		comboBox.setModel(KutaKontainer);
		
		comboBox.setBounds(25, 11, 486, 20);
		contentPane.add(comboBox);
		
		bET = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SetResult"));
		bET.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame J = new setEmaitzaGUI(BL, selectedConcretKuota, NireF, E, Q, Er);
				
				J.setVisible(true);
				
			
			}
		});
		bET.setBounds(566, 10, 153, 23);
		contentPane.add(bET);
		bET.setEnabled(false);
		
		Errorea = new JLabel("");
		Errorea.setBounds(319, 223, 89, 14);
		contentPane.add(Errorea);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NireF.setVisible(false);
			}
		});
		btnNewButton.setBounds(298, 223, 153, 27);
		contentPane.add(btnNewButton);
		
	}
	
	public void setBox(Question Q) {
		
		KutaKontainer.removeAllElements();
		
		KuotaCollection = BL.getQ(Q.getQuestionNumber()).getKuotak();
		
		for(Kuota k: KuotaCollection) {
			System.out.println( k.toString());
			
			KutaKontainer.addElement(k);
		}
		if(KuotaCollection.isEmpty()) System.out.println("NO");
		else System.out.println("BET");;
		
		
	}
}
