package businessLogic;

//hola
//atsaldeon bai
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Administratzailea;
import domain.Apostu;
import domain.Emaitza;
import domain.Erregistratua;
import domain.Event;
import domain.Kuota;
import domain.Langilea;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
		} else
			  dbManager=new DataAccess();
		dbManager.close();
	}

	public BLFacadeImplementation(DataAccess da) {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager = da;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		dbManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	public void close() {
		DataAccess dB4oManager = new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD() {
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
	@WebMethod 
	public int userIsLogin(String user, String password) {
		dbManager.open(false);
		Erregistratua e1 = dbManager.isUserLogin(user, password);
		dbManager.close();
		if (e1 != null) {
			if (e1.getClass().equals(Administratzailea.class)) {
				return 2;
			} else if (e1.getClass().equals(Langilea.class)) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return -1;
		}
	}
	@WebMethod 
	public boolean isDNIin(String dni) {
		dbManager.open(false);
		Erregistratua e1 = dbManager.isDNIin(dni);
		dbManager.close();
		return e1 != null;
	}
	@WebMethod 
	public void createUser(String user, String password, String Izena, String Abizena, String DNI, String email,
			String adina, double bankua) {

		// System.out.println("YES");

		// dbManager.open(false);
		Erregistratua E1 = new Erregistratua(Izena, Abizena, DNI, email, user, password, adina, bankua);

		if (userIsLogin(user, password) == -1 && !isDNIin(DNI)) {
			dbManager.open(false);
			// System.out.println("VV");
			dbManager.createUser(E1);
			dbManager.close();
		} else {
			System.out.println("Errorea DNI-a edo usuarioa bi aldiz dago.");
		}

		System.out.println("HERE");
		// dbManager.close();

	}
	@WebMethod 
	public void createEvent(Event E1) {
		dbManager.open(false);
		dbManager.eventCreate(E1);
		dbManager.close();
	}
	@WebMethod 
	public Erregistratua getUserIsLogin(String user, String password) {
		dbManager.open(false);
		Erregistratua e1 = dbManager.isUserLogin(user, password);
		dbManager.close();
		if (e1 != null) {
			return e1;
		} else {
			return null;
		}
	}
	@WebMethod 
	public void updateMoney(Erregistratua e, Double dirua, Double SartuBeharrekoa) {
		dbManager.open(false);
		dbManager.updateMoney(e, dirua, SartuBeharrekoa);
		dbManager.close();
	}
	@WebMethod 
	public void addKuota(Event e1,Question q1, String S1, double D1) {
		dbManager.open(false);
		dbManager.addKuota(q1, S1, D1);
		dbManager.close();
	}
	@WebMethod 
	public Vector<Kuota> getKuota(Event e1, Question q1){
		dbManager.open(false);
		Vector<Kuota> emaitza = dbManager.getKuotak(q1);
		dbManager.close();
		return emaitza;
	}
	@WebMethod 
	public Event getEvent(int num) {
		dbManager.open(false);
		Event E = dbManager.getEvent(num);
		dbManager.close();
		return E;
	}
	@WebMethod 
	public Question getQ(int num) {
		dbManager.open(false);
		Question E = dbManager.getQ(num);
		dbManager.close();
		return E;
	}
	@WebMethod 
	public void createApostu( double dirukop, Kuota kuota, Erregistratua user) {
		dbManager.open(false);
		if(user.getKopiadores()!=null) {
			for(Erregistratua e1: user.getKopiadores()) {
				double dirua = dirukop * e1.getPorcentaje();
				System.out.println("Dirua hau da!!! : " + dirua + "Porcentaje: " + e1.getPorcentaje() + "dirukop: " + dirukop);
				double k = this.getBetMinimunKuota(kuota);
				dbManager.open(false);
				if(dirua <= e1.getDirua() && dirua >= k) {
					dbManager.addApostu(dirua, kuota, e1);
					double dirube = e1.getDirua() - dirua;
					dbManager.updateVirtualMoney(e1, dirube);
				}
			}
		}
		dbManager.addApostu(dirukop, kuota, user);
		dbManager.close();
	}
	@WebMethod
	public void createApostu2( double dirukop,  Vector<Kuota> k, Erregistratua user) {
		dbManager.open(false);
		/**
		 * 
		 * Tegno que mirar como hacer lo de copiador
		 * 
		if(user.getKopiadores()!=null) {
			for(Erregistratua e1: user.getKopiadores()) {
				double dirua = dirukop * e1.getPorcentaje();
				System.out.println("Dirua hau da!!! : " + dirua + "Porcentaje: " + e1.getPorcentaje() + "dirukop: " + dirukop);
				if(dirua <= e1.getDirua() && dirua >= kuota.getGaldera().getBetMinimum()) {
					dbManager.addApostu(dirua, kuota, e1);
					double dirube = e1.getDirua() - dirua;
					dbManager.updateVirtualMoney(e1, dirube);
				}
			}
		}*/
		dbManager.addApostu2(dirukop, k, user);
		dbManager.close();
	}
	@WebMethod 
	public Vector<Apostu> getApostuU(Erregistratua user) {
		dbManager.open(false);
		Vector<Apostu> emaitza = dbManager.getApostu(user);
		dbManager.close();
		return emaitza;
	}
	@WebMethod 
	public Vector<Apostu> getApostuK(Kuota kuota) {
		dbManager.open(false);
		Vector<Apostu> emaitza = dbManager.getApostu(kuota);
		dbManager.close();
		return emaitza;
	}
	@WebMethod 
	public void updateVirtualMoney(Erregistratua E, double D) {
		dbManager.open(false);
		dbManager.updateVirtualMoney(E, D);
		dbManager.close();
		
	}
	@WebMethod 
	public List<Double> getDiruMug(Erregistratua E) {
		
		List<Double> d = null;
		
		dbManager.open(false);
		d = dbManager.getDiruMug(E);
		dbManager.close();
		
		return d;
	}
	@WebMethod 
	public Emaitza getEmaitza(Emaitza E) {
		dbManager.open(false);
		Emaitza e =dbManager.getEmaitza(E.getEmaitzaN());
		dbManager.close();
		return e;
	}
	@WebMethod 
	public Emaitza createEmaitza(Kuota k, boolean b) {
		dbManager.open(false);
		Emaitza E =dbManager.createEmaitza(k, b);
		dbManager.close();
		return getEmaitza(E);
	}
	@WebMethod 
	public Kuota getKuotaZehatza(Kuota K) {
		dbManager.open(false);
		Kuota k = dbManager.getKuotaZehatza(K);
		dbManager.close();
		return k;
	}
	@WebMethod 
	public void setBofApostu(Apostu A, boolean B) {
		dbManager.open(false);
		dbManager.setBofApostu(A, B);
		dbManager.close();
	}
	@WebMethod 
	public Apostu getApostuZ(Apostu a) {
		dbManager.open(false);
		Apostu A = dbManager.getApostuZ(a);
		dbManager.close();
		return A;
		
	}
	@WebMethod 
	public void ezabatuEv(Event e) {
		dbManager.open(false);
		dbManager.ezabatuEv(e);
		dbManager.close();
	}
	@WebMethod
	public Erregistratua getUserFromName(String User) {
		dbManager.open(false);
		Erregistratua E1 = dbManager.getUserFromName(User);
		dbManager.close();
		return E1;
	}
	
	@WebMethod
	public void updateUser(Erregistratua user) {
		dbManager.open(false);
		dbManager.updateUser(user);
		dbManager.close();
	}
	@WebMethod
	public void HorseApostu(int winner,Erregistratua user, int apostu) {
		dbManager.open(false);
		Event e1 = dbManager.getEventByDesc("Zaldi-Lasterketa");
		Erregistratua u1 = dbManager.getUserFromName(user.getUsername());
		dbManager.close();
		Question q1 = this.getEventQuestion(e1);
		List<Kuota> k1 = this.getKuota(e1, q1);
		List<Apostu> a1 = this.getApostuK(k1.get(apostu));
		if(winner==apostu) {
			System.out.println("Zaldi Lasterketen Irabazlea !!!  **********************************************");
			this.createEmaitza(k1.get(apostu), true);
			this.setBofApostu(a1.get(0), true);
			Apostu a2 = this.getApostuZ(a1.get(0));
			System.out.println("Irabaziko duen dirua: " + a2.getIrabazitakoDirua() + user.getDirua());
			this.updateVirtualMoney(u1, u1.getDirua() + a2.getIrabazitakoDirua());
		}else {
			System.out.println("Zaldi Lasterketen Galtzailea !!!  **********************************************");
			this.createEmaitza(k1.get(apostu), false);
			this.setBofApostu(a1.get(0), false);
		}
		dbManager.open(false);
		dbManager.cleanKuota(k1.get(apostu));
		dbManager.close();
		
	}
	
	@WebMethod
	public Question getEventQuestion(Event ev) {
		dbManager.open(false);
		Question q1 = dbManager.getEventQuestion(ev);
		dbManager.close();
		return q1;
	}
	@WebMethod
	public void createHorseApostu(double dirukop, Erregistratua User,int Zaldia) {
		dbManager.open(false);
		Event e1 = dbManager.getEventByDesc("Zaldi-Lasterketa");
		dbManager.close();
		Question q1 = this.getEventQuestion(e1);
		List<Kuota> k1 = this.getKuota(e1, q1);
		System.out.println(k1.get(Zaldia));
		dbManager.open(false);
		dbManager.addApostu(dirukop, k1.get(Zaldia), User);
		dbManager.close();
	}
	@WebMethod
	public Erregistratua getUserFromApostu(Apostu a) {
		Erregistratua E = null;
		dbManager.open(false);
		E=dbManager.getUserFromApostu(a); 
		dbManager.close();
		return E;
	}
	
	@WebMethod
    public void copyBets(Erregistratua kopiador, String nick, double porcentaje) {
        Erregistratua user2 = null;
        boolean aurretik = false;
        double por = porcentaje/100;
        dbManager.open(false);
        Erregistratua user = dbManager.getUserFromName(nick);
       
        if(kopiador.getKopiando() != null) {
            user2 = dbManager.getUserFromName(kopiador.getKopiando().getUsername());
            aurretik = true;
        }
        
        
        dbManager.setCopyUser(kopiador, user, user2, por, aurretik);
        
        
        dbManager.close();


    }
	@WebMethod 
	public Vector<Apostu> isCombinated(Apostu a,  Erregistratua E) {
		Vector<Apostu> b1=null;
		dbManager.open(false);
		b1=dbManager.isCombinated(a, E);
		dbManager.close();
		return b1;
	}
	@WebMethod 
	public boolean guztiakDiraTrue(Vector<Apostu> v) {
		boolean b1=true;
		dbManager.open(false);
		for(Apostu a : v) {
			if(!a.getIsIrabazita()) {
				b1=false;
			}
		}
		dbManager.close();
		return b1;
	}
	@WebMethod
	public Kuota getKuotaApostu(Apostu a) {
		dbManager.open(false);
		Kuota k = dbManager.getKuotaApostu(a);
		dbManager.close();
		return k;
	}
	
	@WebMethod
	public Vector<Apostu> getApostuKonbinatuak(Erregistratua user){
		dbManager.open(false);
		Vector<Apostu> ap = dbManager.getApostuKonbinatuak(user);
		dbManager.close();
		return ap;
	}
	
	@WebMethod
	public float getBetMinimunKuota(Kuota k) {
		dbManager.open(false);
		float m = dbManager.getBetMinimunKuota(k);
		dbManager.close();
		return m;
	}

}
