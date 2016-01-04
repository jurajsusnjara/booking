package hr.fer.opp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fotografija")
public class Fotografija {

	private int fotoID;
	private String fotoDatoteka;
	private OpisApartmana opisApartmana;
	
	@Id
	@GeneratedValue
	public int getFotoID() {
		return fotoID;
	}
	
	public void setFotoID(int fotoID) {
		this.fotoID = fotoID;
	}
	
	@Column(nullable = false)
	public String getFotoDatoteka() {
		return fotoDatoteka;
	}
	
	public void setFotoDatoteka(String fotoDatoteka) {
		this.fotoDatoteka = fotoDatoteka;
	}
	
	@ManyToOne
	public OpisApartmana getOpisApartmana() {
		return opisApartmana;
	}
	
	public void setOpisApartmana(OpisApartmana opisApartmana) {
		this.opisApartmana = opisApartmana;
	}
}