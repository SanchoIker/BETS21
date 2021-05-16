package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.objectdb.o.BFR;
import com.toedter.calendar.JCalendar;

import domain.Apostu;
import domain.Erregistratua;
import domain.Kuota;
import domain.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableModel;

public class CombinatedGUI extends JFrame {

	private Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public ArrayList<Kuota> UnekoKuotak = new ArrayList<Kuota>();

	Question Q;
	public String text;
	public JFrame nireFrame = this;
	Erregistratua er;

	private DefaultComboBoxModel<Kuota> ApostuKontainer = new DefaultComboBoxModel<Kuota>();
	private Kuota selectedConcreteAp;
	private Collection<Kuota> ApostuCollection;

	private DefaultComboBoxModel<Kuota> ApostuKontainer2 = new DefaultComboBoxModel<Kuota>();
	private Kuota selectedConcreteAp2;
	private Collection<Kuota> ApostuCollection2 = new ArrayList<Kuota>();
	/**
	 * private DefaultComboBoxModel<Apostu> ApostuKontainer2 = new
	 * DefaultComboBoxModel<Apostu>(); private Apostu selectedConcreteAp2;
	 */
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private Erregistratua user = null;

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JLabel MyMoney = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Money")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JLabel lblNewLabel_1 = new JLabel("\u20AC");
	private final JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AvaliableMoney"));
	private JComboBox comboBox;
	private JTextField textField;

	private JButton addNew;

	private JLabel lblNewLabel_3;
	private final JComboBox comboBox2 = new JComboBox();

	public void setUser(Erregistratua user) {
		this.user = user;
	}

	public Erregistratua getUser() {
		return this.user;
	}

	public CombinatedGUI(Erregistratua E, MainGUILog nireFrame2) {
		try {
			jbInit(E, nireFrame2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setE(Erregistratua T) {
		er = T;
	}

	private void jbInit(Erregistratua E, MainGUILog JJ) throws Exception {
		er = E;

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 652));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(84, 248, 406, 14);
		jLabelEvents.setBounds(295, 60, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(544, 572, 130, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
				MainGUILog JJ = new MainGUILog();
				JJ.setAccount(er);
				JJ.setDirua();
				nireFrame.setVisible(false);
				JJ.setVisible(true);
			}
		});

		this.getContentPane().add(jButtonClose, null);

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		setDirua(facade, er);
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay = UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);

					if (monthAct != monthAnt) {
						if (monthAct == monthAnt + 2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2
							// de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt + 1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade = MainGUI.getBusinessLogic();

						Vector<domain.Event> events = facade.getEvents(firstDay);

						if (events.isEmpty())
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
									+ dateformat1.format(calendarAct.getTime()));
						else
							jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev : events) {
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events " + ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not
																												// shown
																												// in
																												// JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			}
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(295, 87, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(84, 273, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
				Vector<Question> queries = ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(
							ResourceBundle.getBundle("Etiquetas").getString("NoQueries") + ": " + ev.getDescription());
				else
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent") + " "
							+ ev.getDescription());

				for (domain.Question q : queries) {
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		MyMoney.setBounds(557, 20, 65, 14);

		getContentPane().add(MyMoney);
		lblNewLabel_1.setBounds(632, 20, 20, 14);

		getContentPane().add(lblNewLabel_1);
		lblNewLabel_2.setBounds(414, 20, 130, 14);

		getContentPane().add(lblNewLabel_2);

		JButton Money = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddMoney"));
		Money.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFrame JJ = new MoneyGUI(facade, nireFrame, facade.getUserIsLogin(er.getUsername(), er.getPassword()),
						true);
				// nireFrame.setVisible(false);
				JJ.setVisible(true);

			}
		});
		Money.setBounds(532, 39, 109, 37);
		getContentPane().add(Money);

		JButton Bet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet")); //$NON-NLS-1$ //$NON-NLS-2$
		Bet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// double Balorea = Double.parseDouble(lblNewLabel_3.getText());

				double Minimoa = 5.0;

				double MomentukoDirua = facade.getUserFromName(er.getUsername()).getDirua();

				if (isNumeric(textField.getText())) {

					double Apostua = Double.parseDouble(textField.getText());

					if (Apostua >= Minimoa) {

						if (MomentukoDirua > Apostua) {
							
							Vector<Kuota> Uk = new Vector<Kuota>();

							for (Kuota k : UnekoKuotak) {
								Uk.add(k);
							}

							facade.createApostu2(Apostua, Uk, er); 

							facade.updateVirtualMoney(er, MomentukoDirua-Apostua);
					
							setUser(facade.getUserFromName(er.getUsername()));
							
							MyMoney.setText(String.valueOf(getUser().getDirua()
									 ));
							
						}else {
							System.out.println("Ez duzu dirurik");
						}
					} else {

						System.out.println("Ez da nahikoa");
					
					}
				} else {
					System.out.println("Ez da numerikoa");
					// Errorea.setForeground(Color.RED);

					// Errorea.setText("Non Valid");

				}

			}

			// }
			// }
		});

		Bet.setBounds(544, 536, 130, 25);
		getContentPane().add(Bet);
		Bet.setEnabled(false);

		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				addNew.setEnabled(true);

				System.out.println(comboBox.getSelectedItem());

				selectedConcreteAp = (Kuota) comboBox.getSelectedItem();

				System.out.println(selectedConcreteAp);

				// bET.setEnabled(true);

			}
		});

		comboBox.setBounds(84, 422, 406, 25);
		getContentPane().add(comboBox);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PosibleQuotes"));
		lblNewLabel.setBounds(84, 400, 140, 14);
		getContentPane().add(lblNewLabel);

		addNew = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Add"));
		addNew.setEnabled(false);
		addNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double k = 1;

				addToList(selectedConcreteAp);

				for (Kuota q : UnekoKuotak) {
					k = k * q.getBalorea();
				}

				lblNewLabel_3.setText(Double.toString(k));

				setBox1();

			}

		});
		addNew.setBounds(544, 500, 130, 25);
		getContentPane().add(addNew);

		JLabel Kuota = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CombinedQuote"));
		Kuota.setBounds(544, 400, 130, 14);
		getContentPane().add(Kuota);

		lblNewLabel_3 = new JLabel("0.0");
		lblNewLabel_3.setBounds(544, 422, 78, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\u20AC");
		lblNewLabel_4.setBounds(637, 422, 20, 14);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("YourBet"));
		lblNewLabel_5.setBounds(544, 447, 130, 14);
		getContentPane().add(lblNewLabel_5);

		textField = new JTextField();
		textField.setText("");
		textField.setBounds(544, 472, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_4_1 = new JLabel("\u20AC");
		lblNewLabel_4_1.setBounds(637, 475, 20, 14);
		getContentPane().add(lblNewLabel_4_1);
		comboBox2.setBounds(84, 536, 406, 25);

		getContentPane().add(comboBox2);

		text = String.valueOf(facade.getUserIsLogin(er.getUsername(), er.getPassword()).getDirua());
		System.out.println(text); // $NON-NLS-1$ //$NON-NLS-2$

		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("HI");
				Bet.setEnabled(true);

				int i = tableQueries.getSelectedRow();

				String S1 = tableModelQueries.getValueAt(i, 0).toString(); // obtain ev object

				// System.out.println(S1);// GET KUOTES: Vector<Question> Kuotes
				// =ev.getQuestions();

				domain.Question q = facade.getQ(Integer.parseInt(S1));

				Q = q;

				Bet.setEnabled(true);
				// System.out.println(Q);

				setBox2(q);
			}
		});

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	public void setDirua(BLFacade facade, Erregistratua E) {
		text = String.valueOf(facade.getUserIsLogin(E.getUsername(), E.getPassword()).getDirua());
		System.out.println(text);
		MyMoney.setText(text);
	}

	public void addToList(Kuota q) {
		if (UnekoKuotak.contains(q)) {
			System.out.println("Dagoeneko listan");
		} else {
			UnekoKuotak.add(q);
		}
	}

	public void setBox2(Question q) {

		ApostuKontainer.removeAllElements();

		ApostuCollection = q.getKuotak();

		// ApostuKontainer2.removeAllElements();

		if (ApostuCollection != null) {

			for (Kuota p : ApostuCollection) {

				ApostuKontainer.addElement(p);

			}
			if (ApostuCollection.isEmpty())
				System.out.println("NO");
			else
				System.out.println("BET");
			;
		} else
			System.out.println("NOPE_2");

		comboBox.setModel(ApostuKontainer);

	}

	public void setBox1() {

		ApostuKontainer2.removeAllElements();

		// ApostuCollection2 = q.getKuotak();
		// ApostuCollection2.addAll(UnekoKuotak);

		for (Kuota k : UnekoKuotak) {
			if (!ApostuCollection2.contains(k)) {
				ApostuCollection2.add(k);
			}
		}
		// UnekoKuotak.addAll(ApostuCollection2);

		if (ApostuCollection2 != null) {

			for (Kuota p : ApostuCollection2) {

				ApostuKontainer2.addElement(p);

			}
			if (ApostuCollection.isEmpty())
				System.out.println("NO");
			else
				System.out.println("BET");
			;
		} else
			System.out.println("NOPE_2");

		comboBox2.setModel(ApostuKontainer2);

	}

	public boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		return pattern.matcher(strNum).matches();
	}
}
