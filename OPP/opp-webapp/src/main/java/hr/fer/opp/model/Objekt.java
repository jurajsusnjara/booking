package hr.fer.zemris.opp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "objekt")
public class Objekt {

	private int objektID;
	private String nazivObjekt;
	private List<Apartman> apartmani;
	
	@Id
	@GeneratedValue
	public int getObjektID() {
		return objektID;
	}
	
	public void setObjektID(int objektID) {
		this.objektID = objektID;
	}
	
	@Column(nullable = false, length=20)
	public String getNazivObjekt() {
		return nazivObjekt;
	}
	
	public void setNazivObjekt(String nazivObjekt) {
		this.nazivObjekt = nazivObjekt;
	}

	//TODO pazi za apartman klasu
	@OneToMany(mappedBy = "objekt")
	public List<Apartman> getApartmani() {
		return apartmani;
	}

	public void setApartmani(List<Apartman> apartmani) {
		this.apartmani = apartmani;
	}
	
	
}
