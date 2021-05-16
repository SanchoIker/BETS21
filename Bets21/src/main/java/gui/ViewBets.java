package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Apostu;
import domain.Erregistratua;
import domain.Kuota;
import domain.Question;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ViewBets extends JFrame {

	JFrame nF = this;

	private JPanel contentPane;

	BLFacade bF;

	private DefaultComboBoxModel<Double> MugimenduKontainer = new DefaultComboBoxModel<Double>();
	private Collection<Double> MugimenduColection;
	private Double selectedConcreteMugimendu;

	private DefaultComboBoxModel<Apostu> ApostuKontainer = new DefaultComboBoxModel<Apostu>();
	private Apostu selectedConcreteAp;

	private Collection<Apostu> ApostuCollection;
	private Collection<Apostu> ApostuCollection2;

	private DefaultComboBoxModel<Apostu> ApostuKontainer2 = new DefaultComboBoxModel<Apostu>();
	private Apostu selectedConcreteAp2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewBets frame = new ViewBets(null, null);
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
	public ViewBets(BLFacade BLf, Erregistratua E1) {

		bF = BLf;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 811, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.println(comboBox.getSelectedItem());

				selectedConcreteMugimendu = (Double) comboBox.getSelectedItem();

				System.out.println(selectedConcreteMugimendu);

				// bET.setEnabled(true);

			}
		});
		comboBox.setModel(MugimenduKontainer);
		comboBox.setBounds(25, 86, 219, 20);
		contentPane.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.println(comboBox_1.getSelectedItem());

				selectedConcreteAp = (Apostu) comboBox_1.getSelectedItem();

				System.out.println(selectedConcreteAp);

				// bET.setEnabled(true);

			}
		});
		comboBox_1.setModel(ApostuKontainer);
		comboBox_1.setBounds(286, 86, 219, 20);
		contentPane.add(comboBox_1);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BankMoneyMovements"));
		lblNewLabel.setBounds(25, 61, 171, 20);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MadeBets"));
		lblNewLabel_1.setBounds(286, 61, 180, 20);
		contentPane.add(lblNewLabel_1);

		JButton return1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Return"));
		return1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nF.setVisible(false);
			}
		});
		return1.setBounds(458, 342, 327, 28);
		contentPane.add(return1);

		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WonBets"));
		lblNewLabel_2.setBounds(547, 61, 163, 20);
		contentPane.add(lblNewLabel_2);

		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.out.println(comboBox_1_1.getSelectedItem());

				selectedConcreteAp2 = (Apostu) comboBox_1_1.getSelectedItem();

				System.out.println(selectedConcreteAp2);

				// bET.setEnabled(true);

			}
		});
		comboBox_1_1.setModel(ApostuKontainer2);
		comboBox_1_1.setBounds(547, 86, 219, 20);
		contentPane.add(comboBox_1_1);

		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("NullValue"));
		lblNewLabel_3.setBounds(25, 342, 760, 28);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Refresh"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBox1(E1);
				setBox2(E1);
			}
		});
		btnNewButton.setBounds(595, 11, 171, 23);
		contentPane.add(btnNewButton);
	}

	public void setBox1(Erregistratua E1) {

		Erregistratua E = bF.getUserFromName(E1.getUsername());
		MugimenduKontainer.removeAllElements();

		if (E.getDiruMug() != null) {
			MugimenduColection = E.getDiruMug();

			// if(MugimenduColection!=null) {
			for (Double d : MugimenduColection) {
				System.out.println("EO");
				System.out.println(d);

				MugimenduKontainer.addElement(d);
			}
			if (MugimenduColection.isEmpty())
				System.out.println("NO");
			else
				System.out.println("BET");
			// }
			// else System.out.println("NOPE\n");
		} else {

		}
	}

	public void setBox2(Erregistratua E1) {
		Erregistratua E = bF.getUserFromName(E1.getUsername());

		ApostuKontainer.removeAllElements();

		ApostuCollection = E.getApostuk();
		ApostuCollection2 = bF.getApostuKonbinatuak(E);
		

		ApostuKontainer2.removeAllElements();

		if (ApostuCollection != null | ApostuCollection2 != null) {
			if (ApostuCollection != null) {
				for (Apostu p : ApostuCollection) {

					if (!p.getIsIrabazita()) {
						// System.out.println( k.toString());
						System.out.println("NO SE Hiso");
						ApostuKontainer.addElement(p);

					} else {

						System.out.println("SE Hiso");
						ApostuKontainer2.addElement(p);

					}
				}
			}
			if (ApostuCollection2 != null) {
				for (Apostu p : ApostuCollection2) {

					if (!p.getIsIrabazita()) {
						// System.out.println( k.toString());
						System.out.println("NO SE Hiso");
						ApostuKontainer.addElement(p);

					} else {

						System.out.println("SE Hiso");
						ApostuKontainer2.addElement(p);

					}
				}
			}


		} else
			System.out.println("NOPE_2");

	}
}
