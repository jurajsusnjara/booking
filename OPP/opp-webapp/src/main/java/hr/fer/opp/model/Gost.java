package hr.fer.opp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "gost")
public class Gost {

	private Integer gostID;
	private short brojGodina;
	private List<Rezervacija> rezervacije;
	
	@Id
	@GeneratedValue
	public Integer getGostID() {
		return gostID;
	}
	
	public void setGostID(Integer gostID) {
		this.gostID = gostID;
	}
	
	@Column
	public short getBrojGodina() {
		return brojGodina;
	}
	
	public void setBrojGodina(short brojGodina) {
		this.brojGodina = brojGodina;
	}
	
	@OneToMany(mappedBy = "gost", cascade = CascadeType.ALL)
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
		result = prime * result + brojGodina;
		result = prime * result + ((gostID == null) ? 0 : gostID.hashCode());
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
		Gost other = (Gost) obj;
		if (brojGodina != other.brojGodina)
			return false;
		if (gostID == null) {
			if (other.gostID != null)
				return false;
		} else if (!gostID.equals(other.gostID))
			return false;
		if (rezervacije == null) {
			if (other.rezervacije != null)
				return false;
		} else if (!rezervacije.equals(other.rezervacije))
			return false;
		return true;
	}
	
	
}
