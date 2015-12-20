package hr.fer.zemris.opp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "adresa")
public class Adresa {

	private int adresaID;
	private String adresa;
	private String grad;
	private String drzava;
	private int postanskiBroj;
	private List<Korisnik> korisnici;
	
	@Id
	@GeneratedValue
	public int getAdresaID() {
		return adresaID;
	}
	
	public void setAdresaID(int adresaID) {
		this.adresaID = adresaID;
	}
	
	@Column(nullable = false)
	public String getAdresa() {
		return adresa;
	}
	
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	
	@Column(nullable = false)
	public String getGrad() {
		return grad;
	}
	
	public void setGrad(String grad) {
		this.grad = grad;
	}
	
	@Column(nullable = false)
	public String getDrzava() {
		return drzava;
	}
	
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	
	@Column
	public int getPostanskiBroj() {
		return postanskiBroj;
	}
	
	public void setPostanskiBroj(int postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}
	
	@OneToMany(mappedBy = "adresa")
	public List<Korisnik> getKorisnici() {
		return korisnici;
	}
	
	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}
}
