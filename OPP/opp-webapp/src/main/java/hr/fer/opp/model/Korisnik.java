package hr.fer.opp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "korisnik")
public class Korisnik {

	private String korisnikID;
	private String ime;
	private String prezime;
	private String email;
	private String telefon;
	private Date datumReg;
	private String lozinka;
	private int uloga;
	private Adresa adresa;
	private boolean logiran;
	private List<Rezervacija> rezervacije;
	private boolean logiran;
	
	@Column
	public boolean isLogiran() {
		return logiran;
	}
	
	public void setLogiran(boolean logiran) {
		this.logiran = logiran;
	}
	
	@Id
	public String getKorisnikID() {
		return korisnikID;
	}
	
	public void setKorisnikID(String korisnikID) {
		this.korisnikID = korisnikID;
	}
	
	@Column(nullable = false, length = 20)
	public String getIme() {
		return ime;
	}
	
	public void setIme(String ime) {
		this.ime = ime;
	}
	
	@Column(nullable = false, length = 20)
	public String getPrezime() {
		return prezime;
	}
	
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	
	@Column(nullable = false, length = 30)
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(nullable = false, length = 20)
	public String getTelefon() {
		return telefon;
	}
	
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatumReg() {
		return datumReg;
	}
	
	public void setDatumReg(Date datumRegistracije) {
		this.datumReg = datumRegistracije;
	}
	
	@Column(nullable = false)
	public String getLozinka() {
		return lozinka;
	}
	
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	
	@Column
	public int getUloga() {
		return uloga;
	}
	
	public void setUloga(int uloga) {
		this.uloga = uloga;
	}
	
	@ManyToOne
	@JoinColumn(name = "adresaID")
	public Adresa getAdresa() {
		return adresa;
	}
	
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	
	@OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL)
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}
	
	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	@Column
	public boolean isLogiran() {
		return logiran;
	}

	public void setLogiran(boolean logiran) {
		this.logiran = logiran;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresa == null) ? 0 : adresa.hashCode());
		result = prime * result + ((datumReg == null) ? 0 : datumReg.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
		result = prime * result + ((korisnikID == null) ? 0 : korisnikID.hashCode());
		result = prime * result + ((lozinka == null) ? 0 : lozinka.hashCode());
		result = prime * result + ((prezime == null) ? 0 : prezime.hashCode());
		result = prime * result + ((rezervacije == null) ? 0 : rezervacije.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		result = prime * result + uloga;
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
		Korisnik other = (Korisnik) obj;
		if (adresa == null) {
			if (other.adresa != null)
				return false;
		} else if (!adresa.equals(other.adresa))
			return false;
		if (datumReg == null) {
			if (other.datumReg != null)
				return false;
		} else if (!datumReg.equals(other.datumReg))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (korisnikID == null) {
			if (other.korisnikID != null)
				return false;
		} else if (!korisnikID.equals(other.korisnikID))
			return false;
		if (lozinka == null) {
			if (other.lozinka != null)
				return false;
		} else if (!lozinka.equals(other.lozinka))
			return false;
		if (prezime == null) {
			if (other.prezime != null)
				return false;
		} else if (!prezime.equals(other.prezime))
			return false;
		if (rezervacije == null) {
			if (other.rezervacije != null)
				return false;
		} else if (!rezervacije.equals(other.rezervacije))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		if (uloga != other.uloga)
			return false;
		return true;
	}
	
	
}
