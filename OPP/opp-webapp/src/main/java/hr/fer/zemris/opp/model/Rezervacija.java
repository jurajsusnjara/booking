package hr.fer.zemris.opp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Rezervacija {

	private Apartman apartman;
	private Korisnik korisnik;
	private Gost gost;
	private Date datumRezervacije;
	private Date rezerviranoOd;
	private Date rezerviranoDo;
	private boolean parking;
	private boolean satelitskaTV;
	private boolean internet;
	
	@ManyToOne
	public Apartman getApartman() {
		return apartman;
	}
	
	public void setApartman(Apartman apartman) {
		this.apartman = apartman;
	}
	
	@ManyToOne
	public Korisnik getKorisnik() {
		return korisnik;
	}
	
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	@ManyToOne
	public Gost getGost() {
		return gost;
	}
	
	public void setGost(Gost gost) {
		this.gost = gost;
	}
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatumRezervacije() {
		return datumRezervacije;
	}
	
	public void setDatumRezervacije(Date datumRezervacije) {
		this.datumRezervacije = datumRezervacije;
	}
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRezerviranoOd() {
		return rezerviranoOd;
	}
	
	public void setRezerviranoOd(Date rezerviranoOd) {
		this.rezerviranoOd = rezerviranoOd;
	}
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getRezerviranoDo() {
		return rezerviranoDo;
	}
	
	public void setRezerviranoDo(Date rezerviranoDo) {
		this.rezerviranoDo = rezerviranoDo;
	}
	
	@Column
	public boolean isParking() {
		return parking;
	}
	
	public void setParking(boolean parking) {
		this.parking = parking;
	}
	
	@Column
	public boolean isSatelitskaTV() {
		return satelitskaTV;
	}
	
	public void setSatelitskaTV(boolean satelitskaTV) {
		this.satelitskaTV = satelitskaTV;
	}
	
	@Column
	public boolean isInternet() {
		return internet;
	}
	
	public void setInternet(boolean internet) {
		this.internet = internet;
	}
}
