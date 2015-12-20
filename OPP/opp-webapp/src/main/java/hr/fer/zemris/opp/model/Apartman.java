package hr.fer.zemris.opp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "apartman")
public class Apartman {

	private int apartmanID;
	private String nazivApartman;
	private Objekt objekt;
	private OpisApartmana opisApartmana;
	private List<Rezervacija> rezervacije;
	
	@Id
	@GeneratedValue
	public int getApartmanID() {
		return apartmanID;
	}
	
	public void setApartmanID(int apartmanID) {
		this.apartmanID = apartmanID;
	}
	
	@Column(nullable = false, length = 45)
	public String getNazivApartman() {
		return nazivApartman;
	}
	
	public void setNazivApartman(String nazivApartman) {
		this.nazivApartman = nazivApartman;
	}
	
	@ManyToOne
	public Objekt getObjekt() {
		return objekt;
	}
	
	public void setObjekt(Objekt objekt) {
		this.objekt = objekt;
	}

	@ManyToOne
	public OpisApartmana getOpisApartmana() {
		return opisApartmana;
	}

	public void setOpisApartmana(OpisApartmana opisApartmana) {
		this.opisApartmana = opisApartmana;
	}

	@OneToMany(mappedBy = "apartman")
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
}
