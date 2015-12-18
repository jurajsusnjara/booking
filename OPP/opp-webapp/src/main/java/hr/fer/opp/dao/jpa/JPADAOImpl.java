package hr.fer.opp.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import hr.fer.opp.dao.DAO;
import hr.fer.zemris.opp.model.Adresa;
import hr.fer.zemris.opp.model.Apartman;
import hr.fer.zemris.opp.model.Fotografija;
import hr.fer.zemris.opp.model.Gost;
import hr.fer.zemris.opp.model.Korisnik;
import hr.fer.zemris.opp.model.Objekt;
import hr.fer.zemris.opp.model.OpisApartmana;
import hr.fer.zemris.opp.model.Rezervacija;

public class JPADAOImpl implements DAO {

	@Override
	public Objekt getObjektFor(int id) {

		EntityManager em = JPAEMProvider.getEntityManager();
		Objekt objekt = em.find(Objekt.class, id);
		
		return objekt;
	}

	@Override
	public Apartman getApartmanFor(int id) {

		EntityManager em = JPAEMProvider.getEntityManager();
		Apartman apartman = em.find(Apartman.class, id);
		
		return apartman;
	}

	@Override
	public OpisApartmana getOpisApartmanaFor(int id) {

		EntityManager em = JPAEMProvider.getEntityManager();
		OpisApartmana opisApartmana = em.find(OpisApartmana.class, id);
		
		return opisApartmana;
	}

	@Override
	public Fotografija getFotografijaFor(int id) {

		EntityManager em = JPAEMProvider.getEntityManager();
		Fotografija fotografija = em.find(Fotografija.class, id);
		
		return fotografija;
	}

	@Override
	public Korisnik getKorisnikFor(int id) {

		EntityManager em = JPAEMProvider.getEntityManager();
		Korisnik korisnik = em.find(Korisnik.class, id);
		
		return korisnik;
	}

	@Override
	public Adresa getAdresaFor(int id) {

		EntityManager em = JPAEMProvider.getEntityManager();
		Adresa adresa = em.find(Adresa.class, id);
		
		return adresa;
	}

	@Override
	public Gost getGostFor(int id) {

		EntityManager em = JPAEMProvider.getEntityManager();
		Gost gost = em.find(Gost.class, id);
		
		return gost;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rezervacija> getReservationsFor(Korisnik korisnik) {

		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Rezervacija> rezervacije = null;
		rezervacije = (List<Rezervacija>) em.createQuery(
				"select r from Rezervacija as r where r.korisnik=:k")
				.setParameter("k", korisnik);
		
		return rezervacije;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rezervacija> getReservationsFor(Apartman apartman) {
	
		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Rezervacija> rezervacije = null;
		rezervacije = (List<Rezervacija>) em.createQuery(
				"select r from Rezervacija as r where r.apartman=:a")
				.setParameter("a", apartman);
		
		return rezervacije;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rezervacija> getReservationsFor(Gost gost) {
		
		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Rezervacija> rezervacije = null;
		rezervacije = (List<Rezervacija>) em.createQuery(
				"select r from Rezervacija as r where r.gost=:g")
				.setParameter("g", gost);
		
		return rezervacije;
	}

	@Override
	public void putObjekt(Objekt objekt) {

		if(objekt == null) {
			return;
		}
		
		EntityManager em = JPAEMProvider.getEntityManager();
		try {
			em.persist(objekt);
		} catch (Exception e) {
		}
	}

	@Override
	public void putApartman(Apartman apartman) {

		if(apartman == null) {
			return;
		}
		
		EntityManager em = JPAEMProvider.getEntityManager();
		try {
			em.persist(apartman);
		} catch (Exception e) {
		}
	}

	@Override
	public void putOpisApartmana(OpisApartmana opisApartmana) {

		if(opisApartmana == null) {
			return;
		}
		
		EntityManager em = JPAEMProvider.getEntityManager();
		try {
			em.persist(opisApartmana);
		} catch (Exception e) {
		}
	}

	@Override
	public void putFotografija(Fotografija fotografija) {

		if(fotografija == null) {
			return;
		}
		
		EntityManager em = JPAEMProvider.getEntityManager();
		try {
			em.persist(fotografija);
		} catch (Exception e) {
		}
	}

	@Override
	public void putKorisnik(Korisnik korisnik) {
		
		
	}

	@Override
	public void putAdresa(Adresa adresa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putGost(Gost gost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void putRezervacija(Rezervacija rezervacija) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Objekt> getAllObjekt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Apartman> getAllApartman() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OpisApartmana> getAllOpisApartmana() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fotografija> getAllFotografija() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Korisnik> getAllKorisnik() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Adresa> getAllAdresa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gost> getAllGost() {
		// TODO Auto-generated method stub
		return null;
	}
}
