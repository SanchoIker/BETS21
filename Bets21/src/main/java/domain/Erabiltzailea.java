package domain;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlSeeAlso;

import java.io.Serializable;

@Entity
@XmlSeeAlso ({Erabiltzailea.class, Langilea.class, Administratzailea.class})
public abstract class Erabiltzailea implements Serializable{
	
	private String izena;
	private String abizena;
	private double bankua;
	private String dni;
	private String email;
	private String Adina;
	private double dirua;
	
	public double getBankua() {
		return bankua;
	}

	public void setBankua(double bankua) {
		this.bankua = bankua;
	}

	public String getAdina() {
		return Adina;
	}

	public void setAdina(String adina) {
		Adina = adina;
	}

	public void setDirua(double dirua) {
		this.dirua = dirua;
	}

	public Erabiltzailea(String izena, String abizena, String dni, String email,String adina, double bankua) {
		this.izena = izena;
		this.abizena = abizena;
		this.dni = dni;
		this.email = email;
		this.bankua = bankua;
		this.Adina = adina;
		this.dirua = 0.0;
	}
	public Erabiltzailea() {
		super();
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getAbizena() {
		return abizena;
	}
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	public double getBank() {
		return bankua;
	}
	public void setBank(double bank) {
		this.bankua = bank;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getDirua() {
		return this.dirua;
	}
	public void addDirua(double x) {
		this.dirua += x;
	}
	
	
}
