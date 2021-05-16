package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Erregistratua;
import domain.Event;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUILog extends JFrame {
	
	public String text;
	
	private static final long serialVersionUID = 1L;
	
	private MainGUILog nireFrame = this;
	
	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;
	private JButton btnNewButton_2 = null;
	private JButton btnNewButton_1 = null;
	private JLabel Testua1= null;
	
	private Erregistratua E1 =null;
	
    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	public void setAccount (Erregistratua E) {
		this.E1=E;
	}
	
	public void setText(String TextA) {
		text=TextA;
	}
	
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton;
	public JLabel Dirua;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	
	/**
	 * This is the default constructor
	 */
	public MainGUILog() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 474);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			//jContentPane.add(getBoton2());
			jContentPane.add(getPanel());
			jContentPane.add(getBtnNewButton());
			
			Dirua = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
			Dirua.setBounds(390, 11, 56, 21);
			jContentPane.add(Dirua);
			
			Testua1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("YourMoney")); //$NON-NLS-1$ //$NON-NLS-2$
			Testua1.setBounds(284, 14, 96, 14);
			jContentPane.add(Testua1);
			
			JLabel Euro = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
			Euro.setText("\u20AC");
			Euro.setBounds(456, 14, 13, 14);
			jContentPane.add(Euro);
			
			btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ViewMovements")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					JFrame JJ = new ViewBets(appFacadeInterface, E1);
					
					System.out.println("");
					System.out.println("KAIXO " + appFacadeInterface.getUserIsLogin(E1.getUsername(), E1.getPassword()).getDiruMug());
					System.out.println("");
					
					((ViewBets) JJ).setBox1(appFacadeInterface.getUserIsLogin(E1.getUsername(), E1.getPassword()));
					((ViewBets) JJ).setBox2(appFacadeInterface.getUserIsLogin(E1.getUsername(), E1.getPassword()));
					
					JJ.setVisible(true);
				}
			});
			btnNewButton_1.setBounds(0, 283, 240, 93);
			jContentPane.add(btnNewButton_1);
			
			btnNewButton_2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CopyBets")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CopyBetsGUI cP = new CopyBetsGUI(getBusinessLogic(), nireFrame, E1); 
					nireFrame.setVisible(false);
					cP.setVisible(true);
					
				}
			});
			btnNewButton_2.setBounds(239, 283, 240, 93);
			jContentPane.add(btnNewButton_2);
			jContentPane.add(getBtnNewButton_3());
			jContentPane.add(getBtnNewButton_4());
		}
		return jContentPane;
	}



	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	/*
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	*/
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 103, 240, 93);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionRegisterGUI(getBusinessLogic().getUserIsLogin(E1.getUsername(), E1.getPassword()));
					
					nireFrame.setVisible(false);
					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(155, 31, 179, 44);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 387, 479, 44);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		btnNewButton.setText(ResourceBundle.getBundle("Etiquetas").getString("GetMoney"));
		btnNewButton_3.setText(ResourceBundle.getBundle("Etiquetas").getString("CombinedBets"));
		btnNewButton_4.setText(ResourceBundle.getBundle("Etiquetas").getString("HorseRace"));
		btnNewButton_2.setText(ResourceBundle.getBundle("Etiquetas").getString("CopyBets"));
		btnNewButton_1.setText(ResourceBundle.getBundle("Etiquetas").getString("ViewMovements"));
		Testua1.setText(ResourceBundle.getBundle("Etiquetas").getString("YourMoney"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			//ResourceBundle.getBundle("Etiquetas").getString("MainGUILog.btnNewButton.text")
			btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("GetMoney")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					JFrame JJ= new MoneyGUI(appFacadeInterface, nireFrame, appFacadeInterface.getUserIsLogin(E1.getUsername(), E1.getPassword()), false);
					//nireFrame.setVisible(false);
					JJ.setVisible(true);
					setDirua();
				}
			});
			btnNewButton.setBounds(366, 39, 103, 36);
		}
		return btnNewButton;
	}
	
	public void setDirua() {
		text =  String.valueOf(appFacadeInterface.getUserIsLogin(E1.getUsername(), E1.getPassword()).getDirua());
		System.out.println(text);
		Dirua.setText(text);
	}
	
	public void setDirua(String text) {
		Dirua.setText(text);
	}
	
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton_3.setText(ResourceBundle.getBundle("Etiquetas").getString("CombinedBets"));
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					CombinatedGUI JJ = new CombinatedGUI(E1, nireFrame);
					JJ.setVisible(true);
					nireFrame.setVisible(false);
					
				}
			});
			btnNewButton_3.setBounds(239, 103, 240, 93);
		}
		return btnNewButton_3;
	}
	private JButton getBtnNewButton_4() {
		if (btnNewButton_4 == null) {
			btnNewButton_4 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("HorseRace")); //$NON-NLS-1$ //$NON-NLS-2$
			btnNewButton_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					runGUI r = new runGUI(nireFrame,E1, appFacadeInterface);
					r.setVisible(true);
					
				}
			});
			btnNewButton_4.setBounds(0, 193, 479, 93);
		}
		return btnNewButton_4;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

