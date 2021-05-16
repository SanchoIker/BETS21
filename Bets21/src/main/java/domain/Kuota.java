package domain;

import java.io.Serializable;

import java.util.List;
import java.util.Vector;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Kuota implements Serializable{
	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@XmlID
	private Integer Id;
	private String deskribapena;
	private double balorea;
	@XmlIDREF
	private Question galdera;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Apostu> apostuk = new Vector<Apostu>();
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Emaitza e=null;
	private String descGald;
	
	
	public Kuota() {
		super();
	}
	public Kuota(String S1, Double D1, Question G1) {
		this.deskribapena = S1;
		this.balorea = D1;
		this.galdera = G1;
		this.descGald = G1.getQuestion();
	}
	
	public String getDeskribapena() {
		return deskribapena;
	}
	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}
	public double getBalorea() {
		return balorea;
	}
	public void setBalorea(double balorea) {
		this.balorea = balorea;
	}
	public Question getGaldera() {
		return this.galdera;
	}
	public void setGaldera(Question q) {
		this.galdera = q;
	}
	public void setId(int i) {
		this.Id = i;
	}
	public int getId() {
		return this.Id;
	}
	public List<Apostu> getApostuk(){
		return this.apostuk;
	}
	
	public void addApostu(Apostu a) {
		this.apostuk.add(a);
	}
	@Override
	public String toString() {
        return this.Id +" / "+ this.deskribapena + " --- " + this.balorea+"€" + "  ---  galdera:  (" + this.descGald +")";
    }
	public Emaitza getE() {
		return e;
	}
	public void setE(Emaitza e) {
		this.e = e;
	}
	
	public void clean() {
		this.apostuk.clear();
		this.e=null;
	}

}
