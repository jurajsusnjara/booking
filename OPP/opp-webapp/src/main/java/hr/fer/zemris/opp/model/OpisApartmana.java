package hr.fer.zemris.opp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "opisApartmana")
public class OpisApartmana {

	private int opisID;
	private short kat;
	private String pogled;
	private short minBroj;
	private short maxBroj;
	private List<Apartman> apartmani;
	private List<Fotografija> fotografije;
	
	@Id
	@GeneratedValue
	public int getOpisID() {
		return opisID;
	}
	
	public void setOpisID(int opisID) {
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
	
	@OneToMany(mappedBy = "opisApartmana")
	public List<Apartman> getApartmani() {
		return apartmani;
	}
	
	public void setApartmani(List<Apartman> apartmani) {
		this.apartmani = apartmani;
	}

	@OneToMany(mappedBy = "opisApartmana")
	public List<Fotografija> getFotografije() {
		return fotografije;
	}

	public void setFotografije(List<Fotografija> fotografije) {
		this.fotografije = fotografije;
	}
}
