package hr.fer.zemris.opp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "gost")
public class Gost {

	private int gostID;
	private short brojGodina;
	private List<Rezervacija> rezervacije;
	
	@Id
	@GeneratedValue
	public int getGostID() {
		return gostID;
	}
	
	public void setGostID(int gostID) {
		this.gostID = gostID;
	}
	
	@Column
	public short getBrojGodina() {
		return brojGodina;
	}
	
	public void setBrojGodina(short brojGodina) {
		this.brojGodina = brojGodina;
	}
	
	@OneToMany(mappedBy = "gost")
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}
	
	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}	
}
