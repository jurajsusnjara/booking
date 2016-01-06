package hr.fer.opp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "apartman")
public class Apartman {

	private Integer apartmanID;
	private String nazivApartman;
	private Objekt objekt;
	private OpisApartmana opisApartmana;
	private List<Rezervacija> rezervacije;
	
	@Id
	@GeneratedValue
	public Integer getApartmanID() {
		return apartmanID;
	}
	
	public void setApartmanID(Integer apartmanID) {
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
	@JoinColumn(name = "objektID")
	public Objekt getObjekt() {
		return objekt;
	}
	
	public void setObjekt(Objekt objekt) {
		this.objekt = objekt;
	}

	@ManyToOne
	@JoinColumn(name = "opisID")
	public OpisApartmana getOpisApartmana() {
		return opisApartmana;
	}

	public void setOpisApartmana(OpisApartmana opisApartmana) {
		this.opisApartmana = opisApartmana;
	}

	@OneToMany(mappedBy = "apartman", cascade = CascadeType.ALL)
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartmanID == null) ? 0 : apartmanID.hashCode());
		result = prime * result + ((nazivApartman == null) ? 0 : nazivApartman.hashCode());
		result = prime * result + ((objekt == null) ? 0 : objekt.hashCode());
		result = prime * result + ((opisApartmana == null) ? 0 : opisApartmana.hashCode());
		result = prime * result + ((rezervacije == null) ? 0 : rezervacije.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apartman other = (Apartman) obj;
		if (apartmanID == null) {
			if (other.apartmanID != null)
				return false;
		} else if (!apartmanID.equals(other.apartmanID))
			return false;
		if (nazivApartman == null) {
			if (other.nazivApartman != null)
				return false;
		} else if (!nazivApartman.equals(other.nazivApartman))
			return false;
		if (objekt == null) {
			if (other.objekt != null)
				return false;
		} else if (!objekt.equals(other.objekt))
			return false;
		if (opisApartmana == null) {
			if (other.opisApartmana != null)
				return false;
		} else if (!opisApartmana.equals(other.opisApartmana))
			return false;
		if (rezervacije == null) {
			if (other.rezervacije != null)
				return false;
		} else if (!rezervacije.equals(other.rezervacije))
			return false;
		return true;
	}
	
	
}
