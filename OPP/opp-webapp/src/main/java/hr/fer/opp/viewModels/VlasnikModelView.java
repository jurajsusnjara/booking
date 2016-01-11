package hr.fer.opp.viewModels;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.OpisApartmana;

import java.util.List;

import javax.persistence.EntityManager;

public class VlasnikModelView {

	public static void deleteApartman(int id) {
		EntityManager em = JPAEMProvider.getEntityManager();
		em.createQuery("delete from Apartman where apartmanID=:id")
		.setParameter("id", id)
		.executeUpdate();;
	}
	
	public static void deleteObjekt(int id) {
		EntityManager em = JPAEMProvider.getEntityManager();
		
		em.createQuery("delete from Objekt where objektID=:id ")
		.setParameter("id", id)
		.executeUpdate();
	}
	
	public static void deleteOpisApartmana(int id) {
		EntityManager em = JPAEMProvider.getEntityManager();
		em.createQuery("delete from OpisApartmana where opisID=:id")
		.setParameter("id", id)
		.executeUpdate();
	}
	
	public static void changeObjekt(Objekt objekt,int id) {
		EntityManager em = JPAEMProvider.getEntityManager();
		if (objekt.getFotografija() != null) {
			em.createQuery("update Objekt set nazivObjekt = '" + objekt.getNazivObjekt()
					+ "', fotografija = '" + objekt.getFotografija() + "' where objektID = " + id)
					.executeUpdate();
		} else {
			em.createQuery("update Objekt set nazivObjekt = '" + objekt.getNazivObjekt()
					+ "' where objektID = " + id)
					.executeUpdate();
		}
		
	}
	
	//treba updatati sve varijable apartmana
	public static void changeApartman(Apartman apartman,int id) {
		
	}
	
	//treba updatati sve varijable opisApartmana
	public static void changeOpisApartmana(OpisApartmana opis, int id) {
		
	}
}
