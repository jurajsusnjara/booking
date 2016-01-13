package hr.fer.opp.viewModels;

import hr.fer.opp.dao.jpa.queries.*;
import hr.fer.opp.model.Korisnik;

import java.util.List;


public class CommonViewModel {

	public static List<Korisnik> getAdministrators() {
		SelectQuery sq = new SelectQuery(Korisnik.class.getName());
		sq.addEqualityCondition("uloga", 2);
		sq.execute();
		return sq.getResultList();
	}
}
