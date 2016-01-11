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
@Table(name = "opisApartmana")
public class OpisApartmana {

	private Integer opisID;
	private short kat;
	private String pogled;
	private short minBroj;
	private short maxBroj;
	private String naslov;
	private List<Apartman> apartmani;
	private List<Fotografija> fotografije;
	
	@Column
	public String getNaslov() {
		return naslov;
	}
	
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	
	@Id
	@GeneratedValue
	public Integer getOpisID() {
		return opisID;
	}
	
	public void setOpisID(Integer opisID) {
		this.opisID = opisID;
	}
	
	@Column
	public short getKat() {
		return kat;
	}
	
	public void setKat(short kat) {
		this.kat = kat;
	}
	
	@Column(nullable = false)
	public String getPogled() {
		return pogled;
	}
	
	public void setPogled(String pogled) {
		this.pogled = pogled;
	}
	
	@Column
	public short getMinBroj() {
		return minBroj;
	}
	
	public void setMinBroj(short minBroj) {
		this.minBroj = minBroj;
	}
	
	@Column
	public short getMaxBroj() {
		return maxBroj;
	}
	
	public void setMaxBroj(short maxBroj) {
		this.maxBroj = maxBroj;
	}
	
	@OneToMany(mappedBy = "opisApartmana", cascade = CascadeType.ALL)
	public List<Apartman> getApartmani() {
		return apartmani;
	}
	
	public void setApartmani(List<Apartman> apartmani) {
		this.apartmani = apartmani;
	}

	@OneToMany(mappedBy = "opisApartmana", cascade = CascadeType.ALL)
	public List<Fotografija> getFotografije() {
		return fotografije;
	}

	public void setFotografije(List<Fotografija> fotografije) {
		this.fotografije = fotografije;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartmani == null) ? 0 : apartmani.hashCode());
		result = prime * result + ((fotografije == null) ? 0 : fotografije.hashCode());
		result = prime * result + kat;
		result = prime * result + maxBroj;
		result = prime * result + minBroj;
		result = prime * result + ((opisID == null) ? 0 : opisID.hashCode());
		result = prime * result + ((pogled == null) ? 0 : pogled.hashCode());
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
		OpisApartmana other = (OpisApartmana) obj;
		if (apartmani == null) {
			if (other.apartmani != null)
				return false;
		} else if (!apartmani.equals(other.apartmani))
			return false;
		if (fotografije == null) {
			if (other.fotografije != null)
				return false;
		} else if (!fotografije.equals(other.fotografije))
			return false;
		if (kat != other.kat)
			return false;
		if (maxBroj != other.maxBroj)
			return false;
		if (minBroj != other.minBroj)
			return false;
		if (opisID == null) {
			if (other.opisID != null)
				return false;
		} else if (!opisID.equals(other.opisID))
			return false;
		if (pogled == null) {
			if (other.pogled != null)
				return false;
		} else if (!pogled.equals(other.pogled))
			return false;
		return true;
	}
	
	
}
