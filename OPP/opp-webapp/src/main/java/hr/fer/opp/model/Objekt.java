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
@Table(name = "objekt")
public class Objekt {

	private Integer objektID;
	private String nazivObjekt;
	private List<Apartman> apartmani;
	private String fotografija;
	
	@Column
	public String getFotografija() {
		return fotografija;
	}
	
	public void setFotografija(String fotografija) {
		this.fotografija = fotografija;
	}
	
	@Id
	@GeneratedValue
	public Integer getObjektID() {
		return objektID;
	}
	
	public void setObjektID(Integer objektID) {
		this.objektID = objektID;
	}
	
	@Column(nullable = false, length=20)
	public String getNazivObjekt() {
		return nazivObjekt;
	}
	
	public void setNazivObjekt(String nazivObjekt) {
		this.nazivObjekt = nazivObjekt;
	}

	@OneToMany(mappedBy = "objekt", cascade = CascadeType.ALL)
	public List<Apartman> getApartmani() {
		return apartmani;
	}

	public void setApartmani(List<Apartman> apartmani) {
		this.apartmani = apartmani;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartmani == null) ? 0 : apartmani.hashCode());
		result = prime * result + ((nazivObjekt == null) ? 0 : nazivObjekt.hashCode());
		result = prime * result + ((objektID == null) ? 0 : objektID.hashCode());
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
		Objekt other = (Objekt) obj;
		if (apartmani == null) {
			if (other.apartmani != null)
				return false;
		} else if (!apartmani.equals(other.apartmani))
			return false;
		if (nazivObjekt == null) {
			if (other.nazivObjekt != null)
				return false;
		} else if (!nazivObjekt.equals(other.nazivObjekt))
			return false;
		if (objektID == null) {
			if (other.objektID != null)
				return false;
		} else if (!objektID.equals(other.objektID))
			return false;
		return true;
	}
	
	
}
