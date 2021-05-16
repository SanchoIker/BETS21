package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.Apostu;
import domain.Emaitza;
import domain.Erregistratua;
import domain.Event;
import domain.Kuota;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod public int userIsLogin(String user, String password);

	@WebMethod public void createUser(String user, String password, String Izena, String Abizena, String DNI, String email,String adina,double bankua);

	@WebMethod public boolean isDNIin(String dni);
	
	@WebMethod public void createEvent(Event E1);
	
	@WebMethod public Event getEvent(int num);
	
	@WebMethod public Question getQ(int num);
	
	@WebMethod public Erregistratua getUserIsLogin(String user, String password);

	@WebMethod public void updateMoney(Erregistratua e, Double dirua, Double SartuBeharrekoa);
	
	@WebMethod public void addKuota(Event e1, Question q1, String S1, double D1);

	@WebMethod public void updateVirtualMoney(Erregistratua E, double D);
	
	@WebMethod public Vector<Kuota> getKuota(Event e1, Question q1);
	
	@WebMethod public void createApostu( double dirukop, Kuota kuota, Erregistratua user);
	
	@WebMethod public void createApostu2( double dirukop,  Vector<Kuota> k, Erregistratua user);
	
	@WebMethod public Vector<Apostu> getApostuU(Erregistratua user);
	
	@WebMethod public Vector<Apostu> getApostuK(Kuota kuota);
	
	@WebMethod public List<Double> getDiruMug(Erregistratua E);
	
	@WebMethod public Emaitza createEmaitza(Kuota k, boolean b);
	
	@WebMethod public Emaitza getEmaitza(Emaitza E);
	
	@WebMethod public Kuota getKuotaZehatza(Kuota K);
	
	@WebMethod public void setBofApostu(Apostu A, boolean B);
	
	@WebMethod public Apostu getApostuZ(Apostu a);
	
	@WebMethod public void ezabatuEv(Event e);
	
	@WebMethod public Erregistratua getUserFromName(String User);
	
	@WebMethod public void copyBets(Erregistratua kopiador, String nick, double porcentaje);
	
	@WebMethod public void updateUser(Erregistratua user);
	
	@WebMethod public void HorseApostu(int winner,Erregistratua user, int apostu);
	
	@WebMethod public Question getEventQuestion(Event ev);
	
	@WebMethod public void createHorseApostu(double dirukop, Erregistratua User,int Zaldia);
	
	@WebMethod public Erregistratua getUserFromApostu(Apostu a);
	
	@WebMethod public Vector<Apostu> isCombinated(Apostu a,  Erregistratua E);
	
	@WebMethod public boolean guztiakDiraTrue(Vector<Apostu> v);
	
	@WebMethod public Kuota getKuotaApostu(Apostu a);
	
	@WebMethod public Vector<Apostu> getApostuKonbinatuak(Erregistratua user);
	
	@WebMethod public float getBetMinimunKuota(Kuota k);


}