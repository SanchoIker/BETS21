package dataAccess;

//hello
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess() {
		this(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));
			Event ev99 = new Event(99, "Zaldi-Lasterketa", null);

			Erregistratua user1 = new Erregistratua("aritz", "basurco", "123", "asdfasdf@", "aritz1", "vivanavarra",
					"18", 150.0);
			Erregistratua user2 = new Erregistratua("a", "a", "a", "a", "a", "a", "18", 180.0);
			Erregistratua user3 = new Erregistratua("a2", "a2", "a2", "a2", "a2", "a2", "18", 180.0);
			Erregistratua lan1 = new Langilea("b", "b", "b", "b", "b", "b", "18", 0.0);
			Erregistratua admin1 = new Administratzailea("c", "c", "c", "c", "c", "c", "18", 0.0);

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
			Question q7;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
				q7 = ev99.addQuestion("�Qui�n Ganar�?", 1);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
				q7 = ev99.addQuestion("Who will win?", 1);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

				q7 = ev99.addQuestion("Nork irabaziko du?", 1);

			}
			q1.addKuota("Athletic", 1.3);
			q1.addKuota("Athletico", 1.4);
			q1.addKuota("Enpate", 1.1);

			q2.addKuota("Suarez", 1.8);
			q2.addKuota("Muniain", 1.2);

			q7.addKuota("Lehen Zaldia", 1.8);
			q7.addKuota("Bigarren Zaldia", 1.3);
			q7.addKuota("Hirugarren Zaldia", 1.5);
			q7.addKuota("Laugarren Zaldia", 1.2);

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
			db.persist(q7);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
			db.persist(ev99);

			db.persist(user1);
			db.persist(user2);
			db.persist(user3);
			db.persist(lan1);
			db.persist(admin1);

			// TODO Txukunago a�aditu

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	public Erregistratua isUserLogin(String user, String password) {
		TypedQuery<Erregistratua> e1 = db.createQuery(
				"SELECT e1 FROM Erregistratua e1 WHERE e1.username=?1 AND e1.password=?2", Erregistratua.class);
		e1.setParameter(1, user);
		e1.setParameter(2, password);
		// Erregistratua e1 = db.find(Erregistratua.class, "a");
		try {
			return e1.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;

		}

	}

	public Erregistratua createUser(Erregistratua E1) {
		System.out.println("NO");
		db.getTransaction().begin();
		Erregistratua E = E1;
		db.persist(E);
		System.out.println(E1.getEmail() + " is now registered");
		db.getTransaction().commit();
		return E;
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}

	public boolean exitsEvent(Event E1) {
		TypedQuery<Erregistratua> e1 = db.createQuery(
				"SELECT e1 FROM Event e1 WHERE e1.description=?1 AND e1.eventDate=?2", Erregistratua.class);
		e1.setParameter(2, E1.getEventDate());
		e1.setParameter(1, E1.getDescription());
		// Erregistratua e1 = db.find(Erregistratua.class, "a");
		try {
			return e1.getSingleResult() != null;
		} catch (javax.persistence.NoResultException e) {
			return false;

		}
	}

	public boolean eventCreate(Event E1) {

		db.getTransaction().begin();
		if (!exitsEvent(E1)) {
			db.persist(E1);
		} else {
			System.out.println("This event already exits");
		}
		// System.out.println(E1.getEmail() + " is now registered");

		db.getTransaction().commit();

		return true;
	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public Erregistratua isDNIin(String dni) {
		TypedQuery<Erregistratua> e1 = db.createQuery("SELECT e1 FROM Erregistratua e1 WHERE e1.dni=?1",
				Erregistratua.class);
		e1.setParameter(1, dni);
		// Erregistratua e1 = db.find(Erregistratua.class, "a");
		try {
			return e1.getSingleResult();
		} catch (javax.persistence.NoResultException e) {
			return null;
		}

	}

	public void updateMoney(Erregistratua e, Double dirua, Double SartuBeharrekoa) {
		db.getTransaction().begin();
		Erabiltzailea E = db.find(Erregistratua.class, e.getUsername());
		E.setBank(dirua);
		E.addDirua(SartuBeharrekoa);
		Erregistratua E1 = (Erregistratua) E;

		System.out.println("HEMEN GAUDE" + E1.getDiruMug());

		E1.addDiruMug(SartuBeharrekoa);

		System.out.println("HEMEN GAUDE" + E1.getDiruMug());

		System.out.println(E.getBank());
		System.out.println(E.getDirua());
		db.persist(E);
		db.getTransaction().commit();
	}

	public void addKuota(Question q1, String S1, double D1) {
		db.getTransaction().begin();
		Question q = db.find(Question.class, q1.getQuestionNumber());
		q.addKuota(S1, D1);

		db.persist(q);
		System.out.println("Kuota Sartuta");
		db.getTransaction().commit();
	}

	public Vector<Kuota> getKuotak(Question q1) {
		TypedQuery<Question> list = db.createQuery("SELECT q1 FROM Question q1 WHERE q1.questionNumber=?1",
				Question.class);
		list.setParameter(1, q1.getQuestionNumber());
		Question q = list.getSingleResult();
		return new Vector<Kuota>(q.getKuotak());
	}

	public Event getEvent(int num) {
		db.getTransaction().begin();
		Event E = db.find(Event.class, num);
		db.getTransaction().commit();
		return E;
	}

	public Question getQ(int num) {
		db.getTransaction().begin();
		Question Q = db.find(Question.class, num);
		db.getTransaction().commit();
		return Q;
	}

	public void addApostu(double dirukop, Kuota kuota, Erregistratua user) {

		// System.out.println("");
		// System.out.println(dirukop + " " + kuota + " " + user);

		Apostu a = new Apostu(dirukop, kuota, user);

		// System.out.println("");
		// System.out.println(a);

		db.getTransaction().begin();

		Kuota k = db.find(Kuota.class, kuota.getId());
		Erregistratua u = db.find(Erregistratua.class, user.getUsername());

		// System.out.println(k + " " + u);

		k.addApostu(a);
		// System.out.println(a.getDiruKop());
		u.addApostu(a);
		// System.out.println("Apostua Gordeta");
		db.getTransaction().commit();

	}

	public void addApostu2(double dirukop, Vector<Kuota> k, Erregistratua user) {

		db.getTransaction().begin();

		Erregistratua u = db.find(Erregistratua.class, user.getUsername());

		Vector<Apostu> A = new Vector<Apostu>();

		for (Kuota q : k) {

			Kuota kq = db.find(Kuota.class, q.getId());

			Apostu ap = new Apostu(0, kq, user);
			kq.addApostu(ap);
			A.add(ap);
		}

		u.addKonb(A, dirukop);

		db.persist(u);

		db.getTransaction().commit();

	}

	public Vector<Apostu> getApostu(Erregistratua user) {
		TypedQuery<Erregistratua> e = db.createQuery("SELECT u1 FROM Erregistratua u1 WHERE u1.getUsername=?1",
				Erregistratua.class);
		e.setParameter(1, user.getUsername());
		Erregistratua u = e.getSingleResult();
		return new Vector<Apostu>(u.getApostuk());
	}

	public Vector<Apostu> getApostu(Kuota kuota) {
		TypedQuery<Kuota> e = db.createQuery("SELECT k1 FROM Kuota k1 WHERE k1.Id=?1", Kuota.class);
		e.setParameter(1, kuota.getId());
		Kuota k = e.getSingleResult();
		return new Vector<Apostu>(k.getApostuk());
	}

	public void updateVirtualMoney(Erregistratua E, double D) {
		db.getTransaction().begin();
		Erregistratua E1 = db.find(Erregistratua.class, E.getUsername());
		E1.setDirua(D);
		db.persist(E1);
		db.getTransaction().commit();
	}

	public List<Double> getDiruMug(Erregistratua E) {
		db.getTransaction().begin();
		Erregistratua E1 = db.find(Erregistratua.class, E.getUsername());
		List<Double> d = E1.getDiruMug();

		System.out.println("");
		System.out.println(d + "EU");
		System.out.println("");

		db.getTransaction().commit();

		return d;
	}

	public Emaitza getEmaitza(int i) {
		db.getTransaction().begin();
		Emaitza E = db.find(Emaitza.class, i);
		db.getTransaction().commit();
		return E;
	}

	public Emaitza createEmaitza(Kuota k, boolean b) {
		db.getTransaction().begin();
		Emaitza E = null;
		Kuota ku = db.find(Kuota.class, k.getId());
		if (ku.getE() == null) {
			E = new Emaitza(ku, b);
			ku.setE(E);
			db.persist(ku);
		} else {
			System.out.println("dagoeneko badu emaitza bat");
			E = ku.getE();
		}
		db.getTransaction().commit();
		return E;

	}

	public Kuota getKuotaZehatza(Kuota K) {
		db.getTransaction().begin();
		Kuota k = db.find(Kuota.class, K.getId());
		db.getTransaction().commit();
		return k;

	}

	public void setBofApostu(Apostu A, boolean B) {
		db.getTransaction().begin();
		Apostu a = db.find(Apostu.class, A.getId());
		a.setErantzuna(B);
		db.persist(a);
		db.getTransaction().commit();
	}

	public Apostu getApostuZ(Apostu a) {
		db.getTransaction().begin();
		Apostu A = db.find(Apostu.class, a.getId());
		db.getTransaction().commit();
		return A;
	}

	public void ezabatuEv(Event e) {
		db.getTransaction().begin();

		Event E = db.find(Event.class, e.getEventNumber());

		for (Question q : E.getQuestions()) {
			for (Kuota k : q.getKuotak()) {
				for (Apostu a : k.getApostuk()) {
					Erregistratua er = db.find(Erregistratua.class, a.getUser().getUsername());

					if (k.getE() == null) {

						double diria = er.getDirua();
						double dirua = a.getDiruKop();
						double Dirut = diria + dirua;

						er.addDiruMug(dirua);

						er.setDirua(Dirut);

						er.remove_apostuZehatzak_inKonb();

						db.persist(er);
					}

				}
			}
		}

		db.remove(E);
		db.getTransaction().commit();
	}

	public Erregistratua getUserFromName(String User) {
		Erregistratua E1 = null;

		db.getTransaction().begin();
		E1 = db.find(Erregistratua.class, User);
		db.getTransaction().commit();

		return E1;
	}

	public void updateUser(Erregistratua user) {

		db.getTransaction().begin();
		Erregistratua user1 = db.find(Erregistratua.class, user.getUsername());
		db.remove(user1);
		db.getTransaction().commit();

		db.getTransaction().begin();
		db.persist(user);
		db.getTransaction().commit();

	}

	public Event getEventByDesc(String desc) {
		TypedQuery<Event> e = db.createQuery("SELECT e1 FROM Event e1 WHERE e1.description=?1", Event.class);
		e.setParameter(1, desc);
		return e.getSingleResult();
	}

	public Question getEventQuestion(Event ev) {
		Event e = db.find(Event.class, ev.getEventNumber());
		return e.getQuestions().get(0);
	}

	public void cleanKuota(Kuota k) {
		db.getTransaction().begin();
		Kuota k1 = db.find(Kuota.class, k.getId());
		k1.clean();
		db.getTransaction().commit();
	}

	public Erregistratua getUserFromApostu(Apostu a) {

		return db.find(Apostu.class, a.getId()).getUser();

	}

	public void setCopyUser(Erregistratua kopiador1, Erregistratua kopia1, Erregistratua aurrekoa1, double por,
			boolean aurretik) {
		db.getTransaction().begin();
		Erregistratua kopiador = db.find(Erregistratua.class, kopiador1);
		Erregistratua kopia = db.find(Erregistratua.class, kopia1);
		Erregistratua aurrekoa = null;
		if (aurretik) {
			aurrekoa = db.find(Erregistratua.class, aurrekoa1);
		}
		System.out.println("DB barrua: " + por);
		kopiador.setPorcentaje(por);
		kopiador.setKopiando(kopia);
		kopia.addKopiadores(kopiador);
		if (aurretik) {
			aurrekoa.deleteKopiadores(kopiador);
			db.persist(aurrekoa);
		}
		db.getTransaction().commit();
		Erregistratua e1 = db.find(Erregistratua.class, kopiador.getUsername());
		System.out.println("DB barruko porcentajea!!: " + e1.getPorcentaje());
		System.out.println("DB barruko kopiadorea!!: " + e1.getKopiando().getUsername());

	}

	public Vector<Apostu> isCombinated(Apostu A, Erregistratua E) {
		int KONT = 0;
		System.out.println(KONT);

		Vector<Apostu> v = null;
		db.getTransaction().begin();

		v = new Vector<Apostu>();
		
		Apostu a = db.find(Apostu.class, A.getId());
		Erregistratua EE = db.find(Erregistratua.class, E.getUsername());

		for (Vector<Apostu> A2 : EE.getKonbinatuak()) {
			if (A2.contains(a)) {
			
				System.out.println("EU ESTOY AQUI");
				v.addAll(A2);

			}
		}
		
		System.out.println("Hau da V:" + v.size());

		db.getTransaction().commit();
		return v;
	}
	
	public Kuota getKuotaApostu(Apostu a) {
		return db.find(Apostu.class, a).getKuota();
	}
	
	public Vector<Apostu> getApostuKonbinatuak(Erregistratua e){
		Erregistratua e1 = db.find(Erregistratua.class, e.getUsername());
		Vector<Apostu> ap = new Vector<Apostu>();
		for (Vector<Apostu> a1 : e1.getKonbinatuak()) {
			for (Apostu a : a1) {
				ap.add(a);
			}
		}
		return ap;
	}
	
	public float getBetMinimunKuota(Kuota k) {
		Kuota k1 = db.find(Kuota.class, k.getId());
		return k1.getGaldera().getBetMinimum();
	}
}
