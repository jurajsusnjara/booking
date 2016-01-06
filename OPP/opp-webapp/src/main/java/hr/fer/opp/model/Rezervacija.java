package hr.fer.opp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "rezervacija")
public class Rezervacija implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Apartman apartman;
	private Korisnik korisnik;
	private Gost gost;
	private Date datumRezervacije;
	private Date rezerviranoOd;
	private Date rezerviranoDo;
	private boolean parking;
	private boolean satelitskaTV;
	private boolean internet;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "apartmanID")
	public Apartman getApartman() {
		return apartman;
	}
	
	public void setApartman(Apartman apartman) {
		this.apartman = apartman;
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name = "korisnikID")
	public Korisnik getKorisnik() {
		return korisnik;
	}
	
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	@Id
	@ManyToOne
	@JoinColumn(name = "gostID")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apartman == null) ? 0 : apartman.hashCode());
		result = prime * result + ((datumRezervacije == null) ? 0 : datumRezervacije.hashCode());
		result = prime * result + ((gost == null) ? 0 : gost.hashCode());
		result = prime * result + (internet ? 1231 : 1237);
		result = prime * result + ((korisnik == null) ? 0 : korisnik.hashCode());
		result = prime * result + (parking ? 1231 : 1237);
		result = prime * result + ((rezerviranoDo == null) ? 0 : rezerviranoDo.hashCode());
		result = prime * result + ((rezerviranoOd == null) ? 0 : rezerviranoOd.hashCode());
		result = prime * result + (satelitskaTV ? 1231 : 1237);
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
		Rezervacija other = (Rezervacija) obj;
		if (apartman == null) {
			if (other.apartman != null)
				return false;
		} else if (!apartman.equals(other.apartman))
			return false;
		if (datumRezervacije == null) {
			if (other.datumRezervacije != null)
				return false;
		} else if (!datumRezervacije.equals(other.datumRezervacije))
			return false;
		if (gost == null) {
			if (other.gost != null)
				return false;
		} else if (!gost.equals(other.gost))
			return false;
		if (internet != other.internet)
			return false;
		if (korisnik == null) {
			if (other.korisnik != null)
				return false;
		} else if (!korisnik.equals(other.korisnik))
			return false;
		if (parking != other.parking)
			return false;
		if (rezerviranoDo == null) {
			if (other.rezerviranoDo != null)
				return false;
		} else if (!rezerviranoDo.equals(other.rezerviranoDo))
			return false;
		if (rezerviranoOd == null) {
			if (other.rezerviranoOd != null)
				return false;
		} else if (!rezerviranoOd.equals(other.rezerviranoOd))
			return false;
		if (satelitskaTV != other.satelitskaTV)
			return false;
		return true;
	}
	
	
}
