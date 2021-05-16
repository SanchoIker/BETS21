package domain;

import javax.persistence.Entity;

@Entity
public class Langilea extends Erregistratua{
	
	
	
	public Langilea(String izena, String abizena, String dni, String email,String user, String password, String adina,double bankua) {
		super(izena,abizena,dni,email,user,password,adina,bankua);
	}

}
