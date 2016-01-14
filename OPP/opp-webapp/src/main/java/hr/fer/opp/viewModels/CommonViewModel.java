package hr.fer.opp.viewModels;

import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.dao.jpa.JPADAOImpl;
import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Rezervacija;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

public class CommonViewModel {

	@SuppressWarnings("unchecked")
	public static List<Korisnik> getAdministrators() {
		List<Korisnik> korisnici = DAOProvider.getDAO().getAllKorisnik();
		List<Korisnik> administratori = new ArrayList<Korisnik>();
		for (Korisnik k : korisnici)
			if (k.getUloga() == 2)
				administratori.add(k);
		return administratori;
		/*
		 * SelectQuery sq = new SelectQuery(Korisnik.class.getName());
		 * sq.addEqualityCondition("uloga", 2); sq.execute(); return
		 * sq.getResultList();
		 */
	}
}
