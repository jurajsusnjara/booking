package hr.fer.opp.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import hr.fer.opp.dao.DAO;
import hr.fer.opp.model.Adresa;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Fotografija;
import hr.fer.opp.model.Gost;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.OpisApartmana;
import hr.fer.opp.model.Rezervacija;

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
				.setParameter("k", korisnik).getResultList();
		
		return rezervacije;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rezervacija> getReservationsFor(Apartman apartman) {
	
		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Rezervacija> rezervacije = null;
		rezervacije = (List<Rezervacija>) em.createQuery(
				"select r from Rezervacija as r where r.apartman=:a")
				.setParameter("a", apartman).getResultList();
		
		return rezervacije;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rezervacija> getReservationsFor(Gost gost) {
		
		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Rezervacija> rezervacije = null;
		rezervacije = (List<Rezervacija>) em.createQuery(
				"select r from Rezervacija as r where r.gost=:g")
				.setParameter("g", gost).getResultList();
		
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
		
		if(korisnik == null) {
			return;
		}
		
		EntityManager em = JPAEMProvider.getEntityManager();
		try {
			em.persist(korisnik);
		} catch(Exception e) {
		}
	}

	@Override
	public void putAdresa(Adresa adresa) {

		if(adresa == null) {
			return;
		}
		
		EntityManager em = JPAEMProvider.getEntityManager();
		try {
			em.persist(adresa);
		} catch(Exception e) {
		}
	}

	@Override
	public void putGost(Gost gost) {

		if(gost == null) {
			return;
		}
		
		EntityManager em = JPAEMProvider.getEntityManager();
		try {
			em.persist(gost);
		} catch(Exception e) {
		}
	}

	@Override
	public void putRezervacija(Rezervacija rezervacija) {

		if(rezervacija == null) {
			return;
		}
		
		EntityManager em = JPAEMProvider.getEntityManager();
		try {
			em.persist(rezervacija);
		} catch(Exception e) {
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Objekt> getAllObjekt() {

		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Objekt> objekti = null;
		objekti = (List<Objekt>) em.createQuery(
				"select o from Objekt o").getResultList();
		
		return objekti;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Apartman> getAllApartman() {

		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Apartman> apartmani = null;
		apartmani = (List<Apartman>) em.createQuery(
				"select a from Apartman a").getResultList();
		
		return apartmani;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OpisApartmana> getAllOpisApartmana() {

		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<OpisApartmana> opisi = null;
		opisi = (List<OpisApartmana>) em.createQuery(
				"select o from OpisApartmana o").getResultList();
		
		return opisi;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fotografija> getAllFotografija() {

		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Fotografija> fotografije = null;
		fotografije = (List<Fotografija>) em.createQuery(
				"select f from Fotografija f").getResultList();
		
		return fotografije;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Korisnik> getAllKorisnik() {

		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Korisnik> korisnici = null;
		korisnici = (List<Korisnik>) em.createQuery(
				"select k from Korisnik k").getResultList();
		
		return korisnici;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Adresa> getAllAdresa() {

		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Adresa> adrese = null;
		adrese = (List<Adresa>) em.createQuery(
				"select a from Adresa a").getResultList();
		
		return adrese;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gost> getAllGost() {

		EntityManager em = JPAEMProvider.getEntityManager();
		
		List<Gost> gosti = null;
		gosti = (List<Gost>) em.createQuery(
				"select g from Gost g").getResultList();
		
		return gosti;
	}
}
