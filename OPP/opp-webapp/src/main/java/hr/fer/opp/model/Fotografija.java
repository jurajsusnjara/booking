package hr.fer.opp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fotografija")
public class Fotografija {

	private Integer fotoID;
	private String fotoDatoteka;
	private OpisApartmana opisApartmana;
	
	@Id
	@GeneratedValue
	public Integer getFotoID() {
		return fotoID;
	}
	
	public void setFotoID(Integer fotoID) {
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
	@JoinColumn(name = "opisID")
	public OpisApartmana getOpisApartmana() {
		return opisApartmana;
	}
	
	public void setOpisApartmana(OpisApartmana opisApartmana) {
		this.opisApartmana = opisApartmana;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fotoDatoteka == null) ? 0 : fotoDatoteka.hashCode());
		result = prime * result + ((fotoID == null) ? 0 : fotoID.hashCode());
		result = prime * result + ((opisApartmana == null) ? 0 : opisApartmana.hashCode());
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
		Fotografija other = (Fotografija) obj;
		if (fotoDatoteka == null) {
			if (other.fotoDatoteka != null)
				return false;
		} else if (!fotoDatoteka.equals(other.fotoDatoteka))
			return false;
		if (fotoID == null) {
			if (other.fotoID != null)
				return false;
		} else if (!fotoID.equals(other.fotoID))
			return false;
		if (opisApartmana == null) {
			if (other.opisApartmana != null)
				return false;
		} else if (!opisApartmana.equals(other.opisApartmana))
			return false;
		return true;
	}
	
	
}
