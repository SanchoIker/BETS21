package domain;

import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Emaitza {
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@XmlID
	private Integer emaitzaN;
	private boolean ema;
	@XmlIDREF
	private Kuota K;
//	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
//	private List<Apostu> Apostuak = new Vector<Apostu>();
	
	
	public Emaitza () {
		super();
	}
	
	public Emaitza(Kuota k, boolean b) {
		this.ema=b;
		this.K = k;
//		this.Apostuak = k.getApostuk();
	}

	public Integer getEmaitzaN() {
		return emaitzaN;
	}

	public void setEmaitzaN(Integer emaitzaN) {
		this.emaitzaN = emaitzaN;
	}

	public boolean isEma() {
		return ema;
	}

	public void setEma(boolean ema) {
		this.ema = ema;
	}

	public Kuota getK() {
		return K;
	}

	public void setK(Kuota k) {
		K = k;
	}

	public List<Apostu> getApostuak() {
		return K.getApostuk();
	}
}
