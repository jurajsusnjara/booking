package hr.fer.zemris.opp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Korisnik {

	private String korisnikID;
	private String ime;
	private String prezime;
	private String email;
	private String telefon;
	private Date datumRegistracije;
	private String lozinka;
	private int uloga;
	private Adresa adresa;
	private List<Rezervacija> rezervacije;
	
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
	public Date getDatumRegistracije() {
		return datumRegistracije;
	}
	
	public void setDatumRegistracije(Date datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
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
	public Adresa getAdresa() {
		return adresa;
	}
	
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	
	@OneToMany(mappedBy = "korisnik")
	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}
	
	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
}
