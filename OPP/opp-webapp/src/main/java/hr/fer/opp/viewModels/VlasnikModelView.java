package hr.fer.opp.viewModels;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.OpisApartmana;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class VlasnikModelView {

	public static void deleteApartman(int id) {
		EntityManager em = JPAEMProvider.getEntityManager();
		em.createQuery("delete from Apartman where apartmanID=:id").setParameter("id", id).executeUpdate();
	}

	public static void deleteObjekt(int id) {
		EntityManager em = JPAEMProvider.getEntityManager();

		em.createQuery("delete from Objekt where objektID=:id ").setParameter("id", id).executeUpdate();
	}

	public static void deleteOpisApartmana(int id) {
		EntityManager em = JPAEMProvider.getEntityManager();
		em.createQuery("delete from OpisApartmana where opisID=:id").setParameter("id", id).executeUpdate();
	}

	public static void changeObjekt(Objekt objekt, int id) {
		EntityManager em = JPAEMProvider.getEntityManager();
		if (objekt.getFotografija() != null) {
			em.createQuery("update Objekt set nazivObjekt = '" + objekt.getNazivObjekt() + "', fotografija = '"
					+ objekt.getFotografija() + "' where objektID = " + id).executeUpdate();
		} else {
			em.createQuery("update Objekt set nazivObjekt = '" + objekt.getNazivObjekt() + "' where objektID = " + id)
					.executeUpdate();
		}
	}

	// treba updatati sve varijable apartmana
	public static void changeApartman(Apartman apartman) {
		Query q = JPAEMProvider.getEntityManager().createQuery(
				"update Apartman set nazivApartman = :naziv, objektID = :objektID, opisID = :opisID where objektID = :id");
		q.setParameter("naziv", apartman.getNazivApartman());
		q.setParameter("objektID", apartman.getObjekt().getObjektID());
		q.setParameter("opisID", apartman.getNazivApartman());
		q.setParameter("naziv", apartman.getOpisApartmana().getOpisID());
		q.setParameter("id", apartman.getApartmanID());
		// TODO?: apartman.rezervacije
	}

	// treba updatati sve varijable opisApartmana
	public static void changeOpisApartmana(OpisApartmana opis) {
		Query q = JPAEMProvider.getEntityManager().createQuery(
				"update OpisApartmana set naslov = :naslov, kat = :kat, pogled = :pogled, minBroj = :minBroj, maxBroj = :maxBroj, opis = :opis where objektID = :id");
		q.setParameter("naslov", opis.getNaslov());
		q.setParameter("kat", opis.getKat());
		q.setParameter("pogled", opis.getPogled());
		q.setParameter("minBroj", opis.getMinBroj());
		q.setParameter("maxBroj",  opis.getMaxBroj());
		q.setParameter("opis", opis.getOpis());
		q.setParameter("opisID", opis.getOpisID());
		// TODO?: OpisApartmana.apartmani
		// TODO?: OpisApartmana.fotografije
	}

	// administrator je korinik s ulogom=2
	public static List<Korisnik> getAdministrators() {
		
		return null;
	}
}
