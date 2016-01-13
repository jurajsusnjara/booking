package hr.fer.opp.viewModels;

import hr.fer.opp.dao.jpa.JPAEMProvider;
import hr.fer.opp.dao.DAOProvider;
import hr.fer.opp.model.Apartman;
import hr.fer.opp.model.Korisnik;
import hr.fer.opp.model.Objekt;
import hr.fer.opp.model.OpisApartmana;
import hr.fer.opp.viewModels.queries.SelectQuery;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.UniqueConstraint;

public class CommonViewModel {

	public static List<Korisnik> getAdministrators() {
		SelectQuery sq = new SelectQuery(Korisnik.class.getName());
		sq.addCondition("uloga", 2);
		sq.execute();
		return sq.getResultList();
	}
}
