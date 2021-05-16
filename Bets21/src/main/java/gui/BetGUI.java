package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Erregistratua;
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

public class BetGUI extends JFrame {
	JFrame NireF = this;
	BLFacade BL;
	Erregistratua er;
	
	private DefaultComboBoxModel<Kuota> KutaKontainer = new DefaultComboBoxModel<Kuota>();
	//private DefaultListModel<Kuota> KuotaInfo = new DefaultListModel<Kuota>();

	private JPanel contentPane;
	private Collection<Kuota> KuotaCollection;
	
	private Kuota selectedConcretKuota;
	private JButton bET;
	private JTextField ApostuaBal;

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
	public BetGUI(Question Q, BLFacade B, domain.Erregistratua E, JFrame J) {
		BL=B;
		er = E;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 321);
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
		
		comboBox.setBounds(25, 11, 275, 20);
		contentPane.add(comboBox);
		
		bET = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MakeBet"));
		bET.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				double Balorea = selectedConcretKuota.getBalorea();
				
				double Minimoa = Q.getBetMinimum();
				
				double MomentukoDirua = er.getDirua();
				
				if(isNumeric(ApostuaBal.getText())) {
				
					double Apostua = Double.parseDouble(ApostuaBal.getText());
					
					if(Apostua >= Minimoa) {
						
						System.out.println(er.getDirua());
						
						BL.updateVirtualMoney(er, MomentukoDirua-Apostua); //Update E-s money
						er= BL.getUserIsLogin(er.getUsername(), er.getPassword()); //get That new E from database						
						BL.createApostu(Apostua, selectedConcretKuota, er); //create a bet for E
						er= BL.getUserIsLogin(er.getUsername(), er.getPassword()); //get That new E from database		
						
						System.out.println(er.getDirua() + " now.");
						
						((FindQuestionRegisterGUI) J).setE(er);
						
						((FindQuestionRegisterGUI) J).setDirua(B, er);
						
						NireF.setVisible(false);
						
					}else {
						
						Errorea.setForeground(Color.RED);
						
						Errorea.setText("Less than minimum");
					}
				}else {
					
					Errorea.setForeground(Color.RED);
					
					Errorea.setText("Non Valid");
					
					
				}
			
			}
		});
		bET.setBounds(319, 10, 105, 23);
		contentPane.add(bET);
		bET.setEnabled(false);
		
		JLabel Lab1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("YourMoney"));
		Lab1.setBounds(319, 58, 105, 14);
		contentPane.add(Lab1);
		
		JLabel Lab1_1 = new JLabel("New label");
		Lab1_1.setBounds(319, 83, 89, 14);
		contentPane.add(Lab1_1);
		Lab1_1.setText(er.getDirua() + " €");
		
		JLabel Lab2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinBet"));
		Lab2.setBounds(319, 108, 105, 14);
		contentPane.add(Lab2);
		
		JLabel Lab2_2 = new JLabel("New label");
		Lab2_2.setBounds(319, 133, 89, 14);
		contentPane.add(Lab2_2);
		Lab2_2.setText(Q.getBetMinimum() + " €");
		
		ApostuaBal = new JTextField();
		ApostuaBal.setBounds(319, 192, 86, 20);
		contentPane.add(ApostuaBal);
		ApostuaBal.setColumns(10);
		
		JLabel lab3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TheBet"));
		lab3.setBounds(319, 169, 105, 14);
		contentPane.add(lab3);
		
		Errorea = new JLabel("");
		Errorea.setBounds(319, 223, 89, 14);
		contentPane.add(Errorea);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NireF.setVisible(false);
			}
		});
		btnNewButton.setBounds(136, 248, 169, 23);
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
