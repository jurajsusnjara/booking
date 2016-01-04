package hr.fer.opp.dao;

import java.util.List;

import hr.fer.opp.model.Adresa;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Fotografija;
import hr.fer.opp.model.Gost;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.OpisApartmana;
import hr.fer.opp.model.Rezervacija;

public interface DAO {
	
//	getting from database ****************************************

	Objekt getObjektFor(int id);
	
	Apartman getApartmanFor(int id);
	
	OpisApartmana getOpisApartmanaFor(int id);
	
	Fotografija getFotografijaFor(int id);
	
	Korisnik getKorisnikFor(int id);
	
	Adresa getAdresaFor(int id);
	
	Gost getGostFor(int id);
	
	List<Rezervacija> getReservationsFor(Korisnik korisnik);
	
	List<Rezervacija> getReservationsFor(Apartman apartman);
	
	List<Rezervacija> getReservationsFor(Gost gost);
	
	List<Objekt> getAllObjekt();
	
	List<Apartman> getAllApartman();
	
	List<OpisApartmana> getAllOpisApartmana();
	
	List<Fotografija> getAllFotografija();
	
	List<Korisnik> getAllKorisnik();
	
	List<Adresa> getAllAdresa();
	
	List<Gost> getAllGost();
	
//	writing in database ******************************************
	
	void putObjekt(Objekt objekt);
	
	void putApartman(Apartman apartman);
	
	void putOpisApartmana(OpisApartmana opisApartmana);
	
	void putFotografija(Fotografija fotografija);
	
	void putKorisnik(Korisnik korisnik);
	
	void putAdresa(Adresa adresa);
	
	void putGost(Gost gost);
	
	void putRezervacija(Rezervacija rezervacija);
}
