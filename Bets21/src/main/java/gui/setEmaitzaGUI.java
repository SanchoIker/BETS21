package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Apostu;
import domain.Emaitza;
import domain.Erregistratua;
import domain.Event;
import domain.Kuota;
import domain.Question;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class setEmaitzaGUI extends JFrame {

	JFrame niref = this;

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton buton1;
	private JRadioButton buton2;

	boolean B;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setEmaitzaGUI frame = new setEmaitzaGUI(null, null, null, null, null, null);
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
	public setEmaitzaGUI(BLFacade bl, Kuota k, JFrame j, Event E, Question Q, Erregistratua E1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 179);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton boton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateResult"));
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean b=false;
				if(buton1.isSelected()) {
					b=true;
				}else {
					b=false;
				}
				
				Kuota K =  bl.getKuotaZehatza(k);
				
				if(K.getE()==null) {
					
					Emaitza E = bl.createEmaitza(K, b);
					Vector<Apostu> ep= bl.getApostuK(K);
					
					for (Apostu a : ep) {
							
							System.out.println(" ");
							System.out.println("!!!!11");
							String n = bl.getUserFromApostu(a).getUsername();
							System.out.println(" ");
							System.out.println("!!!!22");
							Erregistratua Er = bl.getUserFromName(n);
							System.out.println(" ");
							System.out.println("!!!!33");
							
							
							
							bl.setBofApostu(a, b);
							
							Vector<Apostu> VV= bl.isCombinated(a, Er);
							
							if(VV!=null) {
								double Kuota=1.0;
								
							
								print(VV);
								
								if(bl.guztiakDiraTrue(VV)) {
									System.out.println("HELLO");
									for(Apostu a1 : VV) {	
										/**
										 * sortu metodo bat kuota lortzeko apostutik BL-ra deituz
										 */
										double balorea = bl.getKuotaApostu(a1).getBalorea();
										Kuota *= balorea;	
									
									}
									
									/**
									 * Dar la pasta
									 */
									
									double d= Er.getKonbinatuenPrezioa().get(Er.whereIsIt(VV));
									
									Erregistratua eR =  bl.getUserFromApostu(a);
									
									bl.updateVirtualMoney(eR, eR.getDirua() + d*Kuota);
								}
								
							}else {
				
							Apostu A = bl.getApostuZ(a);
						
							Erregistratua eR =  A.getUser();
						
							bl.updateVirtualMoney(eR, eR.getDirua() + A.getIrabazitakoDirua());
					
							}
							
							
							
						
					}
					
					
				}else {
					System.out.println("Emaitza bat already exists");
				}
				
				niref.setVisible(false);
				
			}
		});
		boton.setBounds(37, 74, 300, 45);
		contentPane.add(boton);
		
		JLabel label1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuestionScenario"));
		label1.setBounds(37, 40, 192, 14);
		contentPane.add(label1);
		
		buton1 = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Yes"));
		buttonGroup.add(buton1);
		buton1.setBounds(235, 36, 66, 23);
		contentPane.add(buton1);
		
		buton2 = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("No"));
		buttonGroup.add(buton2);
		buton2.setBounds(303, 36, 83, 23);
		contentPane.add(buton2);
	}
	
	public void print(Vector<Apostu> VV) {
		System.out.println("HUTSIK NAGO");
		if(VV.isEmpty()) {
			System.out.println("Benetan");
		}
		for(Apostu a1 : VV) {
			System.out.println(a1);
			
			if(a1.getIsIrabazita()) {
				System.out.println("HEY");
			}else {
				System.out.println("HEYn't");
			}
		}
	}
	
	/**
	 * 
	 * @param bl
	 * @param k
	 * @param j
	 * @param E
	 * @param Q
	 * @param Er
	 *
	public setEmaitzaGUI(BLFacade bl, Kuota k, JFrame j, Event E, Question Q, Erregistratua Er) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 179);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton boton = new JButton("Create Emaitza");
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean b = false;
				if (buton1.isSelected()) {
					b = true;
				} else {
					b = false;
				}

				Kuota K = bl.getKuotaZehatza(k);

				if (K.getE() == null) {

					Emaitza E = bl.createEmaitza(K, b);

					for (Apostu a : E.getApostuak()) {
						
						*
						* Frogatu apostuz apostu ea konbinatua den edo ez
						*
						boolean isCombinated = false;
						Vector<Apostu> v = new Vector<Apostu>();
						
						
						
						Erregistratua EE = bl.getUserFromApostu(a);
					
						for (Vector<Apostu> A2 : EE.getKonbinatuak()) {
							if (A2.contains(a)) {
								v=A2;
								isCombinated=true;
							}
						}
						
						bl.setBofApostu(a, b);
						
						Apostu A = bl.getApostuZ(a);

						Erregistratua eR = A.getUser();
						
						if(!isCombinated) {
						
							bl.updateVirtualMoney(eR, eR.getDirua() + A.getIrabazitakoDirua());
							 *
							 * Konbinatua bada se lia la de dios
							 *
						}else {
							boolean denakTrue = true;
							double kuota=0;
							 *
							 * beitu ea denak true dian, ta ya de paso kuota kalkulau
							 *
							for(Apostu a1: v) {
								kuota*=a1.getKuota().getBalorea();
								if(!a1.getIsIrabazita()){
									denakTrue=false;
								}
							}
							 *
							 * Denak true badia, DAME LA PASTA PERRO
							 *
							if(denakTrue) {
								Double LaPastaQueTeHasDejauEnLaCombinada = EE.getKonbinatuenPrezioa().get(eR.getKonbinatuak().indexOf(v));
								Double Irabazitakoa = LaPastaQueTeHasDejauEnLaCombinada*kuota;
								bl.updateVirtualMoney(eR, eR.getDirua() + Irabazitakoa);
							}
						}
					}
				} else {
					System.out.println("Emaitza bat already exists");
				}

				niref.setVisible(false);

			}
		});
		boton.setBounds(37, 74, 300, 45);
		contentPane.add(boton);

		JLabel label1 = new JLabel("Galderako eszenarioa gertatu da?");
		label1.setBounds(37, 40, 192, 14);
		contentPane.add(label1);

		buton1 = new JRadioButton("Bai");
		buttonGroup.add(buton1);
		buton1.setBounds(247, 36, 54, 23);
		contentPane.add(buton1);

		buton2 = new JRadioButton("Ez");
		buttonGroup.add(buton2);
		buton2.setBounds(303, 36, 83, 23);
		contentPane.add(buton2);
	}*/
}
