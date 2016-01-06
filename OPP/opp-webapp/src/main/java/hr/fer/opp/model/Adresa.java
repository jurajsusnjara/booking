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
@Table(name = "adresa")
public class Adresa {

	private Integer adresaID;
	private String adresa;
	private String grad;
	private String drzava;
	private int postanskiBroj;
	private List<Korisnik> korisnici;
	
	@Id
	@GeneratedValue
	public Integer getAdresaID() {
		return adresaID;
	}
	
	public void setAdresaID(Integer adresaID) {
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
	
	@OneToMany(mappedBy = "adresa", cascade = CascadeType.ALL)
	public List<Korisnik> getKorisnici() {
		return korisnici;
	}
	
	public void setKorisnici(List<Korisnik> korisnici) {
		this.korisnici = korisnici;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresa == null) ? 0 : adresa.hashCode());
		result = prime * result + ((adresaID == null) ? 0 : adresaID.hashCode());
		result = prime * result + ((drzava == null) ? 0 : drzava.hashCode());
		result = prime * result + ((grad == null) ? 0 : grad.hashCode());
		result = prime * result + ((korisnici == null) ? 0 : korisnici.hashCode());
		result = prime * result + postanskiBroj;
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
		Adresa other = (Adresa) obj;
		if (adresa == null) {
			if (other.adresa != null)
				return false;
		} else if (!adresa.equals(other.adresa))
			return false;
		if (adresaID == null) {
			if (other.adresaID != null)
				return false;
		} else if (!adresaID.equals(other.adresaID))
			return false;
		if (drzava == null) {
			if (other.drzava != null)
				return false;
		} else if (!drzava.equals(other.drzava))
			return false;
		if (grad == null) {
			if (other.grad != null)
				return false;
		} else if (!grad.equals(other.grad))
			return false;
		if (korisnici == null) {
			if (other.korisnici != null)
				return false;
		} else if (!korisnici.equals(other.korisnici))
			return false;
		if (postanskiBroj != other.postanskiBroj)
			return false;
		return true;
	}
	
	
}
