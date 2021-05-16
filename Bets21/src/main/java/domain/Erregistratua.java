package domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Erregistratua extends Erabiltzailea implements Serializable{
	@Id
	@XmlID
	private String username;
	private String password;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Apostu> apostuk;
	@OneToMany(fetch=FetchType.EAGER)
	private List<Double> DiruMug;
	@OneToMany(fetch=FetchType.EAGER)
	private List<Erregistratua> kopiadores;
	@XmlIDREF
	private Erregistratua kopiando = null;
	private double porcentaje = 1.0;
	

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Vector<Apostu>> Konbinatuak = new Vector<Vector<Apostu>>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Double> KonbinatuanApostatutakoDirua = new Vector<Double>();
	
	public void setDiruMug(List<Double> diruMug) {
		DiruMug = diruMug;
	}

	public void setApostuk(List<Apostu> apostuk) {
		this.apostuk = apostuk;
	}
	
	//________________________________________________________ERAGIKETAS PARA KONBINATU
	
	public void addKonb(Vector<Apostu> a, Double balioa) {
		Konbinatuak.add(a);
		KonbinatuanApostatutakoDirua.add(balioa);
	}
	
	public List<Vector<Apostu>> getKonbinatuak() {
		return Konbinatuak;
	}

	public List<Double> getKonbinatuenPrezioa() {
		return KonbinatuanApostatutakoDirua;
	}

	public void remove_apostuZehatzak_inKonb() {
		for(Vector<Apostu> a : Konbinatuak) {
			for(Apostu b : a) {
				if(b==null) {
					a.remove(b);
				}
			}
		}
	}
	
	public int whereIsIt(Vector<Apostu> a) {
		int emaitza =0;
		for(Vector<Apostu> v: Konbinatuak) {
			if(v.containsAll(a)) {
				emaitza= Konbinatuak.indexOf(v);
			}
		}
		return emaitza;
	}
	
	public double get_KuotaKonbinatua(Vector<Apostu> a) {
		Double emaitza = 0.0;
		
		for(Apostu p: a) {
			emaitza *= p.getKuota().getBalorea();
		}
		
		return emaitza;
	}
	
	//__________________________________________________________________________________
	
	
	
	public Erregistratua(String izena, String abizena, String dni, String email, String username, String password, String adina, double bankua) {
		super(izena, abizena, dni, email, adina, bankua);
		this.username = username;
		this.password = password;
		
		//Ojo cuidau
		for(Vector<Apostu> a: Konbinatuak) {
			a= new Vector<Apostu>();
		}
		
	}
	public Erregistratua() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public List<Apostu> getApostuk(){
		return this.apostuk;
	}
	
	public void addApostu(Apostu a) {
		if(this.apostuk == null) {
			this.apostuk = new Vector<Apostu>();
		}
		this.apostuk.add(a);
	}
	
	public void addDiruMug(double a) {
		if(this.DiruMug == null) {
			this.DiruMug = new Vector<Double>();
		}
		this.DiruMug.add(a);
	}
	
	
	public List<Double> getDiruMug(){
		return this.DiruMug;
	}
	@Override
	public String toString() {
		return(this.getIzena() + this.getAbizena()  + this.getDni() +this.getEmail() +this.getAdina()+ this.getBankua() + this.getUsername() + this.getPassword());
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public List<Erregistratua> getKopiadores() {
		return kopiadores;
	}

	public void addKopiadores(Erregistratua kopiador) {
		if(this.kopiadores == null) {
			this.kopiadores = new Vector<Erregistratua>();
		}
		this.kopiadores.add(kopiador);
	}
	public void deleteKopiadores(Erregistratua kopiador) {
		this.kopiadores.remove(kopiador);
	}

	public Erregistratua getKopiando() {
		return kopiando;
	}

	public void setKopiando(Erregistratua kopiando) {
		this.kopiando = kopiando;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

}
