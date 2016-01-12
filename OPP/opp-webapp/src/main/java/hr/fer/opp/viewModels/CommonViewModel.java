package hr.fer.opp.viewModels;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.OpisApartmana;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class CommonViewModel {

	public static List<Korisnik> getAdministrators() {
		List<Korisnik> korisnici = DAOProvider.getDAO().getAllKorisnik();
		List<Korisnik> administratori = new ArrayList<Korisnik>();
		for (Korisnik k : korisnici)
			if (k.getUloga() == 2) // administrator je korisnik s ulogom 2
				administratori.add(k);
		return administratori;
	}
}
