package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Apostu implements Serializable{
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@XmlID
	private Integer Id;
	private double diruKop;
	@XmlIDREF
	private Kuota kuota;
	@XmlIDREF
	private Erregistratua user;
	private String desc;
	private boolean irabazita=false;
	private double irabazitakoDirua;
	
	
	
	public Apostu(double diruKop, Kuota kuota, Erregistratua user) {
		this.diruKop = diruKop;
		this.kuota = kuota;
		this.user = user;
		this.desc = kuota.toString();
	}
	public Apostu() {
		super();
	}
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public double getDiruKop() {
		return diruKop;
	}

	public void setDiruKop(double diruKop) {
		this.diruKop = diruKop;
	}

	public Kuota getKuota() {
		return kuota;
	}

	public void setKuota(Kuota kuota) {
		this.kuota = kuota;
	}

	public Erregistratua getUser() {
		return user;
	}

	public void setUser(Erregistratua user) {
		this.user = user;
	}
	
	public String toString() {
		return "-"+ diruKop +" -- x" + desc ;
	}
	
	public void setErantzuna(boolean b) {
		this.irabazita=b;
		if(b) {
			this.irabazitakoDirua = (this.diruKop * this.kuota.getBalorea()); 
		}
	}
	
	public double getIrabazitakoDirua() {
		return this.irabazitakoDirua;
	}
	
	public boolean getIsIrabazita() {
		return this.irabazita;
	}
}
